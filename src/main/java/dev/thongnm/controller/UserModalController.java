package dev.thongnm.controller;

import javafx.fxml.FXML;

public class UserModalController {

    private Runnable onClose;

    public void setOnClose(Runnable onClose) {
        this.onClose = onClose;
    }

    @FXML
    private void onCancel() {
        onClose.run();
    }

    @FXML
    private void onSave() {
        // validate / save
        onClose.run();


    }
}
