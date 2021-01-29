<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.member.model.CustomerBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>현재 유저정보 출력화면</title>
<link rel="stylesheet" type="text/css" href="css/join_style.css">
<style>
	.mypage-wrap{
		text-align : center;
	}
	#mypage-btn{
		dispaly : inline-block;
		width : 100px;
		height : 40px;
		align : center;
		background-color : #FBF8EF;
		border : 3px solid #F8ECE0;
		border-radius : 50px;
		margin : 0 10px;
	}
</style>
<script type="text/javascript">
	function changeForm(val){
		if(val == "-1"){
			history.back();
        }else if(val == "0"){
        	location.href="MemberModifyFormAction.do";
        	}else if(val == "1"){
        		location.href="DeleteForm.do";
            }
		}
</script>
</head>
<body>
<br><br>
<h3 align="center">My PAGE</h3>
<br>
<!-- 회원정보를 가져와 member변수에 담는다. -->
<c:set var="customer" value="${requestScope.memberInfo}"/>

<!-- 가져온 회원정보를 출력한다. -->
	<table id="member_table">
		<tr>
			<td id="title">아이디</td>
			<td>${customer.customer_id}</td>
		</tr>

		<tr>
			<td id="title">비밀번호</td>
			<td>${customer.customer_pw}</td>
		</tr>

		<tr>
			<td id="title">이름</td>
			<td>${customer.customer_name}</td>
		</tr>
		<tr>
			<td id="title">이메일</td>
			<td>${customer.customer_email}</td>
		</tr>

		<tr>
			<td id="title">휴대전화</td>
			<td>${customer.customer_phone}</td>
		</tr>
		<tr>
			<td id="title">주소</td>
			<td>${customer.customer_address}</td>
		</tr>
	<c:forEach var="book" items="${requestScope.list}">
		<c:if test="${list.size()==0}">
			<tr>
				<td>클래스 신청 내역</td>
				<td id="title">클래스 신청 내역이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${list.size() !=0 }">
			<tr>
				<td id="title" rowspan="3">클래스 신청 내역</td>
				<td>클래스 명 : ${book.cname } </td>
			</tr>
			<tr>
				<td>날짜 : ${book.bday }</td>
			</tr>
			<tr>
				<td>신청 인원 : ${book.people }명</td>
			</tr>
		</c:if>
	</c:forEach>
	</table>
<br><br>
	<div class="mypage-wrap">
		<input id="mypage-btn" type="button" value="뒤로" onclick="changeForm(-1)">
		<input id="mypage-btn" type="button" value="회원 정보 변경" onclick="changeForm(0)" style="width : 150px">
		<input id="mypage-btn" type="button" value="회원 탈퇴" onclick="changeForm(1)">
	</div>
</body>
</html>