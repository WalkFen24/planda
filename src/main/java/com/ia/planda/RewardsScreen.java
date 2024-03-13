package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RewardsScreen implements Initializable {
    @FXML
    public AnchorPane mainAnchorPane;
    @FXML
    public VBox vbox;
    public static final int rewardsNum = 4;
    private Cache cache = new Cache();
    public ArrayList<ArrayList<Node>> itemList = new ArrayList<>();
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
            vbox.getChildren().add(FXMLLoader.load(getClass().getResource("reward-pane.fxml")));

            setItemList();
            setUpRewardPanes();
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
        itemList = getTaskList(0);
    }

    public ArrayList<ArrayList<Node>> getTaskList(int item) {
        //task = column, field = rows
        if (item == vbox.getChildren().size()) {
            return itemList;
        } else {
            itemList.add(itemElementsArr(item));
            return getTaskList(item + 1);
        }
    }

    public void setUpRewardPanes() {
        Label costLabel;
        Label nameLabel;

        //ITEM 1
        int i = 0; //i = item in list
        nameLabel = (Label)itemList.get(i).get(0);
        nameLabel.setText("Flower pot");
        costLabel = (Label)itemList.get(i).get(1);
        costLabel.setText("10 pts");

        //ITEM 2
        i++;
        nameLabel = (Label)itemList.get(i).get(0);
        nameLabel.setText("Bookshelf");
        costLabel = (Label)itemList.get(i).get(1);
        costLabel.setText("30 pts");


        //ITEM 3
        i++;
        nameLabel = (Label)itemList.get(i).get(0);
        nameLabel.setText("Wallpaper colors");
        //get the option to choose between different wallpaper colors (white, black, red, blue, etc.)
        costLabel = (Label)itemList.get(i).get(1);
        costLabel.setText("60 pts");


        //ITEM 4
        i++;
        nameLabel = (Label)itemList.get(i).get(0);
        nameLabel.setText("Planda's task");
        //get the option to choose what kind of work Planda is doing (laptop, physical writing, reading)
        costLabel = (Label)itemList.get(i).get(1);
        costLabel.setText("100 pts");
    }
}
