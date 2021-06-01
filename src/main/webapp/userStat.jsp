<%@include file="templateAdmin.jsp" %>
<!doctype html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/manage.css">
   <link rel="stylesheet" href="/css/table.css">
</head>
<body>
   <h2><b>Activity Statistics</b></h2>
<table id="myTable" class="tablesorter">
      <tr>
         <th onclick="sortTable(0)">Date <></th>
         <th onclick="sortTable(1)">User Email <></th>
         <th onclick="sortTable(2)">User First Name <></th>
         <th onclick="sortTable(3)">User Last Name <></th>
         <th onclick="sortTable(4)">Activity <></th>
         <th onclick="sortTable(5)">Duration <></th>
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