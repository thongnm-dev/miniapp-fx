package dev.thongnm.base;

import dev.thongnm.components.LoadingF;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Getter(AccessLevel.PROTECTED)
    private LoadingF loading;

    public BaseController(LoadingF loading) {
        this.loading = loading;
    }
}
