<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "jsp.bakeryshopping.model.*" %>
<%@ page import = "jsp.bakerymaster.model.*" %>
<%@ page import = "jsp.member.model.*" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.text.NumberFormat" %>

<% 
	String buyer = request.getParameter("customer_id");
%>
<html><head><title>Shopping Mall</title>
<style>
.wrap{
	text-align : center;
	margin-top : 20px;
}
#btn{
	dispaly : inline-block;
	border : none;
	background-color : #FBF8EF;
	color : #3A2F0B;
	font-size : 15px;
}
</style>
</head>
<body>
<%
	ArrayList<Cart> cartLists = null;
	ArrayList<String> accountLists = null;
	ArrayList<Product> productLists = null;
	
	Cart cartList = null;

	CustomerBean customer= null;
	int number = 0;
	int total = 0;

	CartDao cartProcess = CartDao.getInstance();
    cartLists = cartProcess.getCart(buyer);
    
    CustomerDAO customerProcess = CustomerDAO.getInstance();
    customer = customerProcess.select(buyer);
    
    OrderDao orderProcess = OrderDao.getInstance();
    accountLists = orderProcess.getAccount();
    
%>
<br><br>
<h3 align="center">결제 내역</h3>
<br><br>
    <form name="inform" method="post" action="">
    <table border="1" width="700" align="center"> 
      <tr> 
      <td align="center"><b>No.</b></td>
      <td align="center"><b>Product Name</b></td>
      <td align="center"><b>Price</b></td>
      <td align="center"><b>Quantity</b></td>
      <td align="center"><b>Subtotal</b></td>
      </tr>   
<%
      	for(int i=0;i<cartLists.size();i++){
          	 cartList = (Cart)cartLists.get(i);
          	// productList = (Product)productLists.get(i);
          	ProductDao pd = ProductDao.getInstance();
          	Product product  = pd.getProduct(cartList.getProduct_id());
      %>     
       <tr> 
          <td align="center"  width="50"><%=++number %></td> 
          <td  width="300">
             <img src="imageFile/<%=product.getProduct_img()%>" border="0" width="30" height="50" align="middle">
             <%=product.getProduct_name()%>
          </td> 
          <td align="center"  width="100">\<%=NumberFormat.getInstance().format(cartList.getCart_price())%></td>
          <td width="150" align="center"><%=cartList.getCart_count()%></td>
          <td align="center"  width="150" align="center">
              <%total += cartList.getCart_count()*cartList.getCart_price();%>
             \<%=NumberFormat.getInstance().format(cartList.getCart_count()*cartList.getCart_price()) 
 %>           		 </td>
       </tr>
<%
     }
%>
       <tr>
          <td colspan="5" align="right"><b>Total \<%=NumberFormat.getInstance().format(total)%></b></td>
      </tr>
   </table>
   </form>
   
<br>
   <form method="post" action="BuyAction.co?customer_id=<%=buyer %>" name="buyinput">
   <table width="700" border="1" cellspacing="0" cellpadding="3"  align="center">
      <tr > 
       <td colspan="2" align="center"><b>My Detail</b></td>
     </tr>
     <tr> 
       <td  width="150" align="center">Name</td>
       <td  width="550"><%=customer.getCustomer_name()%></td>
     </tr>
     <tr><td align="center">Phone</td><td><%=customer.getCustomer_phone()%></td>
     </tr>
     <tr><td align="center">Address</td><td><%=customer.getCustomer_address()%></td>
     </tr>
     <tr><td align="center">Bank Account</td>
       <td>
         <select name="account">
          <%
              for(int i=0;i<accountLists.size();i++){
            	  String accountList = (String)accountLists.get(i);
          %>
                  <option value="<%=accountList %>"><%=accountList %></option>
         <%}%>
         </select>
       </td>
    </tr>
  </table>
  <br>
   
   <table width="700" border="1" cellspacing="0" cellpadding="3"  align="center">
      <tr > 
       <td colspan="2" align="center"><b>Shopping Detail</b></td>
     </tr>
     <tr> 
       <td align="center" width="150">Name</td>
       <td width="550">
          <input type="text" name="deliveryName" value="<%=customer.getCustomer_name()%>">
       </td>
     </tr>
     <tr> 
       <td align="center">Phone</td><td>
         <input type="text" name="deliveryPhone" value="<%=customer.getCustomer_phone()%>">
       </td>
     </tr>
     <tr><td align="center">Address</td><td>
         <input type="text" name="deliveryAddess" value="<%=customer.getCustomer_address()%>">
       </td>
     </tr>
  </table>
  <div class="wrap">
	<input id="btn" type="submit" value="구매하기" >
	<input id="btn" type="button" value="쇼핑하기" onclick="location.href='shopMain.co'"> 
  </div>
  </form>
</body>
</html>