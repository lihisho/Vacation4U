package View;

import Controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Optional;

public class actionScreenView extends AView {

    public Button btn_Update;
    public Button btn_Delete;
    public Button btn_Search;


    public boolean deleteConfirmationMessage(String alertMessage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText(alertMessage);

        Optional <ButtonType> result = alert.showAndWait();
        if( result.get()  == ButtonType.OK)
            return true;
        else
            return false;
    }

    /**
     * Delete the current user connected to the system.
     */
    public void deleteUser(){
        if(deleteConfirmationMessage("Are you sure you want to delete your User?"))
            myController.deleteUser();

        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            InputStream is = this.getClass().getResource("/login.fxml").openStream();
            Parent loginScreen = fxmlLoader.load(is);
            AView loginView = fxmlLoader.getController();
            loginView.setMyController(this.myController);
            Scene newScene = new Scene(loginScreen, 600, 400);
            Stage curStage = (Stage) btn_Update.getScene().getWindow();
            curStage.setScene(newScene);
            curStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * load
     */
    public void loadUpdateForm(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            InputStream is= this.getClass().getResource("/update.fxml").openStream();
            Parent updateForm = fxmlLoader.load(is);
            updateView update =fxmlLoader.getController();
            update.setMyController(this.myController);
            Scene newScene = new Scene(updateForm,400,400);
            Stage curStage = (Stage) btn_Update.getScene().getWindow();
            curStage.setScene(newScene);
            update.setValues();
            curStage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadSearchForm(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{/*
            InputStream is= this.getClass().getResource("/update.fxml").openStream();
            Parent SearchForm = fxmlLoader.load(is);
            AView updateView =fxmlLoader.getController();
            updateView.setMyController(this.myController);
            Scene newScene = new Scene(SearchForm,400,350);
            Stage curStage = (Stage) btn_Update.getScene().getWindow();
            curStage.setScene(newScene);
            curStage.show();
*/
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
