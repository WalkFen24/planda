package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RewardsScreen implements Initializable {
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private VBox vbox;
    @FXML
    private Label pointsLabel;
    private static final int rewardsNum = 4;
    private Cache cache = new Cache();
    private ArrayList<ArrayList<Node>> itemList = new ArrayList<>();
    //private File file = new File("items.txt");
    //private Scanner scan = new Scanner(file);
    //private FileWriter fw = new FileWriter("items.txt", false);

    public RewardsScreen() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            AnchorPane barAnchor = FXMLLoader.load(getClass().getResource("navigation-bar.fxml"));
            mainAnchorPane.getChildren().add(barAnchor);
            barAnchor.setLayoutY(mainAnchorPane.getPrefHeight() - barAnchor.getPrefHeight()); //~560.0 for 600 height
            setItemList();

            vbox.getChildren().add(FXMLLoader.load(getClass().getResource("reward-pane.fxml")));
            vbox.getChildren().add(FXMLLoader.load(getClass().getResource("reward-pane.fxml")));
            vbox.getChildren().add(FXMLLoader.load(getClass().getResource("reward-pane.fxml")));

            setItemList();
            setUpRewardPanes();

            PointTracker pt = new PointTracker();
            pointsLabel.setText("Points: " + pt.getPoints());
            cache.setPointsLabel(pointsLabel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    public ArrayList<Node> itemElementsArr(int item) {
        AnchorPane ap = (AnchorPane) vbox.getChildren().get(item);
        TitledPane tp = (TitledPane) ap.getChildren().get(0);
        AnchorPane ap2 = (AnchorPane) tp.getContent();
        VBox vboxTask = (VBox) ap2.getChildren().get(0);
        FlowPane fp = (FlowPane) vboxTask.getChildren().get(0);
        ArrayList<Node> arr = new ArrayList<>(fp.getChildren());
        return arr; //[nameLabel, costLabel, image]
    }

    public void setItemList() {
        itemList.clear();
        itemList = getItemList(0);
    }

    public ArrayList<ArrayList<Node>> getItemList(int item) {
        //task = column, field = rows
        if (item == vbox.getChildren().size()) {
            return itemList;
        } else {
            itemList.add(itemElementsArr(item));
            return getItemList(item + 1);
        }
    }

    public void setUpRewardPanes() throws IOException {
        RewardPane rp = new RewardPane();
        rp.resetN();

        Label costLabel;
        Label nameLabel;
        ImageView imageView;
        Image image;

        //ITEM 1
        int i = 0; //i = item in list
        nameLabel = (Label)itemList.get(i).get(0);
        nameLabel.setText("Flower pot");
        costLabel = (Label)itemList.get(i).get(1);
        costLabel.setText("20 pts");
        imageView = (ImageView)itemList.get(i).get(2);
        image = new Image("flower.png");
        imageView.setImage(image);

        //ITEM 2
        i++;
        nameLabel = (Label)itemList.get(i).get(0);
        nameLabel.setText("Bookshelf");
        costLabel = (Label)itemList.get(i).get(1);
        costLabel.setText("100 pts");
        imageView = (ImageView)itemList.get(i).get(2);
        image = new Image("bookshelf.png");
        imageView.setImage(image);


        //ITEM 3
        i++;
        nameLabel = (Label)itemList.get(i).get(0);
        nameLabel.setText("Wallpaper colors");
        //get the option to choose between different wallpaper colors (white, black, red, blue, etc.)
        costLabel = (Label)itemList.get(i).get(1);
        costLabel.setText("200 pts");
        imageView = (ImageView)itemList.get(i).get(2);
        image = new Image("panda study scene full.png");
        imageView.setImage(image);

    }
}
