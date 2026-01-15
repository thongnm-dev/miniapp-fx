module dev.thongnm {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    requires org.kordamp.bootstrapfx.core;

    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    // add icon pack modules
    requires org.kordamp.ikonli.fontawesome6;

    opens dev.thongnm to javafx.fxml;

    exports dev.thongnm;
    exports dev.thongnm.controller;
    opens dev.thongnm.controller to javafx.fxml;
}
