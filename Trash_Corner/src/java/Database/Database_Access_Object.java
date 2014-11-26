package Database;
import Database.Model.Order_Model;
import Database.Model.User_Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
/**
 * @author Neville Ekka
 * Assignment 2
 */
public class Database_Access_Object {

    private String table_name;
    private java.sql.Connection connector;
    private ResultSet result = null;
    private String querry = null;
    //Constructor takes table name to access database
    public Database_Access_Object(String table_name) throws SQLException {
        this.connector = Connection.get_Connector();
        this.table_name = table_name;}
       //Below method gets raw result
    public ResultSet get_Result() throws SQLException {
        return this.result; }
    //Below method gives First row in result
      public ResultSet get_First_Result() throws SQLException {
        if(this.result!=null){ this.result.first();}
        return this.result; }
    //Sets new table
    public void Set_Table(String table) {this.table_name = table;}

    public void select_all() throws SQLException {
        this.querry = "SELECT * FROM " + table_name + " ;";
        System.out.println(querry);
        Execute_Querry();  }
    //Below method inserts New User
    public void insert_User(User_Model userModel) throws SQLException {
        this.querry = "insert into " + table_name 
        + " (uID,fName,sName,Address, City) " + "values (?, ?, ?, ?, ?);";
    Vector m=new Vector(); // This process gives flexibility to querry
           m.add(userModel.get_UserID());
           m.add(userModel.get_FirstName());
           m.add(userModel.get_LastName());
           m.add(userModel.get_Address());
           m.add(userModel.get_City());
        Execute_Update(m); }

    public void insert_Order(Order_Model orderModel) throws SQLException {
        this.querry = "insert into " + table_name + 
               " (oID, uID, Boxes, Desks, Chairs) " + 
                "values (?, ?, ?, ?, ?);";
        Vector m=new Vector();
           m.add(orderModel.get_OrderID());
           m.add(orderModel.get_UserID());
           m.add(orderModel.get_Boxes());
           m.add(orderModel.get_Chairs());
           m.add(orderModel.get_Desks());
        Execute_Update(m); } 
   //Below Selects by coloumn_name and value
    public void select_by(String coloumn_name, String value) 
            throws SQLException {
        this.querry = "SELECT * FROM " + this.table_name + " WHERE " 
                + coloumn_name + " = '" + value + "' ;";
        System.out.println(querry);
        Execute_Querry(); }
    //Below method selects by two comparisons for quick selection
      public void select_by_two(String coloumn_name1, String value1, 
              String coloumn_name2, String value2 ) throws SQLException {
        this.querry = "SELECT * FROM " + this.table_name + " WHERE " 
                + coloumn_name1 + " = " + value1 + " && "+ coloumn_name2 
                + " = "+ value2;
        System.out.println(this.querry);
        Execute_Querry();  }
 //Below method allows user to customize querry by any given comparisons
      public void select_by_multiple(String[] coloumn_name,
              String[] value ) 
              throws SQLException {
        this.querry = "SELECT * FROM " + this.table_name + " WHERE "
                +coloumn_name[0] + " = '" + value[0]+"'" ;
        StringBuilder q=new StringBuilder();      
        for(int i=1;i<coloumn_name.length;i++){ 
                q.append(" && "+coloumn_name[i] + " = '" + value[i]+"'");}
               this.querry=this.querry+q.toString()+" ;";
                System.out.println(querry); 
        Execute_Querry(); }
//Below method updates user by address and city
     public void update_into(User_Model user) throws SQLException {
        String set =  " Address= ?, City= ?";
        String where = " uID= ?";
        this.querry = "UPDATE " + table_name + " SET " + set + " WHERE "
                + where + ";";
        System.out.println(this.querry);
         Vector u=new Vector();
           u.add(user.get_Address());
           u.add(user.get_City());
           u.add(user.get_UserID());
        Execute_Update(u);}
     //Below method deletes order    
      public void update_into(Order_Model order) throws SQLException {
        String set = " Boxes= ? , Desks= ? , Chairs= ? ";
        String where = " oID= ?";
        this.querry = "UPDATE " + table_name + " SET " + set
                + " WHERE " + where + ";";
        System.out.println(this.querry);
        System.out.println("orderID= "+order.get_OrderID());
       Vector o=new Vector();
           o.add(order.get_Boxes());
           o.add(order.get_Desks());
           o.add(order.get_Chairs());
           o.add(order.get_OrderID());
        Execute_Update(o);    }
     //Below method deletes order 
    public void delete_order(String coloumn_name, String value)
            throws SQLException {
        this.querry = "DELETE FROM " + this.table_name +
                " WHERE " + coloumn_name + " = ? ;";
        System.out.println(querry);
        Vector q=new Vector();
           q.add(value);
        Execute_Update(q); }
     //Below method executes querry
    private void Execute_Querry() {
        try {
            PreparedStatement statement 
                    = this.connector.prepareStatement(querry);
            this.result = statement.executeQuery();
           if(!result.isBeforeFirst()){
           System.out.println("Querry Result Empty! ");this.result=null;}
          
        } catch (SQLException e) {
            System.err.println("Unable to connect");
            System.err.println("SQL Error: " + e);
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("SQL Error: " + e.getErrorCode());
        } finally { }
    }
    // Below method us used for updating
    private void Execute_Update(Vector m) {
        try {
          PreparedStatement statement =
                  this.connector.prepareStatement(querry);
       //Below for loop puts in values in querry with "?" as int or string
                for(int i=0;i<m.size();i++){
                    int idx=i+1;
                 if(m.get(i).getClass()==Integer.class)
                 {statement.setInt(idx,(int)m.get(i));}
                 else if(m.get(i).getClass()==String.class)
                 {statement.setString(idx,m.get(i).toString());}  
                }
                System.out.println(statement);
                int rows_affected = statement.executeUpdate();
                System.err.println("Rows affected = " + rows_affected);
        } catch (SQLException e) {
            System.err.println("Unable to connect");
            System.err.println("SQL Error: " + e);
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("SQL Error: " + e.getErrorCode());
        } finally { }
    }
}
