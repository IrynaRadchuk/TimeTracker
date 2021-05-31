<%@include file="templateAdmin.jsp" %>
<!doctype html>
<head>
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/manage.css">
   <link rel="stylesheet" href="/css/table.css">
</head>
<body>
   <h2><b>Activity Statistics</b></h2>
   <table>
      <tr>
         <th>Activity Id</th>
         <th>Activity</th>
         <th>Category ID</th>
         <th>Category</th>
         <th></th>
      </tr>
      <c:forEach items="${activities_list}" var="active">
         <form method="post">
            <tr>
               <td>
                  <option value="${active}" name="active_id" >
                     ${active.id}
                     <input type="text" option value="${active.id}"out value=${active.name} name="active_id" readonly>
               </td>
               <td>
               <c:set var="active_name" value='${active.name}' />
               <input type="text" option value="${active.name}"out value=${active.name} name="active_name">
               </td>
               <td>
               <option value="${active}" name="active_categoryId" > ${active.id}
               <input type="text" option value="${active.categoryId}"name="active_categoryId" out value=${active.categoryId} readonly>
               </td>
               <td>
               <select name="category_list" id="category_list">
               <option selected="selected"> ${active.category} </option>
               <c:forEach items="${category_list}" var="category">
               <option value="${category}" name = "category" id="category">
               ${category}
               </option>
               </c:forEach>
               </select>
               </td>
               <td>
                  <input class="button" type="submit" formaction="manageActivities" value="Change">
                  <input class="cancelbtn" type="submit" formaction="deleteActivities" value="Delete">
               </td>
            </tr>
         </form>
      </c:forEach>
      <form method="post">
         <tr>
            <td>
               <option value="${active}" name="active_id" >
                  ${active.id}
                  <input type="text" option value="${active.id}"name="active_id" readonly>
            </td>
            <td>
            <c:set var="add_activity_name" value='${add_activity_name}' />
            <input type="text" option value="${add_activity_name}"name="add_activity_name">
            </td>
            <td>
            <option value="${active}" name="active_categoryId" > ${active.id}
            <input type="text" option value="${active.categoryId}"name="active_categoryId" readonly>
            </td>
            <td>
            <select name="category_list" id="category_list">
            <c:forEach items="${category_list}" var="category">
            <option value="${category}" name = "category" id="category">
            ${category}
            </option>
            </c:forEach>
            </select>
            </td>
            <td>
               <input class="button" type="submit" formaction="addActivities" value="Add">
            </td>
      </form>
      </tr>
   </table>
</html>