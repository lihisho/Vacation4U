package View;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import vacationClasses.Vacation;
import vacationClasses.swapSearchResultsRec;

import java.net.URL;
import java.util.ResourceBundle;

public class swapVacationSearchResultsView extends AView implements Initializable{
    private Controller controller = Controller.getInstance();

    public TableView<swapSearchResultsRec> tableResults;

    public TableColumn<swapSearchResultsRec, String> from;
    public TableColumn<swapSearchResultsRec, String> destination;
    public TableColumn<swapSearchResultsRec, String> departDate;
    public TableColumn<swapSearchResultsRec, String> returnDate;
    public TableColumn<swapSearchResultsRec, String> supplier;
    public TableColumn<swapSearchResultsRec, String> switchCol;
    public TableColumn<swapSearchResultsRec, String> detailsCol; //TODO: lihi changed to hyperlink
    public Button btn_returnHome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        from.setCellValueFactory(new PropertyValueFactory<>("from"));

        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));

        departDate.setCellValueFactory(new PropertyValueFactory<>("departDate"));

        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        supplier.setCellValueFactory(new PropertyValueFactory<>("supplierUserName"));

        detailsCol.setCellValueFactory(new PropertyValueFactory<>("Hyl_ViewVaction"));

        switchCol.setCellValueFactory(new PropertyValueFactory<>("btnSwitch"));

        ObservableList<Vacation> returnVacation = controller.getReturnVacations();

        ObservableList<swapSearchResultsRec> data = FXCollections.observableArrayList();

        for (Vacation v : returnVacation) {

                data.add(new swapSearchResultsRec(v.getVacationID(),v.getDepartureFrom(),v.getDestination(),v.getDepartDate(),v.getReturnDate(),v.getSupplierUserName(), new Hyperlink(),new Button()));
        }
        tableResults.setItems(data);
    }

    public void loadActionScreen() {
        openNewWindow("/actionScreen.fxml", btn_returnHome, 600, 450);
    }
}
