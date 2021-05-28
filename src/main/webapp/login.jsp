<!DOCTYPE html>
<%@include file="templateGuest.jsp" %>
<html>
   <head>
      <link rel="stylesheet" href="/css/background.css">
      <link rel="stylesheet" href="/css/login.css">
   </head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   </head>
   <body>
      <h2>Login Form</h2>
      <h4>Please sign in to continue using Time Tracker</h4>
      <h4>Not registered yet? <a href ="registration">Sign Up</a></h4>
      <form method="post" action="login">
         <div class="container">
            <label for="email"><b>Email</b></label>
            <input type="text" placeholder="Enter Email" name="email">
            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password">
            <input class="button" type="submit" value="Sign In">
      </form>
      </div>
      <form action="index">
         <input class="cancelbtn" type="submit" value="Cancel">
      </form>
   </body>
</html>