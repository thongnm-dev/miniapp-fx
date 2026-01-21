package dev.thongnm.base;

import org.springframework.beans.factory.annotation.Qualifier;

import dev.thongnm.components.LoadingF;
import lombok.AccessLevel;
import lombok.Getter;

public class BaseController {

    @Getter(AccessLevel.PROTECTED)
    @Qualifier("loadingF")
    private LoadingF loading;
}
