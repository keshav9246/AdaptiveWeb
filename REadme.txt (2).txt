crawlingSoup.java - Used to crawl data from website given and 3 types of folders are created. Jsoup is used to crwal the data.

textfiles : which will store text files for every indexed page
linknames : which stores links to file names
htmlfiles : stores html code for files

Keyword.java - This is file containing third party code which is used in indexing files

SampleLuceneIndexing.java - This file contain indexing code which is combination of code provided by professor, third party code and self written code.
I have also performed filtering of texxt using Tokenizer and also performed Stemming.

RecommenderServlet.java - This file contains servelet code which acts as binding place to call all other java files and dynamically calling index.jsp page

index.jsp - This file uses foundation responsive framework to render collapsible panel functionality.

Execution video attached. 

Server used: Apache Tomcat version 9.

External Jar Libraries used are :

jsoup-1.10.3.jar
lucene-analyzers-common-7.1.0.jar
lucene-core-7.1.0.jar
lucene-demo-7.1.0.jar
lucene-queryparser-7.1.0.jar



