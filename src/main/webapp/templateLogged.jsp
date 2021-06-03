<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tt" uri="time-tracker-tags" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<head>
   <link rel="stylesheet" href="/css/error.css">
   <link rel="stylesheet" href="/css/success.css">
   <link rel="stylesheet" href="/css/background.css">
   <link rel="stylesheet" href="/css/w3.css">
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
   <nav class="w3-bar w3-black">
      <a href="index" class="w3-button w3-bar-item">
         <b>
            <fmt:message key="navbar.home"/>
         </b>
      </a>
      <a href="logout" class="w3-button w3-bar-item">
         <b>
            <fmt:message key="navbar.logout"/>
         </b>
      </a>
      <a href="activityTimeCalendar" class="w3-button w3-bar-item">
         <b>
            <fmt:message key="navbar.calendar"/>
         </b>
      </a>
      <a href="showActivities" class="w3-button w3-bar-item">
         <b>
            <fmt:message key="navbar.showAct"/>
         </b>
      </a>
      <a href="profile" class="w3-button w3-bar-item">
         <b>
            <fmt:message key="navbar.profile"/>
         </b>
      </a>
      <a href="activities" class="w3-button w3-bar-item">
         <b>
            <fmt:message key="navbar.activities"/>
         </b>
      </a>
   </nav>
   <form>
      <div class="mt-5">
         <a href="?lang=en">
            <fmt:message key="lang.en"/>
         </a>
         |
         <a href="?lang=uk">
            <fmt:message key="lang.uk"/>
         </a>
         &emsp;
         <tt:DateTag/>
      </div>
   </form>
   <c:if test="${not empty requestScope['error']}">
      <div class="error"align="center">
         <c:set var="message" value='${requestScope["error"]}' />
         <c:out value="${message}"/>
      </div>
   </c:if>
   <c:if test="${not empty requestScope['success']}">
      <div class="success"align="center">
         <c:set var="message" value='${requestScope["success"]}' />
         <c:out value="${message}"/>
      </div>
   </c:if>
</body>