package com.ia.planda;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    private static boolean isFirstRun = true;
    int numTasks = 1;
    int completeTasks = 0;
    Container container = new Container();

    RandomAccessFile file = new RandomAccessFile(new File("tasks.txt"), "rw");

    public TaskListScreen() throws IOException {
        //TODO delete this - I was just trying the RAF stuff out
        /*
        RandomAccessFile file = new RandomAccessFile(new File("tasks.txt"), "rw");
        file.seek(0);
        while(file.getFilePointer() != file.length()) {
            System.out.println(file.readLine());
        }

         */
    }

    public void onAddButtonClicked(ActionEvent event) throws IOException {
        //adds another task pane to the Vbox
        vbox.getChildren().add(FXMLLoader.load(getClass().getResource("TaskPane.fxml")));
        numTasks++;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (isFirstRun) { //TODO prob remove this later once the persistent storage is set up
                vbox.getChildren().add(FXMLLoader.load(getClass().getResource("TaskPane.fxml")));
                container.setTasksList(vbox.getChildren());
                isFirstRun = false;
            } else { //TODO and just keep this
                vbox.getChildren().setAll(container.getTasksList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        numTasks++;
    }

    public void sortByDueDate(ActionEvent event) {

    }

    public void sortByImportance(ActionEvent event) {

    }


    //this screen has its own buttonbar already in the FXML file, along with its own methods for them,
    //as this class needs to update the container with the new information before the screen is left
    //in order to keep the vbox info in the short term.
    public void onTaskListButtonClicked(ActionEvent event) throws IOException {
    }

    public void onMotiveButtonClicked(ActionEvent event) {
        //TODO remember to update with the NavigationBar class!

        container.setTasksList(vbox.getChildren());
    }

    public void onFocusButtonClicked(ActionEvent event) throws IOException {
        //TODO remember to update with the NavigationBar class!

        container.setTasksList(vbox.getChildren());

        Stage stage = (Stage) focusButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("focus-setup-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();

    }

    public void onRewardsButtonClicked(ActionEvent event) {
        //TODO remember to update with the NavigationBar class!
        container.setTasksList(vbox.getChildren());
    }

    public void onPlanButtonClicked(ActionEvent actionEvent) {
        //TODO remember to update with the NavigationBar class!
        container.setTasksList(vbox.getChildren());
    }

    public void setUpVbox() {

    }
}
