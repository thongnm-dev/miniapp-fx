package dev.thongnm.controller.aws;

import dev.thongnm.base.BaseController;
import dev.thongnm.components.LoadingF;
import dev.thongnm.config.S3Config;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

@Controller
public class AwsController extends BaseController {

    // **************************************************************
    // Variable
    // **************************************************************
    private final S3Config s3Config;

    // **************************************************************
    //  Controls
    // **************************************************************
    @FXML
    private StackPane mainArea;

    @FXML
    private SplitPane splitPane;

    @FXML
    private Button btnRefresh;

    public AwsController(S3Config s3Config, LoadingF loading) {
        super(loading);
        this.s3Config = s3Config;
    }

    @FXML
    public void initialize() {

        btnRefresh.getStyleClass().addAll(Arrays.asList("btn-transparent", "btn-circle", "btn-outline"));

        splitPane.prefWidthProperty().bind(mainArea.widthProperty());
        splitPane.prefHeightProperty().bind(mainArea.heightProperty());
    }
}
