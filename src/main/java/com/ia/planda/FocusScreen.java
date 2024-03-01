package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    public AnchorPane mainAnchorPane;
    Model model = new Model();


    public void onEndButtonClicked(ActionEvent event) {
    }

    public void onAddTimeButtonClicked(ActionEvent event) {
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeLabel.setText(model.getInitFocusTime() + " min");
        progressBar.setProgress(1);
        try {
            mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("navigation-bar.fxml")));
            mainAnchorPane.getChildren().getLast().setLayoutY(560.0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
