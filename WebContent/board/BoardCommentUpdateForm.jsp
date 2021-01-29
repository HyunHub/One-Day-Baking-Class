<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
</head>
<body>
<div id="wrap">
<br>
<b><font size="5" color="gray">댓글수정</font></b>
<hr size="1" width="550">
<br>
<div id="commentUpdateForm">
    <form name="updateInfo" method="post" action="BoardCommentUpdateAction.bo?pageNum=${pageNum }&board_category=${board_category}">  
    <input type="hidden" name="comment_id" value="${sessionScope.sessionID}">
	<input type="hidden" name="comment_num" value="${comment.comment_num}">
	<input type="hidden" name="comment_board" value="${comment.comment_board}">
	<input type="hidden" name="comment_parent" value="${comment.comment_parent }">
        <textarea rows="7" cols="70" name="comment_content">${comment.comment_content}</textarea>
        <br><br>
        <input type="submit" value="등록">
        <input type="button" value="창닫기" onclick="window.close()">
    </form>
</div>
</div>    

</body>
</html>