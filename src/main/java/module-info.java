module dev.thongnm {
    requires javafx.base;
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    requires org.kordamp.bootstrapfx.core;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    // add icon pack modules
    requires org.kordamp.ikonli.materialdesign;
    requires static lombok;
    requires java.net.http;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.boot;
    requires spring.beans;

    opens dev.thongnm to javafx.fxml, spring.core;

    exports dev.thongnm;
    exports dev.thongnm.components;
    exports dev.thongnm.config;
    exports dev.thongnm.controller;
    exports dev.thongnm.controller.auth;
    exports dev.thongnm.controller.aws;
    exports dev.thongnm.event;
    exports dev.thongnm.service;
    exports dev.thongnm.utils;

    opens dev.thongnm.components to javafx.fxml, spring.core;
    opens dev.thongnm.config to javafx.fxml, spring.core;

    opens dev.thongnm.controller to javafx.fxml, spring.core;
    opens dev.thongnm.controller.auth to javafx.fxml, spring.core;
    opens dev.thongnm.controller.aws to javafx.fxml, spring.core;
    opens dev.thongnm.utils to javafx.fxml, spring.core;
}
