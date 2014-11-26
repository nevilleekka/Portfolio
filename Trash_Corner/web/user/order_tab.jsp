<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <link rel="stylesheet" type="text/css" 
          href="user_panel.css" title="style1" />
    <html>
        <head>
            <link rel="stylesheet" type="text/css" 
                  href="user/user_panel.css" title="style1" />
        </head>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
     <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        
        <body >
            <table class="order_tab" 
                   style="width:500px; margin:auto; position:relative;" >
                <tr>
                    <td>
              <form class="form-search" method="post" action="user_panel">
                    <div class="db_order">
                        <table class="success  table-hover" 
                              style=" margin:auto; position:relative;">
                            <tr>
                                <td>
                                    <span class="label
                                          label-info">Order ID</span>                                   
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <textarea class="input-small" 
                                          name="order_id" rows="1" cols="3" 
                                readonly>${order.get_OrderID()}</textarea>
                                </td>
                            </tr>
                        </table>
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
        type="text" name="boxAmount" value="${order.get_Boxes()}" size="4">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="label">Chair</span>
                                </td>
                                <td>
                                    <input class="input-mini" 
                                     type="text" name="chairAmount" 
                                  value="${order.get_Chairs()}" size="4">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="label">Desk</span>
                                </td>
                                <td>
                                    <input class="input-mini" 
                                           type="text" name="deskAmount"
                                     value="${order.get_Desks()}" size="4">
                                </td>
                            </tr>
                        </table>
                    </div>
                                            <div class="db_controls">
                <button class="btn btn-danger" 
                        name="user_form" value="delete">Delete</button>
                <button class="btn btn-primary" 
                        name="user_form" value="modify">Modify</button>
            </div>
            </div>
            </form>             </td>
                                </tr>
            </table>
        </body>
    </html>