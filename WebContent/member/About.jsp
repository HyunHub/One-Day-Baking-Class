<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About</title>
<style>
	.wrap1{
		width: 100%;
		margin: 0 auto 0 auto;
		text-align : center;
		margin-top : 50px;
	}
	#main_about{
		width : 70%;
		height : 50%;
	}   
    .wrap2{
    	text-align : center;
    }
    #img1, #img2, #img3, #img4{
    	width : 300px;
    	height : 300px;
    	dispaly : inline-block;
    	margin : 0 30px;
    }
    #map{
    	width : 60%;
    	height : 50%;
    }
</style>
</head>
<body>
<br><br><br>
<h3 style="margin-left:150px">* 강사 소개  *</h3>
<div class="wrap1">
	<img alt="" src="image/about.jpg" id="main_about">
</div>
<br><br>
<div class="wrap2">
	<img id="img1" alt="" src="image/bakery1.jpg">
	<img id="img2" alt="" src="image/bakery2.jpg">
	<img id="img3" alt="" src="image/bakery3.jpg">
	<img id="img4" alt="" src="image/bakery4.jpg">
</div>
<br><br><br>
<h3 style="margin-left:150px">* 오시는 길  *</h3>
<div class="wrap1">
	<a onclick="window.open('http://bit.do/bakingonedayclass')"><img alt="" src="image/map.jpg" id="map"></a>
	<!-- <a href="http://bit.do/bakingonedayclass"><img alt="" src="image/map.jpg" id="map"></a> -->
</div>
</body>
</html>