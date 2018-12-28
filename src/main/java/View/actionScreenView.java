package View;

import Controller.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import vacationClasses.purchaseRequest;
import vacationClasses.swapRequest;

import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * In this view, a logged user can choose one of three available actions: update his details, delete his user or search
 * details of another user.
 */
public class actionScreenView extends AView implements Initializable {

    public Button btn_Update;
    public Button btn_Delete;
    public Button btn_Search;
    public Button btn_returnHome;
    public Button btn_addFlight;
    public Button btn_searchVacation;
    public Button btn_viewMyReq;
    public Button btn_requestForApprove;
    public Button btn_swapRequestForApprove;
    public Button btn_mySwapRequests;

    public Label lbl_currUserName; //LIHI

//TODO: check if shows.

    /**
     * Shows a confirmation message to the user, following the request for deletion.
     *
     * @param alertMessage - the message shown to the user during the confirmation process.
     * @return true- if the user confirms the deletion of his user, otherwise-false.
     */
    public boolean deleteConfirmationMessage(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText(alertMessage);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) return true;
        else return false;
    }

    /**
     * Delete the current user connected to the system.
     */
    public void deleteUser() {
        if (deleteConfirmationMessage("Are you sure you want to delete your User?")) {
            myController.deleteUser();
            openNewWindow("/login.fxml", btn_Update, 500, 300);
        }
    }

    /**
     * Load a new Update form showing the user's details.
     */
    public void loadUpdateForm() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            InputStream is = this.getClass().getResource("/update.fxml").openStream();
            Parent updateForm = fxmlLoader.load(is);
            updateView update = fxmlLoader.getController();
            update.setMyController(this.myController);
            Scene newScene = new Scene(updateForm, 500, 600);
            newScene.getStylesheets().add(loginView.class.getResource("/actions.css").toExternalForm());
            Stage curStage = (Stage) btn_Update.getScene().getWindow();
            curStage.setScene(newScene);
            update.setValues();
            curStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Switch to the search window after a request for search is activated.
     */
    public void loadSearchForm() {
        openNewWindow("/searchUserAction.fxml", btn_Update, 600, 400);
    }

    /**
     * loads the login screen after a request to return to the home page is invoked.
     */
    public void loadLoginScreen() {
        openNewWindow("/login.fxml", btn_returnHome, 500, 300);
    }

    public void loadSearchVacation() {
        openNewWindow("/searchVacation.fxml", btn_searchVacation, 450, 300);
    }

    public void loadCreateVaction() {
        openNewWindow("/createVacation.fxml", btn_addFlight, 700, 600);
    }

    public void getMyPurchaseReq() {
        try {
            ObservableList<purchaseRequest> returnedPurchase = myController.getMyRequest();
            myController.setMyRequest(returnedPurchase);
            if (returnedPurchase.size() == 0)
                displayInformationMessage("Sorry, no vacation was found", "Search returned with 0 results");
            else {

                FXMLLoader fxmlLoader = new FXMLLoader();
                InputStream is = this.getClass().getResource("/myPurchaseRequests.fxml").openStream();
                Parent actionScreen = fxmlLoader.load(is);
                AView myPurchaseRequestView = fxmlLoader.getController();
                Scene newScene = new Scene(actionScreen, 700, 400);
                newScene.getStylesheets().add(loginView.class.getResource("/actions.css").toExternalForm());
                Stage curStage = (Stage) btn_viewMyReq.getScene().getWindow();
                curStage.setScene(newScene);
                curStage.show();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getRequestForSeller() {
        try {
            ObservableList<purchaseRequest> returnedPurchase = myController.requestForSeller();
            myController.setSellerRequest(returnedPurchase);
            if (returnedPurchase.size() == 0)
                displayInformationMessage("Sorry, no vacation was found", "Search returned with 0 results");
            else {

                FXMLLoader fxmlLoader = new FXMLLoader();
                InputStream is = this.getClass().getResource("/myRequestsForSeller.fxml").openStream();
                Parent actionScreen = fxmlLoader.load(is);
                AView myPurchaseRequestView = fxmlLoader.getController();
                //flightResults.setMyController(this.myController);
                Scene newScene = new Scene(actionScreen, 800, 400);
                newScene.getStylesheets().add(loginView.class.getResource("/actions.css").toExternalForm());
                Stage curStage = (Stage) btn_requestForApprove.getScene().getWindow();
                curStage.setScene(newScene);
                curStage.show();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    public void getSwapRequestsForSeller() {
        try {
            ObservableList<swapRequest> returnedSwapRequests = myController.swapRequestsForSeller();
            myController.setSellerSwapRequests(returnedSwapRequests);
            if (returnedSwapRequests == null)
                displayInformationMessage("Sorry, no vacation was found", "Search returned with 0 results");
            else {

                FXMLLoader fxmlLoader = new FXMLLoader();
                InputStream is = this.getClass().getResource("/swapRequestsForSeller.fxml").openStream();
                Parent actionScreen = fxmlLoader.load(is);
                AView mySwapRequestsView = fxmlLoader.getController();
                Scene newScene = new Scene(actionScreen, 1000, 400);
                newScene.getStylesheets().add(loginView.class.getResource("/actions.css").toExternalForm());
                Stage curStage = (Stage) btn_requestForApprove.getScene().getWindow();
                curStage.setScene(newScene);
                curStage.show();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("Duplicates")
    public void getMyHandedSwapRequests() {
        try {
            ObservableList<swapRequest> returnedSwapRequests = myController.myHandedSwapRequests();
            myController.setMySwapRequests(returnedSwapRequests);
            if (returnedSwapRequests == null)
                displayInformationMessage("Sorry, no vacation was found", "Search returned with 0 results");
            else {

                FXMLLoader fxmlLoader = new FXMLLoader();
                InputStream is = this.getClass().getResource("/myHandedSwapRequests.fxml").openStream();
                Parent actionScreen = fxmlLoader.load(is);
                Scene newScene = new Scene(actionScreen, 900, 400);
                newScene.getStylesheets().add(loginView.class.getResource("/actions.css").toExternalForm());
                Stage curStage = (Stage) btn_requestForApprove.getScene().getWindow();
                curStage.setScene(newScene);
                curStage.show();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Controller myController = new Controller();
        String currUserName = myController.getCurrUserName();
        lbl_currUserName.setText("Hi " + currUserName + ", what would you like to do?");
    }
}

