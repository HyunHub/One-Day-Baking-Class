<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 답변글</title>
<style>
.board_write_table{
	text-align : center;
	border : 1px solid #F8ECE0;
	width : 60%;
	height : 100%;
}
#title{
	width: 100px;
	height : 40px;
	text-align : center;
	background-color : #F8ECE0;
}
#subject, #content{
	width : 100%;
}
.update-wrap{
	text-align : center;
}
#update-btn{
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
<br>
<h2 align="center">답변 작성</h2>
<br>
<form method="post" action="BoardQnaReplyAction.bo?page=${page}" name="qnaForm">
<input type="hidden" name="qna_id" value="${sessionScope.sessionID }">
<input type="hidden" name="qna_num" value="${qna.qna_num }">
<input type="hidden" name="qna_re_ref" value="${qna.qna_re_ref }">
<input type="hidden" name="qna_re_lev" value="${qna.qna_re_lev }">
<input type="hidden" name="qna_re_seq" value="${qna.qna_re_seq }">
<table class="board_write_table" align="center" border="1">
	<tr>
		<td id="title">작성자</td>
		<td id="title2">${sessionScope.sessionID }</td>
	</tr>
	<tr>
		<td id="title">제목</td>
		<td id="title2">
			<input type="text" name="qna_subject" id="subject">
	</tr>
	<tr>
		<td id="title">내용</td>
		<td id="title2">
			<textarea name="qna_content" cols="80" rows="20" id="content"></textarea>
		</td>
	</tr>
</table>
<div class="update-wrap">
	<input id="update-btn" type="reset" value="작성 취소">
	<input id="update-btn" type="submit" value="등록" onclick="return confirm('게시글을 등록하시겠습니까?')">
	<input id="update-btn" type="button" value="목록" onclick="back()">
</div>
</form>
</body>
</html>