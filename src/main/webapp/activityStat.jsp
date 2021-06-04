<%@include file="templateAdmin.jsp" %>
<html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/manage.css">
   <link rel="stylesheet" href="/css/table.css">
</head>
<body>
   <h2>
      <b>
         <fmt:message key="statistics_page.header"/>
      </b>
   </h2>
   <input type="text" id="myInput" onkeyup="myFunction()" placeholder='<fmt:message key="statistics_page.search"/>' title="Type in a name">
   <br>
   <table id="myTable" class="tablesorter">
      <tr>
         <th onclick="sortTable(0)">
            <fmt:message key="statistics_page.activity.table.head0"/>
         </th>
         <th onclick="sortTable(1)">
            <fmt:message key="statistics_page.activity.table.head1"/>
         </th>
         <th onclick="sortTable(2)">
            <fmt:message key="statistics_page.activity.table.head2"/>
         </th>
      </tr>
      <c:forEach items="${activity_statistics}" var="activity_stat">
         <form method="get">
            <tr>
               <td>
                  ${activity_stat.activity}
               </td>
               <td>
                  ${activity_stat.category}
               </td>
               <td>
                  ${activity_stat.users}
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