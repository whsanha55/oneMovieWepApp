<%@ page contentType="text/plain; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

[
	<c:forEach var="schedule" items="${requestScope.scheduleList }" varStatus="loop1">
		{
			"screenName" : "${pageScope.schedule.screenName}" , 
			"turn" : [
			<c:forEach var="turn" items="${pageScope.schedule.turns }" varStatus="loop2">
				{
				"turnNo" : ${pageScope.turn.turnNo } ,
				"startTime" : "${pageScope.turn.startTime }" ,
				"endTime" : "${pageScope.turn.endTime }"
				}
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

