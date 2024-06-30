<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Task</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form:form action="/tasks/create" method="post" modelAttribute="project" >
			<h1>Create a new Task</h1>
			<div>
				<form:label path="title">Task</form:label>
				<form:input path="title" class="form-control" />
				<form:errors path="title" class="text-danger" />
			</div>
			<div>
				<form:label path="asignee">Assignee</form:label>
				<form:select path="asignee" class="form-select">
					<c:forEach items="${namesUsers}" var="namesUsers">
						<form:option value="${namesUsers}">${namesUsers}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div>
				<form:label path="priority">Priority</form:label>
				<form:select path="priority" class="form-select">
					<c:forEach items="${priority}" var="priority">
						<form:option value="${priority}">${priority}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<form:hidden path="lead" value="${userInSession.id}" />
			<input type="submit" class="btn btn-success" value="Submit" >
			<a href="/dashboard" class="btn btn-danger" >Cancel</a>
		</form:form>
	</div>
</body>
</html>