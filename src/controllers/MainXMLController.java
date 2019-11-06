package controllers;

import base.PopupMaker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import model.DefaultTextModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MainXMLController extends  BaseController implements Initializable{

    private Stage primaryStage;
    private static double xOffset = 0;
    private static double yOffset = 0;

    private String typeIt;
    private List<Character> currentText = new ArrayList<>();
    //private static int currTxtIndx = 0;

    private static int idxTypeIt = 0;

    @FXML
    private GridPane titleBar;
    @FXML
    private FlowPane containerTypeText;
    @FXML
    private GridPane rootGird;

    @FXML
    private FlowPane firstKeyboardRow, secondKeyboardRow, thirdKeyboardRow, fourthKeyboardRow;

    @FXML
    private TextFlow textFlow;

    @FXML
    private JFXHamburger jfxHambrger;

    @FXML
    private JFXDrawer jfxDrawerMainMenu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dragUndecoratedWindow();
        setKeyboardThemes();
        setTypeItText();
        installEventHandlerWindow();
        hamburgerTrans();
    }

    @FXML public void startType(){
        resetTyping();
    }

    public void resetTyping(){
        textFlow.getChildren().clear();
        setTypeItText(Color.CORAL);
        idxTypeIt = 0;
        currentText = new ArrayList<>();
    }

    public void setTypeItText() {
        this.typeIt = new DefaultTextModel().getTypeText();
        textFlow.getChildren().add(new Text(typeIt));
    }

    public void setTypeItText(Color color) {
        this.typeIt = new DefaultTextModel().getTypeText();
        Text txt = new Text(typeIt);
        txt.setFill(color);
        textFlow.getChildren().add(txt);
    }

    public void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    private void println(Object object){
        System.out.println(object);
    }

    /* ActionListners  */

    private void hamburgerTrans(){
        HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(jfxHambrger);
        transition.setRate(-1);
        jfxDrawerMainMenu.close();
       // Parent parent = null;

        try {
            VBox parent = FXMLLoader.load(getClass().getResource("/views/drawer_content.fxml"));
            jfxDrawerMainMenu.setSidePane(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }



        jfxHambrger.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();


                if(jfxDrawerMainMenu.isOpened()){
                    jfxDrawerMainMenu.close();
                }

                else{
                    //jfxDrawerMainMenu.minHeight(500);
                    jfxDrawerMainMenu.open();

                }

        });
    }
    private void dragUndecoratedWindow(){

           titleBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                xOffset = primaryStage.getX() - mouseEvent.getScreenX();
                yOffset = primaryStage.getY() - mouseEvent.getScreenY();
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

    private void installEventHandlerWindow(){

       EventHandler<KeyEvent> keyEventEventHandler = new EventHandler<KeyEvent>() {
           @Override
           public void handle(KeyEvent keyEvent) {
               changeBtnStyle(keyEvent.getCharacter(), keyEvent.getCode(), keyEvent.getEventType());
               if(keyEvent.getEventType() == KeyEvent.KEY_TYPED){
                   checkPressedChar(keyEvent.getCharacter().toCharArray()[0]);
               }
           }
       };

        rootGird.addEventFilter(KeyEvent.ANY, keyEventEventHandler);
    }

    /* End ActionListners */

    private List<Node> getKeyboardButton(){

        List<Node> allBtn = new ArrayList<>();
        allBtn.addAll(firstKeyboardRow.getChildren());
        allBtn.addAll(secondKeyboardRow.getChildren());
        allBtn.addAll(thirdKeyboardRow.getChildren());
        allBtn.addAll(fourthKeyboardRow.getChildren());

        return allBtn;
    }

    /*Logic*/
    public void renderPressedText(String pressedChar, boolean correct, String nextChar){

        PopupMaker popupMaker = new PopupMaker(primaryStage);


        Text oldTxt = new Text();
        Text newTxt = new Text();
        Text errTxt = new Text();

        if(correct && idxTypeIt <= typeIt.length()){

            // Введенные символы записываем лист как Character
            currentText.add(idxTypeIt, pressedChar.toCharArray()[0]);

            newTxt.setFill(Color.GREEN);
            //currTxtIndx++;

        }else if(currentText.size() < typeIt.length()){

            errTxt.setText(nextChar);
            newTxt.setFill(Color.AQUA);
            errTxt.setFill(Color.RED);
        }

        //Getting String from List;
        String str = currentText.stream().map(Object::toString)
                .collect(Collectors.joining(""));

        // Вырезаем строку из Дефолтного убираем введенные символы
        String cutText;

        if(!correct && str.length() < typeIt.length()) {
             cutText = typeIt.replace(str, "").substring(1);
        }else{
             cutText = typeIt.replace(str, "");
        }

        oldTxt.setText(cutText);
        newTxt.setText(str);

        if(str.length() >= typeIt.length()){
            popupMaker.endOfTypingText(this);
//            PopupMaker popupMaker = new PopupMaker();
//            popupMaker.endOfTypingText(primaryStage);


        }
        //println("Str length " + str.length() + " typeIt length " + typeIt.length());
        if(str.length() == typeIt.length() || str.length() < typeIt.length()){

            textFlow.getChildren().clear();
            textFlow.getChildren().addAll(newTxt, errTxt , oldTxt);
        }


    }

    private void checkPressedChar(char character){

        char[] textArray = typeIt.toCharArray();

        if(idxTypeIt >= textArray.length) idxTypeIt = 0;

        if(textArray[idxTypeIt] == character){

            println(character + " Right");
            renderPressedText(Character.toString(character), true, String.valueOf(textArray[idxTypeIt]));
            idxTypeIt++;

        }else if(idxTypeIt <= textArray.length){

            //Если введенные символ не правильно то красим на Красный
            renderPressedText(Character.toString(character), false, String.valueOf(textArray[idxTypeIt]));
        }
    }

    /* Вывести отдельный Класс Темы */

    private void changeBtnStyle(String character, KeyCode keyCode, EventType eventType){

        List<Node> allBtn = getKeyboardButton();

        for(int i=0; i<allBtn.size(); i++){

            Button btn = (Button)allBtn.get(i);
            btn.getStyleClass().remove("btn-typed");

            if (btn.getText().equals(character)) {
                btn.getStyleClass().remove("btn-typed");
                btn.getStyleClass().add("btn-typed");
                //println(space);
            }
        }
    }

    private void setKeyboardThemes(){
      List<Node> btn = getKeyboardButton();

      for(int i=0; i < btn.size(); i++){
            btn.get(i).getStyleClass().add("btn-default-theme");
      }
    }

    private void setKeyboardThemes(String theme){
        List<Node> btn = getKeyboardButton();

        for(int i=0; i < btn.size(); i++){
            btn.get(i).getStyleClass().add(theme);
        }
    }
}
