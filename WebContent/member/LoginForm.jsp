<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<link rel="stylesheet" type="text/css" href="css/login_style.css">
<script type="text/javascript">
	function checkValue(){
		if(!loginInfo.id.value){
			alert("아이디를 입력하세요.");
			loginInfo.id.focus();
			return false;
		}
		if(!loginInfo.password.value){
			alert("비밀번호를 입력하세요.");
			loginInfo.password.focus();
			return false;
		}
	}
	
	function Joinfunction(){
		location.href="JoinForm.do";
	}
</script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$('.form-group input').on('focus blur', function (e) {
    $(this).parents('.form-group').toggleClass('active', (e.type === 'focus' || this.value.length > 0));
}).trigger('blur');
</script>
</head>
<body>
<div class="form-box">    
    <form name="loginInfo" method="post" action="MemberLoginAction.do" onsubmit="return checkValue()" id="login-form">
        <div class="form-group">
          <label class="label-control">
            <span class="label-text"></span>
          </label>
          <input type="text" name="id" class="form-control" placeholder="아이디" maxlength="10">
        </div>
        <div class="form-group">
          <label class="label-control">
            <span class="label-text"></span>
          </label> 
          <input type="password" name="password" class="form-control" placeholder="비밀번호" maxlength="20">
        </div>
        <input type="submit" value="Login" class="btn">
        <p class="text-p">Don't have an account? <a href="#Joinfunction" onclick="Joinfunction(); return false">&nbsp; Sign up</a> </p>
    </form>
 </div>

</body>
</html>