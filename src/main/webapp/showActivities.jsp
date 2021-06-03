<%@include file="templateLogged.jsp" %>
<!doctype html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/manage.css">
   <link rel="stylesheet" href="/css/table.css">
</head>
<body>
   <h2><b><fmt:message key="show_page.header"/></b></h2>
   <hr>

<table id="myTable" class="tablesorter">
      <tr>
         <th onclick="sortTable(0)"><fmt:message key="show_page.header0"/></th>
         <th onclick="sortTable(1)"><fmt:message key="show_page.header1"/></th>
         <th onclick="sortTable(2)"><fmt:message key="show_page.header2"/></th>
         <th></th>
      </tr>
      <c:forEach items="${userActivityList}" var="userActivity">
         <form method="post" action = "showActivities">
            <tr>
                                             <td>
                  <option value="${userActivity}" name="userActivity.date" >
                    ${userActivity.date}
                    <input type="text" option value="${userActivity.date}"name="userActivity.date" readonly>
                                                </td>
                                 <td>
                     ${userActivity.activity}
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