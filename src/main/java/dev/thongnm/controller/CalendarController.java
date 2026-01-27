package dev.thongnm.controller;

import dev.thongnm.base.BaseController;
import dev.thongnm.components.LoadingF;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

@Controller
public class CalendarController extends BaseController {

    @FXML
    private Button btnToggle;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnPrev;

    @FXML
    private Button btnToday;

    @FXML
    private Button btnNext;

    @FXML
    private GridPane calendarGrid;

    private LocalDate currentDate;

    private static final Locale LOCALE = Locale.of("vi", "VN");
    private static final DateTimeFormatter MONTH_YEAR_FORMATTER = DateTimeFormatter.ofPattern("'Tháng' M, yyyy", LOCALE);

    public CalendarController(LoadingF loading) {
        super(loading);
    }

    @FXML
    public void initialize() {

        btnToggle.getStyleClass().addAll(Arrays.asList("btn-transparent", "btn-circle", "btn-outline"));

        btnRefresh.getStyleClass().addAll(Arrays.asList("btn-transparent", "btn-circle", "btn-outline"));

        btnPrev.getStyleClass().addAll(Arrays.asList("btn-transparent", "btn-outline"));

        btnToday.getStyleClass().addAll(Arrays.asList("btn-transparent", "btn-outline"));

        btnNext.getStyleClass().addAll(Arrays.asList("btn-transparent", "btn-circle", "btn-outline"));

        currentDate = LocalDate.now();
        updateCalendar();
    }

    private void updateCalendar() {
        // Clear existing calendar
        calendarGrid.getChildren().clear();

        renderMonthView();
    }

    private void renderMonthView() {
        YearMonth yearMonth = YearMonth.from(currentDate);
        LocalDate firstOfMonth = yearMonth.atDay(1);

        // Get the day of week for the first day (1 = Monday, 7 = Sunday)
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        // Adjust so Sunday = 0, Monday = 1, etc.
        int startCol = dayOfWeek % 7;

        LocalDate date = firstOfMonth;
        int row = 0;
        int col = startCol;
        int maxRows = 0;

        while (date.getMonth() == firstOfMonth.getMonth()) {
            Button dayButton = createDayButton(date);
            calendarGrid.add(dayButton, col, row);

            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
            date = date.plusDays(1);
        }
        maxRows = row + 1;

        // Setup responsive grid constraints for month view
        setupGridConstraints(7, maxRows);
    }

    private void setupGridConstraints(int cols, int rows) {
        // Clear existing constraints
        calendarGrid.getColumnConstraints().clear();
        calendarGrid.getRowConstraints().clear();

        // Add column constraints - each column takes equal space
        for (int i = 0; i < cols; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.ALWAYS);
            colConstraints.setFillWidth(true);
            colConstraints.setPercentWidth(100.0 / cols);
            calendarGrid.getColumnConstraints().add(colConstraints);
        }

        // Add row constraints - each row takes equal space
        for (int i = 0; i < rows; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.ALWAYS);
            rowConstraints.setFillHeight(true);
            rowConstraints.setPercentHeight(100.0 / rows);
            calendarGrid.getRowConstraints().add(rowConstraints);
        }
    }

    private Button createDayButton(LocalDate date) {
        Button button = new Button();

        // Create content for the button
        VBox content = new VBox(5);
        content.setAlignment(Pos.TOP_CENTER);

        Label dayNumber = new Label(String.valueOf(date.getDayOfMonth()));
        dayNumber.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        content.getChildren().add(dayNumber);

        // Add task indicator (sample - you can replace with actual data)
        if (date.getDayOfMonth() % 3 == 0) {
            Label taskIndicator = new Label("• " + (date.getDayOfMonth() % 5 + 1) + " việc");
            taskIndicator.setStyle("-fx-font-size: 11px; -fx-text-fill: #007bff;");
            content.getChildren().add(taskIndicator);
        }

        button.setGraphic(content);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        GridPane.setFillWidth(button, true);
        GridPane.setFillHeight(button, true);

        // Styling
        StringBuilder styleBuilder = new StringBuilder(
                "-fx-background-color: white; -fx-border-color: #dee2e6; -fx-border-width: 1; " +
                        "-fx-padding: 10; -fx-cursor: hand; -fx-alignment: TOP_CENTER;");

        // Highlight today
        if (date.equals(LocalDate.now())) {
            styleBuilder.append(" -fx-background-color: #e7f3ff; -fx-border-color: #007bff; -fx-border-width: 2;");
            dayNumber.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #007bff;");
        }

        // Dim dates from other months (for month view)
        if (date.getMonth() != currentDate.getMonth()) {
            styleBuilder.append(" -fx-opacity: 0.4;");
        }

        final String baseStyle = styleBuilder.toString();

        button.setStyle(baseStyle);

        // Hover effect
        button.setOnMouseEntered(e -> {
            if (!date.equals(LocalDate.now())) {
                button.setStyle(baseStyle + " -fx-background-color: #f8f9fa;");
            }
        });

        button.setOnMouseExited(e -> {
            button.setStyle(baseStyle);
        });

        // Click handler
        final LocalDate clickedDate = date;
        button.setOnAction(e -> openDayDetail(clickedDate));

        return button;
    }

    private void openDayDetail(LocalDate date) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modal/DayDetailModal.fxml"));
            Parent root = loader.load();

            DayDetailModalController controller = loader.getController();
            controller.setDate(date);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));

            // Set the stage reference in the controller so it can close itself
            controller.setStage(stage);

            stage.showAndWait();

            // Refresh calendar after closing modal (in case tasks were added/modified)
            updateCalendar();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
