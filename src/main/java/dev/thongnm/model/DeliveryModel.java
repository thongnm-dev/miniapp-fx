package dev.thongnm.model;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DeliveryModel {

    private final StringProperty files = new SimpleStringProperty("");

    public StringProperty filesProperty() {
        return files;
    }

    public List<String> getFiles() {
        return Arrays.stream(StringUtils.split(files.get(), System.lineSeparator())).toList();
    }
}
