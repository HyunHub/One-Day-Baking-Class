<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function winclose() {
		opener.userInfo.id.value=${customer_id};
		window.close();
	}
</script>
<style>
	.btn-wrap{
		text-align : center;
	}
	#btn{
		dispaly : inline-block;
		width : 100px;
		height : 40px;
		align : center;
		background-color : #FBF8EF;
		border : 3px solid #F8ECE0;
		border-radius : 50px;
		margin : 15px 10px;
	}
</style>
</head>
<body>
<br><br>
<h3 align="center">${msg }</h3>
<br><br>
<div class="btn-wrap">
	<button id="btn" onclick="window.close()">닫기</button>
</div>

</body>
</html>