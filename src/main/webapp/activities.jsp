<%@include file="templateLogged.jsp" %>
<html>
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
      <c:set var="localeCode" value="${sessionScope.lang}" />
              <c:if test="${localeCode == 'uk'}">
         <ul>
            <c:forEach items="${user_activities}" var="act">
               <li>
                  <option value="${act}">
                     ${act.activityUa} = ${act.activityStatus}
               </li>
            </c:forEach>
         </ul>
                 </c:if>
      <c:set var="localeCode" value="${sessionScope.lang}" />
       <c:if test="${localeCode != 'uk'}">
                <ul>
                   <c:forEach items="${user_activities}" var="act">
                      <li>
                         <option value="${act}">
                            ${act.activityName} = ${act.activityStatus}
                      </li>
                   </c:forEach>
                </ul>
                        </c:if>
      </span>
      <hr>
      <form method="post" action="activities">
         <c:set var="localeCode" value="${sessionScope.lang}" />
                 <c:if test="${localeCode == 'uk'}">
      <label for="all_activities_ua"><fmt:message key="activities_page.choose"/></label>
      <select name="all_activities_ua" id="all_activities_ua">
      <c:forEach items="${all_activities_ua}" var="activity_add_ua">
      <option value="${activity_add_ua}">
      ${activity_add_ua}
      </option>
      </c:forEach>
      </select>
      </c:if>
         <c:set var="localeCode" value="${sessionScope.lang}" />
                 <c:if test="${localeCode != 'uk'}">
      <label for="all_activities"><fmt:message key="activities_page.choose"/></label>
      <select name="all_activities" id="all_activities">
      <c:forEach items="${all_activities}" var="activity_add">
      <option value="${activity_add}">
      ${activity_add}
      </option>
      </c:forEach>
      </select>
      </c:if>
      <input class="button" type="submit" value='<fmt:message key="activities_page.request"/>'>
      </form>
</body>
</html>
</div>
</body>
</html>