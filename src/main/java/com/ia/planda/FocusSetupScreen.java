package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FocusSetupScreen implements Initializable {
    @FXML
    public Button startButton;
    @FXML
    public Label timeLabel;
    @FXML
    public Button focusButton;
    @FXML
    public Button taskListButton;
    @FXML
    public Slider timeSlider;
    @FXML
    public AnchorPane mainAnchorPane;
    Model model = new Model();

    public void onStartButtonClicked(ActionEvent event) throws IOException {
        model.setInitFocusTime((int)timeSlider.getValue());

        Stage stage = (Stage) startButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("focus-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void updateTimeLabel(MouseEvent mouseEvent) {
        timeLabel.setText((int)timeSlider.getValue() + " min");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            //mainAnchorPane.getChildren().add(FXMLLoader.load(getClass().getResource("navigation-bar.fxml")));
            AnchorPane barAnchor = FXMLLoader.load(getClass().getResource("navigation-bar.fxml"));
            mainAnchorPane.getChildren().add(barAnchor);
            barAnchor.setLayoutY(mainAnchorPane.getPrefHeight() - barAnchor.getPrefHeight()); //~560.0 for 600 height
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
