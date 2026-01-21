package dev.thongnm.config;

import javafx.scene.Node;

public interface IStageManager {

    public String getApplicationTitle();

    public Node loadView(final String viewName);
}
