package Serverlets;
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
/**
 * @author Neville Ekka
 * Assignment 2
 * For User Registration Section
 */
@WebServlet(name = "user_register", urlPatterns = {"/user_register"})
public class user_register extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
      // To ensure that unquie user ID is generated every time in database
        if (request.getSession()
               .getServletContext().getAttribute("user_count") == null) {
            request.getSession()
                 .getServletContext().setAttribute("user_count", 109); }
      // To ensure that unquie user ID is generated every time in database
     try {
    Database_Access_Object new_user = 
            new Database_Access_Object("useraddress");
            String[] key = {"fName", "sName"};
            String[] val = {request.getParameter("reg_firstName"),
                request.getParameter("reg_lastName")};
            //Select by mutiple coloumns
            new_user.select_by_multiple(key, val); 
            
            if (new_user.get_First_Result()==null) {
                int user_count = (int) request.getSession().
                        getServletContext().getAttribute("user_count");
                user_count = user_count + 1;//create unique user ID
                request.getSession().
                     getServletContext().
                        setAttribute("user_count", user_count);
                new_user.insert_User(new User_Model("c" + user_count,
                        request.getParameter("reg_firstName"),
                        request.getParameter("reg_lastName"),
                        request.getParameter("reg_Address"),
                        request.getParameter("reg_City")
                ));
                RequestDispatcher rd =
                        request.getRequestDispatcher("/index.jsp");
                rd.include(request, response);
                alert_message(response, "User Account Created!");             
            } else {
                System.out.println("User Account Already Exists");
                RequestDispatcher rd = 
                        request.getRequestDispatcher("user/register.jsp");
                rd.include(request, response);
                alert_message(response, "User Account Already Exists");
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_register.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
// This method injects html jquerry script to generate alert page
    private void alert_message(HttpServletResponse response,
            String message)
          throws IOException {//injects html to launch alert notifications
        PrintWriter out = response.getWriter();
        out.write("<script> $( document ).ready(function() { alert( \"" + 
                message + "\" ) });</script>");
        out.close();
    }
}
