<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ page import="jsp.member.model.CustomerBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트 관리자 화면</title>
<link rel="stylesheet" type="text/css" href="css/member_list_style.css">
</head>
<body>
<br><br>
    <h3 align="center">전체 회원 조회</h3>
<br>
<table class="table table-hover" align="center">
	<tr>
		<td id=title>아이디</td>
		<td id=title>비밀번호</td>
		<td id=title>이름</td>
		<td id=title>이메일</td>
		<td id=title>전화</td>
		<td id=title>주소</td>
		<td id=title>가입일</td>
	</tr>
	<c:set var="memberList" value="${requestScope.memberList}"/>
	<c:forEach var="customer" items="${memberList}">
	<tr>
		<td>${customer.customer_id}</td>
		<td>${customer.customer_pw}</td>
		<td>${customer.customer_name}</td>
		<td>${customer.customer_email}</td>
		<td>${customer.customer_phone}</td>
		<td>${customer.customer_address}</td>
		<td>${customer.customer_join}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>