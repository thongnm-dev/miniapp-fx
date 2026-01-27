package dev.thongnm.controller;

import dev.thongnm.base.BaseController;
import dev.thongnm.components.LoadingF;
import org.springframework.stereotype.Controller;

@Controller
public class GitController extends BaseController {

    public GitController(LoadingF loading) {
        super(loading);
    }
}
