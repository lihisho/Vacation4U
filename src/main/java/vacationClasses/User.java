package vacationClasses;

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
    private String email;
    private String phoneNumber;
    public User(){
        username=null;
        password=null;
        firstname=null;
        lastname=null;
        birthdate=null;
        residence=null;
        email=null;
        phoneNumber=null;
    }

    //constructor
    public User(String username,String password, String firstName, String lastName, String birthDate, String residence,String phoneNumber,String email){
        this.username=username;
        this.password=password;
        this.firstname=firstName;
        this.lastname=lastName;
        this.birthdate=birthDate;
        this.residence=residence;
        this.email=email;
        this.phoneNumber=phoneNumber;

    }

    //getters

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getFirstname() { return firstname; }

    public String getLastname() { return lastname; }

    public String getBirthdate() { return birthdate; }

    public String getResidence() { return residence; }

    public String getEmail() { return email; }

    public String getPhoneNumber() { return phoneNumber; }

    //setters
    public void setUsername(String username){this .username=username;}
    public void setPassword(String s){this .password=s;}
    public void setFirstname(String s){this .firstname=s;}
    public void setLastname(String s){this .lastname=s;}
    public void setBirthdate(String s){this .birthdate=s;}
    public void setResidence(String s){this .residence=s;}
    public void setEmail(String s){this .email=s;}
    public void setPhoneNumber(String s){this .phoneNumber=s;}



}
