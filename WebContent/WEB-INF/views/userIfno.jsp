<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jspf"%>
	<p>
		User: ${user.username}<br> Id:${user.id}<br>
		Email:${user.email}<br>
	</p>
	<table>
		<tr>
			<th>Title</th>
			<th>Added on</th>
			<th>Date of last update</th>
			<th>Description</th>
		</tr>
		<c:forEach items="${solutions}" var="solution">
			<tr>
				<td>${solution.getExcercise().getTitle() }</td>
				<td>${solution.getCreated()}</td>
				<td>${solution.getUpdated()}</td>
				<td>${solution.getDescription()}</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="footer.jspf"%>
</body>
</html>