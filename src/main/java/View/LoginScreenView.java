package View;

import javafx.scene.control.Hyperlink;

import java.awt.*;

public class LoginScreenView {

    public TextField txtfld_username;
    public TextField txtfld_password;
    public Button btn_login;
    public Hyperlink hpl_createUser;
    public Hyperlink hpl_readUser;

    public String getUserName(){
        return  txtfld_username.getText();
    }

    public String getPassword(){
        return  txtfld_password.getText();
    }

}
