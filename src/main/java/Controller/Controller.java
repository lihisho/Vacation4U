package Controller;

import Model.IModel;
import Model.User;

/**
 * Class Controller as part of the MVC model.
 * Our controller will move functions from the View to the Model and back from the Model to the View.
 */
public class Controller {
    //private field to connect the Controller to the Model
    private IModel myModel;

    //constructor
    public Controller(IModel myModel) {this.myModel=myModel;}

    /**
     * A function that gets the userName and password from the View and sends them to the Model.
     * The function activates the login function un the Model.
     * @param username - the given userName from the view.
     * @param password - the given password from the view.
     * @return true if the login process pass succesfully, else false.
     */
    public boolean login(String username, String password){
        return myModel.login(username,password);
    }

    /**
     * A function that gets all the needed parameters to create new user from the view, sends them to the Model
     * and activates the create_user function in the Model.
     * @param username - the given userName from the view.
     * @param password - the given password from the view.
     * @param firstName - the given firstName from the view.
     * @param lastName - the given lastName from the view.
     * @param birthDate - the given birthDate from the view.
     * @param residence - the given residence from the view.
     * @return true if the creation process passed succesfully, else false.
     */
    public boolean createNewUser(String username,String password, String firstName, String lastName, String birthDate, String residence){
        return  myModel.createUser(username,password,firstName,lastName,birthDate,residence);
    }

    /**
     * A function that gets userName from the view and sends it to the Model.
     * The function activates a function in the model that search user record in the DB by the given userName.
     * @param userName - the given userName from the view
     * @return true if the searching process passed succesfully, else false.
     */
    public boolean searchUserName(String userName){
        return myModel.searchUserName(userName);
    }

    /**
     * A function that activates a function in the model that deletes a record in the DB.
     * @return true if the deletion process passed succesfully, else false.
     */
    public boolean deleteUser(){return myModel.deleteUser();}

    /**
     * A function that gets all the needed parameters to update user details from the view, sends them to the Model
     * and activates the update_user_details function in the Model.
     * @param username - the given userName from the view.
     * @param password - the given password from the view.
     * @param firstName - the given firstName from the view.
     * @param lastName - the given lastName from the view.
     * @param birthDate - the given birthDate from the view.
     * @param residence - the given residence from the view.
     * @return true if the update process passed succesfully, else false.
     */
    public boolean updateUser(String username,String password, String firstName, String lastName, String birthDate, String residence){
        return myModel.updateUser(username,password,firstName,lastName,birthDate,residence);
    }

    /**
     * A function that activates a reading record from the DB (in the Model) by a given userName from the view.
     * @param userName - a given userName from the view. If userName is null, the model will show the details of the current user
     * @return - A UserType that holds all the details to show.
     */
    public User showDetails(String userName){ return myModel.showDetails(null);}

    /**
     *
     * @param userToSearch
     * @return
     */
    public User searchAndReadUser(String userToSearch){ return myModel.searchAndReadUser(userToSearch);}
}
