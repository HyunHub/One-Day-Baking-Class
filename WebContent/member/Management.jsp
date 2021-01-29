<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<style>
	.mag-table{
	
	}
	#mag-item{
		width : 150px;
		height : 40px;
		align : center;
		background-color : #FBF8EF;
		border : 3px solid #F8ECE0;
		border-radius : 50px;
	}
	#mag-td{
		height : 60px;
	}
</style>
</head>
<body>
<br><br>
    <h3 align="center">회원 관리</h3>
<br>
<table class="mag-table" align="center">
	<tr>
		<td id="mag-td"><input id="mag-item" type="button" value="전체 회원 조회" onclick="location.href='MemberListAction.do'">
	</tr>
	<tr>
		<td id="mag-td"><input id="mag-item" type="button" value="클래스 신청 관리" onclick="location.href='BookListAction.ao'">
	</tr>
	<tr>
		<td id="mag-td"><input id="mag-item" type="button" value="쇼핑몰 관리" onclick="location.href='OrderedListAction.co'">
	</tr>
</table>
</body>
</html>