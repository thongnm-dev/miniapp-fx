package dev.thongnm.base;

import dev.thongnm.components.LoadingController;

public class BaseController {

    private LoadingController loadingController = null;

    public void setLoadingController(LoadingController loadingController) {
        this.loadingController = loadingController;
    }

    public LoadingController getLoadingController() {
        return this.loadingController;
    }
}
