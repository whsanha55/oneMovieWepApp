<%-- adminTheaterView.jsp --%>
<%-- 검색 선택 시, listScheduleTurnForm.do로 이동 --%>
<%@page contentType="text/plain; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


[
	<c:forEach var ="schedule" items="${requestScope.screenSchedule}" varStatus="loop">
	[
		"TheaterName":"${pageScope.schedule.theaterName }",
		"ScreenDate":"${pageScope.schedule.screenDate }",
		"ScreenName":"${pageScope.schedule.screenName }",
		"MovieTitle":"${pageScope.schedule.movieTitle }",
		
		<c:forEach var="turn" items="${ pageScope.schedule.turns}" varStatus="loop2">
					{
					"StartTime":"${ pageScope.turn.startTime}",
					"EndTime":"${ pageScope.turn.endTime}}" 
					}
					<c:if test="${loop2.index < fn:length(pageScope.schedule.turns) - 1 }">
					,
					</c:if>	
		</c:forEach> 
	]
		<c:if test="${loop.index < fn:length(requestScope.screenSchedule) - 1 }">
		,
		</c:if>
	</c:forEach>
]

<%-- el형태임 --%>