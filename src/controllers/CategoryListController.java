package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.CategoryModel;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class CategoryListController extends BaseController implements Initializable {

    @FXML
    private ListView<CategoryModel> categoryList;

    private CategoryModel categoryModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryModel = new CategoryModel();
        renderCategoryList(categoryModel.getAllCategory());
    }

    private void renderCategoryList(ResultSet resultSet){
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                //categoryList.getItems().add(resultSet.getString("category_name"));
                categoryList.setCellFactory(lv -> new ListCell<CategoryModel>(){
                    @Override
                    public void updateItem(CategoryModel item, Boolean empty){

                    }
                });
            }
        }catch (Exception e){ e.printStackTrace(); }
    }


}
