<%@page contentType="text/plain; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
{
	"screenList" : [
		<c:forEach var="screenList" items="${requestScope.screenList }" varStatus="loop">
			{
				"screenNo" : ${pageScope.screenList.screenNo } ,
				"screenName" : "${pageScope.screenList.screenName }"			
			}
		
			<c:if test="${!pageScope.loop.last }">
				,
			</c:if>
		
		</c:forEach>
	
	]
	
	 
}