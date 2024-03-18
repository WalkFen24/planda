package com.ia.planda;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class FocusScreen implements Initializable {

    //Resource used to learn about Timers: https://egandunning.com/projects/timemanagement-timer.html

    @FXML
    private Button endButton;
    @FXML
    private Button addTimeButton;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label timeLabel;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private ImageView flowerImageView;
    @FXML
    private ImageView bookshelfImageView;
    @FXML
    public Rectangle wallShape;
    @FXML
    public ImageView pandaDeskImageView;
    private Image[] imageArr = {
            new Image("panda study clap 1.png"),
            new Image("panda study clap 2.png"),
            new Image("panda study clap 3.png"),
            new Image("panda study clap 2.png"),
    };

    private Cache cache = new Cache();
    private Timer timer = new Timer();
    private Timer celebrationTimer = new Timer();

    private static int i;
    private static int period;
    private PointTracker pt = new PointTracker();

    public FocusScreen() throws IOException {
    }


    public void onEndButtonClicked(ActionEvent event) throws IOException {
        timer.cancel();

        Stage stage = (Stage) endButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("focus-setup-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onAddTimeButtonClicked(ActionEvent event) {
        //adds 3 minutes to the timer
        period += 3 * 60000;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (cache.getInitFocusTime() == 10) {
            period = 10000; //TODO delete this and keep only the below; this is just for demo purposes
        } else {
            period = 60000 * cache.getInitFocusTime();
        }

        timeLabel.setText(cache.getInitFocusTime() + " min");
        progressBar.setProgress(1);

        try {
            AnchorPane barAnchor = FXMLLoader.load(getClass().getResource("navigation-bar.fxml"));
            mainAnchorPane.getChildren().add(barAnchor);
            barAnchor.setLayoutY(mainAnchorPane.getPrefHeight() - barAnchor.getPrefHeight()); //~560.0 for 600 height
            Scanner scan = new Scanner(new File("items.txt"));
            scan.nextLine();
            Scanner strScan = new Scanner(scan.nextLine());
            strScan.useDelimiter(",");
            flowerImageView.setVisible(false);
            bookshelfImageView.setVisible(false);

            while (strScan.hasNext()) {
                String item = strScan.next();
                if (item.equalsIgnoreCase("Flower pot")) {
                    flowerImageView.setVisible(true);
                } else if (item.equalsIgnoreCase("Bookshelf")) {
                    bookshelfImageView.setVisible(true);
                } else if (item.equalsIgnoreCase("Wallpaper colors")) {
                    wallShape.setFill(cache.getWallColor());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        i = 0;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    timeLabel.setText(timeToString(period-i));
                    progressBar.setProgress((double)(period-i)/period);
                    if (progressBar.getProgress() <= 0) {
                        cancel();
                        timer.cancel();
                        try {
                            pt.incrPointsBy(cache.getInitFocusTime()/2);
                            celebrate();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
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

    private int t;
    public void celebrate() throws IOException {
        timeLabel.setText("YOU DID IT!!!");

        t = 5000;
        i = 0;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    pandaDeskImageView.setImage(imageArr[i]);
                    i = nextIndex(imageArr.length, i);
                    if (t <= 0) {
                        cancel();
                        celebrationTimer.cancel();
                        pandaDeskImageView.setImage(imageArr[0]);

                        try {
                            Stage stage = (Stage) endButton.getScene().getWindow(); //gets the stage
                            Parent root = FXMLLoader.load(getClass().getResource("focus-setup-screen.fxml"));
                            stage.getScene().setRoot(root); //changes the root node
                            stage.show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                t -= 100;
            }
        };
        celebrationTimer.scheduleAtFixedRate(timerTask, 100, 70);
    }

    private int nextIndex(int arrayLength, int pointer) {
        if (pointer == arrayLength - 1) {
            return 0;
        } else {
            return (pointer + 1);
        }
    }
}
