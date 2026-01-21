package dev.thongnm.controller;

import dev.thongnm.base.BaseController;
import dev.thongnm.model.DeliveryModel;
import dev.thongnm.utils.BranchType;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;

public class DeliveryController extends BaseController implements Initializable  {

    private static final Map<BranchType, Map<String, String>> CONFIG_LOCAL = new
            LinkedHashMap<>() {{
                put(BranchType.BUG, Collections.singletonMap("path", "D:\\Thongnm\\ESS_DELIVERY\\ess_shin_moela"));
                put(BranchType.CHANGE_REQUEST, Collections.singletonMap("path", "D:\\Thongnm\\ESS_CHANGE_REQUEST\\ess_shin_moela"));
                put(BranchType.ST_DEV, Collections.singletonMap("path", "D:\\Thongnm\\ESS_SHIN_MOELA_ST_DEV"));
            }};

    private static final String PATH_STORE_CODE_COMMIT = "D:\\Thongnm\\ESS_SHIN_MOELA\\ess_shin_moela";

    @FXML
    private RadioButton rbBug;

    @FXML
    private RadioButton rbChange;

    @FXML
    private RadioButton rbStDev;

    // #
    @FXML
    private RadioButton rbStDevAws;

    @FXML
    private RadioButton rbCtDev;

    @FXML
    private RadioButton rbChangeRequest;

    @FXML
    private RadioButton rbOther;

    private final ToggleGroup branch = new ToggleGroup();

    private final ToggleGroup branchCodeCommit = new ToggleGroup();

    @FXML
    private TextField txtSrcPath;

    @FXML
    private TextField txtDesPath;

    @FXML
    private TextArea txtFiles;

    private final DeliveryModel model;

    public DeliveryController() {
        this.model = new DeliveryModel();
    }

    // TODO: Implement moveFileTask if needed
    // private <T> Task<T> moveFileTask(Map<String, Object> params) {
    //     final List<String> files = model.getFiles();
    //     final String source = (String) params.get("source");
    //     final String des = (String) params.get("des");
    //     return new Task<T>() {
    //         @Override
    //         protected T call() throws Exception {
    //             updateProgress(-1, -1);
    //             Thread.sleep(1500);
    //             for (String file : files) {
    //                 int indexOfDevShare = file.contains("DEV_SHARE") ? file.indexOf("DEV_SHARE") : 0;
    //                 String targetFile = file.substring(indexOfDevShare);
    //                 final Path sourcePath = Paths.get(source, targetFile);
    //                 final Path destinationPath = Paths.get(des, targetFile);
    //
    //                 if (Files.notExists(sourcePath)) {
    //                     if (Files.exists(destinationPath)) {
    //                         Files.delete(destinationPath);
    //                     }
    //                 } else if (Files.exists(sourcePath)) {
    //                     if (Files.exists(destinationPath)) {
    //                         Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
    //                     } else {
    //                         Files.createDirectories(destinationPath.getParent());
    //                         Files.copy(sourcePath, destinationPath, StandardCopyOption.COPY_ATTRIBUTES);
    //                     }
    //                 }
    //             }
    //             return null;
    //         }
    //     };
    // }

    @FXML
    private void sourceToDes() {
        final Map<String, Object> params = new LinkedHashMap<>();
        params.put("source", txtSrcPath.getText());
        params.put("des", txtDesPath.getText());

        // Task<?> task = moveFileTask(params);
        // getLoadingController().bind(task);

        // task.setOnFailed((e) -> getLoadingController().unbind());

        // task.setOnSucceeded((e) -> getLoadingController().unbind());

        // new Thread(task).start();

    }

    @FXML
    private void desToSource() {
        final Map<String, Object> params = new LinkedHashMap<>();
        params.put("source", txtDesPath.getText());
        params.put("des", txtSrcPath.getText());

        // Task<?> task = moveFileTask(params);
        // getLoadingController().bind(task);

        // task.setOnFailed((e) -> getLoadingController().unbind());

        // task.setOnSucceeded((e) -> getLoadingController().unbind());

        // new Thread(task).start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rbBug.setUserData(BranchType.BUG);
        rbChange.setUserData(BranchType.CHANGE_REQUEST);
        rbStDev.setUserData(BranchType.ST_DEV);

        rbBug.setToggleGroup(branch);
        rbChange.setToggleGroup(branch);
        rbStDev.setToggleGroup(branch);

        rbStDev.setSelected(true);

        rbCtDev.setUserData(BranchType.BUG);
        rbChangeRequest.setUserData(BranchType.CHANGE_REQUEST);
        rbStDevAws.setUserData(BranchType.ST_DEV);

        rbCtDev.setToggleGroup(branchCodeCommit);
        rbChangeRequest.setToggleGroup(branchCodeCommit);
        rbOther.setToggleGroup(branchCodeCommit);
        rbStDevAws.setToggleGroup(branchCodeCommit);
        rbStDevAws.setSelected(true);

        txtSrcPath.textProperty().bindBidirectional(new SimpleStringProperty(CONFIG_LOCAL.get(BranchType.ST_DEV).get("path")));

        branch.selectedToggleProperty().addListener((obs, old, selected) -> {
            if (selected != null) {
                BranchType type = (BranchType) selected.getUserData();
                String currentSrcBinding = switch (type) {
                    case BUG -> CONFIG_LOCAL.get(BranchType.BUG).get("path");
                    case CHANGE_REQUEST -> CONFIG_LOCAL.get(BranchType.CHANGE_REQUEST).get("path");
                    case ST_DEV -> CONFIG_LOCAL.get(BranchType.ST_DEV).get("path");
                };

                txtSrcPath.textProperty().bindBidirectional(new SimpleStringProperty(currentSrcBinding));
            }
        });

        txtDesPath.disableProperty().bind(rbOther.selectedProperty().not());

        txtDesPath.textProperty().bindBidirectional(new SimpleStringProperty(PATH_STORE_CODE_COMMIT));
        rbOther.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            final String path = isSelected ? "" : PATH_STORE_CODE_COMMIT;

            txtDesPath.textProperty().bindBidirectional(new SimpleStringProperty(path));
        });

        txtFiles.textProperty().bindBidirectional(model.filesProperty());

        Platform.runLater(() -> txtFiles.requestFocus());
    }

    public void clear() {
        txtFiles.clear();
    }

}
