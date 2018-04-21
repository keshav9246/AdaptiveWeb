package com.src.pkg;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Servlet implementation class BehaviorController
 */
@WebServlet("/BehaviorController")
public class BehaviorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BehaviorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String content = request.getParameter("content");
		
		if (content != null)
		{
			response.sendRedirect("profileLog.jsp");
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("inside dopost");
		response.setContentType("text/plain");
		String content = request.getParameter("content");
		String className = request.getParameter("className");
		String dTags = request.getParameter("tag");
		System.out.println("content is :" +content);
		System.out.println("class is :" +className);
		System.out.println("tags are:" +dTags);
		
if(content.equals("up vote"))
{
	HttpSession session = request.getSession(false);
	String username = null;
	
	if(session != null)
	{
	 username = session.getAttribute("username").toString();
	}
	else
	{
		response.sendRedirect("index.jsp");
	}
	PreparedStatement stmt = null;

	java.sql.Connection conn = Connection.getConnection();
	String query = "UPDATE users SET upVoteCount = upVoteCount+1 WHERE username = ?";
	
	try {
		 stmt = conn.prepareStatement(query);
		stmt.setString(1, username);
		stmt.executeUpdate();
		
		
		
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
}

if(content.equals("down vote"))
{
	String username = null;
	HttpSession session = request.getSession(false);
	if(session != null)
	{
	 username = session.getAttribute("username").toString();
	}
	else
	{
		response.sendRedirect("index.jsp");
	}
	PreparedStatement stmt = null;

	java.sql.Connection conn = Connection.getConnection();
	String query = "UPDATE users SET downVoteCount = downVoteCount+1 WHERE username = ?";
	
	try {
		 stmt = conn.prepareStatement(query);
		stmt.setString(1, username);
		stmt.executeUpdate();
		
		
		
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
}

if(className.equals("question-hyperlink"))
{
	String username = null;
	//String question = request.getParameter("className");
	HttpSession session = request.getSession(false);
	if(session != null)
	{
	 username = session.getAttribute("username").toString();
	}
	else
	{
		response.sendRedirect("index.jsp");
	}
	
	
	int javaCount = dTags.split("\\bjava\\b").length - 1;
	int cCount = dTags.split("\\bc\\b").length - 1;
	int cppCount = dTags.split("\\bc++\\b").length - 1;
	int javaScriptCount = dTags.split("\\bjavascript\\b").length - 1;
	int pythonCount = dTags.split("\\bpython\\b").length - 1;
	int jQueryCount = dTags.split("\\bjquery\\b").length - 1;
	//int cHCount = dTags.split("\\bc#\\b").length - 1;
	
	PreparedStatement stmt = null;
	PreparedStatement stmt2 = null;
	PreparedStatement stmt3 = null;
	
	java.sql.Connection conn = Connection.getConnection();
	String query1 = "UPDATE users SET questionCount = questionCount+1 , tags = CONCAT(tags,?,?) WHERE username = ?";
	String query2 = "INSERT INTO question (question, username) VALUES (?,?)";
	String query3 = "Update tagsCount set Java = java + ?, C = C + ?,Cpp =Cpp + ?, Python = Python + ?,JavaScript = JavaScript + ?, JQuery = JQuery + ? where username = ?";
	//String query3 = "Update";
	
	try {
		 stmt = conn.prepareStatement(query1);
		 stmt.setString(1, " ");
		 stmt.setString(2, dTags);
		stmt.setString(3, username);
		stmt.executeUpdate();
		
		stmt2 = conn.prepareStatement(query2);
		stmt2.setString(1, content);
		stmt2.setString(2, username);
		stmt2.executeUpdate();
		
		stmt3 = conn.prepareStatement(query3);
		stmt3.setInt(1, javaCount);
		stmt3.setInt(2, cCount);
		stmt3.setInt(3, cppCount);
		stmt3.setInt(4, pythonCount);
		stmt3.setInt(5, javaScriptCount);
		stmt3.setInt(6, jQueryCount);
		stmt3.setString(7, username);
		stmt3.executeUpdate();
		
	} 
	catch(MySQLIntegrityConstraintViolationException ex)
	{
		System.out.println("Question already exists in the database");
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {

		if (stmt != null) {
			try {
				stmt.close();
			//	stmt2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (stmt3 != null) {
			try {
				stmt3.close();
			//	stmt2.close();
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
}
		
if(className.equals("page-numbers") || className.equals("page-numbers next"))
{
	String username = null;
	//String question = request.getParameter("className");
	HttpSession session = request.getSession(false);
	if(session != null)
	{
	 username = session.getAttribute("username").toString();
	}
	else
	{
		response.sendRedirect("index.jsp");
	}
	PreparedStatement stmt = null;
	
	
	java.sql.Connection conn = Connection.getConnection();
	String query1 = "UPDATE users SET nextCount = nextCount+1 WHERE username = ?";
	
	
	try {
		 stmt = conn.prepareStatement(query1);
		stmt.setString(1, username);
		stmt.executeUpdate();
		
		
	} 
	
	catch (SQLException e) {
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
}
		
	}

	}
