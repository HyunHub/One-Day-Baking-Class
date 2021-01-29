<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<script type="text/javascript">
function changeView(value){
	
	if(value == "0") // HOME 버튼 클릭시 첫화면으로 이동
	{
		location.href="MainForm.do";
	}
	else if(value == "1") // 로그인 버튼 클릭시 로그인 화면으로 이동
	{
		location.href="LoginForm.do";
	}
	else if(value == "2") // 회원가입 버튼 클릭시 회원가입 화면으로 이동
	{
		location.href="JoinForm.do";
	}
	else if(value == "3") // 로그아웃 버튼 클릭시 로그아웃 처리
	{
		location.href="MemberLogoutAction.do";
	}
	else if(value == "4") // 내정보 클릭시 회원정보 보여주는 화면으로 이동
	{
		location.href="MemberInfoAction.do";
	}
	else if(value == "5")
	{
		//location.href="MemberListAction.do";
		location.href="Management.do";
	}
	else if(value == "6"){
		location.href="BoardListAction.bo?board_category=1";
	}
	else if(value == "7"){
		location.href="BoardListAction.bo?board_category=2";
	}
	else if(value == "8"){
		location.href="About.do";
	}
	else if(value == "9"){
		/* location.href="BoardListAction.bo?board_category=3"; */
		location.href="BoardNoticeAction.bo?board_category=3";
	}
	else if(value == "10"){
		location.href="BoardQnaListAction.bo";
	}
	else if(value == "11"){
		location.href="BoardEventListAction.bo?event_category=1";
	}else if(value == "12"){
		location.href="ClassListAction.ao";
	}
}
</script>
</head>
<body>
<div class="info">
	<c:if test="${sessionScope.sessionID==null }">
	<div class="info-item">
		<button class="info-btn" onclick="changeView(2)">JOIN</button>
	</div>
	<div class="info-item">
		<button class="info-btn" onclick="changeView(1)">LOGIN</button>
	</div>
	</c:if>
	<c:if test="${sessionScope.sessionID!=null }">
	<div class="info-item">
		<button class="info-btn" onclick="changeView(3)">LOGOUT</button>
	</div>
	<div class="info-item">
		<button class="info-btn" onclick="changeView(4)">MY PAGE</button>
	</div>
	</c:if>
	<c:if test="${sessionScope.sessionID=='admin' }">
	<div class="info-item">
		<button class="info-btn" onclick="changeView(5)">MANAGEMENT</button>
	</div>
	</c:if>
</div>
<div class="page-header">
  <h1 onclick="changeView(0)">BAKING ONEDAY CLASS</h1>
</div>
<div class="container">
      <div class="row">
        <div class="col">
          <ul class="nav nav-tabs justify-content-center" style="border:none">
          <li class="nav-item">
              <a class="nav-link" onclick="changeView(8)">ABOUT</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" onclick="changeView(12)">APPLY</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">BOARD</a>
              <div class="dropdown-menu">
                <a class="dropdown-item" onclick="changeView(6)">Review</a>
                <a class="dropdown-item" onclick="changeView(7)">Recipe</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">SUPPORT</a>
              <div class="dropdown-menu">
                <a class="dropdown-item" onclick="changeView(9)">Notice</a>
                <a class="dropdown-item" onclick="changeView(10)">QnA</a>
              </div>
            </li>
            <li class="nav-item">
              <a class="nav-link" onclick="changeView(11)">EVENT</a>
            </li>
            <li class="nav-item">
            	<c:if test="${sessionScope.sessionID != null }">
            		<a class="nav-link" onclick="window.open('ShopMainForm.jsp')">SHOP</a>
            	</c:if>
            	<c:if test="${sessionScope.sessionID == null }">
            		<a class="nav-link" onclick="#">SHOP</a>
            	</c:if>
            </li>
          </ul>
       </div>
    </div>
</div>
</body>
</html>