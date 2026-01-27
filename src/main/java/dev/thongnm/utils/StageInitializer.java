package dev.thongnm.utils;

import java.util.Objects;

import org.kordamp.bootstrapfx.BootstrapFX;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.thongnm.AppLauncher;
import dev.thongnm.config.AppConfig;
import dev.thongnm.event.StageReadyEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Component
public class StageInitializer {

    private final AppConfig appConfig;

    private final StageManager manager;

    public StageInitializer(StageManager stageManager, AppConfig appConfig) {
        this.appConfig = appConfig;
        this.manager = stageManager;
    }

    @EventListener
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();

        Parent view = manager.loadView(appConfig.getStartupView());

        Scene scene = new Scene(view, appConfig.getWidth(), appConfig.getHeight());
        stage.setScene(scene);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        scene.getStylesheets().add(Objects.requireNonNull(AppLauncher.class.getResource("/css/app.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(AppLauncher.class.getResource("/css/bootstrap-button.css")).toExternalForm());

        scene.getRoot().setStyle("-fx-font-family: 'Segoe UI', 'Arial', sans-serif; -fx-font-size: 14px;");
        stage.setTitle(appConfig.getName());
        stage.show();
    }
}
