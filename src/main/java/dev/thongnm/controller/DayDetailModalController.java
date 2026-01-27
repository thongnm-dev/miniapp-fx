package dev.thongnm.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class DayDetailModalController {

    @FXML
    private Label dayLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private VBox tasksList;

    @FXML
    private VBox tasksContainer;

    private Stage stage;
    private LocalDate selectedDate;

    private static final Locale LOCALE = Locale.of("vi", "VN");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd 'Tháng' M, yyyy", LOCALE);

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setDate(LocalDate date) {
        this.selectedDate = date;
        updateDateDisplay();
        loadTasks();
    }

    private void updateDateDisplay() {
        // Get day of week in Vietnamese
        String dayOfWeek = getDayOfWeekVietnamese(selectedDate);
        dayLabel.setText(dayOfWeek);
        dateLabel.setText(selectedDate.format(DATE_FORMATTER));
    }

    private String getDayOfWeekVietnamese(LocalDate date) {
        int dayValue = date.getDayOfWeek().getValue();
        switch (dayValue) {
            case 1:
                return "Thứ Hai";
            case 2:
                return "Thứ Ba";
            case 3:
                return "Thứ Tư";
            case 4:
                return "Thứ Năm";
            case 5:
                return "Thứ Sáu";
            case 6:
                return "Thứ Bảy";
            case 7:
                return "Chủ Nhật";
            default:
                return "";
        }
    }

    private void loadTasks() {
        // Clear existing tasks (except the sample ones in FXML)
        // In a real application, you would load tasks from a database or service

        // For now, we'll keep the sample tasks from FXML
        // You can replace this with actual data loading logic

        // Example: Clear and add dynamic tasks
        // tasksList.getChildren().clear();
        // addTaskItem("09:00 - 10:30", "Họp team về dự án mới",
        // "Thảo luận về kế hoạch triển khai dự án trong quý 1", "Quan trọng",
        // "#dc3545");
    }

    private void addTaskItem(String time, String title, String description, String priority, String priorityColor) {
        VBox taskItem = new VBox(8.0);
        taskItem.setStyle("-fx-background-color: #f8f9fa; -fx-background-radius: 8; -fx-padding: 15; " +
                "-fx-border-color: #dee2e6; -fx-border-radius: 8; -fx-border-width: 1;");

        // Time and priority row
        HBox timeRow = new HBox(10.0);
        timeRow.setAlignment(Pos.CENTER_LEFT);

        FontIcon clockIcon = new FontIcon("mdi-clock");
        clockIcon.setIconSize(16);
        clockIcon.setIconColor(javafx.scene.paint.Color.web("#6c757d"));

        Label timeLabel = new Label(time);
        timeLabel.setStyle("-fx-text-fill: #6c757d; -fx-font-size: 13px;");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        Label priorityLabel = new Label(priority);
        priorityLabel.setStyle("-fx-background-color: " + priorityColor + "; -fx-text-fill: white; " +
                "-fx-padding: 3 8; -fx-background-radius: 3; -fx-font-size: 11px;");

        timeRow.getChildren().addAll(clockIcon, timeLabel, spacer, priorityLabel);

        // Title
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        // Description
        Label descLabel = new Label(description);
        descLabel.setWrapText(true);
        descLabel.setStyle("-fx-text-fill: #495057; -fx-font-size: 13px;");

        // Action buttons
        HBox buttonRow = new HBox(5.0);

        Button editBtn = new Button("Sửa");
        editBtn.setStyle("-fx-background-color: #ffc107; -fx-text-fill: white; -fx-background-radius: 4; " +
                "-fx-padding: 5 12; -fx-font-size: 12px; -fx-cursor: hand;");

        Button deleteBtn = new Button("Xóa");
        deleteBtn.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-background-radius: 4; " +
                "-fx-padding: 5 12; -fx-font-size: 12px; -fx-cursor: hand;");

        buttonRow.getChildren().addAll(editBtn, deleteBtn);

        taskItem.getChildren().addAll(timeRow, titleLabel, descLabel, buttonRow);
        tasksList.getChildren().add(taskItem);
    }

    @FXML
    private void onAddTask() {
        // TODO: Open a dialog to add a new task
        System.out.println("Add task for date: " + selectedDate);

        // For demonstration, add a sample task
        addTaskItem("16:00 - 17:00", "Công việc mới",
                "Mô tả công việc được thêm vào", "Bình thường", "#17a2b8");
    }

    @FXML
    private void onClose() {
        if (stage != null) {
            stage.close();
        }
    }
}
