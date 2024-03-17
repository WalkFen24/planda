package com.ia.planda;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Cache {
    //holds data to be used and transferred between classes, for short-term storage
    private static int initFocusTime;
    private static List<? extends Node> tasksList;
    private static VBox vbox;
    private static TaskList taskList;
    private static Label pointsLabel;

    private File file = new File("tasks.txt");
    private Scanner scan = new Scanner(file);
    private FileWriter fw = new FileWriter("tasks.txt", true);

    public Cache() throws IOException {
        //file = new RandomAccessFile(new File("tasks.txt"), "r");
        //fw = new FileWriter("tasks.txt");
    }

    public void setVbox(VBox vbox) {
        Cache.vbox = vbox;
        taskList = new TaskList(vbox);
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

    public Label getPointsLabel() {
        return pointsLabel;
    }

    public void setPointsLabel(Label pointsLabel) {
        Cache.pointsLabel = pointsLabel;
    }

    public void setTasksList(List<? extends Node> tasksList) {
        Cache.tasksList = tasksList;
    }
    public List<? extends Node> getTasksList() {
        return Cache.tasksList;
    }

    public void setUpTaskList() throws IOException {
        //read the lines from the text file into the proper storage locations,
        // so it assigns the content of each line as the fields of each sequential TaskPane of the TaskList

        scan = new Scanner(file);
        int i = 0;
        while (scan.hasNextLine()) {
            scan.nextLine();
            taskList.setTaskNameText(i, scan.nextLine());
            taskList.setDate(i, scan.nextLine());
            taskList.setReqTime(i, scan.nextLine());
            taskList.setGoalTime(i, scan.nextLine());
            taskList.setImportance(i, scan.nextLine());
            taskList.setTaskDetailsText(i, scan.nextLine());
            i++;
            if (scan.hasNextLine()) {
                vbox.getChildren().add(FXMLLoader.load(getClass().getResource("TaskPane.fxml")));
                taskList.setTaskList();
            }
        }
        System.out.println("Save loaded.");
    }

    public void updateFile() throws IOException {
        //save important TaskList info to the text file for persistent storage before closing the app

        fw = new FileWriter(file);
        //write to file
        for (int i = 0; i < vbox.getChildren().size(); i++) {
            fw.write("^^^Task " + (i+1) + "^^^\n"); //to make the file easier to manually read if needed
            writeLn(taskList.getTaskNameText(i));
            if (taskList.getDate(i) == null) {
                writeLn("");
            } else {
                writeLn(taskList.getDate(i).toString());
            }
            writeLn(taskList.getReqTime(i));
            writeLn(taskList.getGoalTime(i));
            writeLn(taskList.getImportance(i));
            writeLn(taskList.getTaskDetailsText(i));
        }
        fw.close();

        //read and print file contents to check that it was updated properly
        System.out.println("---Text File Content---");
        printTasksFile();
    }

    public void writeLn(String str) throws IOException {
        fw.write(str + "\n");
    }

    public void printTasksFile() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("tasks.txt"));
        while (scan.hasNext()) {
            System.out.println(scan.nextLine());
        }
    }
}
