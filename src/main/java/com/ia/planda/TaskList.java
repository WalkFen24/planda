package com.ia.planda;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList extends ArrayList<ArrayList<Node>> {
    private static ArrayList<ArrayList<Node>> taskList = new ArrayList<>();
    private static VBox vboxTaskList;

    public TaskList(VBox vboxTaskList) {
        TaskList.vboxTaskList = vboxTaskList;
        setTaskList();
    }



    /* DEBUGGING: I was using the same vboxTaskList field in almost every method, so I decided to make it just a necessary
    // parameter for the class.
    public ArrayList<Node> taskElementsArr(VBox vboxTaskList, int task) {
        AnchorPane ap = (AnchorPane) vboxTaskList.getChildren().get(task);
        TitledPane tp = (TitledPane) ap.getChildren().get(0);
        AnchorPane ap2 = (AnchorPane) tp.getContent();
        VBox vboxTask = (VBox) ap2.getChildren().get(0);
        FlowPane fp = (FlowPane) vboxTask.getChildren().get(0);
        ArrayList<Node> arr = new ArrayList<>(fp.getChildren());
        return arr;
    }
     */

    public ArrayList<Node> taskElementsArr(int task) {
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

    public void setTaskList() {
        taskList.clear();
        //***Debugging: I was having issues before because I forgot to clear taskList, and the below getter only
        //ADDS to the existing list, it doesn't clear it on its own to completely reset it, so it was just constantly
        //appending onto itself, almost duplicating, instead of starting fresh every time.
        taskList = getTaskList(0);
    }

    public ArrayList<ArrayList<Node>> getTaskList(int task) {
        //task = column, field = rows
        if (task == vboxTaskList.getChildren().size()) {
            System.out.println("TaskList created.");
            return taskList;
        } else {
            //taskList.add(taskElementsArr(vboxTaskList, task));
            taskList.add(taskElementsArr(task));
            return getTaskList(task + 1);
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

    public Class getElementClass(int n) {
        return taskList.get(0).get(n).getClass();
    }


    //IMPORTANT GETTERS
    //TODO there's probably a better (improved extensibility) way to do this than having to use a print method to figure out what the indexes of
    //TODO each element are
    public String getTaskNameText(int task) {
        return ((TextField)(taskList.get(task).get(0))).getText();

    }
    public LocalDate getDate(int task) {
        return ((DatePicker)(taskList.get(task).get(1))).getValue();
    }
    public String getReqTime(int task) {
        return ((TextField)(taskList.get(task).get(3))).getText();
    }

    public String getGoalTime(int task) {
        return ((TextField)(taskList.get(task).get(5))).getText();
    }
    public String getImportance(int task) {
        return ((TextField)(taskList.get(task).get(7))).getText();
    }
    public String getTaskDetailsText(int task) {
        return ((TextArea)(taskList.get(task).get(8))).getText();
    }


    public void printElementIndexes() {
        //to save me the trouble of needing to count and carefully look at the FXML file everytime I change what's in a TaskPane
        System.out.println("---Indexes of ID's in TaskPane vbox---");
        for (int i = 0; i < taskList.get(0).size(); i++) {
            System.out.println("index: " + i + " *** element id: " + taskList.get(0).get(i).getId());
        }
    }

    public void setTaskNameText(int task, String text) {
        ((TextField)(taskList.get(task).get(0))).setText(text);
    }
    public void setDate(int task, String dateStr) {
        Scanner scan = new Scanner(dateStr);
        scan.useDelimiter("-");
        LocalDate ld = LocalDate.of(scan.nextInt(), scan.nextInt(), scan.nextInt());
        ((DatePicker)(taskList.get(task).get(1))).setValue(ld);
    }

    /*
    public void setReqTime(int task) {
        ((TextField)(taskList.get(task).get(3))).getText();
    }

    public void setGoalTime(int task) {
        ((TextField)(taskList.get(task).get(5))).getText();
    }
    public void setImportance(int task) {
        ((TextField)(taskList.get(task).get(7))).getText();
    }
    public void setTaskDetailsText(int task) {
        ((TextArea)(taskList.get(task).get(8))).getText();
    }

     */
}
