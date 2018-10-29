package Controller;

import Model.IModel;

public class Controller {

private IModel myModel;

//constructor
    public Controller(IModel myModel) {this.myModel=myModel;}


    public boolean createNewUser(String username,String password, String birthDate, String firstName, String lastName, String residence){
        return  myModel.createUser(username,password,firstName,lastName,birthDate,residence);
    }

    public boolean searchUserName(String userName){
        return myModel.searchUserName(userName);
    }

}
