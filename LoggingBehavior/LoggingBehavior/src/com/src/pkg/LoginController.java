package com.src.pkg;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.equals("admin") && password.equals("admin"))
		{
			HttpSession session = null;
			session = request.getSession(true);
			 session.setAttribute("username", "admin");
			response.sendRedirect("administrator.jsp");
			
		}
		
		else
		{
		PreparedStatement stmt = null;
		HttpSession session = null;
		
		java.sql.Connection conn = Connection.getConnection();
		String query = "select * from users where username = ? AND pass = ?"  ;
		
		try {
			 stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
					{
				java.sql.Timestamp login = new java.sql.Timestamp(new java.util.Date().getTime());
				   
				 session = request.getSession(true);
				 session.setAttribute("username", username);
				 session.setAttribute("login", login);
				response.sendRedirect("profile.jsp");
				
					}
			else
			{
				response.sendRedirect("error.jsp");
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

		
	}
	
	
	}
}

