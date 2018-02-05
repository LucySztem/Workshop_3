<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
</head>
<body>
<%@ include file="/WEB-INF/views/header.jspf"%>
	

	<table>
		<tr>
			<th>Title</th>
			<th>Author</th>
			<th>Date</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${solutions}" var="solution" end="${limit}">

			<tr>
				<td>${solution.getExercise().getTitle()}</td>
				<!--  tutaj wyciagniemy tytul przypisany do excercise id -->
				<td>${solution.user.username}</td>
				<!-- spr solution -->
				<td>${solution.getCreated()}</td>
				<td><a href = "SolutionView?id=${solution.getId()}"> More options</a></td>
			</tr>
		</c:forEach>
	</table>
<%@ include file="/WEB-INF/views/footer.jspf"%>
</body>
</html>
