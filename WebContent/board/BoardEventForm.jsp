<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 목록</title>
<link rel="stylesheet" type="text/css" href="css/board_event_style.css">
</head>
<body>
<div class="event-info">
	<c:if test="${sessionScope.sessionID == 'admin' }">
		<input id="event-btn" type="button" value="이벤트 등록" onclick="location.href='BoardEventWriteForm.bo'">
		<input id="event-btn" style="width:150px" type="button" value="이벤트 목록 관리 " onclick="location.href='BoardEventListAction.bo?event_category=2'">
	</c:if>
</div>
<div class="event-list">
<c:forEach var="event" items="${requestScope.list}">
	<c:if test="${event.event_del != 'y' }">
	<img id="event-item" src="UploadFolder/${event.event_file}">
	</c:if>
</c:forEach>

</div>
</body>
</html>