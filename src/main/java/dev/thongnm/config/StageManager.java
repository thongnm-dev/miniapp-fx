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

    private final FxmlLoader fxmlLoader;

    public StageManager(FxmlLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public Parent loadView(final String viewName) {
        try {
            return fxmlLoader.loadFxml("/view/" + viewName + ".fxml");
        } catch (Exception e) {
            return null;
        }
    }
}