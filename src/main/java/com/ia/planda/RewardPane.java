package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.*;
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
    public ImageView imageView;
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
    private FileWriter fw = new FileWriter("items.txt", true);
    private static int n = -1;
    private String[] rewardNames = {"Flower pot", "Bookshelf", "Wallpaper colors"};


    public RewardPane() throws IOException {
        cache = new Cache();
        n++;
    }

    public void onBuyButtonClicked(ActionEvent event) throws IOException {
        //check for adequate points, if enough, remove the cost from it and set owned to true
        if (owned) {
            System.out.println("You already own this one.");
        } else {
            owned = true;
            System.out.println(nameLabel.getText() + " purchased");

            Scanner scan = new Scanner(file);
            String str = scan.nextLine();

            fw = new FileWriter(file);
            fw.write(str + nameLabel.getText() + ",");
            fw.close();
        }
    }

    public void onSelectButtonClicked(ActionEvent event) throws IOException {
        //check if owned, if so, select it
        if (owned) {
            selected = true;
            System.out.println(nameLabel.getText() + " selected");

            Scanner scan = new Scanner(file);
            String str = scan.nextLine();

            fw = new FileWriter(file);
            fw.write(str + "\n" + nameLabel.getText());
            fw.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (scan.hasNextLine()) {
            strScan = new Scanner(scan.nextLine());
            //scans the string made up of the first line of the items.txt file, which stores the list of items that have been bought
            strScan.useDelimiter(",");
            //sequential scanning while-loop suitable because there's only a few possible items to traverse through

            //checks if this reward has been saved as bought
            while (strScan.hasNext() && !owned && n < 3) {
                if (strScan.next().equalsIgnoreCase(rewardNames[n])) {
                    owned = true; //the field also acts as a flag for this while-loop
                    System.out.println(rewardNames[n] + " purchase loaded");
                }

            }

            //checks if this reward has been saved as selected
            if (scan.hasNext()) {
                if (scan.nextLine().equalsIgnoreCase(rewardNames[n])) {
                    selected = true;
                    System.out.println(rewardNames[n] + " selection loaded");
                }
            }
        }
    }
}