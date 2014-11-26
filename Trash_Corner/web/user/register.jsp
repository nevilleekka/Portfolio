<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Register</title>
    </head>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <style>
        div.registration{
            background-color:orange;
            height:300px;
            width:500px;
            margin:auto;
            position:relative;
            text-align:center;
        }
    </style>    

    <body>
        <div class="registration"  > 
            <h1>Register</h1>
            <form method="post"  action="user_register"> 
                First Name: <input type="text" required="true" 
                        name="reg_firstName" placeholder="first" > </br>
                Last Name: <input type="text" required="true"
                          name="reg_lastName" placeholder="last" > </br>
                Address: <input type="text" required="false"
                          name="reg_Address" placeholder="address" ><br>
                City: <input type="text" required="false" 
                            name="reg_City" placeholder="city" ><br>
                <button name="register">Register</button>
            </form>
            <form method="get"  action="Controller"> 
                <button name="retry">Retry Login</button>
            </form>
        </div>

    </body>
</html>
