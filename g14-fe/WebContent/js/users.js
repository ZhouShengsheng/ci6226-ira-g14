$(function(){
	var users_json/*=[
	                {
	                    "userID": "22656",
	                    "username": "Jon Skeet",
	                    "anwseredCount": 33477
	                  },
	                  {
	                    "userID": "1144035",
	                    "username": null,
	                    "anwseredCount": 31656
	                  },
	                  {
	                    "userID": "29407",
	                    "username": "Darin",
	                    "anwseredCount": 21217
	                  },
	                  {
	                    "userID": "548225",
	                    "username": null,
	                    "anwseredCount": 19048
	                  },
	                  {
	                    "userID": "115145",
	                    "username": null,
	                    "anwseredCount": 18207
	                  },
	                  {
	                    "userID": "157882",
	                    "username": null,
	                    "anwseredCount": 16963
	                  },
	                  {
	                    "userID": "100297",
	                    "username": null,
	                    "anwseredCount": 16619
	                  },
	                  {
	                    "userID": "6309",
	                    "username": "VonC",
	                    "anwseredCount": 16115
	                  },
	                  {
	                    "userID": "17034",
	                    "username": "nobugz",
	                    "anwseredCount": 15820
	                  },
	                  {
	                    "userID": "19068",
	                    "username": "Dorward",
	                    "anwseredCount": 15697
	                  },
	                  {
	                    "userID": "34397",
	                    "username": "SLaks",
	                    "anwseredCount": 14519
	                  },
	                  {
	                    "userID": "1491895",
	                    "username": null,
	                    "anwseredCount": 13835
	                  },
	                  {
	                    "userID": "23354",
	                    "username": "Marc Gravell",
	                    "anwseredCount": 13619
	                  },
	                  {
	                    "userID": "335858",
	                    "username": null,
	                    "anwseredCount": 13370
	                  },
	                  {
	                    "userID": "57695",
	                    "username": "Peter Lawrey",
	                    "anwseredCount": 12737
	                  },
	                  {
	                    "userID": "157247",
	                    "username": null,
	                    "anwseredCount": 12643
	                  },
	                  {
	                    "userID": "571407",
	                    "username": null,
	                    "anwseredCount": 12436
	                  },
	                  {
	                    "userID": "207421",
	                    "username": null,
	                    "anwseredCount": 11964
	                  },
	                  {
	                    "userID": "20862",
	                    "username": "Ignacio Vazquez-Abrams",
	                    "anwseredCount": 11960
	                  },
	                  {
	                    "userID": "505088",
	                    "username": null,
	                    "anwseredCount": 11803
	                  },
	                  {
	                    "userID": "114251",
	                    "username": null,
	                    "anwseredCount": 11100
	                  },
	                  {
	                    "userID": "139985",
	                    "username": null,
	                    "anwseredCount": 11026
	                  },
	                  {
	                    "userID": "15168",
	                    "username": "Jonathan Leffler",
	                    "anwseredCount": 10983
	                  },
	                  {
	                    "userID": "118068",
	                    "username": null,
	                    "anwseredCount": 10743
	                  },
	                  {
	                    "userID": "69083",
	                    "username": "Guffa",
	                    "anwseredCount": 10643
	                  },
	                  {
	                    "userID": "14860",
	                    "username": "Pax Diablo",
	                    "anwseredCount": 9422
	                  },
	                  {
	                    "userID": "284240",
	                    "username": null,
	                    "anwseredCount": 9074
	                  },
	                  {
	                    "userID": "104349",
	                    "username": null,
	                    "anwseredCount": 9068
	                  },
	                  {
	                    "userID": "992484",
	                    "username": null,
	                    "anwseredCount": 9004
	                  },
	                  {
	                    "userID": "3732271",
	                    "username": null,
	                    "anwseredCount": 8865
	                  },
	                  {
	                    "userID": "816620",
	                    "username": null,
	                    "anwseredCount": 8714
	                  },
	                  {
	                    "userID": "13302",
	                    "username": "marc_s",
	                    "anwseredCount": 8488
	                  },
	                  {
	                    "userID": "179910",
	                    "username": null,
	                    "anwseredCount": 8130
	                  },
	                  {
	                    "userID": "65863",
	                    "username": "Remy Lebeau - Indy Team",
	                    "anwseredCount": 7853
	                  },
	                  {
	                    "userID": "37213",
	                    "username": "duffymo",
	                    "anwseredCount": 7816
	                  },
	                  {
	                    "userID": "771848",
	                    "username": null,
	                    "anwseredCount": 7668
	                  },
	                  {
	                    "userID": "440558",
	                    "username": null,
	                    "anwseredCount": 7639
	                  },
	                  {
	                    "userID": "1583",
	                    "username": "Oded",
	                    "anwseredCount": 7583
	                  },
	                  {
	                    "userID": "256196",
	                    "username": null,
	                    "anwseredCount": 7563
	                  },
	                  {
	                    "userID": "507674",
	                    "username": null,
	                    "anwseredCount": 7549
	                  },
	                  {
	                    "userID": "65358",
	                    "username": "Reed Copsey",
	                    "anwseredCount": 7415
	                  },
	                  {
	                    "userID": "131872",
	                    "username": null,
	                    "anwseredCount": 7389
	                  },
	                  {
	                    "userID": "1048572",
	                    "username": null,
	                    "anwseredCount": 7252
	                  },
	                  {
	                    "userID": "476",
	                    "username": "deceze",
	                    "anwseredCount": 7148
	                  },
	                  {
	                    "userID": "965051",
	                    "username": null,
	                    "anwseredCount": 7056
	                  },
	                  {
	                    "userID": "187606",
	                    "username": null,
	                    "anwseredCount": 7006
	                  },
	                  {
	                    "userID": "190597",
	                    "username": null,
	                    "anwseredCount": 6804
	                  },
	                  {
	                    "userID": "61974",
	                    "username": null,
	                    "anwseredCount": 6784
	                  },
	                  {
	                    "userID": "522444",
	                    "username": null,
	                    "anwseredCount": 6766
	                  },
	                  {
	                    "userID": "23283",
	                    "username": "JaredPar",
	                    "anwseredCount": 6429
	                  },
	                  {
	                    "userID": "367273",
	                    "username": null,
	                    "anwseredCount": 6413
	                  },
	                  {
	                    "userID": "95810",
	                    "username": null,
	                    "anwseredCount": 6334
	                  },
	                  {
	                    "userID": "217408",
	                    "username": null,
	                    "anwseredCount": 6279
	                  },
	                  {
	                    "userID": "218196",
	                    "username": null,
	                    "anwseredCount": 6251
	                  },
	                  {
	                    "userID": "13249",
	                    "username": "Nick Craver",
	                    "anwseredCount": 6161
	                  },
	                  {
	                    "userID": "3832970",
	                    "username": null,
	                    "anwseredCount": 6152
	                  },
	                  {
	                    "userID": "3297613",
	                    "username": null,
	                    "anwseredCount": 6127
	                  },
	                  {
	                    "userID": "34088",
	                    "username": "Aaron Digulla",
	                    "anwseredCount": 6003
	                  },
	                  {
	                    "userID": "596781",
	                    "username": null,
	                    "anwseredCount": 5997
	                  },
	                  {
	                    "userID": "85371",
	                    "username": null,
	                    "anwseredCount": 5997
	                  },
	                  {
	                    "userID": "182668",
	                    "username": null,
	                    "anwseredCount": 5933
	                  },
	                  {
	                    "userID": "285587",
	                    "username": null,
	                    "anwseredCount": 5929
	                  },
	                  {
	                    "userID": "341994",
	                    "username": null,
	                    "anwseredCount": 5762
	                  },
	                  {
	                    "userID": "491243",
	                    "username": null,
	                    "anwseredCount": 5735
	                  },
	                  {
	                    "userID": "103167",
	                    "username": null,
	                    "anwseredCount": 5673
	                  },
	                  {
	                    "userID": "203907",
	                    "username": null,
	                    "anwseredCount": 5639
	                  },
	                  {
	                    "userID": "28169",
	                    "username": "unwind",
	                    "anwseredCount": 5607
	                  },
	                  {
	                    "userID": "1221571",
	                    "username": null,
	                    "anwseredCount": 5568
	                  },
	                  {
	                    "userID": "3043",
	                    "username": "Joel Coehoorn",
	                    "anwseredCount": 5468
	                  },
	                  {
	                    "userID": "519413",
	                    "username": null,
	                    "anwseredCount": 5446
	                  },
	                  {
	                    "userID": "50079",
	                    "username": "Jon",
	                    "anwseredCount": 5400
	                  },
	                  {
	                    "userID": "893",
	                    "username": "Greg Hewgill",
	                    "anwseredCount": 5390
	                  },
	                  {
	                    "userID": "12950",
	                    "username": "tvanfosson",
	                    "anwseredCount": 5389
	                  },
	                  {
	                    "userID": "589924",
	                    "username": null,
	                    "anwseredCount": 5358
	                  },
	                  {
	                    "userID": "2970947",
	                    "username": null,
	                    "anwseredCount": 5353
	                  },
	                  {
	                    "userID": "2877241",
	                    "username": null,
	                    "anwseredCount": 5342
	                  },
	                  {
	                    "userID": "76337",
	                    "username": "John Saunders",
	                    "anwseredCount": 5293
	                  },
	                  {
	                    "userID": "1855677",
	                    "username": null,
	                    "anwseredCount": 5292
	                  },
	                  {
	                    "userID": "560648",
	                    "username": null,
	                    "anwseredCount": 5271
	                  },
	                  {
	                    "userID": "1197518",
	                    "username": null,
	                    "anwseredCount": 5260
	                  },
	                  {
	                    "userID": "479863",
	                    "username": null,
	                    "anwseredCount": 5234
	                  },
	                  {
	                    "userID": "263525",
	                    "username": "dystroy",
	                    "anwseredCount": 5229
	                  },
	                  {
	                    "userID": "315935",
	                    "username": null,
	                    "anwseredCount": 5162
	                  },
	                  {
	                    "userID": "27535",
	                    "username": "gbn",
	                    "anwseredCount": 5152
	                  },
	                  {
	                    "userID": "2141635",
	                    "username": null,
	                    "anwseredCount": 5144
	                  },
	                  {
	                    "userID": "851273",
	                    "username": null,
	                    "anwseredCount": 5074
	                  },
	                  {
	                    "userID": "7552",
	                    "username": "glenn jackman",
	                    "anwseredCount": 5064
	                  },
	                  {
	                    "userID": "230513",
	                    "username": null,
	                    "anwseredCount": 5054
	                  },
	                  {
	                    "userID": "1126841",
	                    "username": null,
	                    "anwseredCount": 5024
	                  },
	                  {
	                    "userID": "36305",
	                    "username": "Dimitre Novatchev",
	                    "anwseredCount": 5014
	                  },
	                  {
	                    "userID": "413501",
	                    "username": null,
	                    "anwseredCount": 5000
	                  },
	                  {
	                    "userID": "434551",
	                    "username": null,
	                    "anwseredCount": 4982
	                  },
	                  {
	                    "userID": "2225682",
	                    "username": null,
	                    "anwseredCount": 4979
	                  },
	                  {
	                    "userID": "841108",
	                    "username": null,
	                    "anwseredCount": 4948
	                  },
	                  {
	                    "userID": "330315",
	                    "username": null,
	                    "anwseredCount": 4863
	                  },
	                  {
	                    "userID": "908494",
	                    "username": null,
	                    "anwseredCount": 4790
	                  },
	                  {
	                    "userID": "31671",
	                    "username": "alex",
	                    "anwseredCount": 4787
	                  },
	                  {
	                    "userID": "1501794",
	                    "username": null,
	                    "anwseredCount": 4777
	                  },
	                  {
	                    "userID": "11654",
	                    "username": "CL",
	                    "anwseredCount": 4772
	                  },
	                  {
	                    "userID": "70604",
	                    "username": "Pascal",
	                    "anwseredCount": 4766
	                  }
	                ];
	/*
	var data = [[1,2,3,4,5,6,7,8,9]];
	var data_max = 30; //Y轴最大刻度
	var line_title = ["A","B"]; //曲线名称
	var y_label = "这是Y轴"; //Y轴标题
	var x_label = "这是X轴"; //X轴标题
	var x= new Array();
	for(var i=0;i<users_json.length;i++){
		x.push(users_json[i].userID);
	}
	var title = "这是标题"; //统计图标标题
	j.jqplot.diagram.base("chart2", data, line_title, "这是统计标题", x, x_label, y_label, data_max, 2);
	*/
	$("#btn").click(function(){
		$("#users").empty();
		var count = $("#numbers").val();
		if(count==undefined)
			count=50;
		
		$.ajax({
	        type: "get",
	        url: "http://155.69.150.182:9002/api/user_ranking",  /* 注意后面的名字对应CS的方法名称 */
	        data: {"userCount": count},
	        contentType: "application/json; charset=utf-8",
	        dataType: "text",
	        success: function (result) {
	        	users_json = $.parseJSON(result);
	          for(var i=0;i<users_json.length;i++){
	        	  var username;
	        	  if(users_json[i].username==undefined){
	        		  username = "unanymous";
	        	  }else{
	        		  username = users_json[i].username;
	        	  }
	      		$("#users").append('<tr><td>'+users_json[i].userID+'</td><td>'+username+'</td><td>'+users_json[i].anwseredCount+'</td></tr>');
	      		}
	        },
	         error: function(XMLHttpRequest, textStatus, errorThrown) {
	        	 alert(XMLHttpRequest.status);
	        	 alert(XMLHttpRequest.readyState);
	        	 alert(textStatus);
	         }
	    });
	});
	
	
});