package View;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vacationClasses.Vacation;

import java.io.InputStream;
import java.time.LocalDate;

public class searchVacationView extends AView{
    public TextField flightDestination;
    public DatePicker departDate;
    public DatePicker returnDate;
    public TextField numOfPassengers;
    public Button btn_search;
    public Button btn_back;
    public CheckBox ckbx_forSwap;
    public CheckBox ckbx_forBuy;


    public void handleSearchButton() {
        try {

            if(flightDestination.getText().equals("") && departDate.getValue()==null && returnDate.getValue()==null && numOfPassengers.getText().equals(""))
            {
                displayErrorMessage("You must fill in one of the field in order to search", "fail");
                throw new Exception();
            }

            //if user enter one of the dates he need to enter them two
            if(departDate.getValue()!=null && returnDate.getValue()==null){
                displayErrorMessage("You must enter return date if you wish to search for depart date", "fail");
                throw new Exception();
            }
            //if user enter one of the dates he need to enter them two
            if(returnDate.getValue()!=null && departDate.getValue()==null){
                displayErrorMessage("You must enter depart date if you wish to search for return date", "fail");
                throw new Exception();
            }
            if(departDate.getValue()!=null){
                if(!departDate.getValue().isAfter(LocalDate.now())){
                    displayErrorMessage("You can search only future vacations", "fail");
                    throw new Exception();}
            }
            if(returnDate.getValue()!=null){
                if(!returnDate.getValue().isAfter(LocalDate.now())){
                    displayErrorMessage("You can search only future vacations", "fail");
                    throw new Exception();}
            }
            if((ckbx_forSwap.isSelected()&&ckbx_forBuy.isSelected())) {
                displayErrorMessage("You can choose only one option to search- search for buy or search for swap.", "fail");
                throw new Exception();
            }
            else if(!ckbx_forSwap.isSelected()&&!ckbx_forBuy.isSelected()){
                displayErrorMessage("You need choose one option to search- search for buy or search for swap.", "fail");
                throw new Exception();
            }
            ObservableList<Vacation> returnedVacation;
            String deparDateToString, returnDateToString;
            if (departDate.getValue() == null || departDate.getValue().toString().isEmpty() || departDate.getValue().toString().equals(""))
                deparDateToString = "";
            else
                deparDateToString = departDate.getValue().toString();
            if (returnDate.getValue() == null || returnDate.getValue().toString().isEmpty() || returnDate.getValue().toString().equals(""))
                returnDateToString = "";
            else
                returnDateToString = returnDate.getValue().toString();

            if(ckbx_forBuy.isSelected())
                returnedVacation = myController.getInstance().searchVacationToBuy(flightDestination.getText(), deparDateToString, returnDateToString, numOfPassengers.getText());
            else returnedVacation = myController.getInstance().searchVacationToSwap(flightDestination.getText(), deparDateToString, returnDateToString, numOfPassengers.getText());

            myController.setReturnVacations(returnedVacation);
            if (returnedVacation == null||returnedVacation.size()==0)
                displayInformationMessage("Sorry, no vacation was found", "Search returned with 0 results");
            else if(ckbx_forBuy.isSelected()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                InputStream is = this.getClass().getResource("/searchFlightResults.fxml").openStream();
                Parent actionScreen = fxmlLoader.load(is);
                AView flightResults = fxmlLoader.getController();
                Scene newScene = new Scene(actionScreen, 700, 300);
                newScene.getStylesheets().add(loginView.class.getResource("/actions.css").toExternalForm());
                Stage curStage = (Stage) btn_search.getScene().getWindow();
                curStage.setScene(newScene);
                curStage.show();


            }else{//show results for swap
                FXMLLoader fxmlLoader = new FXMLLoader();
                InputStream is = this.getClass().getResource("/searchVacToExchangeResults.fxml").openStream();
                Parent actionScreen = fxmlLoader.load(is);
                AView flightResults = fxmlLoader.getController();
                Scene newScene = new Scene(actionScreen, 700, 300);
                newScene.getStylesheets().add(loginView.class.getResource("/actions.css").toExternalForm());
                Stage curStage = (Stage) btn_search.getScene().getWindow();
                curStage.setScene(newScene);
                curStage.show();
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void loadActionScreen() {
        openNewWindow("/actionScreen.fxml", btn_back, 600, 350);
    }



}
