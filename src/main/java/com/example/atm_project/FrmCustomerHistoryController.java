package com.example.atm_project;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class FrmCustomerHistoryController implements Initializable {
    public ListView lstView;

    public void goBackRequest(ActionEvent actionEvent) {
        ATM.showSceneIndex(1,"Welcome, Choose an Option");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //logic from database here
    }
}
