package com.ia.planda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    private File file = new File("tasks.txt");
    private Scanner scan = new Scanner(file);

    private static Cache cache;

    static {
        try {
            cache = new Cache();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Main() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("task-list-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Planda");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            //save important Cache info to the text file for persistent storage before closing the app
            try {
                cache.updateFile();

                System.out.println("~~~Text File Content On Close~~~");
                cache.printTasksFile();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Program exited.");
        });
    }

    public static void main(String[] args) throws IOException {
        System.out.println("~~~Text File Content Initially~~~");
        cache.printTasksFile();

        launch();
    }

}
