package com.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.standard.ClassicFilter;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;


/**
 * Lucene Demo: basic similarity based content indexing 
 * @author Sharonpova
 * Current sample files fragments of wikibooks and stackoverflow. 
 */





public class SimpleLuceneIndexing {



	private static void indexDirectory(IndexWriter writer, File dir) throws IOException {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				indexDirectory(writer, f); // recurse
			} else if (f.getName().endsWith(".txt")) {
				// call indexFile to add the title of the txt file to your index (you can also index html)
				indexFile(writer, f);
			}
		}
	}

	private static void indexFile(IndexWriter writer, File f) throws IOException {
		System.out.println("Indexing " + f.getPath());

		//Field filename = new Field(SimpleLuceneIndexing.CONTENTS, new FileReader(file));


		Document doc = new Document();
		doc.add(new TextField("filename", f.toString(), TextField.Store.YES));
		//	doc.add(new Field("url",url.toString(), Store.YES, Index.ANALYZED));


		//	doc.add(new Field("url", url, Field.Store.YES, Field.Index.NOT_ANALYZED));
		//document.add(new TextField("firstName", firstName , Field.Store.YES));


		//open each file to index the content
		try{
			//	LinkParser lp = new LinkParser(url);

			FileInputStream is = new FileInputStream(f);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuffer stringBuffer = new StringBuffer() ;
			String line = null;
			while((line = reader.readLine())!=null){
				stringBuffer.append(line).append("\n");
			}
			reader.close();
			doc.add(new TextField("contents", stringBuffer.toString(), TextField.Store.YES));


		}catch (Exception e) {

			System.out.println("something wrong with indexing content of the files");
		}    



		writer.addDocument(doc);

	}	



	public static String[] mainCall(int index,String path) throws IOException, Exception
	{
		String answerArray[]=new String[10];
		// String type[]={"answer","question","answer accepted-answer","answer","question","question","answer","answer","answer","answer accepted-answer"};
		String text[]=new String[10];
		text[0]="In a class i can have as many constructors as i want with different argument types. i made all the constructors as private it didn't give any error because my implicit default constructor was public but when i declared my implicit default constructor as private then its showing an error while extending the class.  why?       this works fine         this can not be inherited. public class demo4  {     private string name;     private int age;     private double sal;      private demo4(string name  int age) {         this.name=name;         this.age=age;        }      demo4(string name) {         this.name=name;     }      demo4() {         this(\\\"unknown\\\"  20);         this.sal=2000;     }      void show(){         system.out.println(\\\"name\\\"+name);         system.out.println(\\\"age: \\\"+age);     } }  public class demo4  {     private string name;     private int age;     private double sal;      private demo4(string name  int age) {         this.name=name;         this.age=age;     }      demo4(string name) {         this.name=name;     }      private demo4() {         this(\\\"unknown\\\"  20);         this.sal=2000;     }      void show() {         system.out.println(\\\"name\\\"+name);         system.out.println(\\\"age: \\\"+age);     } }";
		text[1]="I was presented with this question in an end of module open book exam today and found myself lost. i was reading Head first Javaand both definitions seemed to be exactly the same. i was just wondering what the MAIN difference was for my own piece of mind. i know there are a number of similar questions to this but, none i have seen which provide a definitive answer.Thanks, Darren";
		text[2]="Inheritance is when a 'class' derives from an existing 'class'.So if you have a Person class, then you have a Student class that extends Person, Student inherits all the things that Person has.There are some details around the access modifiers you put on the fields/methods in Person, but that's the basic idea.For example, if you have a private field on Person, Student won't see it because its private, and private fields are not visible to subclasses.Polymorphism deals with how the program decides which methods it should use, depending on what type of thing it has.If you have a Person, which has a read method, and you have a Student which extends Person, which has its own implementation of read, which method gets called is determined for you by the runtime, depending if you have a Person or a Student.It gets a bit tricky, but if you do something likePerson p = new Student();p.read();the read method on Student gets called.Thats the polymorphism in action.You can do that assignment because a Student is a Person, but the runtime is smart enough to know that the actual type of p is Student.Note that details differ among languages.You can do inheritance in javascript for example, but its completely different than the way it works in Java.";
		text[3]="Polymorphism: The ability to treat objects of different types in a similar manner.Example: Giraffe and Crocodile are both Animals, and animals can Move.If you have an instance of an Animal then you can call Move without knowing or caring what type of animal it is.Inheritance: This is one way of achieving both Polymorphism and code reuse at the same time.Other forms of polymorphism:There are other way of achieving polymorphism, such as interfaces, which provide only polymorphism but no code reuse (sometimes the code is quite different, such as Move for a Snake would be quite different from Move for a Dog, in which case an Interface would be the better polymorphic choice in this case.In other dynamic languages polymorphism can be achieved with Duck Typing, which is the classes don't even need to share the same base class or interface, they just need a method with the same name.Or even more dynamic like Javascript, you don't even need classes at all, just an object with the same method name can be used polymorphically.";
		text[4]="I found out that the above piece of code is perfectly legal in Java. I have the following questions. ThanksAdded one more question regarding Abstract method classes.public class TestClass{public static void main(String[] args) {TestClass t = new TestClass();}private static void testMethod(){abstract class TestMethod{int a;int b;int c;abstract void implementMe();}class DummyClass extends TestMethod{void implementMe(){}}DummyClass dummy = new DummyClass();}}";
		text[5]="In java it's a bit difficult to implement a deep object copy function. What steps you take to ensure the original object and the cloned one share no reference? ";
		text[6]="You can make a deep copy serialization without creating some files. Copy: Restore:.  ByteArrayOutputStream bos = new ByteArrayOutputStream();ObjectOutputStream oos = new ObjectOutputStream(bos);oos.writeObject(object);oos.flush();oos.close();bos.close();byte[] byteData = bos.toByteArray(); ByteArrayInputStream bais = new ByteArrayInputStream(byteData);(Object) object = (Object) new ObjectInputStream(bais).readObject();";
		text[7]="Java has the ability to create classes at runtime. These classes are known as Synthetic Classes or Dynamic Proxies. See for more information. Other open-source libraries, such as and also allow you to generate synthetic classes, and are more powerful than the libraries provided with the JRE. Synthetic classes are used by AOP (Aspect Oriented Programming) libraries such as Spring AOP and AspectJ, as well as ORM libraries such as Hibernate. ";
		text[8]="comment this code /*if (savedinstancestate == null) {     getsupportfragmentmanager().begintransaction()             .add(r.id.container  new placeholderfragment())             .commit(); }*/";
		text[9]="A safe way is to serialize the object, then deserialize.This ensures everything is a brand new reference.about how to do this efficiently. Caveats: It's possible for classes to override serialization such that new instances are created, e.g. for singletons.Also this of course doesn't work if your classes aren't Serializable.";

		String readPath = path + "crawled_text_files";

		File dataDir = new File(readPath);
		if (!dataDir.exists() || !dataDir.isDirectory()) {
			throw new IOException(
					dataDir + " does not exist or is not a directory");
		}
		Directory indexDir = new RAMDirectory();

		// Specifying the analyzer for tokenizing text.
		StandardAnalyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(indexDir, config);

		// call indexDirectory to add to your index
		// the names of the txt files in dataDir
		indexDirectory(writer, dataDir);
		writer.close();

		List<Keyword> temp=tokenStream(text[index]);
		String inputTemp="";
		for(int i=0;i<10;i++)
		{
			Keyword ky=temp.get(i);
			Set<String> tempTerms = new HashSet<String>();
			tempTerms=ky.getTerms();
			for(String s: tempTerms)
			{
				inputTemp = inputTemp+s+" ";
				System.out.println(s);
			}
		}

		String queryString = "contents:"+inputTemp;

		Query q = new QueryParser("contents", analyzer).parse(queryString);
		int hitsPerPage = 10;
		IndexReader reader = null;



		TopScoreDocCollector collector = null;
		IndexSearcher searcher = null;
		reader = DirectoryReader.open(indexDir);
		searcher = new IndexSearcher(reader);
		collector = TopScoreDocCollector.create(hitsPerPage);
		searcher.search(q, collector);



		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		System.out.println("Found " + hits.length + " hits.");
		//System.out.println();

		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			Document d;
			d = searcher.doc(docId);
			answerArray[i]=d.get("filename");
			System.out.println((i + 1) + ". " + d.get("filename"));
		}

		reader.close();
		return answerArray;

	}


	public static List<Keyword> tokenStream(String input) throws IOException {
		TokenStream tok = null;
		ClassicTokenizer tokenTemp = null;


		try {
			input = input.replaceAll("-+", "-0");

			input = input.replaceAll("[\\p{Punct}&&[^'-]]+", " ");

			input = input.replaceAll("(?:'(?:[tdsm]|[vr]e|ll))+\\b", "");


			System.out.println(input);



			tokenTemp = new ClassicTokenizer();
			tokenTemp.setReader(new StringReader(input));




			tok = new LowerCaseFilter(tokenTemp);   
			tok = new ClassicFilter(tok);
			tok = new ASCIIFoldingFilter(tok);
			tok = new StopFilter(tok, EnglishAnalyzer.getDefaultStopSet());				//removing the stop words from the received text

			List<Keyword> keywords = new LinkedList<Keyword>();
			CharTermAttribute token = tok.getAttribute(CharTermAttribute.class);
			tok.reset();
			while (tok.incrementToken()) {
				String term = token.toString();

				String stem = stem(term);													//stemming the text by using tokenizer
				if (stem != null) {
					// create the keyword or get the existing one if any
					Keyword keyword = find(keywords, new Keyword(stem.replaceAll("-0", "-")));
					// add its corresponding initial token
					keyword.add(term.replaceAll("-0", "-"));

				}
			}

			Collections.sort(keywords);
			return keywords;
		} 
		finally {
			if(tokenTemp!=null)
			{

				tokenTemp.close();
			}	    

			if (tok != null) {
				tok.close();
			}

		}

	}


	// Code Courtesy : http://stackoverflow.com/questions/17447045/java-library-for-keywords-extraction-from-input-text
	public static String stem(String term) throws IOException {

		TokenStream tokenStream = null;
		ClassicTokenizer ts = null;
		try {

			// tokenize
			ts = new ClassicTokenizer();
			ts.setReader(new StringReader(term));

			// stem
			tokenStream = new PorterStemFilter(ts);

			// add each token in a set, so that duplicates are removed
			Set<String> stems = new HashSet<String>();
			CharTermAttribute token = tokenStream.getAttribute(CharTermAttribute.class);
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				stems.add(token.toString());
			}

			// if no stem or 2+ stems have been found, return null
			if (stems.size() != 1) {
				return null;
			}
			String stem = stems.iterator().next();
			// if the stem has non-alphanumerical chars, return null
			if (!stem.matches("[a-zA-Z0-9-]+")) {
				return null;
			}

			return stem;

		} finally {

			if (ts != null) {
				ts.close();
			}
			if (tokenStream != null) {
				tokenStream.close();
			}
		}

	}


	// Code Courtesy : http://stackoverflow.com/questions/17447045/java-library-for-keywords-extraction-from-input-text
	public static <T> T find(Collection<T> collection, T example) {
		for (T element : collection) {
			if (element.equals(example)) {
				return element;
			}
		}
		collection.add(example);
		return example;
	}




}
