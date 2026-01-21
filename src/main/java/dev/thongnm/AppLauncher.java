package dev.thongnm;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import org.kordamp.bootstrapfx.BootstrapFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class AppLauncher extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        applicationContext = new SpringApplicationBuilder(MainApp.class).run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(loadFXML("main"), 850, 500);

        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        scene.getStylesheets().add(Objects.requireNonNull(AppLauncher.class.getResource("/css/app.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(AppLauncher.class.getResource("/css/bootstrap-button.css")).toExternalForm());

        scene.getRoot().setStyle(
                "-fx-font-family: 'Segoe UI', 'Arial', sans-serif; -fx-font-size: 14px;"
        );
        stage.setTitle("Công cụ hỗ trợ");
        stage.setMinWidth(850);
        stage.setMinHeight(500);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppLauncher.class.getResource("/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
