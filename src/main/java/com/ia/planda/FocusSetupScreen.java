package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

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
    @FXML
    public ColorPicker colorPicker;
    @FXML
    public Rectangle wallPreview;
    private Cache cache = new Cache();

    public FocusSetupScreen() throws IOException {
    }

    public void onStartButtonClicked(ActionEvent event) throws IOException {
        cache.setInitFocusTime((int)timeSlider.getValue());
        cache.setWallColor(colorPicker.getValue());

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

            colorPicker.setVisible(false);
            startButton.setLayoutX(105);

            Scanner scan = new Scanner(new File("items.txt"));
            scan.nextLine();
            Scanner strScan = new Scanner(scan.nextLine());
            strScan.useDelimiter(",");
            while (strScan.hasNext()) {
                if (strScan.next().equalsIgnoreCase("Wallpaper colors")) {
                    colorPicker.setVisible(true);
                    startButton.setLayoutX(171);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateWallColor(ActionEvent event) throws FileNotFoundException {
        wallPreview.setFill(colorPicker.getValue());
        cache.setWallColor(colorPicker.getValue());
    }
}
