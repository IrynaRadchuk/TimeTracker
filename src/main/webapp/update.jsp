<%@include file="templateLogged.jsp" %>
<html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/login.css">
</head>
<body>
   <h2>
      <b>
         <fmt:message key="update_page.header"/>
      </b>
   </h2>
   <form method="post" action="update">
      <div class="container">
         <label for="user_email">
            <b>
               <fmt:message key="login_page.email"/>
            </b>
         </label>
         <br>
         <c:set var="user_email" value='${requestScope["user_email"]}' />
         <input type="text" out value="${user_email}" name="user_email">
         <br>
         <label for="user_password">
            <b>
               <fmt:message key="login_page.password"/>
            </b>
         </label>
         <br>
         <c:set var="user_password" value='${requestScope["user_password"]}' />
         <input type="password" name="user_password">
         <br>
                  <label for="user_password_confirm">
                     <b>
                        <fmt:message key="login_page.password.confirm"/>
                     </b>
                  </label>
                  <br>
                  <c:set var="user_password_confirm" value='${requestScope["user_password_confirm"]}' />
                  <input type="password" name="user_password_confirm">
                  <br>
         <label for="user_first_name">
            <b>
               <fmt:message key="registration_page.first.name"/>
            </b>
         </label>
         <br>
         <c:set var="user_first_name" value='${requestScope["user_first_name"]}' />
         <input type="text" out value="${user_first_name}" name="user_first_name">
         <br>
         <label for="user_last_name">
            <b>
               <fmt:message key="registration_page.last.name"/>
            </b>
         </label>
         <br>
         <c:set var="user_last_name" value='${requestScope["user_last_name"]}' />
         <input type="text" out value="${user_last_name}" name="user_last_name">
         <br>
         <input class="button" type="submit" value='<fmt:message key="update_page.accept"/>'>
   </form>
   </div>
   <form action="profile">
      <input class="cancelbtn" type="submit" value='<fmt:message key="button.cancel"/>'>
   </form>
   </div>
</body>
</html>