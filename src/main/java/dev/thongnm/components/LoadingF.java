package dev.thongnm.components;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Component;

@Component
public class LoadingF {
    @FXML
    private StackPane overlay;

    @FXML
    private ProgressIndicator progressBar;

    @FXML
    private Label messageLabel;

    public void bind(Task<?> task) {
        overlay.visibleProperty().bind(task.runningProperty());
        progressBar.progressProperty().bind(task.progressProperty());
        messageLabel.textProperty().bind(task.messageProperty());
    }

    public void unbind() {
        overlay.visibleProperty().unbind();
        progressBar.progressProperty().unbind();
        messageLabel.textProperty().unbind();
    }
}
