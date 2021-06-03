<%@include file="templateAdmin.jsp" %>
<!doctype html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/manage.css">
   <link rel="stylesheet" href="/css/table.css">
</head>
<body>
   <h2><b><fmt:message key="admin_page.users.header"/></b></h2>
   <table>
      <tr>
         <th><fmt:message key="admin_page.users.header0"/></th>
         <th><fmt:message key="admin_page.users.header1"/></th>
         <th><fmt:message key="admin_page.users.header2"/></th>
         <th><fmt:message key="admin_page.users.header3"/></th>
         <th><fmt:message key="admin_page.users.header4"/></th>
         <th></th>
      </tr>
      <c:forEach items="${all_users}" var="user">
         <form method="post">
            <tr>
               <td>
                  <option value="${user}" name="user_id" >
                     ${user.id}
                     <input type="text" option value="${user.id}"out value=${user.id} name="user_id" readonly>
               </td>
               <td>
               <c:set var="user_email" value='${user.email}' />
               <input type="text" option value="${user.email}"out value=${user.email} name="user_email">
               </td>
               <td>
               <c:set var="user.firstName" value='${user.firstName}' />
               <input type="text" option value="${user.firstName}"out value=${user.firstName} name="user_first_name">
               </td>
               <td>
               <c:set var="user.lastName" value='${user.lastName}' />
               <input type="text" option value="${user.lastName}"out value=${user.lastName} name="user_last_name">
               </td>
               <td>
               <select name="all_roles" id="all_roles">
               <option selected="selected"> ${user.role} </option>
               <c:forEach items="${all_roles}" var="role">
               <option value="${role}" name = "role" id="role">
               ${role}
               </option>
               </c:forEach>
               </select>
               </td>
               <td>
                  <input class="button" type="submit" formaction="manageUsers" value='<fmt:message key="button.change"/>'>
                  <input class="cancelbtn" type="submit" formaction="deleteUsers" value='<fmt:message key="button.delete"/>'>
               </td>
            </tr>
         </form>
      </c:forEach>
      <form method="post">
         <tr>
            <td>
               <option value="${user}" name="user_id" >
                  ${user.id}
                  <input type="text" option value="${user.id}" name="user_id" readonly>
            </td>
            <td>
            <c:set var="user_email" value='${user.email}' />
            <input type="text" option value="${user.email}" name="add_user_email">
            </td>
            <td>
            <c:set var="user.firstName" value='${user.firstName}' />
            <input type="text" option value="${user.firstName}" name="add_user_first_name">
            </td>
            <td>
            <c:set var="user.lastName" value='${user.lastName}' />
            <input type="text" option value="${user.lastName}" name="add_user_last_name">
            </td>
            <td>
            <select name="all_roles" id="all_roles">
            <c:forEach items="${all_roles}" var="role">
            <option value="${role}" name = "role" id="role">
            ${role}
            </option>
            </c:forEach>
            </select>
            </td>
            <td>
               <input class="button" type="submit" formaction="addUsers" value='<fmt:message key="button.addition"/>'>
            </td>
      </form>
      </tr>
   </table>
</html>