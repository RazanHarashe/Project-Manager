<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Project</title>
</head>
<body>
    <h1>Create a Project</h1>
    <form:form method="POST" modelAttribute="project" action="/projects/new">
        <form:errors path="*" cssClass="error" />
        Title: <form:input path="title" /><br />
        Description: <form:textarea path="description" /><br />
        Due Date: <form:input path="dueDate" type="date" /><br />
        <input type="submit" value="Submit" />
    </form:form>
    <a href="/dashboard">Cancel</a>
</body>
</html>
