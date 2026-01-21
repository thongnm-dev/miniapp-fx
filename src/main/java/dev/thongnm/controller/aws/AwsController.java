package dev.thongnm.controller.aws;

import dev.thongnm.base.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

@Component
public class AwsController extends BaseController implements Initializable {

    @FXML
    private StackPane mainArea;

    @FXML
    private SplitPane splitPane;

    @FXML
    private Button btnRefresh;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnRefresh.getStyleClass().addAll(Arrays.asList("btn-transparent", "btn-circle", "btn-outline"));

        splitPane.prefWidthProperty().bind(mainArea.widthProperty());
        splitPane.prefHeightProperty().bind(mainArea.heightProperty());
    }
}
