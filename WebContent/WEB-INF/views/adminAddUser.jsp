<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add user</title>
</head>
<body>
<form action="AdminUsers" method="post">
	<table>
		<tr>
			<td>Username</td>
			<td><input name="username"></td>
		</tr>
		<tr>
			<td>Group</td>
			<td>
				<select name="group">
					<c:forEach items="${groups}" var="group">
						<option value="${group.id}">${group.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>email</td>
			<td><input name="email"></td>
		</tr>
		<tr>
			<td>password</td>
			<td><input type="password" name="password"></td>
		</tr>
	</table>
	<input type="submit" value="add">
</form>

</body>
</html>