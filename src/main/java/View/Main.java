package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application {

    public Main(){

    }
    public static void main(String[] args) {launch(args);}

    public void start (Stage primaryStage) throws Exception{
        primaryStage.setTitle("Vacation4U");
        FXMLLoader fxmlLoader = new FXMLLoader();
        InputStream is= this.getClass().getResource("/LoginScreen.fxml").openStream();
        Parent root = (Parent)fxmlLoader.load(is);
        Scene Scene = new Scene(root, 600.0D, 400.0D);
        primaryStage.setScene(Scene);
        primaryStage.show();
    }
}