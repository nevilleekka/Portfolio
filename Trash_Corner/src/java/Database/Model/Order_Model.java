package Database.Model;

/**
 * @author Neville Ekka
 * Assignment 2
 * This is simply a format for data to be stored in database
 */
public class Order_Model {

    private String OrderID;
    private String UserID;
    private int Boxes;
    private int Desks;
    private int Chairs;

    public Order_Model(String OrderID,String UserID, int Boxes, int Desks, int Chairs) {
        this.OrderID = OrderID;
        this.UserID = UserID;
        this.Boxes = Boxes;
        this.Desks = Desks;
        this.Chairs = Chairs;

    }

    public Order_Model() {
    }

    public String get_OrderID() {
        return this.OrderID;
    }

    public void set_OrderID(String OrderID) {
        this.OrderID= OrderID;
    }

    public String get_UserID() {
        return this.UserID;
    }

    public void set_UserID (String UserID) {
        this.UserID= UserID;
    }

    public int get_Boxes() {
        return this.Boxes;
    }

    public void set_Boxes(int Boxes) {
        this.Boxes = Boxes;
    }

    public int get_Chairs() {
        return this.Chairs;
    }

    public void set_Chairs(int Chairs) {
        this.Chairs = Chairs;
    }

    public int get_Desks() {
        return this.Desks;
    }
    public void set_Desks(int Desks) {
        this.Desks = Desks;
    }


}
