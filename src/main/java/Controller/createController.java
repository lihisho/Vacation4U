package Controller;

import Model.IModel;
import View.createView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class createController{
    private createView theView;
    private IModel theModel;

    public createController(createView theView, IModel theModel){
        this.theView=theView;
        this.theModel=theModel;
    }

    public void createNewUser(){
        //validation of fields
        try{
            validateUserName(theView.getNewUsername());
            validatePassword(theView.getNewPassword(),theView.getPasswordConfirmation());
            validateDateOfBirth(theView.getDateOfBirth());
            validateFullName(theView.getPrivateName(),theView.getLastName());
            validateResidence(theView.getResidence());
            theModel.createUser(theView.getNewUsername(),theView.getNewPassword(),convertDateToString(theView.getDateOfBirth()),
                    theView.getPrivateName(),theView.getLastName(),theView.getResidence());
        }
        catch(Exception exception){

        }

    }

    private void validateUserName(String userNameInput)throws Exception {
        //check if the string is not empty
        isNotEmpty(userNameInput);
        //spaces are not allowed in the user name.
        if (userNameInput.contains(" ")) {
            theView.displayErrorMessage("Username can not contain spaces. Please enter a new name");
            throw new Exception();
        }
        //check whether the given username already exist in database
        if (theModel.searchUserName(userNameInput)) {
            theView.displayErrorMessage("The given username already exist. Please choose another username");
            throw new Exception();
        }

    }

    private void validatePassword(String password, String confirmationPassword)throws Exception{
        //check if the string is not empty
        isNotEmpty(password);
        isNotEmpty(confirmationPassword);
        //password can not contain spaces
        if( password.contains(" ")){
            theView.displayErrorMessage("Password can not contain spaces. Please enter a new password");
            throw new Exception();
        }
        //both given passwords must be identical for confirmation
        if( !password.equals(confirmationPassword))
        {
            theView.displayErrorMessage("Password confirmation was denied. Please make sure you enter an identical password");
            throw new Exception();
        }
    }

    private String convertDateToString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private void validateDateOfBirth(LocalDate dateOfBirth)throws Exception{
        isNotEmpty(convertDateToString(dateOfBirth));
        //validate that the user is above the age of 18
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(currentDate, dateOfBirth);
        if (period.getYears()< 18 ){
            theView.displayErrorMessage("Only users above 18 can use this app.");
            throw new Exception();
        }
    }

    private void validateFullName (String privaeName, String lastName) throws Exception{
        //check if the string is not empty
        isNotEmpty(privaeName);
        isNotEmpty(lastName);
        if (!isAlpha(privaeName)){
            theView.displayErrorMessage("Private name can contain letters only.");
            throw new Exception();
        }
        if (!isAlpha(lastName)){
            theView.displayErrorMessage("Last name can contain letters only.");
            throw new Exception();
        }
    }

    private void validateResidence (String givenResidence)throws Exception{
        //check if the string is not empty
        isNotEmpty(givenResidence);
        if (!isAlpha(givenResidence)){
            theView.displayErrorMessage("Residence name can contain letters only.");
            throw new Exception();
        }
    }

    //private function which checks whether a given string containes letters only.
    private boolean isAlpha(String name){
        char[] chars = name.toCharArray();
        for (char c: chars) {
          if (!Character.isLetter(c)) {
              return false;
          }
        }
        return true;
    }

    private void isNotEmpty (String input) throws Exception{
        if (input.equals("")){
            theView.displayErrorMessage("All fields must be filled.");
            throw new Exception();
        }
    }
}
