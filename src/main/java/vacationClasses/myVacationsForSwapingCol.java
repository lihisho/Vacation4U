package vacationClasses;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;

import java.awt.*;

public class myVacationsForSwapingCol extends Acolumn{
    public String vacationID;
    public String from;
    public String destination;
    public String departDate;
    public String returnDate;
    public CheckBox btn_Choose;

    public myVacationsForSwapingCol(String _vacationID, String _from, String _destination, String _departDate, String _returnDate, CheckBox toSwitch) {
        myController = Controller.getInstance();
        vacationID = _vacationID;
        from = _from;
        destination = _destination;
        departDate = _departDate;
        returnDate = _returnDate;
        btn_Choose = toSwitch;
    }

    public boolean isLineSelected() {
        return btn_Choose.isSelected();
    }


    public String getVacationIDId() {
        return vacationID;
    }

    public void setVacationID(String s) {
        vacationID = s;
    }


    public CheckBox getBtn_Choose() {
        return btn_Choose;
    }

    public void setBtn_Choose(CheckBox b) {
        btn_Choose = b;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String d) {
        destination = d;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String d) {
        from = d;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String d) {
        departDate = d;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String d) {
        returnDate = d;
    }
}

