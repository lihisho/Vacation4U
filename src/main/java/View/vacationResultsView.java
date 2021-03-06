package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import vacationClasses.Vacation;
import Controller.Controller;

import javafx.scene.control.TableView;
import vacationClasses.vacationSearchResultsRec;

import java.net.URL;
import java.util.ResourceBundle;

public class vacationResultsView extends AView implements Initializable{
    private Controller controller = Controller.getInstance();

    public TableView<vacationSearchResultsRec> tableResults;

    public TableColumn<vacationSearchResultsRec, String> from;
    public TableColumn<vacationSearchResultsRec, String> destination;
    public TableColumn<vacationSearchResultsRec, String> departDate;
    public TableColumn<vacationSearchResultsRec, String> returnDate;
    public TableColumn<vacationSearchResultsRec, String> priceCol;
    public TableColumn<vacationSearchResultsRec, String> supplier;
    public TableColumn<vacationSearchResultsRec, String> buyCol;
    public TableColumn<vacationSearchResultsRec, String> detailsCol;
    public Button btn_returnHome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        from.setCellValueFactory(new PropertyValueFactory<>("from"));

        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));

        departDate.setCellValueFactory(new PropertyValueFactory<>("departDate"));

        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        supplier.setCellValueFactory(new PropertyValueFactory<>("supplierUserName"));

        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        detailsCol.setCellValueFactory(new PropertyValueFactory<>("Hyl_ViewFlight"));

        buyCol.setCellValueFactory(new PropertyValueFactory<>("btnBuy"));

        // buycolumn.setCellValueFactory(new PropertyValueFactory<>("btn_buy"));
        ObservableList<Vacation> returnVacations = controller.getReturnVacations();
        ObservableList<vacationSearchResultsRec> data = FXCollections.observableArrayList();
        for (Vacation v : returnVacations) {
            data.add(new vacationSearchResultsRec(v.getVacationID(),v.getDepartureFrom(),v.getDestination(),v.getDepartDate(),v.getReturnDate(),v.getSupplierUserName(),v.getPriceOffered(), new Hyperlink(),new Button()));
        }

        tableResults.setItems(data);


    }

    public void loadActionScreen() {
        openNewWindow("/actionScreen.fxml", btn_returnHome, 600, 350);
    }
}
