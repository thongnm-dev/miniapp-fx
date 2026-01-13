module dev.thongnm {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires javafx.base;

    opens dev.thongnm to javafx.fxml;

    exports dev.thongnm;
    exports dev.thongnm.controller;
    opens dev.thongnm.controller to javafx.fxml;
}
