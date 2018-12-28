package View;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vacationClasses.*;

import javax.swing.text.html.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class myVacToExchangeView extends AView implements Initializable {
    private Controller controller = Controller.getInstance();
    public TableView<myVacToExchangColumn> tableResults;
    public TableColumn<flightSearchColumn, String> from;
    public TableColumn<flightSearchColumn, String> destination;
    public TableColumn<flightSearchColumn, String> departDate;
    public TableColumn<flightSearchColumn, String> returnDate;
    public TableColumn<flightSearchColumn, String> chooseCol;
    public Button btn_Choose;
    public ObservableList<myVacToExchangColumn> data;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        from.setCellValueFactory(new PropertyValueFactory<>("from"));

        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));

        departDate.setCellValueFactory(new PropertyValueFactory<>("departDate"));

        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        chooseCol.setCellValueFactory(new PropertyValueFactory<>("btn_Choose"));

        ObservableList<Vacation> returnVacation = controller.getThisMyVacationToSwitch();

        data = FXCollections.observableArrayList();

        for (Vacation v : returnVacation) {

            data.add(new myVacToExchangColumn(v.getVacationID(),v.getDepartureFrom(),v.getDestination(),v.getDepartDate(),v.getReturnDate(),new CheckBox()));
        }

        tableResults.setItems(data);

    }
    //when press btn_choose
    public void handleButtonChooseMyVac(){
        String myVactionIDToChange="";
        for(myVacToExchangColumn v: data){

            if( v.isLineSelected()){
                myVactionIDToChange=v.vacationID;
                break;
            }
        }
        myController.setVacationToSwap(myVactionIDToChange);
        Stage stage = (Stage) btn_Choose.getScene().getWindow();
        stage.close();
    }



}
