<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/member_list_style.css">
</head>
<body>
<br><br>
    <h3 align="center">전체 클래스 신청 목록 조회</h3>
<br>
<table class="table table-hover" align="center">
	<tr>
		<td id=title>신청자</td>
		<td id=title>클래스명</td>
		<td id=title>클래스 시작일</td>
		<td id=title>신청 인원</td>
	</tr>
	<c:forEach var="book" items="${requestScope.bookList}">
	<tr>
		<td>${book.customer_id}</td>
		<td>${book.cname}</td>
		<td>${book.bday}</td>
		<td>${book.people}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>