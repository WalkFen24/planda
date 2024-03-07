package com.ia.planda;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateStringConverter;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Cache {
    //holds data to be used and transferred between classes, for short-term storage
    private static int initFocusTime;
    private static List<? extends Node> tasksList;
    private static VBox vbox;
    private static TaskList taskList;

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

    public void setTasksList(List<? extends Node> tasksList) {
        Cache.tasksList = tasksList;
    }
    public List<? extends Node> getTasksList() {
        return Cache.tasksList;
    }

    public void setUpCache() throws IOException {
        //read info from the file to the cache

        //(read the lines into the proper storage locations
        //so it assigns the first line as the name field of the first TaskPane stored in the vbox's collection)

        scan = new Scanner(file);
        taskList.setTaskNameText(0, scan.nextLine());
        /*int i = 0;
        while(scan.hasNext()) {
            taskList.setTaskNameText(i, scan.nextLine());
            //System.out.println(scan.nextLine());
            i++;
        }

         */
        System.out.println("Done parsing file.");
        //System.out.println("Save loaded into cache.");
    }

    public void updateFile() throws IOException {
        //TODO save important Cache info to the text file for persistent storage before closing the app
        fw = new FileWriter(file);
        //write to file
        for (int i = 0; i < vbox.getChildren().size(); i++) {
            //System.out.println(taskList.getTaskNameText(i));
            fw.write("^^^Task " + (i+1) + "^^^\n");
            writeLn("Name: " + taskList.getTaskNameText(i));
            if (taskList.getDate(i) == null) {
                writeLn("Due date: ");
            } else {
                writeLn("Due date: " + taskList.getDate(i).toString());
            }
            writeLn("Estimated time: " + taskList.getReqTime(i));
            writeLn("Goal time per day: " + taskList.getGoalTime(i));
            writeLn("Importance: " + taskList.getImportance(i));
            writeLn("Details: " + taskList.getTaskDetailsText(i));
            writeLn("");
        }
        fw.close();

        //read and print file contents to check that it was updated properly
        System.out.println("---Text File Content---");
        printTasksFile();
        /*
        //read and print file contents to check that it was updated properly
        System.out.println("---Text File Content---");
        file.seek(0);
        while(file.getFilePointer() != file.length()) {
            System.out.println(file.readLine());
        }

         */
        //updateFile(0);
    }

    public void writeLn(String str) throws IOException {
        fw.write(str + "\n");
    }

    public void updateTaskList() throws IOException {
        if (vbox == null) {
            System.out.println("vbox in cache is null.");
        } else {
            //file.seek(0);
            //System.out.println("done reading file in cache");
        }
    }

    public void printTasksFile() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("tasks.txt"));
        while (scan.hasNext()) {
            System.out.println(scan.nextLine());
        }
    }

}
