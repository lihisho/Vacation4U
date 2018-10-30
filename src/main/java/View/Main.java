package View;

import Controller.Controller;
import Model.MyModel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.InputStream;
import java.util.Optional;

public class Main extends Application {

    public static void main(String[] args) {launch(args);}

    public void start (Stage primaryStage) throws Exception{
        primaryStage.setTitle("Vacation4U");
        FXMLLoader fxmlLoader = new FXMLLoader();
        InputStream is= this.getClass().getResource("/Login.fxml").openStream();
        Parent root = (Parent)fxmlLoader.load(is);
        Scene Scene = new Scene(root, 600.0D, 400.0D);
        primaryStage.setScene(Scene);
        MyModel myModel=new MyModel();
        Controller myController = new Controller(myModel);
        AView view=fxmlLoader.getController();
        view.setMyController(myController);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exit");
                alert.setContentText("Are you sure that you want to exit?");
                alert.setHeaderText(null);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    myModel.disconnect();
                }
                else if (result.get() == ButtonType.CANCEL){
                    alert.close();
                    event.consume();
                    System.out.println("here?");
                }
            }
        });
    }
}