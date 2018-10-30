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
    public void deleteUser() {
        if (deleteConfirmationMessage("Are you sure you want to delete your User?"))
            myController.deleteUser();
        openNewWindow("/login.fxml", btn_Update);
    }

    /**
     * Load a new Update form showing the user's details.
     */
    public void loadUpdateForm(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            InputStream is= this.getClass().getResource("/update.fxml").openStream();
            Parent updateForm = fxmlLoader.load(is);
            updateView update =fxmlLoader.getController();
            update.setMyController(this.myController);
            Scene newScene = new Scene(updateForm,500,400);
            Stage curStage = (Stage) btn_Update.getScene().getWindow();
            curStage.setScene(newScene);
            update.setValues();
            curStage.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadSearchForm() {
        openNewWindow("/searchUsers.fxml", btn_Update);
    }


}
