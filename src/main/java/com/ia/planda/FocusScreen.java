package com.ia.planda;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class FocusScreen implements Initializable {

    //Resource used to learn about Timers: https://egandunning.com/projects/timemanagement-timer.html

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
    @FXML
    public ImageView sunflowerImageView;
    @FXML
    public ImageView bookshelfImageView;
    @FXML
    public Rectangle wallShape;

    public Cache cache = new Cache();
    public Timer timer = new Timer();

    public static int i;
    public static int period;

    public FocusScreen() throws IOException {
    }


    public void onEndButtonClicked(ActionEvent event) {
        timer.cancel();
    }

    public void onAddTimeButtonClicked(ActionEvent event) {
        //adds 3 minutes to the timer
        period += 3 * 60000;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        period = 60000 * cache.getInitFocusTime();

        timeLabel.setText(cache.getInitFocusTime() + " min");
        progressBar.setProgress(1);
        try {
            AnchorPane barAnchor = FXMLLoader.load(getClass().getResource("navigation-bar.fxml"));
            mainAnchorPane.getChildren().add(barAnchor);
            barAnchor.setLayoutY(mainAnchorPane.getPrefHeight() - barAnchor.getPrefHeight()); //~560.0 for 600 height
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        i = 0;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //System.out.println(i);
                Platform.runLater(() -> {
                    timeLabel.setText(timeToString(period-i));
                    progressBar.setProgress((double)(period-i)/period);
                    if (progressBar.getProgress() <= 0) {
                        cancel();
                        timer.cancel();
                    }
                });
                i += 1000;
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);


    }

    public static String timeToString(int ms) {
        int seconds = (period-i)/1000;
        int minutes = seconds/60;
        seconds = seconds % 60;
        return (minutes + ":" + seconds);
    }
}
