package dev.thongnm.controller.aws;

import dev.thongnm.base.BaseController;
import dev.thongnm.components.LoadingF;
import dev.thongnm.model.AwsItem;
import dev.thongnm.service.S3Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AwsController extends BaseController {

    // **************************************************************
    // Variable
    // **************************************************************
    private final S3Service s3Service;
    // **************************************************************
    //  Controls
    // **************************************************************
    @FXML
    private StackPane mainArea;

    @FXML
    private SplitPane splitPane;

    @FXML
    private Button btnRefresh;

    @FXML
    private HBox breadcrumbs;

    @FXML
    private VBox tableContainer;

    public AwsController(LoadingF loading, S3Service s3Service) {
        super(loading);

        this.s3Service = s3Service;
    }

    @FXML
    public void initialize() {

        btnRefresh.getStyleClass().addAll(Arrays.asList("btn-transparent", "btn-circle", "btn-outline"));

        btnRefresh.setOnAction((event) -> {
            Task<?> task = loadBucket();

            this.getLoading().bind(task);

            task.setOnFailed((e) -> getLoading().unbind());
            task.setOnSucceeded((e) -> getLoading().unbind());

            new Thread(task).start();
        });
        splitPane.prefWidthProperty().bind(mainArea.widthProperty());
        splitPane.prefHeightProperty().bind(mainArea.heightProperty());
    }

    private Task<Void> loadBucket() {

        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Map<String, Object> params = new LinkedHashMap<>();
                params.put("url", "");
                final List<AwsItem> itemList = s3Service.load(params);

                Thread.sleep(1500);
                return null;
            }
        };
    }
}
