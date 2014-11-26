package Serverlets;

import Database.Connection;
import Database.Database_Access_Object;
import Database.Model.User_Model;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Neville Ekka
 * Assignment 2
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        //Below code is used by Retry login button in registration page
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        String url = "/index.jsp";
        User_Model user = check_login(request.getParameter("firstName"),
                request.getParameter("lastName"));
        if (user != null) {
            url = "user/user_panel.jsp"; //if login True 
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        } else {
            url = "user/register.jsp";
        }//if login false
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
        System.out.println(url);
    }

    private void alert_message(HttpServletResponse response,
            String message)
            throws IOException {//injects HTML code to generate alert
        PrintWriter out = response.getWriter();
        out.write("<script> $( document ).ready(function() { alert( \""
                + message + "\" ) });</script>");
        out.close();
    }

    private User_Model check_login(String firstName, String lastName) {
        try {//code is used to fetch user credentials from database
            Connection.Create_Connection();
            Database_Access_Object users
                    = new Database_Access_Object("useraddress");
            String[] key = {"fName", "sName"};
            String[] val = {firstName, lastName};
            users.select_by_multiple(key, val);
            // users.select_all();
            if (users.get_Result() == null) {
                return null;
            } else {
                users.get_Result().first();
                System.out.println("RESULT" + users.get_Result().getRow());
                User_Model current_user = new User_Model();
                current_user.set_UserID(users.get_Result().getString(1));
               current_user.set_FirstName(users.get_Result().getString(2));
                current_user.set_LastName(users.get_Result().getString(3));
                current_user.set_Address(users.get_Result().getString(4));
                current_user.set_City(users.get_Result().getString(5));
                return current_user; // return user object
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,
                    null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return null;
    }
}
