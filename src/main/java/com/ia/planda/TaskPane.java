package com.ia.planda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    public Button Complete;
    @FXML
    public TitledPane taskPane;
    @FXML
    public AnchorPane taskAnchor;


    public void onDeleteButtonClicked(ActionEvent event) {
    }

    public void onCompleteButtonClicked(ActionEvent event) {
    }
}