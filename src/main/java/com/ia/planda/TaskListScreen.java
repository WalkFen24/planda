package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TaskListScreen implements Initializable {

    @FXML
    private final ToggleGroup group = new ToggleGroup();
    @FXML
    public Button focusButton;
    @FXML
    public TitledPane taskPane;
    @FXML
    public Button taskListButton;
    @FXML
    public Button addButton;
    //@FXML
    //public Button deleteButton;
    @FXML
    public VBox vbox;
    @FXML
    public RadioButton dueDateRb;
    @FXML
    public RadioButton importanceRb;
    @FXML
    public AnchorPane mainAnchorPane;

    int numTasks = 1;
    int completeTasks = 0;

    RandomAccessFile file = new RandomAccessFile(new File("tasks.txt"), "rw");

    public TaskListScreen() throws IOException {
        //TODO delete this - I was just trying the RAF stuff out
        /*file.seek(0);
        while(file.getFilePointer() != file.length()) {
            System.out.println(file.readLine());
        }

         */
    }

    /*public void onDeleteButtonClicked(ActionEvent event) {
        //vbox.getChildren().remove();
        numTasks--;
        System.out.println(numTasks);
    }

     */

    public void onCompleteButtonClicked(ActionEvent event) {
        numTasks--;
        completeTasks++;
    }

    public void onAddButtonClicked(ActionEvent event) throws IOException {
        //adds another task pane to the Vbox
        vbox.getChildren().add(FXMLLoader.load(getClass().getResource("TaskPane.fxml")));
        numTasks++;
    }

    public void onTaskListButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) taskListButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("task-list-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onMotiveButtonClicked(ActionEvent event) {
    }

    public void onFocusButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) focusButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("focus-setup-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onRewardsButtonClicked(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            vbox.getChildren().add(FXMLLoader.load(getClass().getResource("TaskPane.fxml")));
            AnchorPane barAnchor = FXMLLoader.load(getClass().getResource("navigation-bar.fxml"));
            mainAnchorPane.getChildren().add(barAnchor);
            barAnchor.setLayoutY(mainAnchorPane.getPrefHeight() - barAnchor.getPrefHeight()); //~560.0 for 600 height
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        numTasks++;
    }

    public void sortByDueDate(ActionEvent event) {

    }

    public void sortByImportance(ActionEvent event) {

    }
}
