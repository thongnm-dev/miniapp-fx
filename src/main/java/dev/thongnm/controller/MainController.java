package dev.thongnm.controller;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.paint.Paint;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.javafx.Icon;
import org.springframework.stereotype.Component;

import dev.thongnm.service.AppService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

@Component
public class MainController implements Initializable {

    private static final String IP_API = "https://api.ipify.org?format=json";

    @FXML
    private VBox sideBar;

    @FXML
    private TabPane mainArea;

    @FXML
    private BorderPane sideBarGroup;

    @FXML
    private FontIcon chevronIcon;

    @FXML
    private Label ipAddress;

    @FXML
    private Label clockLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Button toggle;

    private List<Button> lstmenu = new ArrayList<>();

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Map<String, Object> _btnToggle = new HashMap<>();
        _btnToggle.put("expanded", true);
        toggle.setUserData(_btnToggle);
        startClock();

        ObservableList<Map<String, Object>> list = AppService.loadMenu();

        for (Map<String, Object> res : list) {

            Button button = new Button();
            button.getStyleClass().add("btn-transparent");
            button.setUserData(res);
            button.setOnAction(this::loadView);

            FontIcon icon = new FontIcon();
            icon.setIconLiteral((String) res.get("icon"));
            icon.setIconSize((int) res.get("size"));
            icon.setIconColor(Paint.valueOf("blue"));
            Label iconLabel = new Label();
            iconLabel.setGraphic(icon);

            Label textLabel = new Label((String) res.get("name"));
            HBox box = new HBox(10, iconLabel, textLabel);
            box.setAlignment(Pos.CENTER_LEFT);

            button.setGraphic(box);
            button.getProperties().put("textLabel", textLabel);

            lstmenu.add(button);
            sideBar.getChildren().add(button);
        }

        Optional<Tab> _tabItem = mainArea.getTabs().stream().filter(item -> Objects.equals("view/dashboard.fxml", item.getId())).findFirst();

        if (_tabItem.isEmpty()) {
            try {
                addView(list.getFirst());
            } catch (Exception e) {

            }
        }

        Task<String> fetchIpTask = new Task<>() {
            @Override
            protected String call() throws Exception {
                return fetchIPAddress();
            }
        };

        new Thread(fetchIpTask).start();

        ipAddress.textProperty().bind(fetchIpTask.valueProperty());

        fetchIpTask.setOnFailed(e -> {
            ipAddress.setText("Unknown");
        });
    }

    @FXML
    private void collapse(ActionEvent event) {

        Button button = (Button) event.getSource();
        @SuppressWarnings("unchecked")
        Map<String, Object> _btnToggle = (Map<String, Object>) button.getUserData();

        final boolean isCollapse = (boolean) _btnToggle.get("expanded");

        for (Button itemMenu : lstmenu) {
            Label label = (Label) itemMenu.getProperties().get("textLabel");
            label.setVisible(!isCollapse);
            label.setManaged(!isCollapse);
        }

        sideBarGroup.setPrefWidth(!isCollapse ? 200 : 60);
        chevronIcon.setIconLiteral(!isCollapse ? "mdi-chevron-double-left" : "mdi-chevron-double-right");
        _btnToggle.put("expanded", !isCollapse);
    }

    private void startClock() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime now = LocalDateTime.now();
            clockLabel.setText(now.format(timeFormatter));
            dateLabel.setText(now.format(dateFormatter));
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private String fetchIPAddress() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(IP_API))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // response: {"ip":"203.xxx.xxx.xxx"}
            return parseIpFromJson(response.body());

        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown";
        }
    }

    private String parseIpFromJson(String json) {
        return json.replaceAll(".*\"ip\"\\s*:\\s*\"([^\"]+)\".*", "$1");
    }

    private void loadView(ActionEvent event) {
        Button button = (Button) event.getSource();

        @SuppressWarnings("unchecked")
        final Map<String, Object> UserData = (Map<String, Object>) button.getUserData();
        final String fxmlFileName = (String) UserData.get("id");
        Optional<Tab> _tabItem = mainArea.getTabs().stream().filter(item -> Objects.equals(fxmlFileName, item.getId())).findFirst();

        if (_tabItem.isEmpty()) {
            addView(UserData);
        } else {
            mainArea.getSelectionModel().select(_tabItem.get());
        }
    }

    private void addView(final Map<String, Object> wMenuData) {
        try {
            final String fxmlFileName = (String) wMenuData.get("id");
            final String screenName = (String) wMenuData.get("name");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + fxmlFileName));
            javafx.scene.Node view = loader.load();

            final Tab childTab = new Tab();
            childTab.setText(screenName);
            childTab.setId(fxmlFileName);
            childTab.getStyleClass().add("content-area");
            childTab.setClosable((boolean) wMenuData.get("closable"));

            final StackPane StackPane = new StackPane();
            StackPane.setAlignment(Pos.CENTER);
            StackPane.getStyleClass().add("main-container");
            StackPane.getChildren().add(view);

            childTab.setContent(StackPane);
            mainArea.getTabs().add(mainArea.getTabs().size(), childTab);
            mainArea.getSelectionModel().select(childTab);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
