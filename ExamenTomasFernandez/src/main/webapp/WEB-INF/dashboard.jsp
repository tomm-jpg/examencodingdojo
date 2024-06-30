<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task Manager</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<header class="d-flex justify-content-between align-items-center ">
			<h1>Welcome, ${userInSession.name}</h1>
			<a class="btn btn-success" href="/tasks/new">Create Task</a>
			<a class="btn btn-danger" href="/logout" >Log out</a>
		</header>
		<div class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Project</th>
						<th>Creator</th>
						<th>Assignee</th>
						<th>Priority</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach items="${myProjects}" var="project">
						<tr>
							<td><a href="/tasks/${project.id}">${project.title}</td>
							<td>${project.lead.name}</td>
							<td>${project.asignee}</td>
							<td>${project.priority}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>