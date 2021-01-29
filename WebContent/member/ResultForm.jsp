<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 페이지</title>
<style>
	.btn-wrap{
		text-align : center;
	}
	#result_btn{
		dispaly : inline-block;
		width : 100px;
		height : 40px;
		align : center;
		background-color : #FBF8EF;
		border : 3px solid #F8ECE0;
		border-radius : 50px;
	}
</style>
</head>
<body>
<br><br><br>
<c:set var="msg" value="${sessionScope.msg}" scope="session" />
    <c:choose>
        <c:when test="${msg!=null && msg=='0'}">
        	<h2 align="center">회원 정보가 수정되었습니다.</h2>
            <c:remove var="msg" scope="session"></c:remove>
        </c:when>
        <c:when test="${msg!=null && msg=='1'}">
            <h2 align="center">회원 가입을 축하합니다.</h2>
            <c:remove var="msg" scope="session"></c:remove>
        </c:when>
        <c:otherwise>
            <h2 align="center">회원 정보가 삭제되었습니다.</h2>
        </c:otherwise>
    </c:choose>
<br><br>
<div class="btn-wrap">
	<input id="result_btn" type="button" value="메인으로" onclick="location.href='MainForm.do'">
</div>
</body>
</html>