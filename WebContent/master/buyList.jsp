<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="jsp.bakeryshopping.model.*"%>

<%@ page import = "jsp.bakerymaster.model.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.NumberFormat"%>

<%
	String buyer = request.getParameter("customer_id");
%>
<html>
<head>
<title>Shopping Mall</title>
<style>
th{
	text-align : center;
	height : 30px;
}
td{
	height : 50px;
}
#btn{
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
<h3 align="center">구매 내역</h3>

	<%
		ArrayList<Order> orderLists = null;
		Order order = null;
		int count = 0;
		int number = 0;
		int total = 0;
		long compareId = 0;
		long preId = 0;

		OrderDao orderProcess = OrderDao.getInstance();
		count = orderProcess.getListCount(buyer);

		if (count == 0) {
	%>
	<br><br>
         <table width="650" cellpadding="3" cellspacing="3" align="center"> 
           <tr><td align="center" >구매 내역이 없습니다.</td> </tr>
         </table>

	<%
		} else {
			orderLists = orderProcess.getOrderList(buyer);
	%>
	<table border="1" width="900" cellpadding="0" cellspacing="0" align="center">
		<caption>
		</caption>
		<tr>
			<th>No.</th>
			<th>Product Name</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Subtotal</th>
			<th>Order Date</th>
		</tr>
		<%
			for (int i = 0; i < orderLists.size(); i++) {
					order = (Order) orderLists.get(i);

					 ProductDao pd = ProductDao.getInstance();
			         Product product  = pd.getProduct(order.getProduct_id());
		%>
		<tr>
			<td align="center" width="80"><%=i + 1%></td>
			<td width="300"> <img src="imageFile/<%=product.getProduct_img()%>" border="0" width="30" height="50" align="middle">
             <%=product.getProduct_name()%></td>
			<td align="center" width="100">\<%=NumberFormat.getInstance().format(order.getOrder_price())%></td>
			<td width="100" align="center"><%=order.getOrder_count()%></td>
			<td align="center" width="150">
				<%
					total += order.getOrder_count() * order.getOrder_price();
				%> \<%=NumberFormat.getInstance().format(
						order.getOrder_count() * order.getOrder_price())%>
			</td>
			<td align="center" width="150"><%=order.getOrder_date() %></td>
		</tr>
		<%
			}
		%>
		<tr>
			<td colspan="6" align="right"><b>Total \<%=NumberFormat.getInstance().format(total)%></b></td>
		</tr>
	</table>
	<br>
	<%
		}
	%>
	<br><br>
		<div align="center">
		<input id="btn" type="button" value="메인으로" onclick="location.href='ShopMainForm.co'">
		
		</div>
</body>
</html>