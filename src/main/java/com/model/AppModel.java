package com.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class AppModel {

    private final StringProperty src_path = new SimpleStringProperty("D:\\Thongnm\\ESS_DELIVERY\\ess_shin_moela");

    private final StringProperty des_path = new SimpleStringProperty("D:\\Thongnm\\ESS_SHIN_MOELA\\ess_shin_moela");

    private final StringProperty files = new SimpleStringProperty("");

    public StringProperty srcProperty() {
        return src_path;
    }

    public StringProperty desProperty() {
        return des_path;
    }

    public StringProperty filesProperty() {
        return files;
    }

    public String getSrcPath() {
        return src_path.get();
    }

    public String getDesPath() {
        return des_path.get();
    }


    public List<String> getFiles() {
        return Arrays.stream(StringUtils.split(files.get(), System.lineSeparator())).toList();
    }
}
