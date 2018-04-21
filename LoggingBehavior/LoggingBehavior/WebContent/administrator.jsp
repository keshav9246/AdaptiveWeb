<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.src.pkg.Connection"%>
<%@page import="com.src.pkg.BehaviorController"%>
<%@page import="com.src.pkg.LoginController"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@ page import="org.json.JSONArray" %> 
<%@ page import="org.json.JSONObject" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Admin</title>
<script src="https://d3js.org/d3.v3.min.js"></script>
</head>
<body>


<form style="background-color: lightgrey;font-weight: bolder;font-size: x-large; padding-right: 1cm; padding-left: 1cm;">
<h3 style="text-align:center;">Welcome Admin</h3>
<h3 style="text-align:right"> <a href = "./LogoutController" > Logout </a></h3>
</form>

<h4><a href = "admin2.jsp">Click here to view visualizations</a></h4>

<form style="background-color: lightgrey; text-align: center">




<h2 align="center"><font><strong>Login details</strong></font></h2>
  


<table align="center" cellpadding="5" cellspacing="5" border="1" bgcolor="lightgreen" id="loginTable">
<tr>

</tr>
<tr></tr>
<tr bgcolor="orange">
<td><b>User</b></td>
<td><b>Login Time</b></td>
<td><b>Logout Time</b></td>
<td><b>Duration</b></td>
</tr>
<% 
JSONArray jsonArray = new JSONArray();
JSONArray jsonArray1 = new JSONArray();
PreparedStatement stmt = null;
PreparedStatement stmt1 = null;
PreparedStatement stmt2 = null;
PreparedStatement stmt3 = null;
PreparedStatement stmt4 = null;
java.sql.Connection conn = Connection.getConnection();
String query = "Select * from login_history order by login desc";
String query6 = "select username, TIME_TO_SEC(duration) as duration from TotalLogin;";

