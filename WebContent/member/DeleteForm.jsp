<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>탈퇴 화면</title>
<link rel="stylesheet" type="text/css" href="css/join_style.css">
<script type="text/javascript">
	// 비밀번호 미입력시 경고창
	function checkValue() {
		if (!deleteform.password.value) {
			alert("비밀번호를 입력하지 않았습니다.");
			return false;
		}
	}
</script>

</head>
<body>
    <br><br>
    <h2 align="center">Delete My Info</h2>
    <br>
 
<form name="deleteform" method="post" action="MemberDeleteAction.do" onsubmit="return checkValue()">

<table id="member_table">
	<tr>
	<td id="title">비밀번호</td>
	<td><input type="password" name="password" maxlength="50"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="취소" onclick="location.href='MainForm.do'">
			<input type="submit" value="탈퇴">
		</td>
</table>
</form>
</body>
</html>