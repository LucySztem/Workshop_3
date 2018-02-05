<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Groups</title>
</head>
<body>
<%@ include file="header.jspf"%>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Description</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${exs}" var="ex">
			<tr>
				<td>${exercise.id}</td>
				<td>${exercise.title}</td>
				<td>${exercise.description}</td>
				<td>
					<a href="AdminExercise?action=edit&id=${exercise.getId()}">EDIT</a>
					<a href="AdminExercise?action=del&id=${exercise.getId()}">DELETE</a>
					<a href="AdminExercise?action=sol&id=${exercise.getId()}">ASSIGN</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="AdminExercise?action=add">ADD EXERCISE</a>
<%@ include file="footer.jspf"%>

</body>
</html>