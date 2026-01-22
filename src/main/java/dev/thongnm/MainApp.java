package dev.thongnm;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;

@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        Application.launch(AppLauncher.class, args);
    }
}
