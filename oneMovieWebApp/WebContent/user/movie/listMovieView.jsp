<%-- listArticleView.jsp --%>
<%@ page contentType="text/plain; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

[
	<c:forEach var="movie" items="${requestScope.movies }" varStatus="loop">
	{
		"movieNo": ${pageScope.movie.movieNo} ,
		"movieTitle": "${pageScope.movie.movieTitle }" ,
		"director": "${pageScope.movie.director }"  ,
		"runningTime": "${pageScope.movie.runningTime }"   ,
		"gradeNo": "${pageScope.movie.gradeNo }",
		"nationNo": "${pageScope.movie.nationNo }"	,
		
		"actorNo": "${pageScope.movie.actorNo }"
	}
	<c:if test="${loop.index < fn:length(requestScope.movies) - 1  }">
	 	,
	</c:if>
	
	</c:forEach>
]
