package com.ia.planda;

import javafx.scene.Node;
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

    RandomAccessFile file = new RandomAccessFile(new File("tasks.txt"), "rw");

    public Cache() throws FileNotFoundException {
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
    public static VBox vbox = new VBox();

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
        updateFile(0);
        vbox.getChildren().setAll(tasksList);
    }

    public static void updateFile(int n) throws IOException {
        //TODO save important Cache info to the text file for persistent storage before closing the app

        if (n == tasksList.size()) { //once all tasks covered
            System.out.println("File updated.");
            System.out.println("Tasks: " + tasksList.size());
            //file.seek(0);
        } else {
            System.out.println(vbox.getChildren().getClass());
            updateFile(n+1);
        }


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
