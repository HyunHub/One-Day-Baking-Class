<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 답변</title>
<script type="text/javascript">
	function chk(){
		if(!replyForm.comment_content.value){
			alert("내용을 입력하세요.");
			replyForm.comment_content.focus();
			return false;
		}
	}
/* 	function checkValue(){
		window.opener("BoardCommentReplyAction.bo?comment_num=${comment.comment_num}&comment_board=${comment.comment_board}&comment_id=${sessionScope.sessionID}"
				+"&board_category=${board_category}&page=${pageNum}");
		self.opener=self;
		window.close();
	} */
</script>
</head>
<body>
<div>
<br>
<h2 align="center">댓글 답변</h2>
<hr>
<br>
<form method="post" action="BoardCommentReplyAction.bo?pageNum=${pageNum }&board_category=${board_category}" name="replyForm" onsubmit="return chk()">
<input type="hidden" name="comment_id" value="${sessionScope.sessionID}">
<input type="hidden" name="comment_num" value="${comment.comment_num}">
<input type="hidden" name="comment_board" value="${comment.comment_board}">
<textarea rows="7" cols="70" name="comment_content" align="center"></textarea>
<br><br>
<input type="submit" value="등록">
<input type="button" value="취소" onclick="window.close()">
</form>
</div>
</body>
</html>