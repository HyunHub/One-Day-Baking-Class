<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 전체 조회</title>
<link rel="stylesheet" type="text/css" href="css/board_event_style.css">
</head>
<body>
<br><br>
<h3 align="center">이벤트 목록 전체 조회</h3>
<br><br>
<div>
<table class="table table-hover" align="center">
	<tr id="event-list-kind">
		<td>글번호</td>
		<td>파일 이름</td>
		<td>작성자</td>
		<td>등록일</td>
		<td>삭제 여부</td>
	</tr>
<c:forEach var="event" items="${requestScope.list}">
	<tr>
		<td>${event.event_num }</td>
		<td><a href="BoardEventDetailAction.bo?num=${event.event_num}">${event.event_file}</a></td>
		<td>${event.event_id }</td>
		<td>${event.event_date }</td>
		<td>
			<c:if test="${event.event_del == 'y' }">삭제된 이벤트 입니다.</c:if>
			<c:if test="${event.event_del != 'y' }">현재 등록 중인 이벤트입니다.</c:if>
		</td>
	</tr>
</c:forEach>
</table>
</div>
</body>
</html>