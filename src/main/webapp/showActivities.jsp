<%@include file="templateLogged.jsp" %>
<html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/manage.css">
   <link rel="stylesheet" href="/css/table.css">
</head>
<body>
   <h2>
      <b>
         <fmt:message key="show_page.header"/>
      </b>
   </h2>
   <hr>
   <table id="myTable" class="tablesorter">
      <tr>
         <th>
            <fmt:message key="show_page.header0"/>
         </th>
         <th>
            <fmt:message key="show_page.header1"/>
         </th>
         <th>
            <fmt:message key="show_page.header2"/>
         </th>
         <th></th>
      </tr>
      <c:forEach items="${userActivityList}" var="userActivity">
         <form method="post" action = "showActivities">
            <tr>
               <td>
                     ${userActivity.date}
                     <input type="hidden" option value="${userActivity.date}"name="userActivity.date" readonly>
               </td>
               <td>
                   <c:set var="localeCode" value="${sessionScope.lang}" />
                             <c:if test="${localeCode == 'uk'}">
               ${userActivity.nameUa}
                                </c:if>
                   <c:set var="localeCode" value="${sessionScope.lang}" />
                             <c:if test="${localeCode != 'uk'}">
               ${userActivity.activity}
                                </c:if>
               </td>
               <td>
               ${userActivity.duration}
               </td>
               <td>
               <input class="cancelbtn" type="submit" value='<fmt:message key="button.delete"/>'>
               </td>
            </tr>
         </form>
      </c:forEach>
      </form>
      </tr>
   </table>
   <script src="/js/statistics.js"></script>
</body>
</html>