package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class BaseController {

        protected MainXMLController mainXMLController;

        public MainXMLController getMainXMLController() {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.xml.fxml"));

            return fxmlLoader.getController();

        }


}
