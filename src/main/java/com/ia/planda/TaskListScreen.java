package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class TaskListScreen implements Initializable {

    @FXML
    private final ToggleGroup group = new ToggleGroup();
    @FXML
    public Button focusButton;
    @FXML
    public Button rewardsButton;
    @FXML
    public Button planButton;
    @FXML
    public Button taskListButton;
    @FXML
    public Button motiveButton;
    @FXML
    public Button addButton;
    @FXML
    public VBox vbox;
    @FXML
    public RadioButton dueDateRb;
    @FXML
    public RadioButton importanceRb;
    @FXML
    public AnchorPane mainAnchorPane;
    @FXML
    public Label pointsLabel;
    private static TaskList taskList;

    //TODO replace almost all public variables with private

    private static boolean isFirstRun = true;

    int numTasks = 1;
    //int completeTasks = 0;
    Cache cache = new Cache();

    //private RandomAccessFile file = new RandomAccessFile(new File("tasks.txt"), "r");

    public TaskListScreen() throws IOException {
    }

    public void onAddButtonClicked(ActionEvent event) throws IOException {
        //adds another task pane to the Vbox

        vbox.getChildren().add(FXMLLoader.load(getClass().getResource("TaskPane.fxml")));
        taskList.setTaskList();
        taskList.printTaskNames();

        cache.updateFile();
        numTasks++;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (isFirstRun) {
                vbox.getChildren().add(FXMLLoader.load(getClass().getResource("TaskPane.fxml")));
                cache.setTasksList(vbox.getChildren());
                cache.setVbox(vbox);
                taskList = new TaskList(vbox);
                cache.setUpTaskList();

                cache.setPointsLabel(pointsLabel);

                //taskList.printElementIndexes(); //this is for myself, for the coding process and debugging purposes
                isFirstRun = false;
            } else {
                vbox.getChildren().setAll(cache.getTasksList());
            }

            PointTracker pt = new PointTracker();
            pointsLabel.setText("Points: " + pt.getPoints());
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

    public void onMotiveButtonClicked(ActionEvent event) throws IOException {
        cache.setTasksList(vbox.getChildren());

        Stage stage = (Stage) motiveButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("motive-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onFocusButtonClicked(ActionEvent event) throws IOException {
        cache.setTasksList(vbox.getChildren());

        Stage stage = (Stage) focusButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("focus-setup-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onRewardsButtonClicked(ActionEvent event) throws IOException {
        cache.setTasksList(vbox.getChildren());

        Stage stage = (Stage) rewardsButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("rewards-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onPlanButtonClicked(ActionEvent actionEvent) throws IOException {
        cache.setTasksList(vbox.getChildren());

        Stage stage = (Stage) planButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("plan-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
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
