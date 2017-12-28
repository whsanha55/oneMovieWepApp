<%-- header.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<nav>
	<ul>
		<li><a href="${pageContext.request.contextPath }/memberLoginForm.do">로그인</a></li>
		<li><a href="${pageContext.request.contextPath }/joinForm.do">회원가입</a></li>
	</ul>
</nav>