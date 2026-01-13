package dev.thongnm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML
    private VBox sideBar;

    @FXML
    private ToggleButton menuToggleBtn;

    @FXML
    private StackPane contentArea;

    @FXML
    public void initialize() {
        // Initial setup if needed
    }

    @FXML
    private void onMenuToggle() {
        // Toggle visibility and management of the sidebar
        boolean isVisible = sideBar.isVisible();
        sideBar.setVisible(!isVisible);
        sideBar.setManaged(!isVisible);
    }

    @FXML
    private void onDashboard() {
        System.out.println("Dashboard clicked");
        // Logic to load Dashboard view
        // For now, we don't have a dashboard.fxml, so maybe just clear content or do
        // nothing
    }

    @FXML
    private void onSettings() {
        System.out.println("Settings clicked");
        // Logic to load Settings view
    }

    @FXML
    private LoadingController loadingController;

    @FXML
    private void onDelivery() {
        loadView("delivery.fxml");
    }

    private void loadView(String fxmlFileName) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/" + fxmlFileName));
            javafx.scene.Node view = loader.load();

            Object controller = loader.getController();
            if (controller instanceof DeliveryController) {
                ((DeliveryController) controller).setLoadingController(this.loadingController);
            }

            contentArea.getChildren().setAll(view);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
