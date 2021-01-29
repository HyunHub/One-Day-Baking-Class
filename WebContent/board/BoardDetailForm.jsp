<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세보기</title>
<link rel="stylesheet" type="text/css" href="css/board_detail_style.css">
<script type="text/javascript">
	function doAction(value){
		if(value == 0){
			location.href="BoardUpdateFormAction.bo?num=${board.board_num}&page=${pageNum}&board_category=${board.board_category}";
		}else if(value == 1){
			location.href="BoardDeleteAction.bo?num=${board.board_num}&page=${pageNum}&board_category=${board.board_category}";
		}else if(value == 2){
			location.href="BoardLikeAction.bo?num=${board.board_num}&recommend_id=${sessionID}&pageNum=${pageNum}";
		}else if(value == 3){
			location.href="BoardListAction.bo?board_category=${board.board_category}&page=${pageNum}";
		}else if(value == 4){
			location.href="BoardNoticeAction.bo?board_category=3&page=${pageNum}";
		}
	}
	
	function cmReplyOpen(comment_num, pageNum, board_category){
		window.open("BoardCommentReplyFormAction.bo?num="+comment_num+"&pageNum="+pageNum+"&board_category="+board_category,"", "width=1050, height=800");
	}
	function doDelete(value){
		if(value == 0){
			location.href="BoardCommentDeleteAction.bo?num=${comment.comment_num}&page=${pageNum}&board_category=${board.board_category}&comment_board=${comment.comment_board}";
		}
	}
	function cmUpdateOpen(comment_num, pageNum, board_category){
		window.open("BoardCommentUpdateFormAction.bo?num="+comment_num+"&board_category="+board_category+"&page="+pageNum, "", "width=1050, height=800");
	}
</script>
</head>
<body>
<div id="wrap">
<br><br>
	<div id="board">
		<table class="board_detail_table" align="center" border="1">
			<tr>
				<td id="title">카테고리</td>
				<td>
					<c:choose>
						<c:when test="${board.board_category==1}">
							Review</td>
						</c:when>
						<c:when test="${board.board_category==2 }">
							Recipe</td>
						</c:when>
						<c:when test="${board.board_category==3 }">
							Notice</td>
						</c:when>
					</c:choose>
				</td>
				<td id="title">작성일</td>
				<td>${board.board_date}</td>
			</tr>
			<tr>
				<td id="title">작성자</td>
				<td>${board.board_id}</td>
				<td id="title">추천수</td>
				<td>${board.like_count}</td>
			</tr>
			<tr>
				<td id="title">제목</td>
				<td colspan="3">${board.board_subject}</td>
			</tr>
			<tr>
				<td id="title">내용</td>
				<td colspan="3">
					<br>
					${board.board_content}
					<br><br>
					<c:if test="${board.board_file != null }">
						<img id="img_cnt" src="UploadFolder/${board.board_file}"><br>
					</c:if>
				</td>
			</tr>
			<tr>
				<td id="title">첨부파일</td>
				<td colspan="3">
					<c:if test="${board.board_file == null }">
						없음
					</c:if>
					<c:if test="${board.board_file != null }">
						${board.board_file}
					</c:if>
				</td>
			</tr>
		</table>
	</div>
</div>
<div class="detail-wrap">
<c:if test="${sessionScope.sessionID != null }">
	<c:if test="${sessionScope.sessionID == board.board_id}">
		<input id="detail-btn" type="button" value="수정" onclick="doAction(0)">
		<button id="detail-btn"><a onclick="return confirm('정말로 삭제하시겠습니까?')" href="javascript:doAction(1);" >삭제</a></button>
	</c:if>
	</c:if>
	<c:if test="${board.board_category !=3 }">
		<input id="detail-btn" type="button" value="추천" onclick="doAction(2)">
		<input id="detail-btn" type="button" value="목록" onclick="doAction(3)">
	</c:if>
	<c:if test="${board.board_category ==3 }">
		<input id="detail-btn" type="button" value="목록" onclick="doAction(4)">
	</c:if>
</div>
<br>
<!-- 댓글 -->
<div class="comment">
<table class="board_detail_table" align="center" style="background-color:#F8ECE0;">
<c:if test="${requestScope.commentList != null }">
	<c:forEach var="comment" items="${requestScope.commentList }">
	<tr>
		<c:if test="${comment.comment_del == 'y' }">
			<td colspan="3" id="cm_cnt">삭제된 댓글입니다.</td>
		</c:if>
	</tr>
	<tr>
		<c:if test="${comment.comment_del != 'y' }">
		<td align="left" id="cm_id">
			<c:if test="${comment.comment_level >1 }">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img alt="" src="image/reply.png">&nbsp;
			</c:if>
			${comment.comment_id }&nbsp;&nbsp;<font size="1">${comment.comment_date }</font>			
		</td>
		<td id="cm_cnt" align="left">
				${comment.comment_content }		
		</td>
		<td id="cm_reply">
			<c:if test="${sessionScope.sessionID != null }">
				<input type="button" value="답변" id="cnt-btn" onclick="cmReplyOpen(${comment.comment_num}, ${pageNum}, ${board.board_category})">
			</c:if>
			<c:if test="${comment.comment_id == sessionScope.sessionID }">
				<input type="button" value="수정" id="cnt-btn" onclick="cmUpdateOpen(${comment.comment_num}, ${pageNum}, ${board.board_category})">
				<button id="cnt-btn"><a onclick="return confirm('정말로 삭제하시겠습니까?')" href="BoardCommentDeleteAction.bo?num=${comment.comment_num}
				&page=${pageNum}&board_category=${board.board_category}&comment_board=${comment.comment_board}" >삭제</a></button>
			</c:if>
		</td>		
		</c:if>
	</tr>
	</c:forEach>
</c:if>
</table>
</div>
<br>
<div>
<!-- 댓글 작성 부분(로그인 했을 경우에만 보임) -->
<c:if test="${sessionScope.sessionID != null }">
<table class="board_detail_table" align="center" border="1">
<form method="post" action="BoardCommentWriteAction.bo?&board_category=${board.board_category}&pageNum=${pageNum }" name="commentForm">
<input type="hidden" name="comment_board" value="${board.board_num }">
<input type="hidden" name="comment_id" value="${sessionScope.sessionID }">
<input type="hidden" name="pageNum" value="${pageNum }">
	<tr>
		<td colspan="2">${sessionScope.sessionID }</td>
		<td rowspan="2"><input id="detail-btn" type="submit" value="등록"></td>
	</tr>
	<tr>
		<td><textarea name="comment_content" rows="4" cols="70"></textarea></td>
	</tr>
</form>
</table>
</c:if>
</div>
</body>
</html>