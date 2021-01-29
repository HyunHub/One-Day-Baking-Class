<%@page import="jsp.bakerymaster.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "jsp.bakeryshopping.model.*,java.text.NumberFormat" %>
<%
	String buyer = request.getParameter("customer_id");
	String product_id = request.getParameter("product_id");
%>
<html><head><title>Shopping Mall</title>
<script type="text/javascript">
	function chk(i) {
		if (parseInt(document.forms[i].cart_count.value) > 
			parseInt(document.forms[i].product_stock.value)) {
			alert("You can only buy up to " + document.forms[i].product_stock.value);
			return false;
		}
	}
</script>
<style>
.btn-wrap{
	text-align : center;
	margin-top : 20px;
}
#btn{
	width : 140px;
	height : 40px;
	align : center;
	background-color : #FBF8EF;
	border : 3px solid #F8ECE0;
	border-radius : 50px;
}
.list-table{
	width : 800px;
	text-align : center;
	border : 1px solid #F8ECE0;
}
#list{
	height : 50px;
	background-color : #F8ECE0;
}
#btn2{
	border : none;
	background-color : #FBF8EF;
}
</style>
</head>
<body>
<br><br>
<h3 align="center">장바구니</h3>

<div align="center">
<%
	CartDao productProcess = CartDao.getInstance();
	ArrayList<Cart> cartLists = productProcess.getCart(buyer);  
	Cart cart = new Cart();
	int count = 0;  int number = 0;  int total = 0;
    count = productProcess.getListCount(buyer);
    if(count == 0){
%> 
<br><br>
         <table width="650" cellpadding="3" cellspacing="3" align="center"> 
           <tr><td align="center" >장바구니가 비어있어요.</td> </tr>
         </table>
         <br><br>
         <input id="btn" type="button" value="쇼핑하러가기" onclick="location.href='shopMain.co'">
   <%
   	}else{
           cartLists = productProcess.getCart(buyer);
   %>
<br><br>
       <table class="list-table" border="1" align="center"> 
         <tr><td id="list">No.</td><td id="list">Product Name</td> 
           <td id="list">Price</td><td id="list">Quantity</td><td id="list">Delete</td></tr>      
<%
      	for(int i=0;i<cartLists.size();i++){
          	 cart = (Cart)cartLists.get(i);
          	 ProductDao pd = ProductDao.getInstance();
          	Product product  = pd.getProduct(cart.getProduct_id());
      %>   <tr> 
          <td width="70" height="100"><%=++number %></td> 
          <td  width="300" align="left">
             <img src="imageFile/<%=product.getProduct_img()%>" border="0" width="70" height="90" align="middle">
             <%=product.getProduct_name()%>
          </td> 

          <td width="100">\<%=NumberFormat.getInstance().format(cart.getCart_price())%></td>
          <td width="150" align="center">
        <form name="im" method="post" action="UpdateCartAction.co?buyer=<%=buyer %>" onsubmit="return chk('<%=i%>')">  
                <input type="text" name="cart_count" size="5" value="<%=cart.getCart_count()%>">
                <input type="hidden" name="cart_no" value="<%=cart.getCart_no()%>">
                <input type="hidden" name="cart_price" value="<%=cart.getCart_price()%>">
               	<input type="hidden" name="product_id" value="<%=cart.getProduct_id()%>">
               	<input type="hidden" name="product_stock" value="<%=product.getProduct_stock()%>">
                <input id="btn2" type="submit" value="Edit" >
       </form>              
          </td> 
          <td align="center"  width="150" align="center">
              <%total += cart.getCart_count()*cart.getCart_price();%>
             <input id="btn2" type="button" value="Delete" onclick="location.href='CartDeleteAction.co?list=<%=cart.getCart_no() %>&buyer=<%=buyer%>'">
          </td>
       </tr>  
<%     }	%>
       <tr>
          <td colspan="5" align="right"><b>Total  \<%=NumberFormat.getInstance().format(total)%></b></td>
      </tr>
   </table>
	<div class="btn-wrap">
		<input id="btn" type="button" value="구매하기" onclick="location.href='BuyFormAction.co?buyer=<%=buyer%>'">
		<input id="btn" type="button" value="쇼핑" onclick="location.href='shopMain.co'">
		<input id="btn" type="button" value="전체 삭제" onclick="location.href='CartDeleteAction.co?list=all&product_id=<%=product_id%>&buyer=<%=buyer%>'">
	</div>
<%   }  %></div>
</body>
</html>