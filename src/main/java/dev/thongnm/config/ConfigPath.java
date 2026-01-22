package dev.thongnm.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigPath {
    private String appName = "Help tools";

    private int width = 1000;

    private int height = 700;

    private boolean allowMaximumSize = true;

    private boolean allowMinimumSize = true;

    private String startup = "main";
}
