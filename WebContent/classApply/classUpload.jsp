<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>클래스 등록</title>
<style>
	.event-table{
		text-align : center;
	}
	#event-td{
		width : 100px;
		height : 50px;
		background-color : #F8ECE0;
	}
	.event-btn-wrap{
		text-align : center;
	}
	#event-btn{
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
	function back(){
		return history.back();
	}
</script>
</head>
<body>
<form method="post" action="UploadAction.ao" name="classForm" enctype="multipart/form-data">
<input type="hidden" name="customer_id" value="${sessionScope.sessionID }">
<table class="event-table" align="center">
	<tr>
		<td id="event-td">클래스명</td>
		<td><input type="text" name="cname"></td>
	</tr>
	<tr>
		<td id="event-td">강사명</td>
		<td>
			<input type="text" name="teacher">
		</td>
	</tr>
	<tr>
		<td id="event-td">날짜</td>
		<td><input type="date" name="bday" required="required"></td>
	</tr>
	<tr>
		<td id="event-td">파일첨부</td>
		<td><input type="file" name="image" style="text-align:center"></td>
	</tr>
</table>
<br><br>
<div class="event-btn-wrap">
	<input id="event-btn" type="submit" value="등록" onclick="return confirm('클래스를 등록하시겠습니까?')">
	<input id="event-btn" type="button" value="뒤로" onclick="back()">
</div>
</form>
</body>
</html>