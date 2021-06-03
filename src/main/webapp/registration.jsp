<!DOCTYPE html>
<%@include file="templateGuest.jsp" %>
<html>
   <head>
      <link rel="stylesheet" href="/css/background.css">
      <link rel="stylesheet" href="/css/login.css">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   </head>
   <body>
   <h2><fmt:message key="registration_page.header"/></h2>
      <h4><fmt:message key="registration_page.subheader1"/></h4>
      <h4><fmt:message key="registration_page.subheader2"/><a href ="login"><fmt:message key="navbar.signin"/></a></h4>
      <form method="post" action="registration">
         <div class="container">
            <label for="email"><b><fmt:message key="login_page.email"/></b></label>
            <br>
            <input type="text" placeholder='<fmt:message key="login_page.email.enter"/>' name="email">
            <br>
            <label for="password"><b><fmt:message key="login_page.password"/></b></label>
            <br>
            <input type="password" placeholder='<fmt:message key="login_page.password.enter"/>' name="password">
            <br>
            <label for="firstName"><b><fmt:message key="registration_page.first.name"/></b></label>
            <br>
            <input type="text" placeholder='<fmt:message key="registration_page.first.name.enter"/>' name="firstName">
            <br>
            <label for="lastName"><b><fmt:message key="registration_page.last.name"/></b></label>
            <br>
            <input type="text" placeholder='<fmt:message key="registration_page.last.name.enter"/>' name="lastName">
            <br>
            <input class="button" type="submit" value='<fmt:message key="navbar.signup"/>'>
      </form>
      </div>
      <form action="index">
      <input class="cancelbtn" type="submit" value='<fmt:message key="button.cancel"/>'>
   </body>
</html>