try {
	 stmt = conn.prepareStatement(query);
	ResultSet rs = stmt.executeQuery();
	
	while(rs.next())
	{
		
		%>
		<tr bgcolor="lightpink">
		
		<td><%=rs.getString("username") %></td>
		<td><%=rs.getTimestamp("login") %></td>
		<td><%=rs.getTimestamp("logout") %></td>
		<td><%=rs.getTime("duration") %></td>
		</tr>



		<% 
	}
	%>

	</table>

<% 	
	
System.out.println("Starting");
	stmt1 = conn.prepareStatement(query6);
	ResultSet rs1 = stmt1.executeQuery();
	while(rs1.next())
	{   JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", rs1.getString("username"));
		jsonObject.put("duration", rs1.getInt("duration"));
		jsonArray.put(jsonObject);
			
	}
	System.out.println(jsonArray.toString());
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

%>
<input type="text" id="Array"  value="${jsonArray}"/>

<div id="java" style="display: none;">${jsonArray.toString()}</div>

 <% 
    JSONArray j = jsonArray;
 %>




<script>

//document.write("Inside script");
//var json = "${jsonArray}";

var details = <%=j%>;





var data = [20,30,40,50];

var width = 1500;
var height = 1500;


var color = d3.scale.ordinal().range(["red","green","blue","orange"]);

var colorBar = d3.scale.ordinal().domain([0,600]).range(["red","blue"]);

var canvas = d3.select("body").append("svg").attr("width",width).attr("height", height);

//var circle = canvas.append("circle").attr("cx",250).attr("cy",200).attr("r",50).attr("fill","red");
//var heightScale = d3.scale.linear().domain([0,100]).range([0,height]); 

//var color=d3.scale.linear().domain([0,60]).range([red,green]);

var group = canvas.append("g").attr("transform","translate(300,300)");

var arc = d3.svg.arc().innerRadius(200).outerRadius(300);



var pie = d3.layout.pie().value(function(d) {return d;});

var arcs = group.selectAll(".arc").data(pie(data)).enter().append("g").attr("class","arc");

arcs.append("path").attr("d",arc).attr("fill",function(d) {return color(d.data);});

arcs.append("text").attr("transform" , function(d){return "translate("+arc.centroid(d)+")";}).attr("text-anchor","middle").attr("font-size","1.5em").text(function(d){ return d.data;});

//var bars = canvas.selectAll("rect").data(dataArray).enter().append("rect").attr("height", function(d){return d*10}).attr("width",50).style("color","red").attr("x",function(d,i){return i*100});


var margin = {top: 20, right: 30, bottom: 30, left: 50},
    width = 960 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;

var x = d3.scale.ordinal()
    .rangeRoundBands([0, width], .1);

var y = d3.scale.linear()
    .range([height, 0]);

var xAxis = d3.svg.axis()
    .scale(x)
    .orient("bottom");

var yAxis = d3.svg.axis()
    .scale(y)
    .orient("left")
    .ticks(10);

var svg = d3.select("body").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

x.domain(details.map(function(d) { return d.name; }));
y.domain([0, d3.max(details, function(d) { return d.duration; })]);

svg.append("g")
    .attr("class", "x axis")
    .attr("transform", "translate(0," + height + ")")
    .call(xAxis);

svg.append("g")
    .attr("class", "y axis")
    .call(yAxis)
    .append("text")
    .attr("transform", "rotate(-90)")
    .attr("y", 6)
    .attr("dy", ".60em")
    .style("text-anchor", "end")
    .text("Total Logged in time");

svg.selectAll(".bar")
    .data(details)
    .enter().append("rect")
    .attr("class", "bar")
    .attr("x", function(d) { return x(d.name); })
    .attr("width", x.rangeBand())
    .attr("y", function(d) { return y(d.duration); })
    .attr("height", function(d) { return height - y(d.duration); })
    .attr("fill", function(d){ return colorBar(d.data);})

</script>



<h2 align="center"><font><strong>Behavior Logs Data</strong></font></h2>
  


<table align="center" cellpadding="5" cellspacing="5" border="1"  bgcolor="lightgreen" style="word-wrap: break-word; table-layout:fixed">
<tr>

</tr>

<tr bgcolor="orange">
<td><b>User</b></td>
<td><b>No. of questions user visited</b></td>
<td><b>No. of times user upvoted a question</b></td>
<td><b>No. of times user downvoted a question</b></td>
<td><b>No. of times user changed the page</b></td>

</tr>
<% 


String query1 = "Select username,upVoteCount,downVoteCount,questionCount,nextCount from users";


try {
	 stmt2 = conn.prepareStatement(query1);
	ResultSet rs = stmt2.executeQuery();
	
	while(rs.next())
	{
		%>
		<tr bgcolor="lightpink">

		<td><%=rs.getString("username") %></td>
		<td><%=rs.getInt("questionCount") %></td>
		<td><%=rs.getInt("upVoteCount") %></td>
		<td><%=rs.getInt("downVoteCount") %></td>
		<td><%=rs.getInt("nextCount") %></td>
		</tr>

		<% 
		
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


%>

</table>



<h2 align="center"><font><strong>Questions visited by each user</strong></font></h2>
  


<table align="center" cellpadding="5" cellspacing="5" border="1" bgcolor="lightgreen" style="word-wrap: break-word; table-layout:fixed">
<tr>

</tr>

<tr bgcolor="orange">
<td><b>User</b></td>
<td><b>Question visited</b></td>
</tr>
<% 


String query2 = "Select * from question";


try {
	 stmt3 = conn.prepareStatement(query2);
	ResultSet rs = stmt3.executeQuery();
	
	while(rs.next())
	{
		%>
		<tr bgcolor="lightpink">

		<td><%=rs.getString("username") %></td>
		<td><%=rs.getString("question") %></td>
		</tr>

		<% 
		
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
finally {

	if (stmt3 != null) {
		try {
			stmt3.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

}
%>

</table>

<h2 align="center"><font><strong>TagsCount</strong></font></h2>
  


<table align="center" cellpadding="5" cellspacing="5" border="1"  bgcolor="lightgreen" style="word-wrap: break-word; table-layout:fixed">
<tr>

</tr>

<tr bgcolor="orange">
<td><b>User</b></td>
<td><b>Java</b></td>
<td><b>C</b></td>
<td><b>C++</b></td>
<td><b>Python</b></td>
<td><b>JavaScript</b></td>
<td><b>JQuery</b></td>
</tr>




<% 

String query5 = "Select * from tagsCount";


try {
	 stmt4 = conn.prepareStatement(query5);
	ResultSet rs = stmt4.executeQuery();
	
	while(rs.next())
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", rs.getString("username"));
		jsonObject.put("java", rs.getInt("Java"));
		jsonObject.put("c", rs.getInt("C"));
		jsonObject.put("cpp", rs.getInt("CPP"));
		jsonObject.put("python", rs.getInt("Python"));
		jsonObject.put("javaScript", rs.getInt("JavaScript"));
		jsonObject.put("iQuery", rs.getInt("jQuery"));
		jsonArray1.put(jsonObject);
		
		%>
		<tr bgcolor="lightpink">
		<td><%=rs.getString("username") %></td>
		<td><%=rs.getInt("Java") %></td>	
		<td><%=rs.getInt("c") %></td>
		<td><%=rs.getInt("cpp") %></td>
		<td><%=rs.getInt("python") %></td>
		<td><%=rs.getInt("JavaScript") %></td>
		<td><%=rs.getInt("jQuery") %></td>	
		</tr>
		<%
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
finally 
{
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


 <% 
    JSONArray j2 = jsonArray1;
 %>


<script>

var logDetails = <%=j2%>;


var margin = {top: 20, right: 30, bottom: 30, left: 50},
    width = 960 - margin.left - margin.right,
    height = 500 - margin.top - margin.bottom;

var x = d3.scale.ordinal()
    .rangeRoundBands([0, width], .1);

var y = d3.scale.linear()
    .range([height, 0]);

var xAxis = d3.svg.axis()
    .scale(x)
    .orient("bottom");

var yAxis = d3.svg.axis()
    .scale(y)
    .orient("left")
    .ticks(10);

var svg = d3.select("body").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
  	.append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

x.domain(logDetails.map(function(d) { return d.name; }));
y.domain([0, d3.max(details, function(d) { return d.java; })]);
y.domain([1, d3.max(details, function(d) { return d.c; })]);
y.domain([2, d3.max(details, function(d) { return d.cpp; })]);
y.domain([3, d3.max(details, function(d) { return d.python; })]);
y.domain([4, d3.max(details, function(d) { return d.javaScript; })]);
y.domain([5, d3.max(details, function(d) { return d.jQuery; })]);


svg.append("g")
    .attr("class", "x axis")
    .attr("transform", "translate(0," + height + ")")
    .call(xAxis);

svg.append("g")
    .attr("class", "y axis")
    .call(yAxis)
    .append("text")
    .attr("transform", "rotate(-90)")
    .attr("y", 6)
    .attr("dy", ".60em")
    .style("text-anchor", "end")
    .text("Number of respective questions viewes");

svg.selectAll(".bar")
    .data(details)
    .enter().append("rect")
    .attr("class", "bar")
    .attr("x", function(d) { return x(d.name); })
    .attr("width", x.rangeBand())
    .attr("y", function(d) { return y(d); })
    .attr("height", function(d) { return height - y(d); })
    .attr("fill", function(d){ return colorBar(d.data);})




</script>


<h2 align="center"><font><strong>Logging Reasons</strong></font></h2>
  

<table align="center" cellpadding="5" cellspacing="5" border="1" bgcolor="lightgreen" style="word-wrap: break-word; table-layout:fixed">


<tr bgcolor="orange">
<td>Behavior Log</td>
<td><i>Why ?</i></td>
</tr>

<tr bgcolor="lightpink">
<td>Capturing the number of times user clicks on a question to view the answer</td>
<td>This gives us a statistics on the relevancy of the data that we are providing to the user.</td>
</tr>

<tr bgcolor="lightpink">
<td>Capturing the number of times the user has to go to the next page to get a better answer</td>
<td>This would give us an idea about how well are we sorting out the results to user so that we return only the closest match on the top</td>
</tr>

<tr bgcolor="lightpink">
<td>Capturing the number of upvotes and downvotes that user makes</td>
<td>This gives us an idea on how interactive a particular user is with the system.</td>
</tr>

<tr bgcolor="lightpink">
<td>Capturing all the questions that user visits </td>
<td>This gives us an idea on the 'frequency of searching questions' for different users. We can figure oout how much active the user is</td>
</tr>

<tr bgcolor="lightpink">
<td>Capturing the duration the user was logged in the system</td>
<td>This when compared with the number of question visits shows if the user is actually using the application or is just logged on</td>
</tr>

<tr bgcolor="lightpink">
<td>Capturing the Tags of the questions that user clicked on</td>
<td>This stat will provide us the information about the technologies the users are more interested in which apparently would be the trending technology.</td>
</tr>

</table>
</form>
<br><br><br><br>
<br><br><br><br></body>
</html>