<!DOCTYPE html>
<%@include file="templateGuest.jsp" %>
<html>
   <head>
      <link rel="stylesheet" href="/css/background.css">
      <link rel="stylesheet" href="/css/login.css">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Login Page</title>
   </head>
   <body>
      <h4>Please sign up to continue using Time Tracker</h4>
      <h4>Already registered ? <a href ="login">Sign In</a></h4>
      <form method="post" action="registration">
         <div class="container">
            <label for="email"><b>Email</b></label>
            <input type="text" placeholder="Enter Email" name="email">
            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password">
            <label for="firstName"><b>First Name</b></label>
            <input type="text" placeholder="Enter First Name" name="firstName">
            <label for="lastName"><b>Last Name</b></label>
            <input type="text" placeholder="Enter Last Name" name="lastName">
            <input class="button" type="submit" value="Sign Up">
      </form>
      </div>
      <form action="index">
      <input class="cancelbtn" type="submit" value="Cancel">
   </body>
</html>