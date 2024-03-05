package com.ia.planda;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.List;

public class Cache {
    //holds data to be used and transferred between classes, for short-term storage
    private static int initFocusTime;
    private static List<? extends Node> tasksList;
    private static VBox vbox;

    RandomAccessFile file = new RandomAccessFile(new File("tasks.txt"), "rw");

    public Cache() throws FileNotFoundException {
    }

    public void setVbox(VBox vbox) {
        Cache.vbox = vbox;
    }
    public VBox getVbox() {
        return vbox;
    }

    public int getInitFocusTime() {
        return initFocusTime;
    }

    public void setInitFocusTime(int initFocusTime) {
        Cache.initFocusTime = initFocusTime;
    }

    public void setTasksList(List<? extends Node> tasksList) {
        Cache.tasksList = tasksList;
    }
    public List<? extends Node> getTasksList() {
        return Cache.tasksList;
    }

    public void setUpContainer() throws IOException {
        //read info from the file to the cache
        if (file.getFilePointer() == file.length()) {
            System.out.println("Done parsing file");
            file.seek(0);
        } else {
            System.out.println(file.readLine()); //TODO replace with code to read the lines into the proper storage locations
            //TODO so it assigns the first line as the name field of the first TaskPane stored in the vbox's collection
            setUpContainer();
        }
    }

    public void updateFile() throws IOException {
        //TODO save important Cache info to the text file for persistent storage before closing the app
        //vbox.getChildren().setAll(tasksList);
        updateFile(0);
    }

    public static void updateFile(int n) throws IOException {
        //TODO save important Cache info to the text file for persistent storage before closing the app

        System.out.println();
        //AnchorPane pane = (AnchorPane)vbox.getChildren().get(0);
        //TitledPane tp = (TitledPane)pane.getChildren().get(0);
        //System.out.println(tp.getChildrenUnmodifiable().get(0));

        /*
        if (n == tasksList.size()) { //once all tasks covered
            System.out.println("File updated.");
            System.out.println("Tasks: " + tasksList.size());
            //file.seek(0);
        } else {
            AnchorPane pane = (AnchorPane)vbox.getChildren().get(0);
            //TitledPane tp = (TitledPane)pane.getChildren().get(0);
            //System.out.println(tp.getChildrenUnmodifiable().get(0));
            updateFile(n+1);
        }

         */


        //file.writeUTF(String.valueOf(tasksList.get(0)));
        /*if() {
            System.out.println("File updated.");
            file.seek(0);
        } else {
            file.writeUTF(String.valueOf(tasksList.get(0)));
            updateFile();
        }

         */
    }
}
