<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 전체 목록 조회</title>
<link rel="stylesheet" type="text/css" href="css/board_style.css">
<script type="text/javascript">
	function doDetail(){
		location.href="checkpassword.jsp";
	}
</script>
</head>
<body>
<div id="wrap">
<h2 style="padding-left:130px">QnA</h2>
<br>
<div id="board">
	<table class="table table-hover" align="center">
		<tr id="list_kind">
			<td id="item1">글번호</td>
			<td id="item2">카테고리</td>
			<td>제목</td>
			<td id="item2">작성자</td>
			<td id="item2">작성일</td>
		</tr>
	<c:forEach var="qna" items="${requestScope.list}">
		<c:if test="${qna.qna_del !='y'}">
			<tr id="list_con">
				<c:if test="${qna.qna_category != null }">
					<td id="item1">${qna.qna_num}</td>
				</c:if>
				<c:if test="${qna.qna_category == null }">
					<td> </td>
				</c:if>
				<td id="item2">
				<c:if test="${qna.qna_category == 1}">클래스 문의</c:if>
				<c:if test="${qna.qna_category == 2}">쇼핑몰 문의</c:if>
				</td>
				<td width="100" align="left">
					<c:if test="${qna.qna_re_lev>0 }">
						<c:forEach begin="1" end="${qna.qna_re_lev }">
							&nbsp;
						</c:forEach>
						<img alt="" src="image/reply.png">
					</c:if>
					<c:if test="${sessionScope.sessionID == null }">
						${qna.qna_subject}
					</c:if>		
 					<c:if test="${sessionScope.sessionID != null and qna.qna_id != 'admin' and sessionScope.sessionID != 'admin'}">
						<a href="BoardCheckpasswordFormAction.bo?num=${qna.qna_num }&pageNum=${spage }">${qna.qna_subject}</a>
					</c:if>
					<c:if test="${sessionScope.sessionID != null and qna.qna_id == 'admin' and sessionScope.sessionID != 'admin' }">
						<a href="BoardQnaDetailAction.bo?num=${qna.qna_num }&pageNum=${spage}">${qna.qna_subject}</a>
					</c:if>
					<c:if test="${sessionScope.sessionID == 'admin' }">
						<a href="BoardQnaDetailAction.bo?num=${qna.qna_num }&pageNum=${spage}">${qna.qna_subject}</a>
					</c:if>
				</td>
				<td id="item2">${qna.qna_id}</td>
				<td id="item2">${qna.qna_date}</td>
			</tr>
		</c:if>
		<c:if test="${qna.qna_del == 'y'}">
			<tr id="list_con">
				<td colspan="5" align="center">삭제된 글입니다.</td>
			</tr>
		</c:if>
	</c:forEach>
	</table>
</div>
<br>
<!-- 글쓰기 -->
<div class="topForm">
	<c:if test="${sessionScope.sessionID!=null}">
		<input id="list-btn1" type="button" value="글쓰기" onclick="location.href='BoardQnaWriteForm.bo'">
		<input id="list-btn2" type="button" value="내 게시글 보기" onclick="location.href='BoardQnamyAction.bo?qna_id=${sessionScope.sessionID}'">
	</c:if>
</div>
<!-- 페이지 넘버 부분 -->
<br>
<div id="pageForm">
	<c:if test="${startPage != 1}">
		<a href='BoardQnaListAction.bo?page=${startPage-1}'>[ 이전 ]</a>
	</c:if>
	
	<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
		<c:if test="${pageNum == spage}">
			${pageNum}&nbsp;
		</c:if>
		<c:if test="${pageNum != spage}">
			<a href='BoardQnaListAction.bo?page=${pageNum}'>${pageNum}&nbsp;</a>
		</c:if>
	</c:forEach>
	<c:if test="${endPage != maxPage }">
		<a href='BoardQnaListAction.bo?page=${endPage+1}'>[다음]</a>
	</c:if>
</div>
</body>
</html>