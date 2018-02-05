<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View group</title>
</head>
<body>
	<%@ include file="header.jspf"%>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${groups}" var="group">
			<tr>
				<td>${group.id}</td>
				<td>${group.name}</td>
				<td><a href="AdminGroup?action=edit&id=${group.getId()}">EDIT</a>
					<a href="AdminGroup?action=del&id=${group.getId()}">DELETE</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="AdminGroup?action=add">ADD GROUP</a>
	<%@ include file="footer.jspf"%>
</body>
</html>