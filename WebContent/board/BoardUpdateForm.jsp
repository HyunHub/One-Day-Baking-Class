<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
<link rel="stylesheet" type="text/css" href="css/board_write_style.css">
</head>
<body>
<br>
<h2 align="center">글 수정</h2>
<br>
<form method="post" action="BoardUpdateAction.bo?page=${pageNum}" name="boardForm" enctype="multipart/form-data">
<input type="hidden" name="board_num" value="${board.board_num }">
<input type="hidden" name="existing_file" value="${board.board_file}">
<table class="board_write_table" align="center" border="1">
	<tr>
		<td id="title">카테고리</td>
		<td id="title2">
			<select name="board_category" id="select_ct">
				<option value="0">선택하세요(필수)</option>
				<option value="1">Review</option>
				<option value="2">Recipe</option>
			</select>
		</td>
		<td id="title">작성자</td>
		<td id="title2">${board.board_id}</td>
	</tr>
	<tr>
		<td id="title">제목</td>
		<td colspan="3">
			<input type="text" size="70" id="subject" name="board_subject" value="${board.board_subject}">
		</td>
	</tr>
	<tr>
		<td id="title">내용</td>
		<td colspan="3">
			<textarea name="board_content" cols="80" rows="20" id="content">${board.board_content}</textarea>
		</td>
	</tr>
	<tr>
		<td id="title">첨부파일</td>
		<td colspan="3" style="text-align:left">
			<input type="file" name="board_file">
		</td>
	</tr>
</table>
<div class="update-wrap">
	<input id="update-btn" type="reset" value="작성 취소">
	<input id="update-btn" type="submit" value="수정">
	<c:if test="${board_category != 3 }">
		<input id="update-btn" type="button" value="목록" onclick="location.href='BoardListAction.bo?page=${pageNum}&board_category=${board_category }'">
	</c:if>
	<c:if test="${board_category == 3 }">
		<input id="update-btn" type="button" value="목록" onclick="location.href='BoardNoticeAction.bo?page=${pageNum}&board_category=${board_category}'">
	</c:if>
</div>
</form>
</body>
</html>