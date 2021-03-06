$(document).ready(function(){
	var keywords, field;
	if (sessionStorage.getItem("keywords")) {
        keywords = sessionStorage.getItem("keywords");
    }
	
	if (sessionStorage.getItem("field")) {
        field = sessionStorage.getItem("field");
    }
	//var result = [{"title":"How to convert string array to int array in java","body":" < p > I have an string array in java program like this < /p >\n\n<pre><code>String results[] = { \"2\", \"1\", \"5\", \"1\" };\n</code > </pre>\n\n<p>I want to convert this to integer array as like this:  </p>\n\n<pre> < code > int results[] = { 2, 1, 5, 1 }; \n < /code></pre > \n\n < p > And finally I want to find the summation of all the < code > int < /code> elements of that array.  </p > \n","score":28.137417},{"title":"Python: Recursively convert int to binary string","body":" < p > I am trying to recursively convert int to binary string but I don't really understand how the whole positive int to binary string conversion works.</p>\n\n<p>Also found out that apparently each position is like a representation of the power of 2.</p>\n\n<p>Any explanation as to how to convert a positive int to a string representation as shown in the Googled example above is extremely helpful.</p>\n","score":28.072052},{"title":"Converting string to int java","body":"<pre><code>package kappa;\nimport java.util.Scanner;\n\npublic class Kappa {\n\n\n    public static void main(String[] args) {\n\n        Scanner scanner = new Scanner(System.in);\n\n        System.out.println(\"Who are you ?\");\n        String Name;\n        Name = scanner.next();\n\n        System.out.println(\"So you are \" + Name);\n\n       String any;\n       System.out.println(\"Press any to continue\");\n       any = scanner.next();\n\n      int age;\n       System.out.println(\"Enter you age : \" );\n        age = Integer.parseInt();\n\n       if(age &gt; 18){\n       System.out.println(Name + \" is older than 18\");\n\n       }\n       if (age &lt; 18){\n       System.out.println(Name + \" is younger than 18\");\n       }\n       }\n\n\n    }\n</code></pre>\n\n<p>// i dont how to convert string to int and i know this is an easy problem but im just new to java language so any help will be really helpful.</p>\n","score":27.767942},{"title":"How to convert int to string","body":"<p>I this a good way to convert int to string?</p>\n\n<pre><code>    int a = 123456789;\n    string str = static_cast&lt;ostringstream*&gt;(&amp;(ostringstream()&lt;&lt;a))-&gt;str();\n</code></pre>\n","score":27.469925},{"title":"Attempting to convert int to string","body":"<p>It's quite clear from the code below that I am attempting to convert an int to a string.</p>\n\n<pre> < code > #include & lt; sstream & gt; \n#include & lt; string & gt; \n#include & lt; iostream & gt; \n\nint num = 1; \nostringstream convert; \nconvert & lt; & lt; num; \nstring str = convert.str(); \n < /code></pre > \n\n < p > However, I get the error message < /p>\n\n<blockquote>\n  <p>Line 7: error: expected constructor, destructor, or type conversion\n  before '&lt;&lt;' token</p > \n < /blockquote>\n\n<p>What I am doing wrong? This is basically the same snippet of code that everyone recommends to convert an int to a string.</p > \n","score":27.3416},{"title":"convert string into signed int","body":" < p > I want to convert a string into a signed int.Following is the requirement.I have stored hex value as a string in buffer.Now I want to convert that value into signed int.</p>\n\n<p> < code > buf = \"fb869e\"</code> Convert this into signed int. So o/p should be -293218. but when I'm trying to convert using strtol I'm getting 16483998. So what I should I do?</p>\n", "score":27.320126}, {"title":"convert string to int error in c#", "body":"<p>Hello I am trying to convert String to Integer.</p>\n\n<p>The code below shows a part from where I am trying to convert my string to integer.</p>\n\n<pre><code>if (other.gameObject.CompareTag(\"PickUp\"))\n{\n    if ( checkpointboolean == false)\n    {\n        string pickupName = other.ToString(); //other = Pickup4\n        //Remove first 6 letters thus remaining with '4'\n        string y = pickupName.Substring(6); \n        print(y); // 4 is being printed\n        int x = 0;\n        int.TryParse(y, out x);\n        print (x); // 0 is being printed\n</code></pre>\n\n<p>I also tried the below code and instead of '0' I am getting the following error:</p>\n\n<pre><code>if (other.gameObject.CompareTag(\"PickUp\"))\n{\n    if ( checkpointboolean == false)\n    {\n        //Get Object name ex: Pickup4\n        string pickupName = other.ToString();\n        //Remove first 6 letters thus remaining with '4'\n        string y = pickupName.Substring(6); \n        print(y);\n        int x = int.Parse(y);\n</code></pre>\n\n<blockquote>\n  <p>FormatException: Input string was not in the correct format\n  System.Int32.Parse (System.String s)</p>\n</blockquote>\n", "score":26.940704}, {"title":"How to convert a string to int in java", "body":"<p>I'm declaring a class called Employee with the following details. For some reason I'm getting an error saying: cannot convert a string to int e.g <code>int empNo = input.nextLine();</code></p>\n\n<pre><code>import java.util.Scanner; \n\npublic class Employee {\n    private String empName, empDesig;\n    private double bsal=0.0, ha=0.0, gsal=0.0;\n    private int empNo;\n\n    public static void main(String[] args) {          \n        Scanner input = new Scanner (System.in);\n        System.out.println(\"Enter number of employees\");\n        int empNo = input.nextLine();\n    }    \n}\n</code></pre>\n", "score":26.56549}, {"title":"How to convert string to int in java to get calculation", "body":"<p>How to convert string to int to get calculation</p>\n\n<pre><code>String a=\"2-1\";\n</code></pre>\n\n<p>How to convert this string to int to get value as 1</p>\n", "score":26.514786}, {"title":"C convert int to string", "body":"<p>I am looking for a way to convert an int to a string of a given length. If the representation of the number is shorter, it should be zero-filled.</p>\n\n<p>I.e., what I'd like to see:</p>\n\n<pre><code>snprintf(buffer, 3, \"%d\", 5); -&gt; 005\nsnprintf(buffer, 2, \"%d\", 55); -&gt; 55\n</code></pre>\n\n<p>Actually, <code>snprintf(buffer, 3, \"%d\", 5)</code>; just return 5 in string.</p>\n\n<p>Is there a simple way to do this ?</p>\n", "score":26.446526}];

	var result;
	var i;
	var title, body;
	
	$.ajax({
        type: "get",
        url: "http://localhost:9000/api/search",  /* 注意后面的名字对应CS的方法名称 */
        data: {"field": field, "keywords":keywords, "count":20},
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success: function (result1) {
          result = $.parseJSON(result1);
          for(i=0; i<result.length;i++){
      		title = result[i].title;
      		//title.replace(/<\s+/g,'<');
      		
      		body = result[i].body;
      		body = body.replace(/<\s+/g,'<');
      		body = body.replace(/&\s+lt;/g,"&lt;");
      		body = body.replace(/&\s+gt;/g,"&gt;");
      		$(".result").append("<div class='result-container'><div class='title'>"+title+"</div><div class='body'>"+body+"</div></div>");
      	}
        },
         error: function(XMLHttpRequest, textStatus, errorThrown) {
        	 alert(XMLHttpRequest.status);
        	 alert(XMLHttpRequest.readyState);
        	 alert(textStatus);
         }
    });
	
	
});



