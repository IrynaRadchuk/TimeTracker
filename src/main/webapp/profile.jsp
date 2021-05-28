<%@include file="templateLogged.jsp" %>
<!doctype html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/profile.css">
</head>
<body>
   <h2><b>Your personal profile</b></h2>
   <hr>
   </div>
   <div class="card-body">
      <div class="card-text">
         <div> <label for="user_email"><b>Email</b></label></div>
         <span>
            <c:set var="user_email" value='${requestScope["user_email"]}' />
         </span>
         <span>
            <c:out value="${user_email}"/>
         </span>
      </div>
      <hr>
      <div class="card-text">
         <div> <label for="user_password"><b>Password</b></label></div>
         <span>
            <c:set var="user_password" value='${requestScope["user_password"]}' />
         </span>
         <span>
            <c:out value="${user_password}"/>
         </span>
      </div>
      <hr>
      <div class="card-text">
         <div> <label for="user_first_name"><b>First Name</b></label></div>
         <span>
            <c:set var="user_first_name" value='${requestScope["user_first_name"]}' />
         </span>
         <span>
            <c:out value="${user_first_name}"/>
         </span>
      </div>
      <hr>
      <div class="card-text">
         <div> <label for="user_last_name"><b>Last Name</b></label></div>
         <span>
            <c:set var="user_last_name" value='${requestScope["user_last_name"]}' />
         </span>
         <span>
            <c:out value="${user_last_name}"/>
         </span>
      </div>
      <div class="card-text">
         <div> <label for="user_activities"><b>Approved activities</b></label></div>
         <span>
            <c:set var="user_activities" value='${requestScope["user_activities"]}'/>
         </span>
         <span>
            <c:out value="${user_activities}"/>
         </span>
         <hr>
         <label for="all_activities">Choose activity to approve:</label>
         <select name="all_activities" id="all_activities">
            <c:forEach items="${all_activities}" var="activity">
               <option value="${activity}">
                  ${activity}
               </option>
            </c:forEach>
         </select>
         <hr>
         <label for="user_activities">Choose activity to approve:</label>
         <select name="user_activities" id="user_activities">
            <c:forEach items="${user_activities}" var="act">
               <option value="${act}">
                  ${act}
               </option>
            </c:forEach>
         </select>
         <hr>
      </div>
      <form method="get" action="update">
         <input class="button" type="submit" value="Update Profile">
      </form>
   </div>
</html>