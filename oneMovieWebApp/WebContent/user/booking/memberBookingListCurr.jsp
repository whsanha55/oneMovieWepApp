<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>예매확인/취소 예매하신 영화 내역과 취소내역을 확인할 수 있습니다.</div>
	
	<div>
	<a href="${pageContext.request.contextPath }/memberBookingListCurr.do">My 예매내역</a>
	<a href="${pageContext.request.contextPath }/memberBookingListPast.do">지난 내역</a>
	<a href="${pageContext.request.contextPath }/memberBookingListCancel.do">취소 내역</a>
	</div>
	<table border="1">
	<tr>
		<th>NO</th>
		<th>예매번호</th>
		<th>영화명</th>
		<th>상영관</th>
		<th>좌석번호</th>
		<th>상영일시</th>
		<th>예매취소</th>
		
	</tr>
	<c:forEach var="booking" items="${requestScope.bookingList}" varStatus="loop1">
		<tr>
			<td>${pageScope.loop1.count }</td>
			<td>${pageScope.booking.ticketNo }</td>
			<td>${pageScope.booking.movieTitle }</td>
			<td>${pageScope.booking.theaterName } ${pageScope.booking.screenName }</td>
			<td>
			<c:forEach var="seat" items="${pageScope.booking.seatNames }" varStatus="loop2">
				${pageScope.seat }
				<c:if test="${!pageScope.loop2.last }">
					,
				</c:if>
			</c:forEach>
			</td>
			<td>${pageScope.booking.screenDate } ${pageScope.booking.startTime }</td>
			<td><button>예매취소</button></td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>