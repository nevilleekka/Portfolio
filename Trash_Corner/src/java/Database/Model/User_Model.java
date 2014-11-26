package Database.Model;
/**
 * @author Neville Ekka
 * Assignment 2
 * This is simply a format for data to be stored in database
 */
public class User_Model {

    private String UserID;
    private String FirstName;
    private String LastName;
    private String Address;
    private String City;

    public User_Model(String UserID,String FirstName, String LastName,
            String Address, String City) {
        this.UserID = UserID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Address = Address;
        this.City = City;

    }

    public User_Model() {
    }

    public String get_UserID() {
        return this.UserID;
    }

    public void set_UserID(String UserID) {
        this.UserID = UserID;
    }
    
    public String get_FirstName() {
        return this.FirstName;
    }

    public void set_FirstName(String name) {
        this.FirstName= name;
    }

    public String get_LastName() {
        return this.LastName;
    }

    public void set_LastName (String name) {
        this.LastName= name;
    }

    public String get_Address() {
        return this.Address;
    }

    public void set_Address(String Address) {
        this.Address = Address;
    }

    public String get_City() {
        return this.City;
    }
    
    public void set_City(String City) {
        this.City = City;
    }


}
