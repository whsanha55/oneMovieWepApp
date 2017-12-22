<%@page contentType="text/plain; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
{
	"theaterList" : [
		<c:forEach var="theater" items="${requestScope.theaterList }" varStatus="loop">
			{
				"theaterNo" : ${pageScope.theater.theaterNo } ,
				"theaterName" : "${pageScope.theater.theaterName }"			
			}
		
			<c:if test="${!pageScope.loop.last }">
				,
			</c:if>
		
		</c:forEach>
	
	]
	
	 
}