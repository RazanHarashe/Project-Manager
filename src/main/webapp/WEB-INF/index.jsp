<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Project Manager</title>
</head>
<body>
    <h1>Project Manager</h1>
    <h2>Register</h2>
    <form:form method="POST" modelAttribute="newUser" action="/register">
        <form:errors path="*" cssClass="error" />
        First Name: <form:input path="firstName" /><br />
        Last Name: <form:input path="lastName" /><br />
        Email: <form:input path="email" /><br />
        Password: <form:password path="password" /><br />
        Confirm PW: <form:password path="confirm" /><br />
        <input type="submit" value="Register" />
    </form:form>

    <h2>Log in</h2>
    <form:form method="POST" modelAttribute="newLogin" action="/login">
        <form:errors path="*" cssClass="error" />
        Email: <form:input path="email" /><br />
        Password: <form:password path="password" /><br />
        <input type="submit" value="Log in" />
    </form:form>
</body>
</html>
