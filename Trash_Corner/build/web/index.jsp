<!DOCTYPE html>
<html>
    <head>
        <title>Trash Corner</title>
        <link rel="stylesheet" type="text/css" href="index.css" title="style1"/>
    </head>
   
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <% 
     //goes to user_panel if sessions exists as soon as the page is loaded
        try {
            if (request.getSession().getAttribute("user") != null) {
                RequestDispatcher rd = request.
                        getRequestDispatcher("user/user_panel.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
        }
    %>
    <body>

        <div class="user_info well" style="" >

            <h1>Trash Corner</h1>

            <div class="login">
                <form class="form-actions" method="post" 
                      action="Controller"> 
                    First Name: <input type="text" required="true" 
                      name="firstName" placeholder="First" > </br>
                    Last Name: <input type="text" required="true" 
                        name="lastName" placeholder="Last" > </br>
                    <button class="btn btn-success"
                            name="login">Log In</button><br>
                </form>	
            </div>
        </div>
    </body>
</html>		