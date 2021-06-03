<%@include file="templateLogged.jsp" %>
<!doctype html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/profile.css">
</head>
<body>
   <h2>
      <b>
         <fmt:message key="activities_page.header"/>
      </b>
   </h2>
   <hr>
   </div>
   <div class="card-body">
   <div class="card-text">
      <div>
         <label for="user_activities">
            <b>
               <fmt:message key="activities_page.subheader"/>
            </b>
         </label>
      </div>
      <span>
         <ul>
            <c:forEach items="${user_activities}" var="act">
               <li>
                  <option value="${act}">
                     ${act.activityName} = ${act.activityStatus}
               </li>
            </c:forEach>
         </ul>
      </span>
      <hr>
      <form method="post" action="activities">
      <label for="all_activities"><fmt:message key="activities_page.choose"/></label>
      <input name="all_activities" id="all_activities">
      <c:forEach items="${all_activities}" var="activity_add">
      <option value="${activity_add}">
      ${activity_add}
      </option>
      </c:forEach>
      </select>
      <input class="button" type="submit" value='<fmt:message key="activities_page.request"/>'>
      </form>
</body>
</html>
</div>
</body>
</html>