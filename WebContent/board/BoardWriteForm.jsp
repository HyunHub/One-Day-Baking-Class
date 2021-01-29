<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 - 글쓰기</title>
<link rel="stylesheet" type="text/css" href="css/board_write_style.css">
<script type="text/javascript">
	function back(){
		return history.back();
	}
</script>
</head>
<body>
<br>
<h2 style="text-align : center">글쓰기</h2>
<br>
<form method="post" action="BoardWriteAction.bo" name="boardForm" enctype="multipart/form-data">
<input type="hidden" name="board_id" value="${sessionScope.sessionID}">
<table class="board_write_table" align="center" border="1">
	<tr>
		<td id="title">카테고리</td>
		<td id="title2">
		<c:if test="${sessionScope.sessionID=='admin'}">
			<select name="board_category" id="select_ct">
				<option value="3" style="text-align:center">공지사항</option>
			</select>
		</c:if>
		<c:if test="${sessionScope.sessionID != 'admin' }">
			<select name="board_category" id="select_ct">
				<option value="1" style="text-align:center">Review</option>
				<option value="2" style="text-align:center">Recipe</option>
			</select>
		</c:if>
		</td>
		<td id="title">작성자</td>
		<td id="title2">${sessionScope.sessionID}</td>
	</tr>
	<tr>
		<td id="title">제목 </td>
		<td colspan="3"><input type="text" name="board_subject" size="70" id="subject"></td>
	</tr>
	<tr>
		<td id="title">내용</td>
		<td colspan="3"><textarea name="board_content" cols="80" rows="20" id="content"></textarea></td>
	</tr>
	<tr>
		<td id="title">파일첨부</td>
		<td colspan="3" style="text-align:left"><input type="file" name="board_file"></td>
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