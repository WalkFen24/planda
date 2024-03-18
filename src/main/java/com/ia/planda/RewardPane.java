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
import javafx.scene.paint.Color;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RewardPane extends AnchorPane implements Initializable {

    @FXML
    private AnchorPane itemAnchor;
    @FXML
    private TitledPane itemPane;
    @FXML
    private VBox itemVbox;
    @FXML
    private Label nameLabel;
    @FXML
    private Label costLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private ButtonBar itemButtonBar;
    @FXML
    private Button buyButton;
    @FXML
    private Button selectButton;

    private Cache cache;
    private boolean owned = false;
    private boolean selected = false;
    private File file = new File("items.txt");
    private Scanner scan = new Scanner(file);
    private Scanner strScan;
    private FileWriter fw = new FileWriter("items.txt", true);
    private static int n = -1;
    private String[] rewardNames = {"Flower pot", "Bookshelf", "Wallpaper colors"};

    /*
        I used this array instead of just nameLabel.getText() in the initializer because these RewardPanes are initialized
        before the RewardsScreen finishes initializing, which means that their rewardNames haven't been set yet.
     */

    private PointTracker pt = new PointTracker();


    public RewardPane() throws IOException {
        cache = new Cache();
        n++;
    }

    public void onBuyButtonClicked(ActionEvent event) throws IOException {
        //check for adequate points, if enough, remove the cost from it and set owned to true
        Scanner costScan = new Scanner(costLabel.getText());
        int cost = costScan.nextInt();
        if (owned) {
            System.out.println("You already own this one.");
        } else if (pt.getPoints() < cost) {
            System.out.println("You don't have enough points for this item quite yet. Keep earning points, then come back!");
        } else {
            owned = true;
            System.out.println(nameLabel.getText() + " purchased");
            buyButton.setDisable(true);

            pt.decrPointsBy(cost);
            cache.getPointsLabel().setText("Points: " + pt.getPoints());

            Scanner scan = new Scanner(file);
            String str = scan.nextLine();
            String str2 = scan.nextLine() + "\n" + scan.nextLine();

            fw = new FileWriter(file);
            fw.write(str + nameLabel.getText() + ",\n" + str2);
            fw.close();
        }
    }

    public void onSelectButtonClicked(ActionEvent event) throws IOException {
        //check if owned, if so, select it. If it's already selected, deselect it.
        if (selected) {
            selected = false;
            System.out.println(nameLabel.getText() + " deselected");

            Scanner scan = new Scanner(file);
            String newItemsStr = scan.nextLine() + "\n";
            Scanner strScan = new Scanner(scan.nextLine());
            strScan.useDelimiter(",");
            String current;
            while(strScan.hasNext()) {
                current = strScan.next();
                if (!current.equalsIgnoreCase(nameLabel.getText())) {
                    newItemsStr += current + ",";
                }
            }
            newItemsStr += "\n" + scan.nextLine();
            fw = new FileWriter(file);
            fw.write(newItemsStr);
            fw.close();

            selectButton.setText("Select");
        } else if (owned) {
            selected = true;
            System.out.println(nameLabel.getText() + " selected");

            Scanner scan = new Scanner(file);
            String str = scan.nextLine();
            String str2 = scan.nextLine();
            String str3 = scan.nextLine();

            fw = new FileWriter(file);
            fw.write(str + "\n" + str2 + nameLabel.getText() + ",\n" + str3);
            fw.close();

            selectButton.setText("Deselect");
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
                    buyButton.setDisable(true);
                }
            }

            strScan = new Scanner(scan.nextLine());
            strScan.useDelimiter(",");

            //checks if this reward has been saved as selected
            while (strScan.hasNext() && !selected) {
                if (strScan.next().equalsIgnoreCase(rewardNames[n])) {
                    selected = true; //the field also acts as a flag for this while-loop
                    System.out.println(rewardNames[n] + " selection loaded");
                    selectButton.setText("Deselect");
                }
            }
        }
    }

    public void resetN() {
        n = -1;
    }
}