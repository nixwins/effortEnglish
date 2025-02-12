package base;

import controllers.BaseController;
import controllers.CategoryListController;
import controllers.MainXMLController;
import controllers.PopupEndOfTypingXMLController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PopupMaker {

    private final Stage primaryStage;

    public PopupMaker(Stage stage){
        this.primaryStage = stage;
    }

    public  void endOfTypingText(BaseController controller){

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/popup_endoftyping.fxml"));
            Parent root  = fxmlLoader.load();
            Scene dialogScene = new Scene(root);
            dialog.setScene(dialogScene);

        }catch (IOException e){
            System.out.println("Loaded error");
        }

        dialog.show();
    }

    public  void listOfCategory(){

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/category_list.fxml"));
            Parent root  = fxmlLoader.load();
            Scene dialogScene = new Scene(root);
            dialog.setScene(dialogScene);

        }catch (IOException e){
            System.out.println("Loaded error");
        }

        dialog.show();
    }
    public  void listOfCategory(boolean show){


        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/category_list.fxml"));
            Parent root  = fxmlLoader.load();
            Scene dialogScene = new Scene(root);
            dialog.setScene(dialogScene);

        }catch (IOException e){
            System.out.println("Loaded error");
        }
        if(show)
            dialog.show();
        else
            dialog.hide();
    }
}
