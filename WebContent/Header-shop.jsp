<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>shop-header</title>
<script type="text/javascript">
	function changeView(value){
		if(value == "0") // HOME 버튼 클릭시 첫화면으로 이동
		{
			location.href="ShopMainForm.co";
		}
		else if(value == "1") // 내정보 클릭시 회원정보 보여주는 화면으로 이동
		{
			location.href="MemberInfoAction.do";
		}
		else if(value == "2"){
			location.href="shopMain.co";
		}
		else if(value == "3"){
			location.href="CartListAction.co?customer_id=${sessionScope.sessionID}";
		}
		else if(value == "4"){
			location.href="BoardEventListAction.bo?event_category=1";
		}
		else if(value == "5"){
			location.href="masterMain.co";
		}else if(value == "6"){
			location.href="Management.do";
		}else if(value == "7"){
			location.href="productRegisterForm.co";
		}else if(value == "8"){
			location.href="productList.co";
		}else if(value == "9"){
			location.href="BuyAction.co?customer_id=${sessionScope.sessionID}";
		}
	}
</script>
</head>
<body>
<div class="info">
	<c:if test="${sessionScope.sessionID!=null }">
	<div class="info-item">
		<button class="info-btn" onclick="changeView(1)">MY PAGE</button>
	</div>
	</c:if>
	<c:if test="${sessionScope.sessionID=='admin' }">
	<div class="info-item">
		<button class="info-btn" onclick="changeView(6)">MANAGEMENT</button>
	</div>
	</c:if>
</div>
<div class="page-header">
  <h1 onclick="changeView(0)">BAKING ONEDAY CLASS SHOPPING</h1>
</div>
<div class="container">
      <div class="row">
        <div class="col">
          <ul class="nav nav-tabs justify-content-center" style="border:none">
          <li class="nav-item">
              <a class="nav-link" onclick="changeView(2)">PRODUCT</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" onclick="changeView(4)">EVENT</a>
            </li>
            <li class="nav-item dropdown">
            	<c:if test="${sessionScope.sessionID=='admin' }">
              	<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">MANAGEMENT</a>
              	<div class="dropdown-menu">
                <a class="dropdown-item" onclick="changeView(7)">REGISTER</a>
                <a class="dropdown-item" onclick="changeView(8)">EDIT/DELETE</a>
              	</div>
              	</c:if>
                <c:if test="${sessionScope.sessionID != null and sessionScope.sessionID !='admin' }">
            	<a class="nav-link" onclick="changeView(3)">MY CART</a>
            	</c:if>
            </li>
            	<c:if test="${sessionScope.sessionID != null and sessionScope.sessionID !='admin' }">
           		<li class="nav-item">
             	<a class="nav-link" onclick="changeView(9)">BUY</a>
            	</li>
            	</c:if>
          </ul>
       </div>
    </div>
</div>
</body>
</html>