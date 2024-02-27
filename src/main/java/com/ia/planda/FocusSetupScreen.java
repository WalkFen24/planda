package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;

public class FocusSetupScreen {
    public Button startButton;
    public Button endButton;
    public Button addTimeButton;
    public ProgressBar progressBar;
    public Label timeLabel;

    public void onStartButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) startButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("focus-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onEndButtonClicked(ActionEvent event) {
    }

    public void onAddTimeButtonClicked(ActionEvent event) {
    }
}
