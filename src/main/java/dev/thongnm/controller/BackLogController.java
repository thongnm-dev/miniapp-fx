package dev.thongnm.controller;

import dev.thongnm.base.BaseController;
import dev.thongnm.components.LoadingF;
import org.springframework.stereotype.Controller;

@Controller
public class BackLogController extends BaseController {

    public BackLogController(LoadingF loading) {
        super(loading);
    }
}
