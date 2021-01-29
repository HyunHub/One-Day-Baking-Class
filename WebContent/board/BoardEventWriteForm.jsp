<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 글쓰기</title>
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
<br><br>
<h3 align="center">이벤트 등록</h3>
<br><br>
<form method="post" action="BoardEventWriteAction.bo?event_category=1" name="eventForm" enctype="multipart/form-data">
<input type="hidden" name="event_id" value="${sessionScope.sessionID }">
<table class="event-table" align="center">
	<tr id="event-tr">
		<td id="event-td">작성자</td>
		<td>${sessionScope.sessionID}</td>
	</tr>
	<tr id="event-tr">
		<td id="event-td">파일첨부</td>
		<td><input type="file" name="event_file" style="text-align:center"></td>
	</tr>
</table>
<br><br>
<div class="event-btn-wrap">
	<input id="event-btn" type="submit" value="등록" onclick="return confirm('이벤트를 등록하시겠습니까?')">
	<input id="event-btn" type="button" value="뒤로" onclick="back()">
</div>
</form>
</body>
</html>