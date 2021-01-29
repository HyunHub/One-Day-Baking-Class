<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First View</title>
</head>
<body>
<!-- 로그인이 안되어있을 경우 -->
<%-- <c:if test="${sessionScope.sessionID==null}"> --%>
<!-- 수평선 -->
<div class="hr-sect">Enjoy Your Baking Time</div>
<div id="demo" class="carousel slide" data-ride="carousel"  data-interval="3000">
		<div class="carousel-inner">
			<!-- 슬라이드 쇼 -->
			<div class="carousel-item active">
				<!--가로-->
				<img class="d-block" src="image/main1.jpg" alt="First slide">
				<div class="carousel-caption d-none d-md-block">
					<h5>BAKING ONEDAY CLASS</h5>
				</div>
			</div>
			<div class="carousel-item">
				<img class="d-block" src="image/main2.jpg" alt="First slide">
				<div class="carousel-caption d-none d-md-block">
					<h5>BAKING ONEDAY CLASS</h5>
				</div>
			</div>
			<div class="carousel-item">
				<img class="d-block" src="image/main3.jpg" alt="First slide">
				<div class="carousel-caption d-none d-md-block">
					<h5>BAKING ONEDAY CLASS</h5>
				</div>
			</div>
			<!-- / 슬라이드 쇼 끝 -->
			<!-- 왼쪽 오른쪽 화살표 버튼 -->
			<a class="carousel-control-prev" href="#demo" data-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<!-- <span>Previous</span> -->
			</a> <a class="carousel-control-next" href="#demo" data-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<!-- <span>Next</span> -->
			</a>
			<!-- / 화살표 버튼 끝 -->
			<!-- 인디케이터 -->
			<ul class="carousel-indicators">
				<li data-target="#demo" data-slide-to="0" class="active"></li>
				<!--0번부터시작-->
				<li data-target="#demo" data-slide-to="1"></li>
				<li data-target="#demo" data-slide-to="2"></li>
			</ul>
			<!-- 인디케이터 끝 -->
		</div>
</div>
<!-- 수평선 -->
<div class="hr-sect">Hand Made Special Bread</div>
<%-- </c:if> --%>
<!--  로그인 했을 경우 -->
<%-- <c:if test="${sessionScope.sessionID!=null}">
	<br><br><br>
    <font size=6 color="brown">${sessionScope.sessionID}</font>
    <font size=6>님 환영합니다.</font>
</c:if> --%>
</body>
</html>