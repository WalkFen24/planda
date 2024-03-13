package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RewardPane extends AnchorPane implements Initializable {

    @FXML
    public AnchorPane itemAnchor;
    @FXML
    public TitledPane itemPane;
    @FXML
    public VBox itemVbox;
    @FXML
    public Label nameLabel;
    @FXML
    public Label costLabel;
    @FXML
    public ImageView image;
    @FXML
    public ButtonBar itemButtonBar;
    @FXML
    public Button buyButton;
    @FXML
    public Button selectButton;

    private Cache cache;
    public boolean owned = false;
    public boolean selected = false;
    private File file = new File("items.txt");
    private Scanner scan = new Scanner(file);
    private Scanner strScan;
    //private FileWriter fw = new FileWriter("items.txt", true);


    public RewardPane() throws IOException {
        cache = new Cache();
    }

    public void onBuyButtonClicked(ActionEvent event) {
        //check for adequate points, if enough, remove the cost from it and set owned to true
        owned = true;
        System.out.println(nameLabel.getText() + " purchased");
    }

    public void onSelectButtonClicked(ActionEvent event) {
        //check if owned, if so, select it
        if (owned == true) {
            selected = true;
            System.out.println(nameLabel.getText() + " selected");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        strScan = new Scanner(scan.nextLine());
        //scans the string made up of the first line of the items.txt file, which stores the list of items that have been bought
        strScan.useDelimiter(",");
        //sequential scanning while loop suitable because there's only a few possible items to traverse through

        //checks if this reward has been saved as bought
        boolean found = false;
        while (strScan.hasNext() && !found) {
            if (strScan.next().equalsIgnoreCase(itemPane.getText())) {
                owned = true;
                System.out.println(this.itemPane.getText() + " purchase loaded");
                found = true;
            }
        }

        //checks if this reward has been saved as selected
        if (scan.hasNext()) {
            if (scan.nextLine().equalsIgnoreCase(itemPane.getText())) {
                selected = true;
                System.out.println(this.itemPane.getText() + " selection loaded");
            }
        }
    }
}