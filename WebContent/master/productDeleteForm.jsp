<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  
	int product_id=Integer.parseInt(request.getParameter("product_id"));
%>
<html><head><title>Delete Product</title>
</head>
<body>
<div align="center">
<div class="hr-sect" ><b><font size="+1">Delete Product</font></b></div> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;</p>
<form method="post" name="deleteform" action="ProductDeleteAction.co?product_id=<%=product_id %>" enctype="multipart/form-data">
<a href="productList.co">List</a> &nbsp;
<input type="submit" value="Delete" class="btn btn-warning danger" >  
</form>
</body>
</html>