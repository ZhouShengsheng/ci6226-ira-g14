$(function(){
	var languages_json 
	/*= {
			  "2008": [
			           {
			             "name": "java",
			             "popularity": 769
			           },
			           {
			             "name": "c",
			             "popularity": 333
			           },
			           {
			             "name": "c++",
			             "popularity": 273
			           },
			           {
			             "name": "c#",
			             "popularity": 414
			           },
			           {
			             "name": "go",
			             "popularity": 1225
			           },
			           {
			             "name": "swift",
			             "popularity": 0
			           },
			           {
			             "name": "python",
			             "popularity": 806
			           },
			           {
			             "name": "php",
			             "popularity": 539
			           }
			         ],
			         "2009": [
			           {
			             "name": "java",
			             "popularity": 5845
			           },
			           {
			             "name": "c",
			             "popularity": 2608
			           },
			           {
			             "name": "c++",
			             "popularity": 1611
			           },
			           {
			             "name": "c#",
			             "popularity": 3119
			           },
			           {
			             "name": "go",
			             "popularity": 7886
			           },
			           {
			             "name": "swift",
			             "popularity": 6
			           },
			           {
			             "name": "python",
			             "popularity": 5686
			           },
			           {
			             "name": "php",
			             "popularity": 6318
			           }
			         ],
			         "2010": [
			           {
			             "name": "java",
			             "popularity": 13675
			           },
			           {
			             "name": "c",
			             "popularity": 6665
			           },
			           {
			             "name": "c++",
			             "popularity": 3751
			           },
			           {
			             "name": "c#",
			             "popularity": 5823
			           },
			           {
			             "name": "go",
			             "popularity": 15839
			           },
			           {
			             "name": "swift",
			             "popularity": 9
			           },
			           {
			             "name": "python",
			             "popularity": 12412
			           },
			           {
			             "name": "php",
			             "popularity": 16718
			           }
			         ],
			         "2011": [
			           {
			             "name": "java",
			             "popularity": 24521
			           },
			           {
			             "name": "c",
			             "popularity": 12760
			           },
			           {
			             "name": "c++",
			             "popularity": 6134
			           },
			           {
			             "name": "c#",
			             "popularity": 9445
			           },
			           {
			             "name": "go",
			             "popularity": 28343
			           },
			           {
			             "name": "swift",
			             "popularity": 22
			           },
			           {
			             "name": "python",
			             "popularity": 21128
			           },
			           {
			             "name": "php",
			             "popularity": 33818
			           }
			         ],
			         "2012": [
			           {
			             "name": "java",
			             "popularity": 37070
			           },
			           {
			             "name": "c",
			             "popularity": 19380
			           },
			           {
			             "name": "c++",
			             "popularity": 8375
			           },
			           {
			             "name": "c#",
			             "popularity": 11240
			           },
			           {
			             "name": "go",
			             "popularity": 39104
			           },
			           {
			             "name": "swift",
			             "popularity": 55
			           },
			           {
			             "name": "python",
			             "popularity": 31521
			           },
			           {
			             "name": "php",
			             "popularity": 48525
			           }
			         ],
			         "2013": [
			           {
			             "name": "java",
			             "popularity": 46347
			           },
			           {
			             "name": "c",
			             "popularity": 28644
			           },
			           {
			             "name": "c++",
			             "popularity": 10919
			           },
			           {
			             "name": "c#",
			             "popularity": 14678
			           },
			           {
			             "name": "go",
			             "popularity": 50794
			           },
			           {
			             "name": "swift",
			             "popularity": 53
			           },
			           {
			             "name": "python",
			             "popularity": 46276
			           },
			           {
			             "name": "php",
			             "popularity": 62676
			           }
			         ],
			         "2014": [
			           {
			             "name": "java",
			             "popularity": 52051
			           },
			           {
			             "name": "c",
			             "popularity": 33852
			           },
			           {
			             "name": "c++",
			             "popularity": 11182
			           },
			           {
			             "name": "c#",
			             "popularity": 13835
			           },
			           {
			             "name": "go",
			             "popularity": 55811
			           },
			           {
			             "name": "swift",
			             "popularity": 3754
			           },
			           {
			             "name": "python",
			             "popularity": 52804
			           },
			           {
			             "name": "php",
			             "popularity": 63954
			           }
			         ],
			         "2015": [
			           {
			             "name": "java",
			             "popularity": 50467
			           },
			           {
			             "name": "c",
			             "popularity": 35010
			           },
			           {
			             "name": "c++",
			             "popularity": 10938
			           },
			           {
			             "name": "c#",
			             "popularity": 14179
			           },
			           {
			             "name": "go",
			             "popularity": 57352
			           },
			           {
			             "name": "swift",
			             "popularity": 10264
			           },
			           {
			             "name": "python",
			             "popularity": 59565
			           },
			           {
			             "name": "php",
			             "popularity": 58975
			           }
			         ],
			         "2016": [
			           {
			             "name": "java",
			             "popularity": 49137
			           },
			           {
			             "name": "c",
			             "popularity": 34022
			           },
			           {
			             "name": "c++",
			             "popularity": 10411
			           },
			           {
			             "name": "c#",
			             "popularity": 15694
			           },
			           {
			             "name": "go",
			             "popularity": 57603
			           },
			           {
			             "name": "swift",
			             "popularity": 11328
			           },
			           {
			             "name": "python",
			             "popularity": 70951
			           },
			           {
			             "name": "php",
			             "popularity": 59549
			           }
			         ]
			       };*/
	
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
	        url: "http://155.69.150.182:9001/api/language_trend?rankLanguages=java%2Cc%2Cc%2B%2B%2Cc%23%2Cgo%2Cswift%2Cpython%2Cphp&startYear=2008&endYear=2016",  /* 注意后面的名字对应CS的方法名称 */
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