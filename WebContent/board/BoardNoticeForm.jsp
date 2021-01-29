<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/board_style.css">
<title>Notice</title>
</head>
<body>
<div id="wrap">
<c:if test="${board_category==3 }">
	<h2 style="padding-left:130px">Notice</h2>
</c:if>
<br>
<br>
	<div id="board">
		<table class="table table-hover" align="center">
			<tr id="list_kind">
				<td id="item1">글번호</td>
				<td>제목</td>
				<td id="item2">작성자</td>
				<td id="item2">작성일</td>
				<td id="item1">조회수</td>
			</tr>
		<c:forEach var="board" items="${requestScope.list}">
			<tr id="list_con">
					<td id="item1">${board.board_num }</td>
				<c:if test="${board.board_del == 'y'and board.board_category == board_category }">
					<td colspan="5" align="center">삭제된 글입니다.</td>
			</tr>
				</c:if>
				<c:if test="${board.board_del !='y' and board.board_category == board_category}">
					<td width="100">
						<a href="BoardDetailAction.bo?num=${board.board_num}&page=${spage}">${board.board_subject}</a>
					</td>
					<td id="item2">${board.board_id}</td>
					<td id="item2">${board.board_date}</td>
					<td id="item1">${board.board_count}</td>
			</tr>
			</c:if>
		</c:forEach>
		</table>
	</div>
<br>
<!-- 글쓰기 -->
	<div class="topForm">
		<c:if test="${sessionScope.sessionID=='admin'}">
			<input id="list-btn2" type="button" value="글쓰기" onclick="location.href='BoardWriteForm.bo?board_category=3'">
		</c:if>
	</div>
	
<!-- 페이지 넘버 부분 -->
	<br>
	<div id="pageForm">
		<c:if test="${startPage != 1}">
			<a href='BoardNoticeAction.bo?page=${startPage-1}&board_category=${board_category}'>[ 이전 ]</a>
		</c:if>
		
		<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
			<c:if test="${pageNum == spage}">
				${pageNum}&nbsp;
			</c:if>
			<c:if test="${pageNum != spage}">
				<a href='BoardNoticeAction.bo?page=${pageNum}&board_category=${board_category}'>${pageNum}&nbsp;</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${endPage != maxPage }">
			<a href='BoardNoticeAction.bo?page=${endPage+1}&board_category=${board_category}'>[다음]</a>
		</c:if>
	</div>
</div>
</body>
</html>