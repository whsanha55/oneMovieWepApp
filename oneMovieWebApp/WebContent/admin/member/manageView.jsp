<%@ page contentType="text/plain; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

[

	<c:forEach var="member" items="${requestScope.members }" varStatus="loop">
	{
		"memberNo": "${pageScope.member.memberNo }"	,
		"memberId": "${pageScope.member.memberId }"	,
		"memberPwd": "${pageScope.member.memberPwd }"	,
		"name": "${pageScope.member.name }"	,
		"gender": "${pageScope.member.gender }"	,	
		"phone": "${pageScope.member.phone }"	,
		"email": "${pageScope.member.email }"	,
		"address1": "${pageScope.member.address1 }"	, 
		"address2": "${pageScope.member.address2 }"	,
		"zipcode": "${pageScope.member.zipcode }"	,
		"isWithdraw": "${pageScope.member.isWithdraw }"	,
		"withdrawDay": "${pageScope.member.withdrawDay }"	
	}
	
		
	<c:if test="${loop.index < fn:length(requestScope.members) -1 }">
		,
	</c:if>
	
	</c:forEach>


]
