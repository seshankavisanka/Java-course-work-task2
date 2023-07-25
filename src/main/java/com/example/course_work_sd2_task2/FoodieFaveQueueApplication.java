package com.example.course_work_sd2_task2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FoodieFaveQueueApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FoodieFaveQueueApplication.class.getResource("foodie-fave-queue-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Foodie Fave Queue Management System");
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            Platform.exit();
//            System.exit(0);
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
