package vacationClasses;

import Controller.Controller;
import View.AView;
import View.actionScreenView;
import View.updateView;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.InputStream;


public class exchangeSearchResultColumn extends Acolumn {
    public String vacationId;
    public String from;
    public String destination;
    public String departDate;
    public String returnDate;
    public String supplierUserName;
    public Button btnSwitch;
    private Hyperlink Hyl_ViewVaction;
    public String vacationIdToExchange;


    public exchangeSearchResultColumn(String _vacationID, String _from, String _destination, String _departDate, String _returnDate, String _supplierUserName, Hyperlink viewB, Button _switch){
        myController=Controller.getInstance();
        vacationId=_vacationID;
        from=_from;
        destination=_destination;
        departDate=_departDate;
        returnDate=_returnDate;
        supplierUserName=_supplierUserName;
        btnSwitch=_switch;
        btnSwitch.setText("swap");
        Hyl_ViewVaction=viewB;
        Hyl_ViewVaction.setText("vacation details");

        //handle when choose switch
        btnSwitch.setOnAction(event -> {
            ObservableList<Vacation> returnedVacations = myController.getMyVacationToSwitch();
            myController.setReturnedMyVacations(returnedVacations);
            if(returnedVacations==null||returnedVacations.size()==0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("You must create a vacation to exchange before trying to switch one.");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            else {
                loadMyVacToExchangeResult();
                if(!myController.getVacationToSwap().equals("")){
                    myController.createSwapRequest(vacationId,supplierUserName,myController.getVacationToSwap());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setContentText("Purchase order was sent successfully");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            }
        });

        Hyl_ViewVaction.setOnAction(event -> {
//TODO: needs to check how we get this detail from the table + change from "3"
            String VacationIDForShowingDetails = vacationId;
            Vacation vacationReturned= myController.showVacationDetails(vacationId);
            if(VacationIDForShowingDetails == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search returned with 0 results");
                alert.setContentText("System can not show flight details.");
            }
            else {
                showVacToSwapDetails(vacationReturned);

            }        });
    }

    public String getVacationId(){
        return vacationId;
    }

    public void setVacationId(String s){
        vacationId=s;
    }

    public Button getBtnSwitch(){
        return btnSwitch;
    }

    public void setBtnSwitch(Button b){
        btnSwitch=b;
    }

    public String getDestination(){
        return destination;
    }

    public void setDestination(String d){
        destination=d;
    }

    public Hyperlink getHyl_ViewVaction(){
        return Hyl_ViewVaction;
    }

    public void setHyl_ViewVaction(Hyperlink d){
        Hyl_ViewVaction=d;
    }

    public String getFrom(){
        return from;
    }

    public void setFrom(String d){
        from=d;
    }

    public String getDepartDate(){
        return departDate;
    }

    public void setDepartDate(String d){
        departDate=d;
    }

    public String getReturnDate(){
        return returnDate;
    }

    public void setReturnDate(String d){
        returnDate=d;
    }

    public String getSupplierUserName(){
        return supplierUserName;
    }

    public void setSupplierUserName(String d){
        supplierUserName=d;
    }

    public void loadMyVacToExchangeResult(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try{
            InputStream is= this.getClass().getResource("/myVacToExchange.fxml").openStream();
            Parent parent = fxmlLoader.load(is);
            AView update =fxmlLoader.getController();
            update.setMyController(this.myController);
            Scene newScene = new Scene(parent,600,400);
            newScene.getStylesheets().add(actionScreenView.class.getResource("/actions.css").toExternalForm());

            Stage curStage = new Stage();
            curStage.setScene(newScene);
            curStage.showAndWait();//Tal- maybe show and wait????

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



}
