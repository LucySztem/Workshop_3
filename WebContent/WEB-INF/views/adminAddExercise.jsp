<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adding new Exercise</title>
</head>
<body>
	<form action="AdminExercise" method="post">
		Title: <input name="title"><br> 
		Description:<br>
		<textarea rows="15" cols="60" name="description"></textarea>
		,<br> <input type="submit" value="add" name="add">
	</form>
</body>
</html>