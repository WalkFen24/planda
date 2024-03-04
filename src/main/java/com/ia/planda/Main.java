package com.ia.planda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main extends Application {

    //RandomAccessFile file = new RandomAccessFile(new File("tasks.txt"), "rw");
    Container container;

    public Main() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("task-list-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Planda Task List");
        stage.setScene(scene);
        stage.show();
        container = new Container();
        container.setUpContainer(); //sets up the container/cache class

        stage.setOnCloseRequest(event -> {
            //save important Container info to the text file for persistent storage before closing the app
            container.updateFile(container);
            System.out.println("Program exited.");
        });
    }

    public static void main(String[] args) {
        launch();
    }
    /*
    public void setUpContainer(Container container) throws IOException {
        //TODO read info from the file to the container/cache
        if (file.getFilePointer() == file.length()) {
            System.out.println("Done parsing file");
        } else {
            System.out.println(file.readLine());

            setUpContainer(container);
        }
    }

    public void updateFile(Container container) {
        //TODO save important Container info to the text file for persistent storage before closing the app
    }


     */



            /*
        RandomAccessFile file = new RandomAccessFile(new File("tasks.txt"), "rw");
        file.seek(0);
        while(file.getFilePointer() != file.length()) {
            System.out.println(file.readLine());
        }

         */
}
