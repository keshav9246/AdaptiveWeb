<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.src.pkg.Connection"%>
<%@page import="com.src.pkg.BehaviorController"%>
<%@page import="com.src.pkg.LoginController"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>

</head>
<body>



<form style="background-color: lightgrey;font-weight: bolder;font-size: x-large; padding-right: 1cm; padding-left: 1cm;">
<h3 style="text-align:center;">Welcome ${username}</h3>
<h3 style="text-align:right"> <a href = "./LogoutController" > Logout </a></h3>
</form>
  
<form style="text-align:center;background-color: lightgrey; padding: 1em;">
<h4 style="text-align:center;">Your account is now linked to Stack overflow. Please click on the link below to visit questions on java</h4>
<h2 style="text-align:center"><a href = "https://stackoverflow.com/questions/tagged/java?sort=votes&pageSize=15" target="_blank" > Stack overflow </a></h2></form>




<form style="background-color: lightgrey;padding: 1em;">

<h2 align="center"><font><strong>Your Login History</strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1" bgcolor="lightgreen">
<tr>

</tr>
<tr bgcolor="orange">
<td><b>Login Time</b></td>
<td><b>Logout Time</b></td>
<td><b>Duration</b></td>
</tr>
<% 

HttpSession sess = request.getSession(false);
String username = null;

if(sess != null)
{
 username = sess.getAttribute("username").toString();
}
else
{
	response.sendRedirect("index.jsp");
}
PreparedStatement stmt = null;

java.sql.Connection conn = Connection.getConnection();
String query = "Select * from login_history WHERE username = ? order by login desc Limit 10";


try {
	 stmt = conn.prepareStatement(query);
	stmt.setString(1, username);
	ResultSet rs = stmt.executeQuery();
	
	while(rs.next())
	{
		%>
		<tr bgcolor="lightpink">


		<td><%=rs.getTimestamp("login") %></td>
		<td><%=rs.getTimestamp("logout") %></td>
		<td><%=rs.getString("duration") %></td>
		</tr>

		<% 
		
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
finally {

	if (stmt != null) {
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	if (conn != null) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
%>
</table>


</form>
</body>
</html>