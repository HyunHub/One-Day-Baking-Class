<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 게시글 수정</title>
<link rel="stylesheet" type="text/css" href="css/board_write_style.css">
<script type="text/javascript">
	function back(){
		return history.back();
	}
</script>
</head>
<body>
<br>
<h2 style="text-align : center">QnA 게시글 수정</h2>
<br>
<form method="post" action="BoardQnaUpdateAction.bo?page=${pageNum}" name="qnaForm">
<input type="hidden" name="qna_id" value="${sessionScope.sessionID}">
<input type="hidden" name="qna_num" value="${qna.qna_num }">
<table class="board_write_table" align="center" border="1">
	<tr>
		<td id="title">카테고리</td>
		<td id="title2">
			<c:if test="${qna.qna_category != null }">
			<select name="qna_category" id="select_ct">
				<option value="1" style="text-align:center">클래스 문의</option>
				<option value="2" style="text-align:center">쇼핑몰 문의</option>
			</select>
			</c:if>
		</td>
		<td id="title">작성자</td>
		<td id="title2">${qna.qna_id}</td>
	</tr>
	<tr>
		<td id="title">제목</td>
		<td colspan="3"><input type="text" name="qna_subject" size="70" id="subject" value="${qna.qna_subject}"></td>
	</tr>
	<tr>
		<td id="title">내용</td>
		<td colspan="3"><textarea name="qna_content" cols="80" rows="20" id="content">${qna.qna_content }</textarea></td>
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