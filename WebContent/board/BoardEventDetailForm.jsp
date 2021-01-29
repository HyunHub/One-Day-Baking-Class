<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 내용 상세보기</title>
<link rel="stylesheet" type="text/css" href="css/event_detail_style.css">
<script type="text/javascript">
	function doModify(value){
		if(value == 1){
			location.href="BoardEventUpdateFormAction.bo?num=${event.event_num}";
		}else if(value == 2){
			location.href="BoardEventDeleteAction.bo?num=${event.event_num}";
		}else if(value ==3){
			location.href="BoardEventListAction.bo?event_category=2"
		}
	}
</script>
</head>
<body>
<div class="detail-wrap">
	<button id="detail-btn"><a href="javascript:doModify(1);" >수정</a></button>
	<button id="detail-btn"><a onclick="return confirm('정말로 삭제하시겠습니까?')" href="javascript:doModify(2);" >삭제</a></button>
	<button id="detail-btn"><a href="javascript:doModify(3);" >목록</a></button>
</div>
<br>
<table class="detail-table" align="center">
	<tr>
		<td id="detail-item1">작성자</td>
		<td id="detail-item2">${event.event_id}</td>
		<td id="detail-item1">등록일</td>
		<td id="detail-item2">${event.event_date }</td>
	</tr>
	<tr><td></td></tr>
	<tr>
		<td colspan="4" id="detail-item2">${event.event_file }</td>
	</tr>
	<tr>
		<td colspan="4">
			<img src="UploadFolder/${event.event_file}">
		</td>
	</tr>
</table>
</body>
</html>