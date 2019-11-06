package controllers;

public class BaseController {

    private MainXMLController mainXMLController;

    public void setMainController(MainXMLController controller) {
        mainXMLController = controller;
    }

    public MainXMLController getMainXMLController() {
        return mainXMLController;
    }

}
