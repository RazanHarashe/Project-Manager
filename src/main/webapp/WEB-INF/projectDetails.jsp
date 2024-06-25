<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Project Details</title>
</head>
<body>
    <h1>Project Details</h1>
    <h2>${project.title}</h2>
    <p>${project.description}</p>
    <p>Due Date: ${project.dueDate}</p>
    <a href="/dashboard">Back to Dashboard</a>
</body>
</html>
