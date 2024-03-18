package com.ia.planda;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class TaskPane extends AnchorPane {
    @FXML
    private TextField taskNameText;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea taskDetailsText;
    @FXML
    private ButtonBar taskButtonBar;
    @FXML
    private Button deleteButton;
    @FXML
    private Button completeButton;
    @FXML
    private TitledPane taskPane;
    @FXML
    private AnchorPane taskAnchor;
    @FXML
    private AnchorPane titledAnchor;
    @FXML
    private VBox taskVbox;
    @FXML
    private Button addSubtaskButton;
    @FXML
    private TextField importanceText;
    @FXML
    private TextField timeReqText;

    private TaskList taskList;
    private Cache cache;
    private PointTracker pt = new PointTracker();


    public TaskPane() throws IOException {
        cache = new Cache();
    }

    public void onDeleteButtonClicked(ActionEvent event) {
        if (taskAnchor.getParent().getClass() == VBox.class) {
            VBox parent = (VBox) taskAnchor.getParent();
            parent.getChildren().remove(this.taskAnchor);
        }
    }

    public void onCompleteButtonClicked(ActionEvent event) throws IOException, InterruptedException {
        if (taskAnchor.getParent().getClass() == VBox.class) {
            VBox parent = (VBox) taskAnchor.getParent();
            parent.getChildren().remove(this.taskAnchor);
            int pointIncr = 5;
            if (datePicker.getValue() != null) {
                LocalDate ld = LocalDate.now();
                pointIncr += (datePicker.getValue().getDayOfYear() - ld.getDayOfYear());
            }
            try {
                int val = Integer.parseInt(timeReqText.getText());
                pointIncr += val / 2;
                //so they will get 5 points for every 10 minutes of work time, just like in the focus sessions
            } catch (NumberFormatException e) {
                System.out.println("Estimate time cost was not an integer.");
            }
            pt.incrPointsBy(pointIncr);
            cache.getPointsLabel().setText("Points: " + pt.getPoints());

            //congratulations
            cache.getCongratsLabel().setVisible(true);

            Timer celebrationTimer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        cache.getCongratsLabel().setVisible(false);
                    });
                }
            };
            celebrationTimer.schedule(timerTask, 2500);
        }
    }

    /*
    public TextField getTaskNameText() {
        return taskNameText;
    }

    public void setTaskNameText(TextField taskNameText) {
        this.taskNameText = taskNameText;
    }

    public LocalDate getDate() {
        return datePicker.getValue();
    }

    public void setDate(LocalDate localDate) {
        this.datePicker.setValue(localDate);
    }

    public String getTaskDetailsText() {
        return taskDetailsText.getText();
    }

    public void setTaskDetailsText(String text) {
        this.taskDetailsText.setText(text);
    }
    */

    public ButtonBar getTaskButtonBar() {
        return taskButtonBar;
    }

    public void setTaskButtonBar(ButtonBar taskButtonBar) {
        this.taskButtonBar = taskButtonBar;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getCompleteButton() {
        return completeButton;
    }

    public TitledPane getTaskPane() {
        return taskPane;
    }

    public void setTaskPane(TitledPane taskPane) {
        this.taskPane = taskPane;
    }

    public AnchorPane getTitledAnchor() {
        return titledAnchor;
    }

    public void setTitledAnchor(AnchorPane titledAnchor) {
        this.titledAnchor = titledAnchor;
    }

    public void onAddSubtaskButtonClicked(ActionEvent actionEvent) throws IOException {
        taskVbox.getChildren().add(FXMLLoader.load(getClass().getResource("TaskPane.fxml")));
        taskList = new TaskList(cache.getVbox());
        taskList.setTaskList();
        taskList.printTaskNames();
        cache.updateFile();
        //numTasks++;
    }

}