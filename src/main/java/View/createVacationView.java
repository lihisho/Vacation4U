package View;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class createVacationView extends AView implements Initializable {
    //Text fields and buttons in the createVacation.fxml
    public TextField txtfld_from;
    public TextField txtfld_destination;
    public DatePicker dateP_depurtureDate;
    public DatePicker dateP_ReturnDate;
    public TextField txtfld_airline;
    public RadioButton rb_forExchange;
    public RadioButton rb_forSale;
    public ChoiceBox<String> cb_ticketType;
    public TextField txtfld_numOfPass;
    public CheckBox cb_luggageIncluded;
    public TextField txtfld_luggageWeight;
    public TextField txtfld_originalPrice;
    public TextField txtfld_priceOffered;
    public TextField txtfld_hotelName;
    public TextField txtfld_hotelRank;
    public ChoiceBox cb_vacationType;
    public Button btn_create;
    public Button btn_back;
    public ToggleGroup optionGroup;

    //getters
    public String getDestination(){
        return txtfld_destination.getText(); }

    public String getFrom(){
        return txtfld_from.getText();
    }

    public LocalDate getDepurtureDate(){
        return dateP_depurtureDate.getValue();
    }

    public LocalDate getReturnDate(){
        return dateP_ReturnDate.getValue();
    }

    public String getAirline(){
        return txtfld_airline.getText();
    }

    //check
    public String getTicketType () throws Exception{
        if (cb_ticketType.getValue() == null || cb_ticketType.getValue().toString().isEmpty() ||
                cb_ticketType.getValue().toString().equals("")){
            displayErrorMessage("You must choose ticket type", "Failed");
            throw new Exception();
        }
        return cb_ticketType.getValue().toString();
    }

    public String getNumOfPass(){
        return txtfld_numOfPass.getText();
    }

    public String getLuggageIncluded(){
        if (cb_luggageIncluded.isSelected())
            return "Yes";
        else
            return "No";
    }

    public String getLuggageWeight(){
        if (txtfld_luggageWeight.getText().equals("") || txtfld_luggageWeight.getText().isEmpty())
            return "None";
        return txtfld_luggageWeight.getText();
    }

    public String getOriginPrice(){
        return txtfld_originalPrice.getText();
    }

    public String getPriceOffered(){
        if (txtfld_priceOffered.getText().equals("") || txtfld_priceOffered.getText().isEmpty())
            return "-1";
        return txtfld_priceOffered.getText();
    }

    public String getHotelName(){
        if (txtfld_hotelName.getText().isEmpty() || txtfld_hotelName.getText().equals(""))
            return "None";
        return txtfld_hotelName.getText();
    }

    public String getHotelRank(){
        if (txtfld_hotelRank.getText().isEmpty() || txtfld_hotelRank.getText().equals(""))
            return "None";
        return txtfld_hotelRank.getText();
    }

    public String getVacationType(){
        if (cb_vacationType.getValue() == null || cb_vacationType.getValue().toString().equals("")||
                cb_vacationType.getValue().toString().isEmpty())
            return "None";
        return cb_vacationType.getValue().toString();
    }

    //handle when press add button
    public void createNewVacation(){
        try{
            //validation of fields
            isNotEmpty(getFrom());
            isNotEmpty(getDestination());
            validateVacationDate(getDepurtureDate());
            validateVacationDate(getReturnDate());
            isNotEmpty(getAirline());
            if (!rb_forExchange.isSelected() && !rb_forSale.isSelected()){
                displayErrorMessage("One of the check box 'For sale' or 'For exchange' must be selected.", "Failed");
                throw new Exception();
            }
            isNotEmpty(getTicketType());
            isNotEmpty(getNumOfPass());
            isNotEmpty(getOriginPrice());
            isNotEmpty(getPriceOffered());
            //if all the flight Dates passed validation, send them to the controller
            if (myController.createVacation(getFrom(),getDestination(),getDepurtureDate().toString(),getReturnDate().toString(),
                    getAirline(), getLuggageIncluded(), getLuggageWeight(),getNumOfPass(), getTicketType(), getHotelName(),
                    getHotelRank(), getVacationType(),getOriginPrice(), getPriceOffered())){
                //show alert and return to login view
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vacation was added successfully!");
                alert.setHeaderText(null);
                alert.setContentText("Vacation was added successfully");
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
        openNewWindow("/actionScreen.fxml", btn_create, 600, 400);
    }

    public void handleLuggageIncluded(){
        if(cb_luggageIncluded.isSelected()){
            txtfld_luggageWeight.setDisable(false);
        }
        else {
            txtfld_luggageWeight.clear();
            txtfld_luggageWeight.setDisable(true);
        }
    }

    /**
     * User can choose only one of the options: vacation for sale\ vacation for exchange.
     */
    public void handleSaleOrExchange(){
        if(rb_forSale.isSelected()){
            txtfld_priceOffered.setDisable(false);
            rb_forExchange.setSelected(false);
        }
        if(rb_forExchange.isSelected() ) {
            txtfld_priceOffered.clear();
            txtfld_priceOffered.setDisable(true);
            rb_forSale.setSelected(false);
        }

    }

    @Override
    /**
     * Initialize view with choice box lists.
     * ticket type: infant,child,adult.
     * vacation type: urban, exotic.
     */
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList ticketTypes = FXCollections.observableArrayList();
        ticketTypes.add("Infant");
        ticketTypes.add("Child");
        ticketTypes.add("Adult");
        cb_ticketType.setItems(ticketTypes);

        ObservableList vacationTypes = FXCollections.observableArrayList();
        vacationTypes.add("Urban");
        vacationTypes.add("Exotic");
        cb_vacationType.setItems(vacationTypes);

        optionGroup =new ToggleGroup();
        rb_forSale.setToggleGroup(optionGroup);
        rb_forExchange.setToggleGroup(optionGroup);


    }
}
