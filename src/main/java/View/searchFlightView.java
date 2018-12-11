package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vacationClasses.flight;
import vacationClasses.User;
import vacationClasses.flight;

import java.io.InputStream;
import java.sql.ResultSet;

public class searchFlightView extends AView{
    public TextField flightDestination;
    public DatePicker departDate;
    public DatePicker returnDate;
    public TextField numOfPassengers;
    public Button btn_search;
    public Button btn_back;




    public void handleSearchButton() {
        // String flightDest = flightDestination.getText();
        // String depDate = departDate.getValue().toString();
        try {
            validateDestination(flightDestination.getText());
            validateFlightDate(departDate.getValue());
            validateFlightDate(returnDate.getValue());

            ObservableList<flight> returnedFlight = myController.searchFlight(flightDestination.getText(), departDate.getValue().toString(), returnDate.getValue().toString(), Integer.parseInt(numOfPassengers.getText()));
            myController.setReturnFlights(returnedFlight);

            if (returnedFlight == null)
                displayInformationMessage("Sorry, no flight was found", "Search returned with 0 results");
            else {

                FXMLLoader fxmlLoader = new FXMLLoader();
                InputStream is = this.getClass().getResource("/searchFlightResults.fxml").openStream();
                Parent actionScreen = fxmlLoader.load(is);
                AView flightResults = fxmlLoader.getController();
                //flightResults.setMyController(this.myController);
                Scene newScene = new Scene(actionScreen, 900, 400);
                Stage curStage = (Stage) btn_search.getScene().getWindow();
                curStage.setScene(newScene);
                //((flightResultsView)flightResults).setResults();
                curStage.show();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadActionScreen() {
        openNewWindow("/actionScreen.fxml", btn_back, 600, 400);
    }



}
