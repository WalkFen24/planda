package com.ia.planda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    //RandomAccessFile file = new RandomAccessFile(new File("tasks.txt"), "rw");
    Cache cache;

    public Main() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("task-list-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Planda Task List");
        stage.setScene(scene);
        stage.show();
        cache = new Cache();
        cache.setUpContainer(); //sets up the cache/cache class

        /*
        stage.setOnCloseRequest(event -> {
            //save important Cache info to the text file for persistent storage before closing the app
            try {
                cache.updateFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Program exited.");
        });

         */
    }

    public static void main(String[] args) {
        launch();
    }
    /*
    public void setUpContainer(Cache cache) throws IOException {
        //TODO read info from the file to the cache/cache
        if (file.getFilePointer() == file.length()) {
            System.out.println("Done parsing file");
        } else {
            System.out.println(file.readLine());

            setUpContainer(cache);
        }
    }

    public void updateFile(Cache cache) {
        //TODO save important Cache info to the text file for persistent storage before closing the app
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
