package dev.thongnm;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import dev.thongnm.config.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppLauncher extends Application {

    private ConfigurableApplicationContext applicationContext;

    private StageManager stageManager;

    @Override
    public void init() throws Exception {
        applicationContext = new SpringApplicationBuilder(MainApp.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Register the stage as a bean in the Spring context
        applicationContext.getBeanFactory().registerSingleton("primaryStage", primaryStage);

        stageManager = applicationContext.getBean(StageManager.class);

        stageManager.setPrimaryStage(primaryStage);
        stageManager.switchScene("main");
        primaryStage.setTitle(stageManager.getApplicationTitle());
    }
}
