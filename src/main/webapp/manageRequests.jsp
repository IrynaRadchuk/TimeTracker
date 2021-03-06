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
         <fmt:message key="admin_page.requests.header"/>
      </b>
   </h2>
   <table>
      <tr>
         <th>
            <fmt:message key="admin_page.users.header0"/>
         </th>
         <th>
            <fmt:message key="admin_page.users.header1"/>
         </th>
         <th>
            <fmt:message key="admin_page.users.header2"/>
         </th>
         <th>
            <fmt:message key="admin_page.users.header3"/>
         </th>
         <th>
            <fmt:message key="admin_page.activities.header1"/>
         </th>
         <th></th>
      </tr>
      <c:forEach items="${pending_activities}" var="pending">
         <form method="post">
            <tr>
               <td>
               <option value="${pending}" name="pending_id" >
               ${pending.userId}
               <input type="text" option value="${pending.userId}"out value=${pending.userId} name="pending_id" readonly>
               </td>
               <td>
               <option value="${pending}" name="pending_email" >
               ${pending.email}
               <input type="text" option value="${pending.email}"out value=${pending.email} name="pending_email" readonly>
               </td>
               <td>
               <option value="${pending}" name="pending_first_name" >
               ${pending.firstName}
               <input type="text" option value="${pending.firstName}"out value=${pending.firstName} name="pending_first_name" readonly>
               </td>
               <td>
               <option value="${pending}" name="pending_last_name" >
               ${pending.lastName}
               <input type="text" option value="${pending.lastName}"out value=${pending.lastName} name="pending_last_name" readonly>
               </td>
               <td>
               <option value="${pending}" name="pending_activity_name" >
               ${pending.activityName}
               <input type="text" option value="${pending.activityName}"out value=${pending.activityName} name="pending_activity_name" readonly>
               </td>
               <td>
               <input class="button" type="submit" formaction="approveRequest" value='<fmt:message key="button.approve"/>'>
               <input class="cancelbtn" type="submit" formaction="denyRequest" value='<fmt:message key="button.deny"/>'>
               </td>
            </tr>
         </form>
      </c:forEach>
      </form>
      </tr>
   </table>
   </body>
</html>