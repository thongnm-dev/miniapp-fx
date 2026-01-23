package dev.thongnm.utils;

import javafx.scene.Parent;
import org.springframework.stereotype.Component;

@Component
public class StageManager {

    private final FxmlLoader fxmlLoader;

    public StageManager(FxmlLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }

    public Parent loadView(final String viewName) {
        try {
            return fxmlLoader.loadFxml(viewName);
        } catch (Exception e) {
            return null;
        }
    }
}