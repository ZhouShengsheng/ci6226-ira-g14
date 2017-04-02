
$(function(){
	var languages_json;

	//var data = [[1,2,3,4,5,6,7,8,9],[3,6,8,1,11,22,4,21,6],[3,3,4,15,161,22,40,21,60]];
	var data_max = 200; //Y轴最大刻度
	var line_title = ["java","c","c++","c#","swift","python","php","go"]; //曲线名称
	var y_label = "amount"; //Y轴标题
	var x_label = "year"; //X轴标题
	var x = [2008,2009,2010,2011,2012,2013,2014,2015,2016]; //x axes
	var title = "Program Lnaguage Ranking"; //统计图标标题
	//j.jqplot.diagram.base("chart1", data, line_title, "title", x, x_label, y_label, data_max, 1);
	//j.jqplot.diagram.base("chart2", data, line_title, "这是统计标题", x, x_label, y_label, data_max, 2);
	var years = new Array();
	var languages = new Array("java","c","c++","c#","swift","python","php","go");
	var  all_data = new Array();
	var data_one_year = new Array();
	var java = new Array();
	var c = new Array();
	var cplus = new Array();
	var csharp = new Array();
	var swift = new Array();
	var python = new Array();
	var php = new Array();
	var go = new Array();
	/*
	for(i=2005;i<=2016;i++){
		
	}*/
	
	$("#btn").click(function(){
		var max = 0;
		var years= new Array();
		var java = new Array();
		var c = new Array();
		var cplus = new Array();
		var csharp = new Array();
		var swift = new Array();
		var python = new Array();
		var php = new Array();
		var go = new Array();
		var start = $("#start").val();
		var end = $("#end").val();
		
		$.ajax({
	        type: "get",
	        url: "http://localhost:9001/api/language_trend?rankLanguages=java%2Cc%2Cc%2B%2B%2Cc%23%2Cgo%2Cswift%2Cpython%2Cphp&startYear=2008&endYear=2016",  /* 注意后面的名字对应CS的方法名称 */
	        contentType: "application/json; charset=utf-8",
	        dataType: "text",
	        success: function (result) {
	          languages_json = $.parseJSON(result);
	          
	          for(var i=start;i<=end;i++){
	  			years.push(i);//x axes is years
	  			
	  			java.push((languages_json[i])[0].popularity);
	  			if((languages_json[i])[0].popularity>max)
	  				max=(languages_json[i])[0].popularity;
	  			
	  			c.push((languages_json[i])[1].popularity);
	  			if((languages_json[i])[1].popularity>max)
	  				max=(languages_json[i])[1].popularity;
	  			
	  			cplus.push((languages_json[i])[2].popularity);
	  			if((languages_json[i])[2].popularity>max)
	  				max=(languages_json[i])[2].popularity;
	  			
	  			csharp.push((languages_json[i])[3].popularity);
	  			if((languages_json[i])[03].popularity>max)
	  				max=(languages_json[i])[3].popularity;
	  			
	  			swift.push((languages_json[i])[4].popularity);
	  			if((languages_json[i])[04].popularity>max)
	  				max=(languages_json[i])[4].popularity;
	  			
	  			python.push((languages_json[i])[5].popularity);
	  			if((languages_json[i])[5].popularity>max)
	  				max=(languages_json[i])[5].popularity;
	  			
	  			php.push((languages_json[i])[6].popularity);
	  			if((languages_json[i])[06].popularity>max)
	  				max=(languages_json[i])[6].popularity;
	  			
	  			go.push((languages_json[i])[7].popularity);
	  			if((languages_json[i])[7].popularity>max)
	  				max=(languages_json[i])[7].popularity;
	  		}
	  		x=years;
	  		data_max = max;
	  		data = [java, c, cplus, csharp, swift, python, php, go];
	  		$("#chart1").empty();
	  		j.jqplot.diagram.base("chart1", data, line_title, "Programming Langauges Ranking", x, x_label, y_label, data_max+Math.ceil(data_max/10), 1);
	  		
	          
	          
	        },
	         error: function(XMLHttpRequest, textStatus, errorThrown) {
	        	 alert(XMLHttpRequest.status);
	        	 alert(XMLHttpRequest.readyState);
	        	 alert(textStatus);
	         }
	    });
		
	});
});