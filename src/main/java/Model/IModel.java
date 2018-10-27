package Model;

public interface IModel {
    // create
    void createUser(String username,String password, String birthDate, String firstName, String lastName, String residence);
    // read
    boolean searchUserName(String username0);
    // update
    void updateUser();
    // delete
    void deleteUser();
}
