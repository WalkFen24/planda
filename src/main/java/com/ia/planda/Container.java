package com.ia.planda;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;

public class Container {
    //holds data to be used and transferred between classes, for short-term storage
    private static int initFocusTime;
    private static Collection<? extends Node> tasksList;

    RandomAccessFile file = new RandomAccessFile(new File("tasks.txt"), "rw");

    public Container() throws FileNotFoundException {
    }

    public int getInitFocusTime() {
        return initFocusTime;
    }

    public void setInitFocusTime(int initFocusTime) {
        Container.initFocusTime = initFocusTime;
    }

    public void setTasksList(Collection<? extends Node> tasksList) {
        Container.tasksList = tasksList;
    }
    public Collection<? extends Node> getTasksList() {
        return Container.tasksList;
    }

    public void setUpContainer() throws IOException {
        //TODO read info from the file to the container/cache
        if (file.getFilePointer() == file.length()) {
            System.out.println("Done parsing file");
        } else {
            System.out.println(file.readLine()); //TODO replace with code to read the lines into the proper storage locations
            //TODO so it assigns the first line as the name field of the first TaskPane stored in the vbox's collection

            setUpContainer();
        }
    }

    public void updateFile(Container container) {
        //TODO save important Container info to the text file for persistent storage before closing the app
    }
}
