<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>회원 정보 수정</title>
</head>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
</script> 
<body>
	<div id="title">회원 정보 수정</div>
	<div id="note">본인 확인을 위해 비밀번호를 입력해주세요.</div>
	<form action="${pageContext.request.contextPath }/reLogin.do" method="post">
		<label>아이디<input type="text" name="memberId" value=${sessionScope.memberId} readonly></label><br>
		<label>비밀번호<input type="password" name="memberPwd"></label><br>
		<button id="btn" type="submit">개인정보수정/탈퇴 페이지로 이동</button>
	</form>

</body>
</html>