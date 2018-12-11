package View;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import vacationClasses.purchaseRequest;

import java.io.InputStream;
import java.util.Optional;

/**
 * In this view, a logged user can choose one of three available actions: update his details, delete his user or search
 * details of another user.
 */
public class actionScreenView extends AView {

    public Button btn_Update;
    public Button btn_Delete;
    public Button btn_Search;
    public Button btn_returnHome;
    public Button btn_addFlight;//Tal
    public Button btn_searchFlight;
    public Button btn_viewMyReq;
    public Button btn_requestForApprove;
    //Yafit
    public Button btn_myPurchasingRequest;


    /**
     * Shows a confirmation message to the user, following the request for deletion.
     * @param alertMessage - the message shown to the user during the confirmation process.
     * @return true- if the user confirms the deletion of his user, otherwise-false.
     */
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
        if (deleteConfirmationMessage("Are you sure you want to delete your User?")) {
            myController.deleteUser();
            openNewWindow("/login.fxml", btn_Update, 500, 400);
        }
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

    /**
     * Switch to the search window after a request for search is activated.
     */
    public void loadSearchForm() {
        openNewWindow("/searchUserAction.fxml", btn_Update,600,400);
    }

    /**
     * loads the login screen after a request to return to the home page is invoked.
     */
    public void loadLoginScreen(){
        openNewWindow("/login.fxml", btn_returnHome, 500,400);
    }
//Yafit
    public void loadMyPurchaseRequestsScreen() {openNewWindow("/myPurchaseRequests.fxml", btn_myPurchasingRequest, 500, 400);}
//Tal
    public void loadSearchFlight(){openNewWindow("/searchFlight.fxml",btn_searchFlight,600,400);}

    public void loadCreateFlight(){openNewWindow("/createFlight.fxml",btn_addFlight,600,400);}
    public void getMyPurchaseReq(){
        try {
            ObservableList<purchaseRequest> returnedPurchase = myController.getMyRequest();
            myController.setMyRequest(returnedPurchase);
            if (returnedPurchase == null)
                displayInformationMessage("Sorry, no flight was found", "Search returned with 0 results");
            else {

                FXMLLoader fxmlLoader = new FXMLLoader();
                InputStream is = this.getClass().getResource("/myPurchaseRequests.fxml").openStream();
                Parent actionScreen = fxmlLoader.load(is);
                AView myPurchaseRequestView = fxmlLoader.getController();
                //flightResults.setMyController(this.myController);
                Scene newScene = new Scene(actionScreen, 900, 400);
                Stage curStage = (Stage) btn_viewMyReq.getScene().getWindow();
                curStage.setScene(newScene);
                curStage.show();


            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void getRequestForSeller() {
        try {
            ObservableList<purchaseRequest> returnedPurchase = myController.requestForSeller();
            myController.setSellerRequest(returnedPurchase);
            if (returnedPurchase == null)
                displayInformationMessage("Sorry, no flight was found", "Search returned with 0 results");
            else {

                FXMLLoader fxmlLoader = new FXMLLoader();
                InputStream is = this.getClass().getResource("/myPurchaseRequests.fxml").openStream();
                Parent actionScreen = fxmlLoader.load(is);
                AView myPurchaseRequestView = fxmlLoader.getController();
                //flightResults.setMyController(this.myController);
                Scene newScene = new Scene(actionScreen, 900, 400);
                Stage curStage = (Stage) btn_requestForApprove.getScene().getWindow();
                curStage.setScene(newScene);
                curStage.show();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
