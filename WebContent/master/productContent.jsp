<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "jsp.bakerymaster.model.*,java.text.NumberFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String product_id = request.getParameter("product_id");
    String id = "";
    int buy_price=0;
    if (session.getAttribute("customer_id") == null){
       id = "not";
    } else {  id = (String)session.getAttribute("customer_id");    }
    
  %>
<html><head><title>Shopping Mall</title>
<script type="text/javascript">
	function chk() {
		if (parseInt(inform.cart_count.value) > parseInt(inform.product_stock.value)) {
			alert("Stock Unavailable " + inform.cart_count.value);
			inform.cart_count.focus();	
			return false;
		}
		return true;
	}
</script>
<style>
table{
	width : 800px;
}
img{
	width : 200px;
	height : 250px;
	margin-left : 30px;
}
#cart-btn{
	width : 140px;
	height : 40px;
	align : center;
	background-color : #FBF8EF;
	border : 3px solid #F8ECE0;
	border-radius : 50px;
}
</style>
</head>
<body>
<br><br>
<%
     ProductDao productProcess = ProductDao.getInstance(); 
     Product product = productProcess.getProduct(Integer.parseInt(product_id));
     
%>
     <form name="inform" method="post" action="CartInsertAction.co?customer_id=${sessionScope.sessionID }&product_id=${product.product_id}" onsubmit="return chk()">
     <table border="0" cellpadding="5" cellspacing="5" align="center"> 
       <tr height="30"> 
         <td rowspan="6"  width="150" align="center" valign="middle">
             <img src="imageFile/<%=product.getProduct_img()%>" border="0"></td> 
         <td width="500"><font size="+1"><b><%=product.getProduct_name() %></b></font></td>
       </tr> 
       <tr>
       		<td>Price : <%=product.getProduct_price()%></td>
       </tr>
        		<%buy_price = (int)(product.getProduct_price()) ;%>
       <tr>
       			<td colspan="2"><%=product.getProduct_content()%></td>
       </tr>
       <tr>
       	<td>수량 : <input type="text" size="5" name="cart_count">
       <c:if test="${sessionScope.sessionID == null }">
			 <%
                	 if(product.getProduct_state()==2){
              %>
                		 &nbsp;&nbsp;<b>품절</b>
              <%    }   %>
       </c:if>
       <c:if test="${sessionScope.sessionID != null }">
				<% if(product.getProduct_state()==2){  %>
                         &nbsp;&nbsp;<b>품절</b>                           
             <%      }else{    %>	 
                        <input type="hidden" name="product_id" value="<%=product_id%>">
                        <input type="hidden" name="product_img" value="<%=product.getProduct_img()%>">
                        <input type="hidden" name="product_name" value="<%=product.getProduct_name() %>">
                        <input type="hidden" name="product_price" value="<%=buy_price %>">              
            	        <input type="hidden" name="product_stock" value="<%=product.getProduct_stock()%>">
            	        &nbsp;&nbsp;<input id="cart-btn" type="submit" value="장바구니 담기">
    <%      } %>
       
       </c:if>
            </td>
       </tr>
       <tr>
       		<td>
       			<input id="cart-btn" type="button" value="상품 보러 가기" onclick="location.href='list.co'">
       		</td>
       </tr>
     </table></form></body></html>