<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Welcome, ${user.firstName}</h1>
    <a href="/logout">Logout</a>
    <h2>All Projects</h2>
    <a href="/projects/new">+ new project</a>
    <table border="1">
        <tr>
            <th>Project</th>
            <th>Team Lead</th>
            <th>Due Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="project" items="${unassignedProjects}">
            <tr>
                <td><a href="/projects/${project.id}">${project.title}</a></td>
                <td>${project.lead.firstName} ${project.lead.lastName}</td>
                <td>${project.dueDate}</td>
                <td><a href="/projects/${project.id}/join">Join team</a></td>
            </tr>
        </c:forEach>
    </table>
    
    <h2>Your Projects</h2>
    <table border="1">
        <tr>
            <th>Project</th>
            <th>Team Lead</th>
            <th>Due Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="project" items="${assignedProjects}">
            <tr>
                <td><a href="/projects/${project.id}">${project.title}</a></td>
                <td>${project.lead.firstName} ${project.lead.lastName}</td>
                <td>${project.dueDate}</td>
                <td>
                    <c:if test="${project.lead.id == user.id}">
                        <a href="/projects/edit/${project.id}">Edit</a>
                        <form action="/projects/delete/${project.id}" method="POST" style="display:inline">
                            <input type="hidden" name="_method" value="DELETE" />
                            <input type="submit" value="Delete" />
                        </form>
                    </c:if>
                    <a href="/projects/${project.id}/leave">Leave team</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

