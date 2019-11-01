package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PopupEndOfTypingXMLController implements Initializable {

    private final Stage primaryStage;
    private final Stage dialog = new Stage();

    @FXML
    private AnchorPane rootPopup;
    @FXML
    private Button restartTyping;

    public PopupEndOfTypingXMLController(Stage stage){

        this.primaryStage  = stage;

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);



        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/popup_endoftyping.fxml"));
            Parent root  = fxmlLoader.load();
            Scene dialogScene = new Scene(root);
            dialog.setScene(dialogScene);

        }catch (IOException e){
            e.printStackTrace();
        }

     }

    public void showPopup(){
        dialog.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
