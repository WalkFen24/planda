package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlanScreen implements Initializable {

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private AnchorPane planTasksAnchor;
    @FXML
    private VBox vbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            AnchorPane barAnchor = FXMLLoader.load(getClass().getResource("navigation-bar.fxml"));
            mainAnchorPane.getChildren().add(barAnchor);
            barAnchor.setLayoutY(mainAnchorPane.getPrefHeight() - barAnchor.getPrefHeight()); //~560.0 for 600 height

            vbox.getChildren().add(FXMLLoader.load(getClass().getResource("plan-task.fxml")));
            vbox.getChildren().add(FXMLLoader.load(getClass().getResource("plan-task.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
