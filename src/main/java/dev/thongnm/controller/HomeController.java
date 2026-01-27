package dev.thongnm.controller;

import dev.thongnm.base.BaseController;
import dev.thongnm.components.LoadingF;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends BaseController {
    public HomeController(LoadingF loading) {
        super(loading);
    }
}
