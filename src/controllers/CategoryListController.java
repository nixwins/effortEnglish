package controllers;

import javafx.fxml.Initializable;
import model.CategoryModel;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryListController extends BaseController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new CategoryModel();
    }

}
