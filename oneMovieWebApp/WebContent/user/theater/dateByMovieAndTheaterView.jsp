<%@page contentType="text/plain; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
{
	"dateList" : [
		<c:forEach var="date" items="${requestScope.dateList }" varStatus="loop">			
				"${pageScope.date.screenDate }"
			<c:if test="${!pageScope.loop.last }">
				,
			</c:if>
		
		</c:forEach>
	
	]
	
	 
}