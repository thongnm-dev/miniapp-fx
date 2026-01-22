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
    requires org.kordamp.ikonli.fontawesome6;
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
    exports dev.thongnm.controller;
    exports dev.thongnm.controller.auth;
    exports dev.thongnm.controller.aws;
    exports dev.thongnm.config;
    exports dev.thongnm.utils;
    exports dev.thongnm.event;

    opens dev.thongnm.components to javafx.fxml, spring.core;
    opens dev.thongnm.config to spring.core;

    opens dev.thongnm.controller to javafx.fxml, spring.core;
    opens dev.thongnm.controller.auth to javafx.fxml, spring.core;
    opens dev.thongnm.controller.aws to javafx.fxml, spring.core;
}
