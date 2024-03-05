package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class TaskListScreen implements Initializable {

    @FXML
    private final ToggleGroup group = new ToggleGroup();
    @FXML
    public Button focusButton;
    @FXML
    public TitledPane taskPane;
    @FXML
    public Button taskListButton;
    @FXML
    public Button addButton;
    //@FXML
    //public Button deleteButton;
    @FXML
    public VBox vbox;
    @FXML
    public RadioButton dueDateRb;
    @FXML
    public RadioButton importanceRb;
    @FXML
    public AnchorPane mainAnchorPane;

    private static boolean isFirstRun = true;
    int numTasks = 1;
    int completeTasks = 0;
    Cache cache = new Cache();

    RandomAccessFile file = new RandomAccessFile(new File("tasks.txt"), "rw");

    public TaskListScreen() throws IOException {
        //TODO delete this - I was just trying the RAF stuff out
        /*
        RandomAccessFile file = new RandomAccessFile(new File("tasks.txt"), "rw");
        file.seek(0);
        while(file.getFilePointer() != file.length()) {
            System.out.println(file.readLine());
        }

         */
    }

    public void onAddButtonClicked(ActionEvent event) throws IOException {
        //adds another task pane to the Vbox

        //FUNCTIONAL but pretty bad, unless I make it its own method, but I wanted TaskPane to have all the
        //getters and setters for the different fields so I could avoid this...
        //TODO maybe a method that will return an ArrayList or LinkedList of TaskPanes, given the vbox?
        //TODO and it basically does the below stuff over and over again I guess? Or I can make a separate
        //TODO method to get that first arr (from the flowpane children) and then that way I can skip all those extra steps
        //TODO so like a custom method using abstraction from ANOTHER method?

        vbox.getChildren().add(FXMLLoader.load(getClass().getResource("TaskPane.fxml")));
        ArrayList<Node> arr = cache.taskElementsArr(vbox, 0,0);

        System.out.println(arr.get(0));


        //cache.updateFile();
        numTasks++;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (isFirstRun) { //TODO prob remove this later once the persistent storage is set up
                vbox.getChildren().add(FXMLLoader.load(getClass().getResource("TaskPane.fxml")));
                cache.setTasksList(vbox.getChildren());
                cache.setVbox(vbox);
                //System.out.println(vbox.getChildren().get(0).);
                isFirstRun = false;
            } else { //TODO and just keep this
                vbox.getChildren().setAll(cache.getTasksList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        numTasks++;
    }

    public void sortByDueDate(ActionEvent event) {

    }

    public void sortByImportance(ActionEvent event) {

    }


    //this screen has its own buttonbar already in the FXML file, along with its own methods for them,
    //as this class needs to update the cache with the new information before the screen is left
    //in order to keep the vbox info in the short term.
    public void onTaskListButtonClicked(ActionEvent event) throws IOException {
    }

    public void onMotiveButtonClicked(ActionEvent event) {
        //TODO remember to update with the NavigationBar class!

        cache.setTasksList(vbox.getChildren());
    }

    public void onFocusButtonClicked(ActionEvent event) throws IOException {
        //TODO remember to update with the NavigationBar class!

        cache.setTasksList(vbox.getChildren());

        Stage stage = (Stage) focusButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("focus-setup-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();

    }

    public void onRewardsButtonClicked(ActionEvent event) {
        //TODO remember to update with the NavigationBar class!
        cache.setTasksList(vbox.getChildren());
    }

    public void onPlanButtonClicked(ActionEvent actionEvent) {
        //TODO remember to update with the NavigationBar class!
        cache.setTasksList(vbox.getChildren());
    }

    /*
    public static ArrayList<Node> taskPaneArr(VBox taskList) {
        AnchorPane ap = (AnchorPane) taskList.getChildren().get(0);
        TitledPane tp = (TitledPane) ap.getChildren().get(0);
        AnchorPane ap2 = (AnchorPane) tp.getContent();
        VBox vbox = (VBox) ap2.getChildren().get(0);
        FlowPane fp = (FlowPane) vbox.getChildren().get(0);
        ArrayList<Node> arr = new ArrayList<>(fp.getChildren());
        return arr;
    }

     */
}
