<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Assign solution</title>
</head>
<body>
<%@ include file="header.jspf"%>
	Title: ${exercise.title}<br>
	Description: ${exercise.description}<br>
	<form action="AdminEx" method="post">
		<table>
			<tr>
				<th>User ID</th>
				<th>Username</th>
				<th>Assign</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.username}</td>
					<td><input type="checkbox" name="solution" value="${user.id}"></td>
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" name="id" value="${exercise.id}">
		<input type="submit" name="assign" value="assign">
	</form>
<%@ include file="footer.jspf"%>

</body>
</html>