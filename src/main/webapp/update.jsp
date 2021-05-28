<%@include file="templateLogged.jsp" %>
<!doctype html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/login.css">
</head>
<body>
   <h2><b>Change your profile</b></h2>
   <form method="post" action="update">
      <div class="container">
         <label for="user_email"><b>Email</b></label>
         <c:set var="user_email" value='${requestScope["user_email"]}' />
         <input type="text" out value="${user_email}" name="user_email">
         <label for="user_password"><b>Password</b></label>
         <c:set var="user_password" value='${requestScope["user_password"]}' />
         <input type="password" out value="${user_password}" name="user_password">
         <label for="user_first_name"><b>First Name</b></label>
         <c:set var="user_first_name" value='${requestScope["user_first_name"]}' />
         <input type="text" out value="${user_first_name}" name="user_first_name">
         <label for="user_last_name"><b>Last Name</b></label>
         <c:set var="user_last_name" value='${requestScope["user_last_name"]}' />
         <input type="text" out value="${user_last_name}" name="user_last_name">
         <input class="button" type="submit" value="Accept">
   </form>
   </div>
   <form action="profile">
      <input class="cancelbtn" type="submit" value="Cancel">
   </form>
   </div>