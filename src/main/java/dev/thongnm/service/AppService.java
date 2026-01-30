package dev.thongnm.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppService {

    public static ObservableList<Map<String, Object>> loadMenu() {
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> Dashboard = new HashMap<>();
        Dashboard.put("id", "dashboard.fxml");
        Dashboard.put("name", "dashboard");
        Dashboard.put("icon", "mdi-home-variant");
        Dashboard.put("size", 32);
        Dashboard.put("color", "");
        Dashboard.put("closable", false);
        result.add(Dashboard);

        Map<String, Object> delivery = new HashMap<>();
        delivery.put("id", "delivery.fxml");
        delivery.put("name", "delivery");
        delivery.put("icon", "mdi-truck-delivery");
        delivery.put("size", 32);
        delivery.put("color", "");
        delivery.put("closable", true);
        result.add(delivery);

        return FXCollections.observableList(result);
    }
}
