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
import vacationClasses.exchangeSearchResultColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class exchangeVacationResultsView extends AView implements Initializable{
    private Controller controller = Controller.getInstance();

    public TableView<exchangeSearchResultColumn> tableResults;

    public TableColumn<exchangeSearchResultColumn, String> from;
    public TableColumn<exchangeSearchResultColumn, String> destination;
    public TableColumn<exchangeSearchResultColumn, String> departDate;
    public TableColumn<exchangeSearchResultColumn, String> returnDate;
    public TableColumn<exchangeSearchResultColumn, String> supplier;
    public TableColumn<exchangeSearchResultColumn, String> switchCol;
    public TableColumn<exchangeSearchResultColumn, String> detailsCol; //TODO: lihi changed to hyperlink
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

        ObservableList<exchangeSearchResultColumn> data = FXCollections.observableArrayList();

        for (Vacation v : returnVacation) {

                data.add(new exchangeSearchResultColumn(v.getVacationID(),v.getDepartureFrom(),v.getDestination(),v.getDepartDate(),v.getReturnDate(),v.getSupplierUserName(), new Hyperlink(),new Button()));
        }
        tableResults.setItems(data);
    }

    public void loadActionScreen() {
        openNewWindow("/actionScreen.fxml", btn_returnHome, 600, 450);
    }
}
