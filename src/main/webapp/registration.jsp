<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>

        <h1>Sign in</h1><br/>
        <form method="post" action="${pageContext.request.contextPath}/TimeTracker/registration">
        <p><b>Email:</b><br>
        <input type="text" name="email"><br/>
        <p><b>Password:</b><br>
        <input type="password" name="password"><br/><br/>
        <p><b>First Name (English):</b><br>
        <input type="text" name="firstName"><br/>
<!--        <p><b>First Name (Ukrainian):</b><br>
        <input type="text" name="first_name_uk"><br/><br/> -->
        <p><b>Last Name (English):</b><br>
        <input type="text" name="lastName"><br/>
<!--       <p><b>Last Name (Ukrainian):</b><br>
        <input type="text" name="last_name_uk"><br/><br/> -->
        <input class="button" type="submit" value="Enter">
        </form>
        <br/>
        <a href="${pageContext.request.contextPath}/TimeTracker/logout">Home</a>

</body>
</html>