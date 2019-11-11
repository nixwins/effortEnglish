package controllers;

import base.PopupMaker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawerContentController extends  BaseController implements Initializable {

    @FXML
    private Label chooseDB;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML private void listOfPhrases(){
        PopupMaker popupMaker = new PopupMaker((Stage)chooseDB.getScene().getWindow());

       // getMainXMLController().setTypeItText("HI");
        popupMaker.listOfCategory();
    }

}
