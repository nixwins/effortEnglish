package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

        categoryList.setCellFactory(lv -> new ListCell<CategoryModel>(){
            @Override
            protected void updateItem(CategoryModel item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getCategoryName());
            }
        });

        ObservableList divisionListRow = FXCollections.observableArrayList();

        try {
            while (resultSet.next()) {

                //Изменить Категори модель использовать другой калсс а то создает новый коннеткт каждый раз смотри Модель!
                System.out.println(resultSet.getInt("id"));
                divisionListRow.add(new CategoryModel(resultSet.getInt("id"), resultSet.getString("category_name")));
                //categoryList.getItems().add(resultSet.getString("category_name"), resultSet.getInt("id"));
                categoryList.setItems(divisionListRow);

            }
        }catch (Exception e){ e.printStackTrace(); }



        CategoryModel selectedItem = categoryList.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            System.out.println("id = " + selectedItem.getId());
        } else {
            System.out.println("no item selected");
        }

    }


}
