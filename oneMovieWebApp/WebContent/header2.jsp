<%-- header.jsp --%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav>
	<ul>
		<li><a href="${pageContext.request.contextPath }/auth/memberBookingListCurr.do">예매확인</a></li>
		<li><a href="${pageContext.request.contextPath }/auth/reLoginForm.do">회원정보수정/탈퇴</a></li>
		<li><a href="${pageContext.request.contextPath }/auth/memberLogout.do">로그아웃</a></li>
	</ul>
</nav>