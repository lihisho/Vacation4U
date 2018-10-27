package Model;

public interface IModel {
    // create
    boolean createUser(String username,String password, String birthDate, String firstName, String lastName, String residence);
    // read
    boolean searchUserName(String username0);
    // update

}
