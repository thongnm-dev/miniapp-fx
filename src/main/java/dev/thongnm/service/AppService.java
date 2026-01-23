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
        Dashboard.put("id", "/view/dashboard.fxml");
        Dashboard.put("name", "dashboard");
        Dashboard.put("icon", "mdi-home-variant");
        Dashboard.put("size", 32);
        Dashboard.put("color", "");
        Dashboard.put("closable", false);
        result.add(Dashboard);

        Map<String, Object> calendar = new HashMap<>();
        calendar.put("id", "/view/calendar.fxml");
        calendar.put("name", "calendar");
        calendar.put("icon", "mdi-calendar-today");
        calendar.put("size", 32);
        calendar.put("color", "");
        calendar.put("closable", true);
        result.add(calendar);

        Map<String, Object> delivery = new HashMap<>();
        delivery.put("id", "/view/delivery.fxml");
        delivery.put("name", "delivery");
        delivery.put("icon", "mdi-truck-delivery");
        delivery.put("size", 32);
        delivery.put("color", "");
        delivery.put("closable", true);
        result.add(delivery);

        Map<String, Object> aws = new HashMap<>();
        aws.put("id", "/view/aws.fxml");
        aws.put("name", "aws");
        aws.put("icon", "mdi-amazon");
        aws.put("size", 32);
        aws.put("color", "");
        aws.put("closable", true);
        result.add(aws);

        Map<String, Object> backlog = new HashMap<>();
        backlog.put("id", "/view/backlog.fxml");
        backlog.put("name", "backlog");
        backlog.put("icon", "mdi-beats");
        backlog.put("size", 32);
        backlog.put("color", "");
        backlog.put("closable", true);
        result.add(backlog);

        Map<String, Object> git = new HashMap<>();
        git.put("id", "/view/git.fxml");
        git.put("name", "git");
        git.put("icon", "mdi-git");
        git.put("size", 32);
        git.put("color", "");
        git.put("closable", true);
        result.add(git);

        Map<String, Object> folder = new HashMap<>();
        folder.put("id", "/view/explore.fxml");
        folder.put("name", "explore");
        folder.put("icon", "mdi-folder");
        folder.put("size", 32);
        folder.put("color", "");
        folder.put("closable", true);
        result.add(folder);

        Map<String, Object> setting = new HashMap<>();
        setting.put("id", "/view/setting.fxml");
        setting.put("name", "setting");
        setting.put("icon", "mdi-settings");
        setting.put("size", 32);
        setting.put("color", "");
        setting.put("closable", true);
        result.add(setting);

        return FXCollections.observableList(result);
    }
}
