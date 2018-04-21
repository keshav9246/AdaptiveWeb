package com.src.pkg;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		//long login = 0;
		java.sql.Timestamp login =null;
		java.sql.Timestamp logout =null;
		//long logout = 0;
		
		String username = session.getAttribute("username").toString();;
		Time duration = null;
		
			if(username != "admin")
			{
				try
	
				{
			
		     
		          login = (Timestamp) session.getAttribute("login");
				  logout = new java.sql.Timestamp(new java.util.Date().getTime());
				  System.out.println("Login: "+login);
				  System.out.println("Logout: "+logout);
		   
			session.invalidate();
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//String dateStart = login.toString();
		//String dateStop = logout.toString();

		//HH converts hour in 24 hours format (0-23), day calculation
		//SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);

		//java.util.Date d1 = null;
	//	java.util.Date d2 = null;
		//Date parsedDate = sdf.parse(date);
		try {
			//d1 = format.parse(dateStart);
			//d2 = format.parse(dateStop);

			//in milliseconds
			
			if(login != null && logout != null)
			{
			long diff = logout.getTime() - login.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");
			
			String dur = diffHours+":"+diffMinutes+":"+diffSeconds;
			duration = Time.valueOf(dur);
			System.out.println(duration);
			
		
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//java.sql.Timestamp duration = (logout-login);
		
		PreparedStatement stmt = null;

		PreparedStatement stmt1 = null;

		java.sql.Connection conn = Connection.getConnection();
		String query = "INSERT INTO login_history (login, logout, duration, username) VALUES (?,?,?,?)";
		String query1 = "Update TotalLogin set duration = addtime(duration,?) where username = ?";
		
		try {
			 stmt = conn.prepareStatement(query);
			stmt.setTimestamp(1, login);
			stmt.setTimestamp(2, logout);
			stmt.setTime(3, duration);
			stmt.setString(4, username);
			stmt.executeUpdate();
			
			stmt1= conn.prepareStatement(query1);
			stmt1.setTime(1, duration);
			stmt1.setString(2, username);
			stmt1.executeUpdate();
			
			
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
		response.sendRedirect("index.jsp");
		
		}
			else
			{
		
		response.sendRedirect("index.jsp");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
