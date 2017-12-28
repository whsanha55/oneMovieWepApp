<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>회원 로그인</title>
</head>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		
		$('#login').on('click', function(event){
			event.preventDefault();
			
			var id = $('#memberId').val();
			var pwd = $('#memberPwd').val();
				
			if(id=="" || pwd=="") {
				alert("아이디/비밀번호를 입력해주세요.")
				return false;
			}

			
			$.ajax({
				url: "${pageContext.request.contextPath}/memberLogin.do"
				,
				method: 'POST'
				,
				data: {
					memberId: $('#memberId').val()	,
					memberPwd: $('#memberPwd').val()
				}
				,
				dataType: 'json'
				,
				success: function(data){
					alert(data.result);
					location.href="${pageContext.request.contextPath}/layoutUser.jsp";
				}
				,
				error: function(jqXHR) {
					alert('Error: ' + jqXHR)
				}
				
			});			
		});		

		
		

		
		
		
	});
	
	
	
	


</script> 
<body>
	<div>로그인</div>
	<form>
		<label>아이디<input type="text" name="memberId" id="memberId" tabindex="1"> </label><br>
		<label>비밀번호<input type="password" name="memberPwd" id="memberPwd" tabindex="2"></label><br>
	</form>
	<button id="login">Login</button><br>
	<button id="findId" onclick="location.href='${pageContext.request.contextPath}/findIdForm.do'">ID 찾기</button><br>
	<button id="findPwd" onclick="location.href='${pageContext.request.contextPath}/findPwdForm.do'">비밀번호 찾기</button><br>
	

</body>
</html>