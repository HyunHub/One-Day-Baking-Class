<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkValue(){
		if(checkpw.qna_password.value != checkpw.qna_dbpw.value){
			alert("비밀번호와 비밀번호확인이 일치하지 않습니다.");
			checkpw.qna_password.focus();
			checkpw.qna_password.value="";
			return false;
		}
	}
</script>
<style>
.pw-table{
	text-align : center;
}
#item{
	padding-bottom : 10px;
}
.btn-wrap{
	text-align : center;
}
#item-btn{
	dispaly : inline-block;
	width : 100px;
	height : 40px;
	align : center;
	background-color : #FBF8EF;
	border : 3px solid #F8ECE0;
	border-radius : 50px;
	margin : 15px 10px;
}
</style>
<script type="text/javascript">
	function doAction(){
		history.back();
	}
</script>
</head>
<body>
<br><br><br><br>
<form action="BoardCheckpasswordAction.bo" name="checkpw" onsubmit="return checkValue()">
<input type="hidden" name="qna_dbpw" value="${qna.qna_pw }">
<input type="hidden" name="num" value="${qna.qna_num }">
<input type="hidden" name="pageNum" value="${pageNum }">
<table class="pw-table" align="center">
	<tr>
		<td id="item">글의 비밀번호를 입력하세요</td>
	</tr>
	<tr>
		<td id="item"><input type="password" name="qna_password"></td>
	</tr>
</table>
<div class="btn-wrap">
<input type="submit" id="item-btn">
<input type="button" value="취소" id="item-btn" onclick="doAction()">
</div>
</form>
</body>
</html>