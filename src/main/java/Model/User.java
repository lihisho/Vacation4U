package Model;

public class User {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String birthdate;
    private String residence;

    public User(String username,String password, String firstName, String lastName, String birthDate, String residence){
        this.username=username;
        this.password=password;
        this.firstname=firstName;
        this.lastname=lastName;
        this.birthdate=birthDate;
        this.residence=residence;
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getFirstname() { return firstname; }

    public String getLastname() { return lastname; }

    public String getBirthdate() { return birthdate; }

    public String getResidence() { return residence; }
}
