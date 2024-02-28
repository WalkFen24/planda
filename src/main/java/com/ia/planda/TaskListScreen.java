package com.ia.planda;

//import com.gluonhq.charm.glisten.control.BottomNavigationButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskListScreen {

    @FXML
    public Button focusButton;

    public void onDeleteButtonClicked(ActionEvent event) {
    }

    public void onCompleteButtonClicked(ActionEvent event) {
    }

    public void onAddButtonClicked(ActionEvent event) {
    }

    public void onTaskListButtonClicked(ActionEvent event) {
    }

    public void onMotiveButtonClicked(ActionEvent event) {
    }

    public void onFocusButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) focusButton.getScene().getWindow(); //gets the stage
        Parent root = FXMLLoader.load(getClass().getResource("focus-setup-screen.fxml"));
        stage.getScene().setRoot(root); //changes the root node
        stage.show();
    }

    public void onRewardsButtonClicked(ActionEvent event) {
    }
}
