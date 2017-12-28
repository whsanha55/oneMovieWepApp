<%@page contentType="text/plain; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
{
	"bookingList" : [
	<c:forEach var="bookingList" items="${requestScope.bookingList }" varStatus="loop1">

		{

		"memberNo" : "${pageScope.bookingList.memberNo}" ,
		"memberName" : "${pageScope.bookingList.memberName}" , 
		"theaterName" : "${pageScope.bookingList.theaterName}" ,
		"screenName" : "${pageScope.bookingList.screenName}" ,
		"screenDate" : "${pageScope.bookingList.screenDate}" ,
		"startTime" : "${pageScope.bookingList.startTime}" ,
		"endTime" : "${pageScope.bookingList.endTime}" ,
		"ticketNo" : "${pageScope.bookingList.ticketNo}" ,
		"movieTitle" : "${pageScope.bookingList.movieTitle}" ,
		"paymentCode" : "${pageScope.bookingList.paymentCode}" ,
		"seatNames" : [
				<c:forEach var="seatName" items="${pageScope.bookingList.seatNames}" varStatus="loop2" >
					"${pageScope.seatName }"
					<c:if test="${!pageScope.loop2.last }">
						,
					</c:if>
				</c:forEach>
					]
		
		}
		<c:if test="${!pageScope.loop1.last }">
			,
		</c:if>
		
	</c:forEach>
	]
	
}