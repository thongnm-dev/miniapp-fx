package dev.thongnm.base;

import dev.thongnm.components.LoadingController;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class BaseController {

    @Getter(AccessLevel.PROTECTED)
    @Setter
    private LoadingController loadingController;
}
