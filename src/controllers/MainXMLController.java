package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.DefaultTextModel;
import model.Model;

import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.lang.System.*;

public class MainXMLController extends  BaseController implements Initializable{

    private Stage primaryStage;
    private Scene scene;
    private static double xOffset = 0;
    private static double yOffset = 0;

    private static int currentIndex = 0;

    @FXML
    private FlowPane titleBar;
    @FXML
    private GridPane rootGird;

    @FXML
    private FlowPane firstKeyboardRow, secondKeyboardRow, thirdKeyboardRow, fourthKeyboardRow;


    @FXML
    private TextArea typeIt;
    @FXML
    private Button space;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dragUndecoratedWindow();
        setKeyboardThemes();
        setTypeItText();
        installEventHandlerStage();
        scene = rootGird.getScene();
       // println(rootGird.getScene());
    }

    public void setTypeItText(){

        typeIt.setText(new DefaultTextModel().getTypeText());
    }

    public void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    /* ActionListners  */
    private void println(Object object){
        System.out.println(object);
    }

    private void dragUndecoratedWindow(){

           titleBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = primaryStage.getX() - mouseEvent.getScreenX();
                yOffset = primaryStage.getY() - mouseEvent.getScreenY();
                //println("stageFROM event " + primaryStage.getY() );
            }
        });

        titleBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setX(mouseEvent.getScreenX() + xOffset);
                primaryStage.setY(mouseEvent.getScreenY() + yOffset);
            }
        });
    }

    private void installEventHandlerStage(){
       EventHandler<KeyEvent> keyEventEventHandler = new EventHandler<KeyEvent>() {
           @Override
           public void handle(KeyEvent keyEvent) {
               changeBtnStyle(keyEvent.getCharacter(), keyEvent.getCode(), keyEvent.getEventType());
               if(keyEvent.getEventType() == KeyEvent.KEY_TYPED){
                   correctCharacterPressed(keyEvent.getCharacter().toCharArray()[0]);
               }

               //println(keyEvent.getCode());
           }
       };

        rootGird.addEventFilter(KeyEvent.ANY, keyEventEventHandler);
    }
    /* End ActionListners */

    private List<Node> getAllBtn(){
        List<Node> allBtn = new ArrayList<>();
        allBtn.addAll(firstKeyboardRow.getChildren());
        allBtn.addAll(secondKeyboardRow.getChildren());
        allBtn.addAll(thirdKeyboardRow.getChildren());
        allBtn.addAll(fourthKeyboardRow.getChildren());

        return allBtn;
    }

    /*Logic*/
    private void correctCharacterPressed(char character){

        char[] textArray = typeIt.getText().toCharArray();
       /// println("correct() " + character);
        if(textArray[currentIndex] == character){
            println(character + " Right");
            currentIndex++;
        }


    }

    /* Вывести отдельный Класс Темы */

    private void changeBtnStyle(String character, KeyCode keyCode, EventType eventType){

        List<Node> allBtn = getAllBtn();

        for(int i=0; i<allBtn.size(); i++){

            Button btn = (Button)allBtn.get(i);
            btn.getStyleClass().remove("btn-typed");

            if (keyCode == KeyCode.SPACE) {
                space.getStyleClass().remove("btn-typed");
                space.getStyleClass().add("btn-typed");
                println(space);
            }

            if(btn.getText().equals(character)){
                println(btn);
                btn.getStyleClass().add("btn-typed");
            }
        }
    }

    private void setKeyboardThemes(){
      List<Node> btn = getAllBtn();

      for(int i=0; i < btn.size(); i++){
            btn.get(i).getStyleClass().add("btn-default-theme");
      }
    }

    private void setKeyboardThemes(String theme){
        List<Node> btn = getAllBtn();

        for(int i=0; i < btn.size(); i++){
            btn.get(i).getStyleClass().add(theme);
        }
    }
}
