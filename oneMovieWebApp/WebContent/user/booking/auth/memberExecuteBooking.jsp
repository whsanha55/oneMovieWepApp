<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
<script>
	alert("결제가 완료되었습니다!!");
	location.href = "${pageContext.request.contextPath}/auth/memberBookingListCurr.do";
</script>
</head>
<body>

</body>
</html>