<%@include file="templateLogged.jsp" %>
<!doctype html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/profile.css">
</head>
<body>
   <h2><b><fmt:message key="profile_page.header"/></b></h2>
   <hr>
   </div>
   <div class="card-body">
      <div class="card-text">
         <div> <label for="user_email"><b><fmt:message key="login_page.email"/></b></label></div>
         <span>
            <c:set var="user_email" value='${requestScope["user_email"]}' />
         </span>
         <span>
            <c:out value="${user_email}"/>
         </span>
      </div>
      <hr>
      <div class="card-text">
         <div> <label for="user_first_name"><b><fmt:message key="registration_page.first.name"/></b></label></div>
         <span>
            <c:set var="user_first_name" value='${requestScope["user_first_name"]}' />
         </span>
         <span>
            <c:out value="${user_first_name}"/>
         </span>
      </div>
      <hr>
      <div class="card-text">
         <div> <label for="user_last_name"><b><fmt:message key="registration_page.last.name"/></b></label></div>
         <span>
            <c:set var="user_last_name" value='${requestScope["user_last_name"]}' />
         </span>
         <span>
            <c:out value="${user_last_name}"/>
         </span>
      </div>
      <form method="get" action="update">
         <input class="button" type="submit" value='<fmt:message key="profile_page.update"/>'>
      </form>
   </div>
</html>