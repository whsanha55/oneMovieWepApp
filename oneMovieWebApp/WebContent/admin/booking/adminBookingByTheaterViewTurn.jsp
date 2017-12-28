<%@page contentType="text/plain; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
[
	<c:forEach var="turn" items="${requestScope.turnList }" varStatus="loop">
		{
			"turnNo" : "${pageScope.turn.turnNo }" , 
			"startTime" : "${pageScope.turn.startTime }" , 
			"endTime" : "${pageScope.turn.endTime }"  
		}
		<c:if test="${!pageScope.loop.last }">
			,
		</c:if>
	</c:forEach>
	
	 
]