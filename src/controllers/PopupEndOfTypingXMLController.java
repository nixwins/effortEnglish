package controllers;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupEndOfTypingXMLController extends BaseController implements Initializable {

    @FXML
    private GridPane rootPopup;
    @FXML
    private Button restartTyping;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void restartTypingTextPopup(){
        getMainXMLController().resetTyping();
    }



}
