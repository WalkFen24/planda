package com.ia.planda;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.Collection;

public class Container {
    //holds data to be used and transferred between classes, for short-term storage
    private static int initFocusTime;
    private static Collection<? extends Node> tasksList;

    public Container() {
    }

    public int getInitFocusTime() {
        return initFocusTime;
    }

    public void setInitFocusTime(int initFocusTime) {
        Container.initFocusTime = initFocusTime;
    }

    public boolean setTasksList(Collection<? extends Node> tasksList) {
        try {
            Container.tasksList = tasksList;
            System.out.println("successful");
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Collection<? extends Node> getTasksList() {
        return Container.tasksList;
    }

}
