package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FocusScreen implements Initializable {
    @FXML
    public Button endButton;
    @FXML
    public Button addTimeButton;
    @FXML
    public ProgressBar progressBar;
    @FXML
    public Button focusButton;
    @FXML
    public Button taskListButton;
    @FXML
    public Label timeLabel;
    Model model = new Model();


    public void onEndButtonClicked(ActionEvent event) {
    }

    public void onAddTimeButtonClicked(ActionEvent event) {
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
        timeLabel.setText(model.getInitFocusTime() + " min");
        progressBar.setProgress(1);
    }
}
