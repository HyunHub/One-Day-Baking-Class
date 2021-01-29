<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.member.model.CustomerDAO" %>    
<%@ page import="jsp.member.model.CustomerBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<link rel="stylesheet" type="text/css" href="css/modify_style.css">
<script type="text/javascript">
	// 비밀번호 입력여부 체크
	function checkValue() {
		if(!userInfo.password.value){
			alert("비밀번호를 입력하세요.");
			return false;
		}
	}        
</script>

</head>
<br><br>
<h3 align="center">회원 정보 수정</h3>
<br>
<!-- 회원정보를 가져와 member 변수에 담는다. -->
<c:set var="customer" value="${requestScope.memberInfo}"/>
	<!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
	<!-- 값(파라미터) 전송은 POST 방식 -->
	<form method="post" action="MemberModifyAction.do" name="userInfo" onsubmit="return checkValue()">
		<table id="member_table">
			<tr>
				<td id="title" width="150">아이디</td>
				<td width="150">${customer.customer_id}</td>
				<td id="title" width="150">이름</td>
				<td width="150"><input id="answer" type="text" name="name" value="${customer.customer_name}"></td>
				
			</tr>
		</table>
		<br>
		<br>
		<table id="member_table">
			<tr>
				<td id="title">비밀번호</td>
				<td><input id="answer" type="password" name="password" maxlength="50" value="${customer.customer_pw}"></td>
			</tr>
			<tr>
				<td id="title">이메일</td>
				<td><input id="answer" type="text" name="email" maxlength="50" value="${customer.customer_email}">
				</td>
			</tr>
			<tr>
				<td id="title">휴대전화</td>
				<td><input id="answer" type="text" name="phone"	value="${customer.customer_phone}" /></td>
			</tr>
			<tr>
				<td id="title">주소</td>
				<td><input id="answer" type="text" size="50" name="address" value="${customer.customer_address}" /></td>
			</tr>
		</table>
		<br><br>
<div class="modify-wrap">
	<input id="modify-btn" type="button" value="취소" onclick="location.href='MainForm.do'">
	<input id="modify-btn" type="submit" value="수정  완료">
</div>
	</form>
</body>
</html>