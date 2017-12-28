<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>회원 정보 수정</title>
</head>
<script src="${pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script>
	$(document).ready(function() {
		
		$('#btn').on('click', function(){
			
			var id = $('#memberId').val();
			var pwd = $('#memberPwd').val();
			
			if(id=="" || pwd=="") {
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			
		});
	
	});



</script> 
<body>
	<div id="title">회원 정보 수정</div>
	<div id="note">본인 확인을 위해 비밀번호를 입력해주세요.</div>
	<form action="${pageContext.request.contextPath }/auth/reLogin.do" method="post">
		<label>아이디<input type="text" name="memberId" id="memberId" value=${sessionScope.memberId} readonly></label><br>
		<label>비밀번호<input type="password" name="memberPwd" id="memberPwd"></label><br>
		<button id="btn" type="submit">개인정보수정/탈퇴 페이지로 이동</button>
	</form>

</body>
</html>