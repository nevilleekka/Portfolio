package Serverlets;

import Database.Connection;
import Database.Database_Access_Object;
import Database.Model.Order_Model;
import Database.Model.User_Model;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Neville Ekka Assignment 2 For UserPanel section
 */
@WebServlet(urlPatterns = {"/user_panel"})
public class user_panel extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //signout

        switch (request.getParameter("user_form")) {
            case "signout":

                request.getSession().removeAttribute("user");
                try {
                    Connection.Close_Connection();
                } catch (SQLException ex) {
                    Logger.getLogger(user_panel.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
                RequestDispatcher rd = request.
                        getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                break;

            case "change":
                try {//Update user info
                    Database_Access_Object user_db
                            = new Database_Access_Object("useraddress");
                    User_Model current_user
                            = (User_Model) request.getSession()
                            .getAttribute("user");
                    current_user.set_Address(request.getParameter("address"));
                    current_user.set_City(request.getParameter("city"));
                    user_db.update_into(current_user);
                } catch (SQLException ex) {
                    Logger.getLogger(user_panel.class.getName()).
                            log(Level.SEVERE, null, ex);
                }

                break;

// If clicked 
            case "modify":
                try {
                    Database_Access_Object order_db
                            = new Database_Access_Object("userorder");
                    order_db.update_into(new Order_Model(
                            request.getParameter("order_id"),
                            "",
                            Integer.parseInt(request.getParameter("boxAmount")),
                            Integer.parseInt(request.getParameter("deskAmount")),
                            Integer.parseInt(request.getParameter("chairAmount"))
                    ));
                } catch (SQLException ex) {
                    Logger.getLogger(user_panel.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            break;
            case "delete":
                try {
                    Database_Access_Object order_db
                            = new Database_Access_Object("userorder");
                    order_db.delete_order("OID",
                            request.getParameter("order_id"));
                } catch (SQLException ex) {
                    Logger.getLogger(user_panel.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
                break;
            //if form has value "create" retrieved from button
            case "create":
                if (request.getSession().getServletContext().
                        getAttribute("order_count") == null) {
                    request.getSession().getServletContext().
                            setAttribute("order_count", 29);
                }
                try {//create new order count to keep track of last order No.
                    Database_Access_Object order_db
                            = new Database_Access_Object("userorder");
                    int order_id = (int) request.getSession()
                            .getServletContext()
                            .getAttribute("order_count");
                    order_id = order_id + 1;
                    request.getSession().getServletContext()
                            .setAttribute("order_count", order_id);

                    order_db.insert_Order(new Order_Model("O" + order_id,
                            ((User_Model) request.getSession()
                            .getAttribute("user")).get_UserID(),
                            Integer.parseInt(request.getParameter("boxAmount")),
                            Integer.parseInt(request.getParameter("deskAmount")),
                            Integer.parseInt(request.getParameter("chairAmount"))
                    ));

                } catch (SQLException ex) {
                    Logger.getLogger(user_panel.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
                break;

        }
        RequestDispatcher rd = //by default refresh current page
                request.getRequestDispatcher("user/user_panel.jsp");
        rd.forward(request, response);
    }

    public static Order_Model[] get_order(User_Model user) {
        try { //retrives order from database
            Connection.Create_Connection();
            Database_Access_Object order
                    = new Database_Access_Object("userorder");
            order.select_by("uID", user.get_UserID());
            Vector<Order_Model> order_list = new Vector<Order_Model>();
            if (order.get_Result() != null) {
                while (order.get_Result().next()) {
                    Order_Model current_order = new Order_Model();
                    current_order.set_OrderID(order.get_Result().getString(1));
                    current_order.set_UserID(order.get_Result().getString(2));
                    current_order.set_Boxes(order.get_Result().getInt(3));
                    current_order.set_Desks(order.get_Result().getInt(4));
                    current_order.set_Chairs(order.get_Result().getInt(5));
                    order_list.add(current_order);
                }
                Order_Model[] final_order_list
                        = new Order_Model[order_list.size()];
                order_list.toArray(final_order_list);//return as array
                return final_order_list;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName())
                    .log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
