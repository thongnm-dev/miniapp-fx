package dev.thongnm.config;

import javafx.scene.Node;
import org.springframework.stereotype.Component;

@Component
public class StageManager implements IStageManager {

    private final FxmlLoader fxmlLoader;

    private final String applicationTitle;

    public StageManager(FxmlLoader fxmlLoader
    ,String applicationTitle) {
        this.fxmlLoader = fxmlLoader;
        this.applicationTitle = applicationTitle;
    }

    @Override
    public String getApplicationTitle() {
        return applicationTitle;
    }

    @Override
    public Node loadView(final String viewName) {
        return null;
    }
}
