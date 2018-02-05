<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
td, th, table {
	border: 1px solid black;
	padding: 5px
}
</style>
<title>View users</title>
</head>

<body>
	<%@ include file="header.jspf"%>
	<table>
		<tr>
			<th>Id</th>
			<th>Group ID</th>
			<th>Username</th>
			<th>Email</th>
			<th>Password</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.getUser_group_id()}</td>
				<td>${user.username}</td>
				<td>${user.email}</td>
				<td>${user.password}</td>
				<td><a href="AdminUsers?action=edit&id=${user.getId()}">EDIT</a>
					<a href="AdminUsers?action=del&id=${user.getId()}">DELETE</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="AdminUsers?action=add">ADD USER</a>
	<%@ include file="footer.jspf"%>

</body>
</html>