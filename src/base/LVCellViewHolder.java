package base;

import com.jfoenix.controls.JFXListCell;
import controllers.CategoryListController;
import javafx.scene.Node;
import model.Model;

public class LVCellViewHolder<Model> extends JFXListCell<Model> {

    private CategoryListController viewHolder = new CategoryListController();
    private Node view = viewHolder.getView();

    @Override
    protected void updateItem(Model item, boolean empty){
        super.updateItem(item, empty);
        if(empty){
            setGraphic(null);
        }else{
            //viewHolder.setItem((model.Model)item);
            setGraphic(view);
        }


    }


}
