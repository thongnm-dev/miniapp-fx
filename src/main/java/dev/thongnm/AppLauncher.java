package dev.thongnm;

import dev.thongnm.event.StageReadyEvent;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class AppLauncher extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        context = new SpringApplicationBuilder(MainApp.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        context.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() throws Exception {
        context.close();
        Platform.exit();
    }
}
