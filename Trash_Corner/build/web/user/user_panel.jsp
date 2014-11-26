<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Serverlets.user_panel"%>
<%@page import="Database.Model.User_Model"%>
<%@page import="Database.Model.Order_Model"%>

<html>

    <head>
        <link rel="stylesheet" type="text/css" 
              href="user/user_panel.css" title="style1" />

    </head>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>


    <body>
        <div class="main">
            <h1>User Panel</h1>

            <div class="user_settings" style="padding:5px;">
                <form  method="post"  action="user_panel">
                    <div class="user_info">
                        <span class="label label-success"
                        style="width:220px; height:15px;">User Info</span>
                        <div style="display:flex;">
                            <div style="display:block;">
                          <span class="label label-info">First Name</span>
                                <textarea name="firstName" 
                                class="input-small" rows="1"
                                cols="10" readonly 
                 required >${sessionScope.user.get_FirstName()}</textarea>
                            </div>
                            <div style="display:block;">
                          <span class="label label-info" >Last Name</span>
                                <textarea name="lastName" 
                                   class="input-small" rows="1" cols="10"
                             readonly 
                required > ${sessionScope.user.get_LastName()} </textarea>
                            </div>

                        </div>
                        <span class="label label-info">Contents</span>
                    <textarea name="address" class="input-medium" rows="1" 
                    required>${sessionScope.user.get_Address()}</textarea>
                        <span class="label label-info">City</span>
                      <textarea name="city"  class="input-medium" rows="1" 
                   required>${sessionScope.user.get_City()}</textarea>

                        <div>
                          <button class="btn btn-primary" name="user_form" 
                                    value="change">Change</button>
                          <button class="btn btn-inverse" name="user_form"
                                    value="signout">Sign Out</button>

                        </div>


                    </div>
                </form>		



                <div class="db_order_create">

                    <span class="label label-success" 
                          style="width:270px; height:15px;">Order</span>
                    <form  method="post"  action="user_panel">
                        

                            <div class="db_order_form"  >
                                


                                <table class="success table-hover" 
                   style=" width:300px; margin:auto; position:relative;">
                                    <tr>
                                        <td>
                                            <span class="label 
                                               label-info">Contents</span>
                                        </td>
                                        <td>
                                            <span class="label 
                                               label-info">Quantity</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <span class="label">Box</span>

                                        </td>
                                        <td>
                                            <input class="input-mini" 
                          type="text" name="boxAmount" value="0" size="4">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                          <span class="label">Chair</span>
                                        </td>
                                        <td>
                                            <input class="input-mini" 
                       type="text" name="chairAmount" value="0" size="4" >
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                           <span class="label">Desk</span>
                                        </td>
                                        <td>
                                            <input class="input-mini" 
                         type="text" name="deskAmount" value="0" size="4">
                                        </td>
                                    </tr>
                                </table>
                            </div> <div style="display:block;
                            margin:auto; position:relative;">
                                <button  class="btn btn-info" 
                             name="user_form" value="create" >Create</button>
                            </div>
 
                    </form>
                </div>

            </div>

            <div  style="padding:5px;">

                <%
//populates the whole page with order by callin in get_order
 //function and injecting order_tab html
                    if (session.getAttribute("user") != null) {
                        Order_Model[] order_list = 
          user_panel.get_order((User_Model) session.getAttribute("user"));
                        if (order_list != null) {
                            for (int i = 0; i < order_list.length; i++) {
                                System.out.println("Found Order " + i);
                             session.setAttribute("order", order_list[i]);
                %>
                <div  style="padding:5px;">
                    <table class="well"
                       style="margin:auto; position:relative;">
                        <tr> 
                            <td>
                                <%@include file="order_tab.jsp" %>
                            </td>
                        </tr>
                    </table>
                </div>
                <%
                                session.removeAttribute("order");
                            }
                        }
                    }
                %>
            </div>
        </div>
    </body>

</html>	