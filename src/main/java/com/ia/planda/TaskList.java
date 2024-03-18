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
import java.util.Arrays;
import java.util.Scanner;

public class TaskList extends ArrayList<ArrayList<Node>> {
    private static ArrayList<ArrayList<Node>> taskList = new ArrayList<>();
    private static VBox vboxTaskList;

    /*
    public static ArrayList<TaskList> taskListArr = new ArrayList<>();
    //stores the list of the list of each task's subtasks
    //rows = task
    //columns = subtask

     */


    public TaskList(VBox vboxTaskList) {
        TaskList.vboxTaskList = vboxTaskList;
        setTaskList();
    }

    /*
    public void sortTaskList(String sortType) {
        if (sortType.equalsIgnoreCase("Due date")) {
            mergeSort(getDueDateArr(), taskList.size());
        } else if (sortType.equalsIgnoreCase("Importance")) {
            int[] array = getImportanceArr();
            System.out.println(Arrays.toString(array));
            mergeSort(getImportanceArr(), taskList.size());
        } else {
            System.out.println("No valid type selected.");
        }
    }

     */



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
        ScrollPane sp = (ScrollPane) vboxTask.getChildren().get(0);
        AnchorPane ap3 = (AnchorPane) sp.getContent();
        FlowPane fp = (FlowPane) ap3.getChildren().get(0);
        ArrayList<Node> arr = new ArrayList<>(fp.getChildren());
        return arr;
    }

    public void setTaskList() {
        taskList.clear();
        taskList = getTaskList(0);
    }

    public ArrayList<ArrayList<Node>> getTaskList(int task) {
        //task = column, field = rows
        if (task == vboxTaskList.getChildren().size()) {
            return taskList;
        } else {
            taskList.add(taskElementsArr(task));
            return getTaskList(task + 1);
        }
    }

    /*
    public int[] getDueDateArr() {
        int[] dateDifs = new int[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) { //for each task
            if (getDate(i) != null) {
                LocalDate ld = LocalDate.now();
                dateDifs[i] = (getDate(i).getDayOfYear() - ld.getDayOfYear());
            } else {
                dateDifs[i] = Integer.MAX_VALUE; //a task with no due date inputted will go at the end of the list.
            }
        }
        return dateDifs;
    }

    public int[] getImportanceArr() {
        int[] importanceArr = new int[taskList.size()];
        int val;
        for (int i = 0; i < taskList.size(); i++) { //for each task
            try {
                val = Integer.parseInt(getImportance(i));
                if (val > 0 && val < 11) {
                    importanceArr[i] = val;
                } else {
                    importanceArr[i] = 1; //a task with no valid importance inputted will go at the end of the list.
                }
            } catch (NumberFormatException e) {
                System.out.println("Importance was not an integer.");
                importanceArr[i] = 1; //a task with no valid importance inputted will go at the end of the list.
            }
        }
        return importanceArr;
    }

    public void mergeSort(int[] arr, int length) {
        //length inputted as a parameter to prevent needing to do arr.length over and over again

        System.out.println("sorting");
        //base case: length of array is 1 element
        if (length < 2) {
            return;
        }
            int mid = length / 2;
            int[] l = new int[mid]; //left subarray
            int[] r = new int[length - mid]; //right subarray

            //populate the two subarrays by copying over the corresponding original array element values
            for (int i = 0; i < mid; i++) {
                l[i] = arr[i];
            }
            for (int i = mid; i < length; i++) {
                r[i - mid] = arr[i];
            }

            //recursively keep splitting the subarrays into smaller subarrays until  they're all 1 element long
            mergeSort(l, mid);
            mergeSort(r, length - mid);

            //start (recursively) merging all the subarrays back together
            merge(arr, l, r, mid, length - mid);
    }

    public void merge(int[] arr, int[] l, int[] r, int leftIndex, int rightIndex) {
        //where arr = the original array before splitting

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftIndex && j < rightIndex) {
            if (l[i] <= r[j]) {
                arr[k++] = l[i++];
            } else {
                arr[k++] = r[j++];
            }
        }
        while (i < leftIndex) {
            arr[k++] = l[i++];
        }
        while (j < rightIndex) {
            arr[k++] = r[j++];
        }
    }


     */







    public ArrayList<ArrayList<Node>> getTaskList() {
        return taskList;
    }

    public void printTaskNames() {
        System.out.println(taskList.size() + " tasks total:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("Task " + (i+1) + ": " + ((TextField)(taskList.get(i).get(0))).getText());
        }
    }

    public void printElementIndexes() {
        //to save me the trouble of needing to count and carefully look at the FXML file everytime I change what's in a TaskPane
        System.out.println("---Indexes of ID's in TaskPane vbox---");
        for (int i = 0; i < taskList.get(0).size(); i++) {
            System.out.println("index: " + i + " *** element id: " + taskList.get(0).get(i).getId());
        }
    }


    /* ELEMENTS
    TextField taskNameText;
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

    //IMPORTANT GETTERS
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


    //IMPORTANT SETTERS
    public void setTaskNameText(int task, String text) {
        ((TextField)(taskList.get(task).get(0))).setText(text);
    }
    public void setDate(int task, String dateStr) {
        Scanner scan = new Scanner(dateStr);
        if (!dateStr.equals("")) {
            scan.useDelimiter("-");
            LocalDate ld = LocalDate.of(scan.nextInt(), scan.nextInt(), scan.nextInt());
            ((DatePicker)(taskList.get(task).get(1))).setValue(ld);
        }
    }

    public void setReqTime(int task, String time) {
        ((TextField)(taskList.get(task).get(3))).setText(time);
    }

    public void setGoalTime(int task, String time) {
        ((TextField)(taskList.get(task).get(5))).setText(time);
    }
    public void setImportance(int task, String num) {
        ((TextField)(taskList.get(task).get(7))).setText(num);
    }
    public void setTaskDetailsText(int task, String text) {
        ((TextArea)(taskList.get(task).get(8))).setText(text);
    }

}
