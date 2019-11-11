package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class BaseController {

    protected MainXMLController mainXMLController;

    public void setMainController(MainXMLController controller) {
        mainXMLController = controller;
    }

    public MainXMLController getMainXMLController() {
        return mainXMLController;
    }

    public BaseController(){
        initMainController();
    }

    public void initMainController(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.xml.fxml"));
        mainXMLController = fxmlLoader.getController();

    }


}
