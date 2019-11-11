
import controllers.MainXMLController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App  extends Application {

    public void start(Stage primaryStage){

        try{

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.xml.fxml"));

            Parent root = fxmlLoader.load();

            MainXMLController mainXMLController = fxmlLoader.getController();
            mainXMLController.setMainController(mainXMLController);
            mainXMLController.setPrimaryStage(primaryStage);

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);

            primaryStage.show();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String args){
        Application.launch(args);
    }
}
