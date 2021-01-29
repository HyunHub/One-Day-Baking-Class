<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>클래스 전체 목록</title>
<link rel="stylesheet" type="text/css" href="css/apply_style.css">
</head>
<body>
<br>
<div class="class-wrap">
	<c:if test="${sessionScope.sessionID == 'admin' }">
		<input id="class-btn" type="button" value="클래스 등록" onclick="location.href='classUpload.ao'">
	</c:if>
</div>
<br>
<div class="apply-main">
	<img src="image/cake1.png" width="800" height="600">
</div>
<div class="class-box">
<c:forEach var="classinfo" items="${requestScope.list}">
	<div id="item">
	<c:if test="${sessionScope.sessionID == null }">
		<img id="event-item" src="UploadFolder/${classinfo.image}" width="400" height="400"><br><br>
	</c:if>
	<c:if test="${sessionScope.sessionID != null }">
	<img id="event-item" src="UploadFolder/${classinfo.image}" width="400" height="400" onclick="location.href='BookActionForm.ao?cname=${classinfo.cname}'"><br><br>
	</c:if>
	클래스 명 : ${classinfo.cname }<br>
	강사 : ${classinfo.teacher }<br>
	날짜 : ${classinfo.bday}<br>
	</div>
</c:forEach>
</div>


</div>
</body>
</html>