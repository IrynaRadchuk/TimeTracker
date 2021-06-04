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
         <fmt:message key="admin_page.activities.header"/>
      </b>
   </h2>
   <table>
      <tr>
         <th>
            <fmt:message key="admin_page.activities.header0"/>
         </th>
         <th>
            <fmt:message key="admin_page.activities.header1"/>
         </th>
         <th>
            <fmt:message key="admin_page.activities.header4"/>
         </th>
         <th>
            <fmt:message key="admin_page.activities.header2"/>
         </th>
         <th>
            <fmt:message key="admin_page.activities.header3"/>
         </th>
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
               <c:set var="active_nameUa" value='${active.nameUa}' />
               <input type="text" option value="${active.nameUa}"out value=${active.nameUa} name="active_nameUa">
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
                  <input class="button" type="submit" formaction="manageActivities" value='<fmt:message key="button.change"/>'>
                  <input class="cancelbtn" type="submit" formaction="deleteActivities" value='<fmt:message key="button.delete"/>'>
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
            <c:set var="add_activity_ua" value='${add_activity_ua}' />
            <input type="text" option value="${add_activity_ua}"name="add_activity_ua">
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
               <input class="button" type="submit" formaction="addActivities" value='<fmt:message key="button.addition"/>'>
            </td>
      </form>
      </tr>
   </table>
   </body>
</html>