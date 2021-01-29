<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 글쓰기</title>
<link rel="stylesheet" type="text/css" href="css/board_write_style.css">
<script type="text/javascript">
	function back(){
		return history.back();
	}
</script>
</head>
<body>
<br>
<h2 style="text-align : center">QnA 글쓰기</h2>
<br>
<form method="post" action="BoardQnaWriteAction.bo" name="qnaForm">
<input type="hidden" name="qna_id" value="${sessionScope.sessionID}">
<table class="board_write_table" align="center" border="1">
	<tr>
		<td id="title">카테고리</td>
		<td id="title2">
			<select name="qna_category" id="select_ct">
				<option value="1" style="text-align:center">클래스 문의</option>
				<option value="2" style="text-align:center">쇼핑몰 문의</option>
			</select>
		</td>
		<td id="title">비밀번호</td>
		<td><input type="password" name="qna_pw" id="title2">
		<td id="title">작성자</td>
		<td id="title2">${sessionScope.sessionID}</td>
	</tr>
	<tr>
		<td id="title">제목</td>
		<td colspan="5"><input type="text" name="qna_subject" size="70" id="subject"></td>
	</tr>
	<tr>
		<td id="title">내용</td>
		<td colspan="5"><textarea name="qna_content" cols="80" rows="20" id="content"></textarea></td>
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