<%@include file="templateAdmin.jsp" %>
<!doctype html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/manage.css">
   <link rel="stylesheet" href="/css/table.css">
</head>
<body>
   <h2><b>Pending Activities</b></h2>
   <table>
      <tr>
         <th>User Id</th>
         <th>User Email</th>
         <th>User First Name</th>
         <th>User Last Name</th>
         <th>Activity</th>
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
                  <input class="button" type="submit" formaction="approveRequest" value="Approve">
                  <input class="cancelbtn" type="submit" formaction="denyRequest" value="Deny">
               </td>
            </tr>
         </form>
      </c:forEach>
      </form>
      </tr>
   </table>
</html>