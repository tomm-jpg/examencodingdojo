<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${project.title}</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="col-6">
			<h1>Task: ${project.title}</h1>
				<p>
					<b>Creator</b>${project.lead.name}
				</p>
				<p>
					<b>Assignee</b>${project.asignee}
				</p>
				<p>
					<b>Priority</b>${project.priority}
				</p>
		</div>
		<div class="col-6">
			<c:if test="${userInSession.id == project.lead.id}" >
				<a href="/tasks/edit/${project.id}" class="btn btn-warning">Edit</a>
			</c:if>
			<c:if test="${userInSession.id == project.lead.id}" >
				<form action="/tasks/delete/${project.id}" method="post">
					<input type="hidden" name="_method" value="DELETE">
					<input type="submit" value="Delete" class="btn btn-danger" >
				</form>
			</c:if>
			<c:if test="${userInSession.name == project.asignee}" >
				<form action="/tasks/delete/${project.id}" method="post">
					<input type="hidden" name="_method" value="DELETE">
					<input type="submit" value="Completed" class=success >
				</form>
			</c:if>
		</div>
		
	</div>
</body>
</html>