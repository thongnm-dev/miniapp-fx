module app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires org.apache.commons.lang3;

    opens com.thongnm to javafx.fxml;
    opens com.controller to javafx.fxml;
    opens com.model to javafx.fxml;

    exports com.thongnm;
    exports com.controller;
    exports com.model;
}