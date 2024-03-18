package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MotiveScreen implements Initializable {
    @FXML
    private Button okayButton;
    @FXML
    private ImageView pandaImageView;
    @FXML
    private Label motivationLabel;
    @FXML
    private AnchorPane mainAnchorPane;
    private Random rand = new Random();
    private ArrayList<String> messages = new ArrayList<>();
    //most of the quotes were taken from https://resource.hix.ai/messages/inspirational-messages-for-students
    //the rest I came up with myself


    public MotiveScreen() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("quotes.txt"));
        while (scan.hasNextLine()) {
            messages.add(scan.nextLine());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        motivationLabel.setText("Press the \"Okay\" button for some motivational words from me—a huge fan and supporter of you.");
        try {
            AnchorPane barAnchor = FXMLLoader.load(getClass().getResource("navigation-bar.fxml"));
            mainAnchorPane.getChildren().add(barAnchor);
            barAnchor.setLayoutY(mainAnchorPane.getPrefHeight() - barAnchor.getPrefHeight()); //~560.0 for 600 height
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onOkayButtonClicked(ActionEvent event) {
        motivationLabel.setText(messages.get(rand.nextInt(messages.size())));
    }
}
