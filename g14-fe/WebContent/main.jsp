<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jQuery.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<header>
	<div class="logo">Search Engine</div>
</header>
<div class="search">
	<form action="http://155.69.149.197:8080/api/search?count=20" method="get">
	<div class="search-bar">
		<input  type="search" name="keywords" placeholder="input your query here">
		<input type="text" name="count" value=20>
		<input type="submit" value="search">
 	</div>
 	<div class="field">
 	    <span>Title <input type="radio" name="field" value="title" checked></span>
 	    <span>Body <input type="radio" name="field" value="body"></span>
 	    <span>Title&Body <input type="radio" name="field" value="all"></span>
 	</div>
	</form>
</div>
</body>
</html>