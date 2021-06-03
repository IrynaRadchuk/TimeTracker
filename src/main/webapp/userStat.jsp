<%@include file="templateAdmin.jsp" %>
<!doctype html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/manage.css">
   <link rel="stylesheet" href="/css/table.css">
</head>
<body>
   <h2>
      <b>
         <fmt:message key="statistics_page.user.header"/>
      </b>
   </h2>
   <table id="myTable" class="tablesorter">
      <tr>
         <th onclick="sortTable(0)">
            <fmt:message key="statistics_page.user.table.head0"/>
         </th>
         <th onclick="sortTable(1)">
            <fmt:message key="statistics_page.user.table.head1"/>
         </th>
         <th onclick="sortTable(2)">
            <fmt:message key="statistics_page.user.table.head2"/>
         </th>
         <th onclick="sortTable(3)">
            <fmt:message key="statistics_page.user.table.head3"/>
         </th>
         <th onclick="sortTable(4)">
            <fmt:message key="statistics_page.user.table.head4"/>
         </th>
         <th onclick="sortTable(5)">
            <fmt:message key="statistics_page.user.table.head5"/>
         </th>
      </tr>
      <c:forEach items="${user_statistics}" var="user_stat">
         <form method="get">
            <tr>
               <td>
                  ${user_stat.date}
               </td>
               <td>
                  ${user_stat.email}
               </td>
               <td>
                  ${user_stat.firstName}
               </td>
               <td>
                  ${user_stat.lastName}
               </td>
               <td>
                  ${user_stat.activity}
               </td>
               <td>
                  ${user_stat.duration}
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