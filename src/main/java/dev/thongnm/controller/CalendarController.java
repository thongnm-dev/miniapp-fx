package dev.thongnm.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class CalendarController {

    @FXML
    private Label periodLabel;

    @FXML
    private GridPane dayHeadersGrid;

    @FXML
    private GridPane calendarGrid;

    @FXML
    private ToggleButton weekViewBtn;

    @FXML
    private ToggleButton monthViewBtn;

    @FXML
    private VBox calendarContainer;

    private LocalDate currentDate;
    private boolean isMonthView = true;
    private static final Locale LOCALE = Locale.of("vi", "VN");
    private static final DateTimeFormatter MONTH_YEAR_FORMATTER = DateTimeFormatter.ofPattern("'Tháng' M, yyyy",
            LOCALE);

    @FXML
    public void initialize() {
        currentDate = LocalDate.now();
        updateCalendar();
    }

    @FXML
    private void onWeekView() {
        isMonthView = false;
        updateCalendar();
    }

    @FXML
    private void onMonthView() {
        isMonthView = true;
        updateCalendar();
    }

    @FXML
    private void onPreviousPeriod() {
        if (isMonthView) {
            currentDate = currentDate.minusMonths(1);
        } else {
            currentDate = currentDate.minusWeeks(1);
        }
        updateCalendar();
    }

    @FXML
    private void onNextPeriod() {
        if (isMonthView) {
            currentDate = currentDate.plusMonths(1);
        } else {
            currentDate = currentDate.plusWeeks(1);
        }
        updateCalendar();
    }

    @FXML
    private void onToday() {
        currentDate = LocalDate.now();
        updateCalendar();
    }

    private void updateCalendar() {
        // Update period label
        if (isMonthView) {
            periodLabel.setText(currentDate.format(MONTH_YEAR_FORMATTER));
        } else {
            WeekFields weekFields = WeekFields.of(LOCALE);
            int weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
            periodLabel.setText("Tuần " + weekNumber + ", " + currentDate.getYear());
        }

        // Clear existing calendar
        dayHeadersGrid.getChildren().clear();
        calendarGrid.getChildren().clear();

        // Add day headers
        String[] dayNames = { "CN", "T2", "T3", "T4", "T5", "T6", "T7" };
        for (int i = 0; i < 7; i++) {
            Label dayHeader = new Label(dayNames[i]);
            dayHeader.setStyle(
                    "-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #495057; -fx-alignment: center;");
            dayHeader.setMaxWidth(Double.MAX_VALUE);
            dayHeader.setAlignment(Pos.CENTER);
            dayHeader.setPrefHeight(40);
            GridPane.setFillWidth(dayHeader, true);
            dayHeadersGrid.add(dayHeader, i, 0);
        }

        if (isMonthView) {
            renderMonthView();
        } else {
            renderWeekView();
        }
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

    private void renderWeekView() {
        // Get the start of the week (Sunday)
        LocalDate startOfWeek = currentDate.with(DayOfWeek.SUNDAY);

        for (int i = 0; i < 7; i++) {
            LocalDate date = startOfWeek.plusDays(i);
            Button dayButton = createDayButton(date);
            calendarGrid.add(dayButton, i, 0);
        }

        // Setup responsive grid constraints for week view
        setupGridConstraints(7, 1);
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
        if (isMonthView && date.getMonth() != currentDate.getMonth()) {
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
