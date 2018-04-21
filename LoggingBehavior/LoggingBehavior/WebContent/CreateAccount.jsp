<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Account</title>
</head>
<body>

<form action="CreateAccountController" method="post" style=" padding-right: 1cm; padding-left: 1cm; text-align: center; align-self: center; vertical-align: center;horizontal-align:center;">

<table align="center">
<tr><td>Enter username :</td> <td><input type="text" name="username"> </td></tr>
<tr><td>Enter password :</td> <td><input type="password" name="password"><td><tr>
</table>
<input type="submit" value="Create">
<br>

</form>
</body>
</html>