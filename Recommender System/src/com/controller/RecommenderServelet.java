package com.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryparser.classic.ParseException;
/**
 * Servlet implementation class RecommenderServelet
 */
@WebServlet("/RecommenderServelet")
public class RecommenderServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecommenderServelet() {
		super();

	}



	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// String initial = config.getInitParameter("initial");
		try {
			Crawl_jsoup.crawl("",getServletContext().getRealPath("/"));
		}
		catch (Exception e) {
		}
	}






	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

		response.getWriter().append("Served at: ").append(request.getContextPath());

		String id=request.getParameter("id");
		if(id==null)
			id="0";
		try {

			String output[]=SimpleLuceneIndexing.mainCall(Integer.parseInt(id),getServletContext().getRealPath("/"));
			System.out.println("AnswerARRY"+output);
			BufferedReader br = null;
			BufferedReader brLink = null;
			String path=null;
			if(System.getProperty("os.name").toLowerCase().startsWith("windows"))
			{
				path=getServletContext().getRealPath("/")+"crawled_text_files\\";
			}
			else
			{
				path=getServletContext().getRealPath("/")+"crawled_text_files/";
			}
			System.out.println("ye hai path:"+path);
			for(int i=0;i<output.length;i++)
			{
				String temppath=null;
				System.out.println(output[i]+"outputi");
				//temppath=path+output[i];
				//System.out.println(temppath+"TEMPPATH");
				br = new BufferedReader(new FileReader(output[i]));
				String sCurrentLine="",finalOutput="";
				sCurrentLine = br.readLine(); 
				finalOutput += sCurrentLine;
				br.close();

				temppath = output[i].replaceAll("crawled_text_files", "linkfiles");
				System.out.println(temppath.charAt(temppath.length()-5));
				while(Character.isDigit(temppath.charAt(temppath.length()-5)))
				{
					System.out.println("INSIDE CHECK");
					//temppath=temppath.deleteCharAt();
					temppath = new StringBuilder(temppath).deleteCharAt(temppath.length()-5).toString();

				}
				System.out.println(temppath.substring(temppath.length()-9, temppath.length()-4));
				if(temppath.substring(temppath.length()-9, temppath.length()-4).equals("_code"))
				{
					temppath = new StringBuilder(temppath).deleteCharAt(temppath.length()-5).toString();
					temppath = new StringBuilder(temppath).deleteCharAt(temppath.length()-5).toString();
					temppath = new StringBuilder(temppath).deleteCharAt(temppath.length()-5).toString();
					temppath = new StringBuilder(temppath).deleteCharAt(temppath.length()-5).toString();
					temppath = new StringBuilder(temppath).deleteCharAt(temppath.length()-5).toString();
				}
				System.out.println("LINKPATH::::::"+temppath);
				brLink = new BufferedReader(new FileReader(temppath));
				String currentLink = "", finalLink="";
				currentLink= brLink.readLine();
				finalLink += currentLink;
				brLink.close();
				System.out.println(finalLink+"-"+i);
				String messageString="message"+i;
				String link = "link"+i;
				//finalOutput = finalOutput.replace("(Â vÂ â?¢Â dÂ â?¢Â eÂ )"," ");
				finalOutput.replaceAll("Â", "");

				//finalOutput=finalOutput.substring(0, 500);
				request.setAttribute(link, "https://en.wikibooks.org"+finalLink);
				request.setAttribute(messageString,   finalOutput+".....");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/index.jsp").forward(request, response);



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
