package dev.thongnm.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class MainController {

    private String styleClassSidebar = "mdi-chevron-double-left";

    private boolean collapsed = false;

    @FXML
    private BorderPane sideBarGroup;

    @FXML
    private VBox sideBar;

    @FXML
    private StackPane contentArea;

    @FXML
    private FontIcon chevronIcon;

    @FXML
    public void initialize() {

    }

    @FXML
    private void onMenuToggle() {
        // Toggle visibility and management of the sidebar
        boolean isVisible = sideBar.isVisible();
        sideBar.setVisible(!isVisible);
        sideBar.setManaged(!isVisible);
    }

    @FXML
    private LoadingController loadingController;

    @FXML
    private void onDelivery() {
        loadView("delivery.fxml");
    }

    @FXML
    private void onCalendar() {
        loadView("calendar.fxml");
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
