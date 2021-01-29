<%@ page contentType = "text/html; charset=utf-8" %>
<html><head><title>Shopping Mall</title>
<style>
.shop-table{
	text-align : center;
	width : 1000px;
}
#shop-td{
}
</style>
</head><body>
<%
	String pgm = request.getParameter("pgm");
	if (pgm == null) {	pgm = "introList.jsp";	}
%>
<div class="wrap" align="center">
<table class="shop-table" width="850" cellpadding="2" cellspacing="0">
	<tr>
		<td id="shop-td" width="700" valign="top">
			<jsp:include page="<%=pgm %>" />
		</td>
	</tr>
</table>
</div>
</body></html>