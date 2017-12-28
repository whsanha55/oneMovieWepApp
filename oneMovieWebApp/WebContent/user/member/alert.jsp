<%@ page contentType="text/html; charset=utf-8" %>

<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script> 
<script>

$(document).ready(function() {
	
	alert("로그인이 필요한 서비스입니다.");
	
	location.href='${pageContext.request.contextPath}/memberLoginForm.do';
	
});

</script>

<title></title>
</head>
<body>

</body>
</html>