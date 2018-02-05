<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit exercise</title>
</head>
<body>
<form action="AdminEx" method="post">
	Title: <input name="title" value="${exercise.title}"><br>
	Description:<br>
	<textarea rows="15" cols="60" name="description">${exercise.description}</textarea>,<br>
	<input type="hidden" value="${exercise.id}" name="id">
	<input type="submit" value="edit" name="edit">
</form>

</body>
</html>