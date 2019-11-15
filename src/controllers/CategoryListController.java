package controllers;

import base.LVCellViewHolder;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.CategoryModel;
import model.Model;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import core.App;
import model.PhraseModel;

public class CategoryListController extends BaseController implements Initializable {

    @FXML
    private JFXListView<CategoryModel> categoryList;

    private CategoryModel categoryModel = new CategoryModel();;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        renderCategoryList(categoryModel.getAllCategory());
        setEventListnerListvView();
    }

    private void setEventListnerListvView(){

        EventHandler<MouseEvent> handler = mouseEvent -> {

            CategoryModel selectedItem = categoryList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                System.out.println("id = " + selectedItem.getId());

                categoryList.getScene().getWindow().hide();
                App.getMainController().resetTyping(new PhraseModel().getAll(selectedItem.getId()));

            } else {
                System.out.println("no item selected");
            }
        };
        categoryList.addEventFilter(MouseEvent.MOUSE_CLICKED, handler);

    }

    private void renderCategoryList(ResultSet resultSet){

        categoryList.setCellFactory(lv -> new LVCellViewHolder<>(){
            @Override
            protected void updateItem(CategoryModel item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getCategoryName());
            }
        });

        ObservableList categoryListRow = FXCollections.observableArrayList();

        try {
            while (resultSet.next()) {

                categoryListRow.add(new CategoryModel(resultSet.getInt("id"), resultSet.getString("category_name")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        categoryList.setItems(categoryListRow);
    }

    public Node getView(){
        return categoryList;
    }

}
