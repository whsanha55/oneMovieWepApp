<%@page contentType="text/plain; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
{
	"movieList" : [
		<c:forEach var="movie" items="${requestScope.movieList }" varStatus="loop">
			{
				"screenNo" : ${pageScope.movie.movieNo } ,
				"screenName" : "${pageScope.movie.movieTitle }"			
			}
		
			<c:if test="${!pageScope.loop.last }">
				,
			</c:if>
		
		</c:forEach>
	
	]
	
	 
}