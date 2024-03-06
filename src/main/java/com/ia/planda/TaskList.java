package com.ia.planda;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TaskList extends ArrayList<ArrayList<Node>> {
    private static ArrayList<ArrayList<Node>> taskList = new ArrayList<>();
    private static VBox outerVbox;

    public TaskList(VBox vboxTaskList) {
        outerVbox = vboxTaskList;
        setTaskList(vboxTaskList);
    }

    public ArrayList<Node> taskElementsArr(VBox vboxTaskList, int task) {
        AnchorPane ap = (AnchorPane) vboxTaskList.getChildren().get(task);
        TitledPane tp = (TitledPane) ap.getChildren().get(0);
        AnchorPane ap2 = (AnchorPane) tp.getContent();
        VBox vboxTask = (VBox) ap2.getChildren().get(0);
        FlowPane fp = (FlowPane) vboxTask.getChildren().get(0);
        ArrayList<Node> arr = new ArrayList<>(fp.getChildren());
        return arr;
    }

    public ArrayList<ArrayList<Node>> getTaskList() {
        return taskList;
    }

    public void setTaskList(VBox originalVbox) {
        taskList.clear();
        //***Debugging: I was having issues before because I forgot to clear taskList, and the below getter only
        //ADDS to the existing list, it doesn't clear it on its own to completely reset it, so it was just constantly
        //appending onto itself, almost duplicating, instead of starting fresh every time.
        taskList = getTaskList(originalVbox, 0);
    }

    public ArrayList<ArrayList<Node>> getTaskList(VBox vboxTaskList, int task) {
        //task = column, field = rows
        if (task == vboxTaskList.getChildren().size()) {
            System.out.println("TaskList created.");
            return taskList;
        } else {
            taskList.add(taskElementsArr(vboxTaskList, task));
            return getTaskList(vboxTaskList, task + 1);
        }
    }

    public void printTaskNames() {
        System.out.println(taskList.size() + " tasks total:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("Task " + (i+1) + ": " + ((TextField)(taskList.get(i).get(0))).getText());
        }
    }




    /*TextField taskNameText;
     DatePicker datePicker;
     TextArea taskDetailsText;
     ButtonBar taskButtonBar;
     Button deleteButton;
     Button Complete;
     TitledPane taskPane;
     AnchorPane taskAnchor;
     AnchorPane titledAnchor;
     VBox taskVbox;
     */
}
