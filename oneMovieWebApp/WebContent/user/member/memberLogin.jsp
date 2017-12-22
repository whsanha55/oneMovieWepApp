<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>회원 로그인</title>
</head>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('#login').on('click', function(){
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
					
				}
				,
				error: function(jqXHR) {
					alert('Error: ' + jqXHR)
				}
				
			});			
		});		
		
		
		
		$('#findId').on('click', function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/findIdForm.do"
				,
				method: 'POST'
				,
				data: {

				}
				,
				dataType: 'json'
				,
				success: function(data){
					
				}
				,
				error: function(jqXHR) {
					alert('Error: ' + jqXHR)
				}
				
			});	
		});
		
		$('#findPwd').on('click', function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/findPwdForm.do"
				,
				method: 'POST'
				,
				data: {

				}
				,
				dataType: 'json'
				,
				success: function(data){
					
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
		<label>아이디<input type="text" name="memberId"></label><br>
		<label>비밀번호<input type="password" name="memberPwd"></label><br>
		<button id="login">Login</button><br>
		<button id="findId">ID 찾기</button><br>
		<button id="findPwd">비밀번호 찾기</button><br>
	</form>

</body>
</html>