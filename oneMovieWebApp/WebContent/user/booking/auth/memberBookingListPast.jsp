<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<h3>지난 내역</h3>
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
		
		
	</tr>
	<c:forEach var="booking" items="${requestScope.bookingList}" varStatus="loop1">
		<tr>
			<td>${requestScope.paging.num + pageScope.loop1.count }</td>
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
			
		</tr>
	</c:forEach>
	</table>
	
		<%-- 페이지 네비게이션 처리 --%>
	<c:if test="${requestScope.paging.prevPage >= 1 }">
		<c:url var="prevUrl" value="/memberBookingListPast.do" scope="page">
			<c:param name="currentPage" value="${requestScope.paging.prevPage }"/>
		</c:url>
		<a href="${pageScope.prevUrl }">[이전]</a>&nbsp;&nbsp;
	</c:if>	
	<c:if test="${requestScope.paging.prevPage < 1 }">
		[이전] &nbsp;&nbsp;
	</c:if>

	<c:forEach var="i" begin="${requestScope.paging.startPage }" end="${requestScope.paging.endPage }" step="1">
		<c:if test="${requestScope.paging.currentPage == pageScope.i }">
			${pageScope.i }&nbsp;&nbsp;		
		</c:if>
		<c:if test="${requestScope.paging.currentPage != pageScope.i }">
			<c:url var="currentPageUrl" value="/memberBookingListPast.do" scope="page">
				<c:param name="currentPage" value="${pageScope.i }"/>		
			</c:url>
			<a href="${pageScope.currentPageUrl }">${pageScope.i }</a>&nbsp;&nbsp;		
		</c:if>
	</c:forEach>
	<c:if test="${requestScope.paging.pageBlock < requestScope.paging.totalPage }">
		<c:url var="nextUrl" value="/memberBookingListPast.do" scope="page">
			<c:param name="currentPage" value="${requestScope.paging.nextPage }"/>
		</c:url>
		<a href="${pageScope.nextUrl }">[다음]</a>&nbsp;&nbsp;
	</c:if>	
	<c:if test="${requestScope.paging.pageBlock >= requestScope.paging.totalPage }">
		[다음] &nbsp;&nbsp;
	</c:if>
	
