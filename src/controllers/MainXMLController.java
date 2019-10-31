package controllers;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.DefaultTextModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainXMLController extends  BaseController implements Initializable{

    private Stage primaryStage;
    private static double xOffset = 0;
    private static double yOffset = 0;

    private String typeIt;
    private List<Character> currentText = new ArrayList<>();
    private static int currTxtIndx = 0;

    private static int currentIndex = 0;

    @FXML
    private FlowPane titleBar;
    @FXML
    private FlowPane containerTypeText;
    @FXML
    private GridPane rootGird;

    @FXML
    private FlowPane firstKeyboardRow, secondKeyboardRow, thirdKeyboardRow, fourthKeyboardRow;

    @FXML
    private TextFlow textFlow;
    @FXML
    private Button space;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dragUndecoratedWindow();
        setKeyboardThemes();
        setTypeItText();
        installEventHandlerWindow();

        textFlow.getStyleClass().add("textflow");
    }

    public void setTypeItText() {
        this.typeIt = new DefaultTextModel().getTypeText();
        textFlow.getChildren().add(new Text(typeIt));
    }

    public void renderPressedText(String txtChanged){

        // Введенные символы записываем лист как Character
        currentText.add(currentIndex, txtChanged.toCharArray()[0]);

        //Getting String from List;
        String str = currentText.stream().map(Object::toString)
                .collect(Collectors.joining(""));

        // Вырезаем строку из Дефолтного убираем введенные символы
        String cutText = typeIt.replace(str, "");

        Text oldTxt = new Text(cutText);
        Text newTxt = new Text(str);

        newTxt.setFill(Color.GREEN);

        currTxtIndx++;

        textFlow.getChildren().clear();
        textFlow.getChildren().addAll(newTxt, oldTxt);

    }

    public void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    private void println(Object object){
        System.out.println(object);
    }

    /* ActionListners  */

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
                   correctCharacterPressed(keyEvent.getCharacter().toCharArray()[0]);
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
    private void correctCharacterPressed(char character){

        char[] textArray = typeIt.toCharArray();

        if(currentIndex >= textArray.length) currentIndex = 0;

        if(textArray[currentIndex] == character){

            println(character + " Right");
            renderPressedText(Character.toString(character));
            currentIndex++;

        }else{

            //Если введенные символ не правильно то красим на Красный
        }
    }

    /* Вывести отдельный Класс Темы */

    private void changeBtnStyle(String character, KeyCode keyCode, EventType eventType){

        List<Node> allBtn = getKeyboardButton();

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
