package View;

import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.Optional;

public class createFlightView extends AView {
    //Text fields and buttons in the createFlight.fxml
    public TextField txtfld_from;

    public TextField txtfld_destination;
    public DatePicker dateP_depurtureDate;
    public DatePicker dateP_ReturnDate;
    public TextField txtfld_numOfPass;
    public TextField txtfld_luggageWeight;
    public TextField txtfld_price;

    public Button btn_add;

    public String getDestination(){ return txtfld_destination.getText(); }

    public String getFrom(){ return txtfld_from.getText(); }

    public LocalDate getDepurtureDate(){ return dateP_depurtureDate.getValue(); }

    public LocalDate getReturnDate(){ return dateP_ReturnDate.getValue(); }

    public String getNumOfPass(){
        return txtfld_numOfPass.getText();
    }

    public String getLuggageWeight(){
        return txtfld_luggageWeight.getText();
    }

    public String getPrice(){
        return txtfld_price.getText();
    }

    //handle when press add button
    public void createNewFlight(){
        try{
            //validation of fields

            validateFlightDate(getDepurtureDate());
            validateFlightDate(getReturnDate());
            //if all the flight Dates passed validation, send them to the controller
            if (myController.createFlight(getFrom(),getDestination(),getDepurtureDate().toString(),getReturnDate().toString(),getNumOfPass(),getLuggageWeight(),getPrice())){
                //show alert and return to login view
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("flight was added successfully!");
                alert.setHeaderText(null);
                alert.setContentText("flight was added succesfully");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)
                    loadactionScreen();
            }
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());

        }
    }
    public void loadactionScreen() {
        openNewWindow("/actionScreen.fxml", btn_add, 600, 400);
    }


}
