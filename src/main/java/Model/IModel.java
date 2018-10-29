package Model;

public interface IModel {
    // create
    boolean createUser(String username,String password, String birthDate, String firstName, String lastName, String residence);
    // read
    boolean searchUserName(String username0);
    // update
    public boolean updateUser(String username,String password, String firstName, String lastName, String birthDate, String residence);
    //delete
    public boolean deleteUser();

    public User showDetails();

    // login
    boolean login(String username, String password);

    }
