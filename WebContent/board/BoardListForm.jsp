<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<link rel="stylesheet" type="text/css" href="css/board_style.css">
</head>
<body>
<div id="wrap">
<c:choose>
	<c:when test="${board_category==1}">
		<h2 style="padding-left:130px">Review Board</h2>
	</c:when>
	<c:when test="${board_category==2 }">
		<h2 style="padding-left:130px">Recipe Board</h2>
	</c:when>
</c:choose>
<br>
<br>
	<div id="board">
		<table class="table table-hover" align="center">
			<tr id="list_kind">
				<td id="item1">글번호</td>
				<td>제목</td>
				<td id="item2">작성자</td>
				<td id="item2">작성일</td>
				<c:if test="${board.board_category !=3 }">
				<td	id="item1">추천수</td>
				</c:if>
				<td id="item1">조회수</td>
			</tr>
			
		<!-- 추천수 높은 글 상단에 나오도록 -->
		<c:forEach var="board" items="${requestScope.list}">
			<c:if test="${board.board_del !='y' and board.board_category == board_category and board.like_count>=10}">
				<tr id="list_con">
					<td id="item1">${board.board_num}</td>
					<td width="100">
						<img alt="" src="image/star.png">
						<a href="BoardDetailAction.bo?num=${board.board_num}&page=${spage}">${board.board_subject}</a>
					</td>
					<td id="item2">${board.board_id}</td>
					<td id="item2">${board.board_date}</td>
					<c:if test="${board.board_category !=3 }">
					<td id="item1">${board.like_count}</td>
					</c:if>
					<td id="item1">${board.board_count}</td>
				</tr>
			</c:if>
			<c:if test="${board.board_del == 'y'and board.board_category == board_category }">
				<tr id="list_con">
					<td colspan="6" align="center">삭제된 글입니다.</td>
				</tr>
			</c:if>
			<c:if test="${board.board_del != 'y'and board.board_category == board_category and board.like_count<10}">
				<tr id="list_con">
					<td id="item1">${board.board_num}</td>
					<td width="100">
						<a href="BoardDetailAction.bo?num=${board.board_num}&page=${spage}">${board.board_subject}</a>
					</td>
					<td id="item2">${board.board_id}</td>
					<td id="item2">${board.board_date}</td>
					<c:if test="${board.board_category !=3 }">
					<td id="item1">${board.like_count}</td>
					</c:if>
					<td id="item1">${board.board_count}</td>
				</tr>
			</c:if>
		</c:forEach>
		</table>
	</div>
	<br>
	
<!-- 글쓰기 -->
	<div class="topForm">
		<c:if test="${sessionScope.sessionID!=null}">
			<input id="list-btn2" type="button" value="글쓰기" onclick="location.href='BoardWriteForm.bo'">
		</c:if>
	</div>
	
<!-- 페이지 넘버 부분 -->
	<br>
	<div id="pageForm">
		<c:if test="${startPage != 1}">
			<a href='BoardListAction.bo?page=${startPage-1}&board_category=${board_category}'>[ 이전 ]</a>
		</c:if>
		
		<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
			<c:if test="${pageNum == spage}">
				${pageNum}&nbsp;
			</c:if>
			<c:if test="${pageNum != spage}">
				<a href='BoardListAction.bo?page=${pageNum}&board_category=${board_category}'>${pageNum}&nbsp;</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${endPage != maxPage }">
			<a href='BoardListAction.bo?page=${endPage+1}&board_category=${board_category}'>[다음]</a>
		</c:if>
	</div>
	
	<!--  검색 부분 -->
	<br>
	<div id="searchForm">
		<form>
		<input type="hidden" name="board_category" value="${board_category }">
		<input type="hidden" name="page" value="${spage }">
			<select name="opt">
				<option value="0">제목</option>
				<option value="1">내용</option>
				<option value="2">제목+내용</option>
				<option value="3">글쓴이</option>
			</select>
			<input type="text" size="20" name="condition">&nbsp;
			<input id="list-btn2" type="submit" value="검색" >
		</form>	
	</div>
</div>
</body>
</html>