package com.ia.planda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("task-list-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Planda Task List");
        stage.setScene(scene);
        stage.show();
    }
//TODO plan: make the button bar thing its own class so that I don't have to go through and edit each section over and over again
    //TODO and cuz they share the same abilities so it's just way better (encapsulation, less bad redundancy, smoother updating/debugging/whatever)
    public static void main(String[] args) {
        launch();
    }

}
