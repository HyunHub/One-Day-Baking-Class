<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%-- <%@ include file="../masterSessionChk.jsp" %> --%>
<%@ page import = "jsp.bakeryshopping.model.*" %>
<%@ page import = "jsp.bakerymaster.model.*" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.text.NumberFormat" %>

<html><head><title>Shopping Mall</title>
<style>
body{
	text-align : center;
}
#item{
	background-color : #F8ECE0;
}
.table{
	border : 1px solid #F8ECE0;
}
</style>

</head>
<body >
<%
	ArrayList<Order> orderLists = null;
	Order order = null;
	OrderDao orderProcess = OrderDao.getInstance();
	int count = orderProcess.getListCount();

if(count == 0){
%>
<br><br>
<h3 align="center">주문 내역</h3> 
<table border="1" width="650" cellpadding="3" cellspacing="3" align="center"> 
	<tr>
		<td align="center" >No Ordered List</td>
	</tr>
</table>
<div align="center"><a href="Management.do">뒤로가기</a></div>
    <%
     	}else{ 	orderLists = orderProcess.getOrderList();
     %>
<br><br>
<h3 align="center">주문 내역</h3> 
<div align="center"><a href="Management.do">뒤로가기</a></div>
<br><br>
<table class="table table-hover" border="1" align="center"> 
 	<tr>
 		<td id="item">No.</td>
 		<td id="item">Customer ID</td>
		<td id="item">Order Date</td>
		<td id="item">Product Name</td>
		<td id="item">Product ID</td>
		<td id="item">Quantity</td>
		<td id="item">Addressee</td>
		<td id="item">Account</td>
		<td id="item">Address</td>
		<td id="item">Phone</td>
	</tr>
 <%
 	for(int i = 0; i < orderLists.size(); i++) {
     	 order = (Order)orderLists.get(i);
     	 ProductDao pd = ProductDao.getInstance();
     	 Product product = pd.getProduct(order.getProduct_id());
 %>
   <tr> <td><%=i+1 %></td> 
        <td><%=order.getCustomer_id() %></td> 
        <td><%=order.getOrder_date() %></td>
        <td><%=product.getProduct_name() %></td>
        <td><%=order.getProduct_id() %></td> 
        <td><%=order.getOrder_count()%></td>
        <td><%=order.getDeliveryName() %></td>
        <td><%=order.getAccount() %></td>
        <td><%=order.getDeliveryAddress() %></td>
        <td><%=order.getDeliveryPhone() %></td>
  </tr>
<%     }  %>
    </table>
<%  }%>
</body></html>