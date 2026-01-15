package dev.thongnm;

import java.io.IOException;
import java.util.Objects;

import org.kordamp.bootstrapfx.BootstrapFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("main"), 850, 500);

        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        scene.getStylesheets().add(Objects.requireNonNull(AppLauncher.class.getResource("/css/bootstrap-button.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(AppLauncher.class.getResource("/css/app.css")).toExternalForm());

        scene.getRoot().setStyle(
                "-fx-font-family: 'Segoe UI', 'Arial', sans-serif; -fx-font-size: 14px;"
        );
        stage.setTitle("Công cụ hỗ trợ");
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppLauncher.class.getResource("/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
