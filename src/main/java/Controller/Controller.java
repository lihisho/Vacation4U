package Controller;

import Model.IModel;
import Model.User;

public class Controller {

private IModel myModel;

    //constructor
    public Controller(IModel myModel) {this.myModel=myModel;}

    public boolean login(String username, String password){
        return myModel.login(username,password);
    }

    public boolean createNewUser(String username,String password, String firstName, String lastName, String birthDate, String residence){
        return  myModel.createUser(username,password,firstName,lastName,birthDate,residence);
    }

    public boolean searchUserName(String userName){
        return myModel.searchUserName(userName);
    }
    public boolean deleteUser(){return myModel.deleteUser();}
    public boolean updateUser(String username,String password, String firstName, String lastName, String birthDate, String residence){
        return myModel.updateUser(username,password,firstName,lastName,birthDate,residence);
    }

    public User showDetails(){
        return myModel.showDetails();

    }
}
