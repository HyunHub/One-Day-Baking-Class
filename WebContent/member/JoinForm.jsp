<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link rel="stylesheet" type="text/css" href="css/join_style.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function checkValue(){
		if(!userInfo.id.value){
			alert("아이디를 입력하세요.");
			userInfo.id.focus();
			return false;
		}
		if(!userInfo.password.value){
			alert("비밀번호를 입력하세요.");
			userInfo.password.focus();
			return false;
		}
		if(userInfo.password.value != userInfo.passwordcheck.value){
			alert("비밀번호와 비밀번호확인이 일치하지 않습니다.");
			userInfo.password.focus();
			userInfo.password.value="";
			return false;
		}
	}
	function idChk() {
		if (!userInfo.id.value) {
			alert("아이디 입력한 후에 체크하시오");
			userInfo.id.focus();
			return false;
			}
		window.open("IdChkAction.do?customer_id="+userInfo.id.value,"", "width=1050, height=470");
		
/* 		$.post('IdChkAction.do', "customer_id="+userInfo.id.value, function(data) {
			$('#err').html(data);
		}); */
	}
	
	$('.form-group input').on('focus blur', function (e) {
	    $(this).parents('.form-group').toggleClass('active', (e.type === 'focus' || this.value.length > 0));
	}).trigger('blur');
</script>
<script type="text/javascript">
</script>
</head>
<body>
<div class="form-box">    
	<form method="post" action="MemberJoinAction.do" name="userInfo" onsubmit="return checkValue()" id="login-form">
		<p class="text-p">* 모든 사항은 필수 입력입니다.</p> 
        <div class="form-group">
          <label class="label-control">
            <span class="label-text"></span>
          </label>
          <input type="text" name="id" class="form-control1" placeholder="아이디" maxlength="20" required="required">
          <button id="chk-btn" onclick="idChk()">중복 확인</button>
          <div id="err"></div>
        </div>
        <div class="form-group">
          <label class="label-control">
            <span class="label-text"></span>
          </label> 
          <input type="password" name="password" class="form-control" placeholder="비밀번호" maxlength="20" required="required">
        </div>
        <div class="form-group">
          <label class="label-control">
            <span class="label-text"></span>
          </label> 
          <input type="password" name="passwordcheck" class="form-control" placeholder="비밀번호 확인" maxlength="20" required="required">
        </div>
        <div class="form-group">
          <label class="label-control">
            <span class="label-text"></span>
          </label> 
          <input type="text" name="name" class="form-control" placeholder="이름" maxlength="20" required="required">
        </div>
        <div class="form-group">
          <label class="label-control">
            <span class="label-text"></span>
          </label> 
          <input type="text" name="email" class="form-control" placeholder="이메일" required="required">
        </div>
        <div class="form-group">
          <label class="label-control">
            <span class="label-text"></span>
          </label> 
          <input type="tel" name="phone" class="form-control" required="required" pattern="\d{3}-\d{3,4}-\d{4}" title="xxx-xxxx-xxxx형식입니다" placeholder="휴대전화">
        </div>
        <div class="form-group">
          <label class="label-control">
            <span class="label-text"></span>
          </label> 
          <input type="text" name="address" class="form-control" placeholder="주소" required="required">
        </div>
        <input type="submit" value="Join" class="btn">
    </form>
 </div>
</body>
</html>