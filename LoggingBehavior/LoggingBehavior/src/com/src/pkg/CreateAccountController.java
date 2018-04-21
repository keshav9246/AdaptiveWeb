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

/**
 * Servlet implementation class CreateAccountController
 */
@WebServlet("/CreateAccountController")
public class CreateAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		//HttpSession session = null;
		
		java.sql.Connection conn = Connection.getConnection();
		String query = "select username from users";
		String query1= "Insert into users values (?,?,0,0,0,0,?)";
		String query2 = "Insert into tagscount values(?,0,0,0,0,0,0)";
		String query3 = "Insert into TotalLogin values(?, 00:00:00)";
		
		try {
			if(username.equals("admin") && password.equals("admin"))
			{
				response.sendRedirect("createError.jsp");
				
			}
			else
			{
			 stmt = conn.prepareStatement(query);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
					{
			//	java.sql.Timestamp login = new java.sql.Timestamp(new java.util.Date().getTime());
				   
				 //session = request.getSession(true);
				 //session.setAttribute("username", username);
				 //session.setAttribute("login", login);
				//response.sendRedirect("profile.jsp");
				String name = rs.getString("username");
					if(username == name)
						{
						response.sendRedirect("createError.jsp");
						}
					}
					stmt1=conn.prepareStatement(query1);
						stmt1.setString(1, username);
						stmt1.setString(2, password);
						stmt1.setString(3, "");
						int check = stmt1.executeUpdate();
						if(check == 0)
						{
							response.sendRedirect("createError.jsp");
							
						}
						else {
							
							response.sendRedirect("createSuccess.jsp");
						}
						
					stmt2 = conn.prepareStatement(query2);
					stmt2.setString(1, username);
					stmt2.executeUpdate();
					
					stmt3 = conn.prepareStatement(query3);
					stmt3.setString(1, username);
					stmt3.executeUpdate();
					
					
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
			
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (stmt2 != null) {
				try {
					stmt2.close();
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
