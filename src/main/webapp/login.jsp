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
      <h2>
         <fmt:message key="login_page.header"/>
      </h2>
      <h4>
         <fmt:message key="login_page.subheader1"/>
      </h4>
      <h4>
         <fmt:message key="login_page.subheader2"/>
         <a href ="registration">
            <fmt:message key="navbar.signup"/>
         </a>
      </h4>
      <form method="post" action="login">
         <div class="container">
            <label for="email">
               <b>
                  <fmt:message key="login_page.email"/>
               </b>
            </label>
            <br>
            <input type="text" placeholder='<fmt:message key="login_page.email.enter"/>' name="email"/>
            <br>
            <label for="password">
               <b>
                  <fmt:message key="login_page.password"/>
               </b>
            </label>
            <br>
            <input type="password" placeholder='<fmt:message key="login_page.password.enter"/>' name="password">
            <br>
            <input class="button" type="submit" value='<fmt:message key="navbar.signin"/>'>
      </form>
      </div>
      <form action="index">
         <input class="cancelbtn" type="submit" value='<fmt:message key="button.cancel"/>'>
      </form>
   </body>
</html>