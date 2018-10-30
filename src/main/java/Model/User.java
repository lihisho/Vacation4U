package Model;

/**
 * A class that holds all the fields in the DB of a user.
 * We use this Type to hold the current user that login to the system and to
 * send user details to show on the view
 */
public class User {

    //The fields of a user record in the DB
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String birthdate;
    private String residence;

    //constructor
    public User(String username,String password, String firstName, String lastName, String birthDate, String residence){
        this.username=username;
        this.password=password;
        this.firstname=firstName;
        this.lastname=lastName;
        this.birthdate=birthDate;
        this.residence=residence;
    }

    //getters

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getFirstname() { return firstname; }

    public String getLastname() { return lastname; }

    public String getBirthdate() { return birthdate; }

    public String getResidence() { return residence; }
}
