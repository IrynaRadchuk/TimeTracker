<%@include file="templateAdmin.jsp" %>
<!doctype html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/manage.css">
   <link rel="stylesheet" href="/css/table.css">
</head>
<body>
   <h2><b>Activity Statistics</b></h2>
   <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for category.." title="Type in a name">
   <br>

<table id="myTable" class="tablesorter">
      <tr>
         <th onclick="sortTable(0)">Activity <></th>
         <th onclick="sortTable(1)">Category <></th>
         <th onclick="sortTable(2)">Users <></th>
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