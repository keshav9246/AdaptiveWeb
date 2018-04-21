<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    
    <link rel="stylesheet" href="CSS/foundation.css" />
    <script src="JS/modernizr.js"></script>
    
    

</head>
<body>


<h3 style = "align:center; text-align:center;">Content-based Recommendations</h3>

<div style="align:center;width:100%;background-color:lightblue;padding-right: 1cm; padding-left: 1cm;">
<div style="float:left;padding:1px;width:48%;height:1080px;text-align:justify;overflow:scroll;background-color:lightpink;border:1px solid black;">


<form>

<b>Question 1</b> <a href="./RecommenderServelet?id=0"><p>In a class i can have as many constructors as i want with different argument types. i made all the constructors as private it didn't give any error because my implicit default constructor was public but when i declared my implicit default constructor as private then its showing an error while extending the class.  why?       this works fine         this can not be inherited. public class demo4  {     private string name;     private int age;     private double sal;      private demo4(string name  int age) {         this.name=name;         this.age=age;        }      demo4(string name) {         this.name=name;     }      demo4() {         this(\"unknown\"  20);         this.sal=2000;     }      void show(){         system.out.println(\"name\"+name);         system.out.println(\"age: \"+age);     } }  public class demo4  {     private string name;     private int age;     private double sal;      private demo4(string name  int age) {         this.name=name;         this.age=age;     }      demo4(string name) {         this.name=name;     }      private demo4() {         this(\"unknown\"  20);         this.sal=2000;     }      void show() {         system.out.println(\"name\"+name);         system.out.println(\"age: \"+age);     } } </p></a>
<b>Question 2</b> <a href="./RecommenderServelet?id=1"><p>I was presented with this question in an end of module open book exam today and found myself lost. i was reading Head first Java and both definitions seemed to be exactly the same.</p></a>
<b>Question 3</b> <a href="./RecommenderServelet?id=2"><p>Inheritance is when a 'class' derives from an existing 'class'.So if you have a Person class, then you have a Student class that extends Person, Student inherits all the things that Person has.There are some details around the access modifiers you put on the fields/methods in Person, but that's the basic idea.For example, if you have a private field on Person, Student won't see it because its private.</p></a>
<b>Question 4</b> <a href="./RecommenderServelet?id=3"><p>Polymorphism: The ability to treat objects of different types in a similar manner.Example: Giraffe and Crocodile are both Animals, and animals can Move.If you have an instance of an Animal then you can call Move without knowing or caring what type of animal it is.Inheritance: This is one way of achieving both Polymorphism and code reuse at the same time.Other forms of polymorphism:There are other way of achieving polymorphism, such as interfaces, which provide only polymorphism hoice in this case.</p></a>
<b>Question 5</b> <a href="./RecommenderServelet?id=4"><p>I found out that the above piece of code is perfectly legal in Java. I have the following questions. ThanksAdded one more question regarding Abstract method classes.public class TestClass{public static void main(String[] args) {TestClass t = new TestClass();}private static void testMethod(){abstract class TestMethod{int a;int b;int c;abstract void implementMe();}class DummyClass extends TestMethod{void implementMe(){}}DummyClass dummy = new DummyClass();}}</p></a>
<b>Question 6</b> <a href="./RecommenderServelet?id=5"><p>In java it's a bit difficult to implement a deep object copy function. What steps you take to ensure the original object and the cloned one share no reference? </p></a>
<b>Question 7</b> <a href="./RecommenderServelet?id=6"><p>You can make a deep copy serialization without creating some files. Copy: Restore:.  ByteArrayOutputStream bos = new ByteArrayOutputStream();ObjectOutputStream oos = new ObjectOutputStream(bos);oos.writeObject(object);oos.flush();oos.close();bos.close();byte[] byteData = bos.toByteArray(); ByteArrayInputStream bais = new ByteArrayInputStream(byteData);(Object) object = (Object) new ObjectInputStream(bais).readObject();"</p></a>
<b>Question 8</b> <a href="./RecommenderServelet?id=7"><p>Java has the ability to create classes at runtime. These classes are known as Synthetic Classes or Dynamic Proxies. See for more information. Other open-source libraries, such as and also allow you to generate synthetic classes, and are more powerful than the libraries provided with the JRE. Synthetic classes are used by AOP (Aspect Oriented Programming) libraries such as Spring AOP and AspectJ, as well as ORM libraries such as Hibernate.</p></a>
<b>Question 9</b> <a href="./RecommenderServelet?id=8"><p>comment this code /*if (savedinstancestate == null) {     getsupportfragmentmanager().begintransaction()             .add(r.id.container  new placeholderfragment())             .commit(); }*/</p></a>
<b>Question 10</b> <a href="./RecommenderServelet?id=9"><p>A safe way is to serialize the object, then deserialize.This ensures everything is a brand new reference.about how to do this efficiently. Caveats: It's possible for classes to override serialization such that new instances are created, e.g. for singletons.Also this of course doesn't work if your classes aren't Serializable."</p></a>
</form>



</div>


  
      <div style="float:right;padding:1px;width:50%;height:1080px;text-align:left;overflow:scroll;background-color:lightgray;border:1px solid black;">
      
        	<ul class="accordion">
        	
  <li class = "accordion-navigation">
    <a href="${link0}" target="new" ><b>Recommendation 1</b> : <i>${link0}</i></a>
    ${message0}
  </li>
  <li class="accordion-navigation">
    <a href="${link1}"><b>Recommendation 2</b> : <i>${link1}</i></a>
    ${message1}
  </li>
  <li class="accordion-navigation">
    <a href="${link2}"><b>Recommendation 3</b> : <i>${link2}</i></a>
    ${message2}
  </li>
    <li class="accordion-navigation">
    <a href="${link3}"><b>Recommendation 4</b> : <i>${link3}</i></a>
    ${message3}
  </li>
  <li class="accordion-navigation">
    <a href="${link4}"><b>Recommendation 5</b> : <i>${link4}</i></a>
    ${message4}
  </li>
  <li class="accordion-navigation">
    <a href="${link5}"><b>Recommendation 6</b> : <i>${link5}</i></a>
   ${message5}
  </li>
    <li class="accordion-navigation">
    <a href="${link6}"><b>Recommendation 7</b> : <i>${link6}</i></a>
    ${message6}
  </li>
  <li class="accordion-navigation">
    <a href="${link7}"><b>Recommendation 8</b> : <i>${link7}</i></a>
    ${message7}
  </li>
  <li class="accordion-navigation">
    <a href="${link8}"><b>Recommendation 9</b> : <i>${link8}</i></a>
    ${message8}
  </li>
  <li class="accordion-navigation">
    <a href="${link9}"><b>Recommendation 10</b> : <i>${link9}</i></a>
    ${message9}
  </li>
</ul>
      </div>
</div>      

    
    <script src="JS/jquery.js"></script>
    <script src="JS/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
</body>
</html>