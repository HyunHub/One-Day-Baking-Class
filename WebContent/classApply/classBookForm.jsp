<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table{
	width : 400px;
	border : 1px solid #F8ECE0;
}
#item{
	width : 150px;
	height : 40px;
	background-color : #F8ECE0;
	text-align : center;
}
#item2{
	background-color : #FBF8EF;
	border : none;
}
.wrap{
	text-align : center;
}
#btn{
	dispaly : inline-block;
	width : 150px;
	height : 40px;
	align : center;
	background-color : #FBF8EF;
	border : 3px solid #F8ECE0;
	border-radius : 50px;
	margin-top : 10px;
}
</style>
</head>
<body>
<br><br>
<h3 align="center">클래스 신청</h3>
<form action="BookAction.ao" method="post">
<input type="hidden" name="customer_id" value="${sessionScope.sessionID }">
<input type="hidden" name="cname" value="${bclass.cname }">
<input type="hidden" name="bday" value="${bclass.bday }">
<br><br>
	<table align="center"> 
		<tr>
			<td id="item">클래스 선택</td>
			<td>
				${bclass.cname}
			</td>
		</tr>
		<tr>     
	  	<td id="item">인원 선택</td>
	  	<td>
	  		<select name="people" required="required">
	      	<option value="1">1명</option>
	      	<option value="2">2명</option>
	        <option value="3">3명</option>
	        <option value="4">4명</option>
				</select>
			</td>
		</tr>
		<tr>
			<td id="item">날짜 </td>
			<td>
				${bclass.bday}
			</td>
		</tr>
		<tr>     
	  	<td id="item">신청자</td>
	  	<td>
	  		<input id="item2" type="text" name="id" value="${sessionScope.sessionID}">
		</td>
		</tr>
	</table>
	<div class="wrap">
		<input id="btn" type="submit" value="클래스 신청하기">
		<input id="btn" type="button" value="목록" onclick="location.href='ClassListAction.ao'">
	</div>
</form>
</body> 
</html>