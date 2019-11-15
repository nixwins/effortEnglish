package controllers;

import base.PopupMaker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawerContentController extends  BaseController implements Initializable {
    private static PopupMaker popupMaker;

    @FXML
    private Label chooseDB;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML private void listOfPhrases(){
        popupMaker = new PopupMaker((Stage)chooseDB.getScene().getWindow());

       // getMainXMLController().setTypeItText("HI");
        popupMaker.listOfCategory();
    }

    public static void closeDrawerMenu(boolean show){
        popupMaker.listOfCategory(show);

    }

}
