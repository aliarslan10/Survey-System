
<%
	request.getSession().removeAttribute("personelinfo");
	request.getSession().removeAttribute("yetki");
	response.sendRedirect("index.jsp");
%>