<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 글 수정</title>
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
<h3 align="center">이벤트 수정</h3>
<br><br>
<form method="post" action="BoardEventUpdateAction.bo" name="eventForm" enctype="multipart/form-data">
<input type="hidden" name="event_num" value="${event.event_num}">
<input type="hidden" name="existing_file" value="${event.event_file}">
<table class="event-table" align="center">
	<tr>
		<td id="event-td">작성자</td>
		<td>${event.event_id }</td>
	</tr>
	<tr id="event-tr">
		<td id="event-td">파일첨부</td>
		<td><input type="file" name="event_file" style="text-align:center"></td>
	</tr>
</table>
<br><br>
<div class="event-btn-wrap">
	<input id="event-btn" type="submit" value="등록">
	<input id="event-btn" type="button" value="뒤로" onclick="back()">
</div>
</form>
</body>
</html>