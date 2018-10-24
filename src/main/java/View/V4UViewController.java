package View;

import Model.MyModel;
import javafx.scene.control.Alert;

public class V4UViewController {

    private MyModel model;
    private V4UDisplayer appDisplayer;


    //Constructor

    public V4UViewController (MyModel model)
    {
        this.model = model;
    }


    //Allerts

    private void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.show();
    }


}
