package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TaskListScreen {

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
    //@FXML
    //public AnchorPane taskAnchor;

    int numTasks = 1;
    int completeTasks = 0;

    public TaskListScreen() {
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
        System.out.println(numTasks);
        System.out.println(vbox.getChildren().get(0).getClass());
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

}
