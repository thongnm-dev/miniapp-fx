package dev.thongnm.base;

import dev.thongnm.components.LoadingF;
import lombok.AccessLevel;
import lombok.Getter;

public class BaseController {

    @Getter(AccessLevel.PROTECTED)
    private final LoadingF loading;

    public BaseController(LoadingF loading) {
        this.loading = loading;
    }
}
