<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 상세보기</title>
<link rel="stylesheet" type="text/css" href="css/board_detail_style.css">
<script type="text/javascript">
	function doAction(value){
		if(value == 0){
			location.href="BoardQnaUpdateFormAction.bo?num=${qna.qna_num}&page=${pageNum}";
		}else if(value == 1){
			location.href="BoardQnaDeleteAction.bo?num=${qna.qna_num}&page=${pageNum}";
		}else if(value == 2){
			location.href="BoardQnaListAction.bo?page=${pageNum}";
		}else if(value ==3){
			location.href="BoardQnaReplyFormAction.bo?num=${qna.qna_num}&page=${pageNum}";
		}
	}
</script>
</head>
<body>
<div id="wrap">
<br><br>
	<div id="board">
		<table class="board_detail_table" align="center" border="1">
			<tr>
				<td id="qna-title">카테고리</td>
				<td id="qna-cont">
					<c:if test="${qna.qna_category == 1 }">클래스 문의</c:if>
					<c:if test="${qna.qna_category == 2 }">쇼핑몰 문의</c:if>
				</td>
				<td id="qna-title">작성자</td>
				<td id="qna-cont">${qna.qna_id}</td>
				<td id="qna-title">작성일</td>
				<td id="qna-cont">${qna.qna_date}</td>
			</tr>
			<tr>
				<td id="qna-title">제목</td>
				<td colspan="5">${qna.qna_subject}</td>
			</tr>
			<tr>
				<td id="qna-title">내용</td>
				<td colspan="5">${qna.qna_content}</td>
			</tr>
		</table>
	</div>
</div>
<div class="detail-wrap">
<c:if test="${sessionScope.sessionID != null }">
	<c:if test="${sessionScope.sessionID == qna.qna_id}">
		<input id="detail-btn" type="button" value="수정" onclick="doAction(0)">
		<button id="detail-btn"><a onclick="return confirm('정말로 삭제하시겠습니까?')" href="javascript:doAction(1);" >삭제</a></button>
	</c:if>
	<c:if test="${sessionScope.sessionID == 'admin' }">
		<input id="detail-btn" type="button" value="답변하기" onclick="doAction(3)">
	</c:if>
		<input id="detail-btn" type="button" value="목록" onclick="doAction(2)">
</c:if>
</div>
</body>
</html>