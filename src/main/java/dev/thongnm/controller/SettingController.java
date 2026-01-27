package dev.thongnm.controller;

import dev.thongnm.base.BaseController;
import dev.thongnm.components.LoadingF;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class SettingController extends BaseController {
    public SettingController(LoadingF loading) {
        super(loading);
    }
}
