package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class TaskPane extends AnchorPane {
    @FXML
    public TextField taskNameText;
    @FXML
    public DatePicker datePicker;
    @FXML
    public TextArea taskDetailsText;
    @FXML
    public ButtonBar taskButtonBar;
    @FXML
    public Button deleteButton;
    @FXML
    public Button completeButton;
    @FXML
    public TitledPane taskPane;
    @FXML
    public AnchorPane taskAnchor;
    @FXML
    public AnchorPane titledAnchor;
    @FXML
    public VBox taskVbox;
    @FXML
    public Button addSubtaskButton;
    @FXML
    public TextField importanceText;

    TaskList taskList;
    Cache cache;
    PointTracker pt = new PointTracker();


    public TaskPane() throws IOException {
        cache = new Cache();
    }

    public void onDeleteButtonClicked(ActionEvent event) {
        if (taskAnchor.getParent().getClass() == VBox.class) {
            VBox parent = (VBox) taskAnchor.getParent();
            parent.getChildren().remove(this.taskAnchor);
        }
    }

    public void onCompleteButtonClicked(ActionEvent event) throws IOException {
        if (taskAnchor.getParent().getClass() == VBox.class) {
            VBox parent = (VBox) taskAnchor.getParent();
            parent.getChildren().remove(this.taskAnchor);
            int pointIncr = 5;
            if (datePicker.getValue() != null) {
                LocalDate ld = LocalDate.now();
                pointIncr += (datePicker.getValue().getDayOfYear() - ld.getDayOfYear());
            }
            try {
                int val = Integer.parseInt(importanceText.getText());
                if (val > 0 && val < 11) {
                    pointIncr += val;
                }
            } catch (NumberFormatException e) {
                System.out.println("Importance was not an integer.");
            }
            pt.incrPointsBy(pointIncr);
            cache.getPointsLabel().setText("Points: " + pt.getPoints());
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