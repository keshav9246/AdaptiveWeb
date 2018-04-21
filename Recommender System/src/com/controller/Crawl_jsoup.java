

package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crawl_jsoup {


	//mthod to crawl wiki pages and save its content in files
	public static void crawl(String url, String path) throws IOException
	{
		Document doc = Jsoup.connect("https://en.wikibooks.org/wiki/Java_Programming").get();
		//String title = doc.title();

		String html=doc.html();
		Document doc1 = Jsoup.parseBodyFragment(html);
		Elements ele=doc1.select("div#mw-content-text");
		Elements resultLinks = ele.select("li");	
		Elements resulta = resultLinks.select("a");

		File file1 = new File(path+"htmlfiles");
		file1.mkdir();
		//getServletContext().getRealPath("/")
		File file2 = new File(path+"crawled_text_files");
		file2.mkdir();

		File file3 = new File(path+"linkfiles");
		file3.mkdir();

		//int i=0;
		for(int i=7;i<resulta.size();i++)
		{
			if(resulta.get(i).hasText()){
				String title = resulta.get(i).text();
				if(title.matches("Basic I/O"))
					title="Basic IO";
				Document textDoc = Jsoup.connect("https://en.wikibooks.org"+resulta.get(i).attr("href")).get();			
				Document text = Jsoup.parseBodyFragment(textDoc.html());
				Elements depth2Element = text.select("div#mw-content-text").select("p");
				Elements depth2Code = text.select("div#mw-content-text").select("pre");
				String divider =System.getProperty("file.separator");
				//Write objects to write into the text files
				PrintWriter writer1 = null;
				PrintWriter writer2;
				PrintWriter writer3;

				writer2 = new PrintWriter(path+"htmlfiles\\"+title+".txt", "UTF-8");
				writer3 = new PrintWriter(path+"linkfiles\\"+title+".txt", "UTF-8");

				for(int j=0;j<depth2Element.size();j++){
					writer1 = new PrintWriter(path+divider+"crawled_text_files\\"+title+j+".txt", "UTF-8");
					writer1.println(depth2Element.get(j).text());
					writer1.close();

					//	System.out.println(writer1);
				}


				for(int j=0;j<depth2Code.size();j++){
					writer1 = new PrintWriter(path+divider+"crawled_text_files\\"+title+"_code"+j+".txt", "UTF-8");
					writer1.println(depth2Code.get(j).text());
					writer1.close();

					System.out.println(writer1);
				}


				writer2.println(ele.html());
				writer3.println(resulta.get(i).attr("href"));

				writer2.close();
				writer3.close();
			}
		}

	}


	//method to save the text content of the crawled pages in file directory
	public static void crawlText(String url,String title,String html,String path) throws IOException
	{
		if(title.matches("Basic I/O"))
			title="Basic IO";
		Document doc2 = Jsoup.connect("https://en.wikibooks.org"+url).get();
		String html2=doc2.html();
		//Document doc3 = Jsoup. 
		Document doc3 = Jsoup.parseBodyFragment(html2);
		Elements ele1=doc3.select("div#mw-content-text").select("p");
		PrintWriter writer;
		PrintWriter writer1;
		PrintWriter writer2;

		if(System.getProperty("os.name").toLowerCase().startsWith("windows"))
		{
			writer = new PrintWriter(path+"crawled_text_files\\"+title+".txt", "UTF-8");
			writer1 = new PrintWriter(path+"htmlfiles\\"+title+".txt", "UTF-8");
			writer2 = new PrintWriter(path+"linkfiles\\"+title+".txt", "UTF-8");
		}

		else
		{
			writer = new PrintWriter(path+"crawled_text_files/"+title+".txt", "UTF-8");
			writer1 = new PrintWriter(path+"htmlfiles/"+title+".html", "UTF-8");
			writer2 = new PrintWriter(path+"linkfiles/"+title+".txt", "UTF-8");
		}

		writer.println(ele1.text());
		writer1.println(html);
		writer2.println(url);
		writer.close();
		writer1.close();
		writer2.close();
	}	

}
