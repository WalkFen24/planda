package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
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
    @FXML
    public Label rewardLabel;
    @FXML
    public Label pointsLabel;
    private Cache cache = new Cache();

    public FocusSetupScreen() throws IOException {
    }

    public void onStartButtonClicked(ActionEvent event) throws IOException {
        cache.setInitFocusTime((int)timeSlider.getValue());

        Stage stage = (Stage) startButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("focus-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void updateTimeLabel(MouseEvent mouseEvent) {
        timeLabel.setText((int)timeSlider.getValue() + " min");
        rewardLabel.setText((int)timeSlider.getValue()/2 + " pts");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            AnchorPane barAnchor = FXMLLoader.load(getClass().getResource("navigation-bar.fxml"));
            mainAnchorPane.getChildren().add(barAnchor);
            barAnchor.setLayoutY(mainAnchorPane.getPrefHeight() - barAnchor.getPrefHeight()); //~560.0 for 600 height
            PointTracker pt = new PointTracker();
            pointsLabel.setText("Points: " + pt.getPoints());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
