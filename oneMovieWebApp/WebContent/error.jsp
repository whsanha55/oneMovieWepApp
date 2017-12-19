<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html; charset=utf-8" %>
	Error : <br>
<%
	//request 영역에 바인딩된 exception 객체를 구한다.
	Exception exception = (Exception)request.getAttribute("exception");
	exception.printStackTrace(new PrintWriter(out));	
%>


