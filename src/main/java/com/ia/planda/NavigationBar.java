package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class NavigationBar extends AnchorPane {
    
    @FXML public Button focusButton;
    @FXML public Button taskListButton;
    @FXML public HBox navigationBar;
    @FXML public Button motiveButton;
    @FXML public Button planButton;
    @FXML public Button rewardsButton;

    public NavigationBar() {
    }

    public void onTaskListButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) taskListButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("task-list-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onFocusButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) focusButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("focus-setup-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onMotiveButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) motiveButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("motive-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onRewardsButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) rewardsButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("rewards-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onPlanButtonClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) planButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("plan-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }
}
