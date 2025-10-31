package com.controller;

import com.model.AppModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    private TextField txtSrcPath;

    @FXML
    private TextField txtDesPath;

    @FXML
    private TextArea txtFiles;

    private final AppModel model;

    public AppController() {
        this.model = new AppModel();
    }

    private void moveFile(final String source, final String des) {

        final List<String> files = model.getFiles();

        try {
            for (String file : files) {
                String targetFile = StringUtils.substring(file, StringUtils.indexOf(file, "DEV_SHARE"), StringUtils.length(file));
                final Path sourcePath = Paths.get(source, targetFile);
                final Path destinationPath = Paths.get(des, targetFile);

                if (Files.notExists(sourcePath)) {
                    if (Files.exists(destinationPath)) {
                        Files.delete(destinationPath);
                    }
                } else if (Files.exists(sourcePath)) {
                    if (Files.exists(destinationPath)) {
                        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        Files.createDirectories(destinationPath.getParent());
                        Files.copy(sourcePath, destinationPath, StandardCopyOption.COPY_ATTRIBUTES);

                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void sourceToDes() {
        final String source = model.getSrcPath();
        final String destination = model.getDesPath();

        moveFile(source, destination);
    }

    @FXML
    private void desToSource() {
        final String source = model.getSrcPath();
        final String destination = model.getDesPath();

        moveFile(destination, source);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtSrcPath.textProperty().bindBidirectional(model.srcProperty());

        txtDesPath.textProperty().bindBidirectional(model.desProperty());

        txtFiles.textProperty().bindBidirectional(model.filesProperty());

        Platform.runLater(() -> txtFiles.requestFocus());
    }
}
