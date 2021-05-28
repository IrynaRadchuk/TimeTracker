<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:set var="language" value="${not empty param.language
                                        ? param.language : not empty language
                                        ? language : 'en'}" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />
<head>
<link rel="stylesheet" href="/css/error.css">
<link rel="stylesheet" href="/css/background.css">
<link rel="stylesheet" href="/css/w3.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<nav class="w3-bar w3-black">
  <a href="index" class="w3-button w3-bar-item"><b>Home</b></a>
  <a href="index" class="w3-button w3-bar-item"><b>Sign Out</b></a>
  <a href="profile" class="w3-button w3-bar-item"><b>Profile</b></a>
  <a href="activities" class="w3-button w3-bar-item"><b>Activities</b></a>
</nav>
<form>
     <div class="mt-5">
           <input name="language" type="image" value="en"
             ${language=='en' ? 'selected' : '' } src="/img/flag_en.png"
              style="height: 24px; width: 32px; margin: 8px 0 0 0;">
             <input name="language" type="image" value="ua"
             ${language=='ua' ? 'selected' : '' } src="/img/flag_ua.png"
               style="height: 24px; width: 32px; margin: 8px 0 0 0;">
      </div>
</form>
    <c:if test="${not empty requestScope['error']}">
	<div class="error"align="center">
	<c:set var="message" value='${requestScope["error"]}' />
	<c:out value="${message}"/>
	</div>
	</c:if>
</body>