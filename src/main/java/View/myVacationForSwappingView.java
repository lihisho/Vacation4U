package View;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vacationClasses.*;

import java.net.URL;
import java.util.ResourceBundle;

public class myVacationForSwappingView extends AView implements Initializable {
    private Controller controller = Controller.getInstance();
    public TableView<myVacationsForSwapingRec> tableResults;
    public TableColumn<vacationSearchResultsRec, String> from;
    public TableColumn<vacationSearchResultsRec, String> destination;
    public TableColumn<vacationSearchResultsRec, String> departDate;
    public TableColumn<vacationSearchResultsRec, String> returnDate;
    public TableColumn<vacationSearchResultsRec, String> chooseCol;
    public Button btn_Choose;
    public ObservableList<myVacationsForSwapingRec> data;



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

            data.add(new myVacationsForSwapingRec(v.getVacationID(),v.getDepartureFrom(),v.getDestination(),v.getDepartDate(),v.getReturnDate(),new CheckBox()));
        }

        tableResults.setItems(data);

    }
    //when press btn_choose
    public void handleButtonChooseMyVac(){
        String myVactionIDToChange="";
        for(myVacationsForSwapingRec v: data){

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
