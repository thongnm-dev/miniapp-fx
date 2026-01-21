package dev.thongnm.config;

import java.util.Objects;

import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.stereotype.Component;

import dev.thongnm.AppLauncher;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
public class StageManager {

    private static final String applicationTitle = "Mini App FX";

    private Stage primaryStage;

    private final FxmlLoader fxmlLoader;

    public StageManager(FxmlLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public String getApplicationTitle() {
        return applicationTitle;
    }

    public Node loadView(final String viewName) {
        return null;
    }

    public void switchScene(final String viewName) {

        try {
            Parent view = fxmlLoader.loadFxml("/view/" + viewName + ".fxml");
            
            if (primaryStage.getScene() == null) {
                Scene scene = new Scene(view, 1200, 800);
                primaryStage.setScene(scene);
                scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
                scene.getStylesheets().add(Objects.requireNonNull(AppLauncher.class.getResource("/css/app.css")).toExternalForm());
                scene.getStylesheets().add(Objects.requireNonNull(AppLauncher.class.getResource("/css/bootstrap-button.css")).toExternalForm());

                scene.getRoot().setStyle(
                        "-fx-font-family: 'Segoe UI', 'Arial', sans-serif; -fx-font-size: 14px;"
                );
            } else {
                primaryStage.getScene().setRoot(view);
                primaryStage.sizeToScene();
            }

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}