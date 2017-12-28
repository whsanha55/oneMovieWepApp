<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>관리자 로그인</title>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script> 
<script>
	$(document).ready(function(){
		
		$('#btn').on('click', function(event){
			event.preventDefault();
			$.ajax({
				url:'${pageContext.request.contextPath}/adminLogin.do'	
				,
				method: 'POST'
				,
				cache: false
				,
				data: {
					adminId: $('#adminId').val()		,
					adminPwd: $('#adminPwd').val()
				}
				,
				dataType: 'json'
				,
				success: function(data) {
					alert(data.result);
					location.href = "${pageContext.request.contextPath}/admin/layoutAdmin.jsp";
				}
				,
				
				error: function(jqXHR) {
			    	alert('Error : ' + jqXHR.status);
					}
			});
		});		
		
	});


</script>
</head>
<body>
	<div id="title">관리자 로그인</div>
	<div id="note">관리자 페이지로 접속합니다.</div>
	
	<form>
		<label>아이디<input type="text" name="adminId" id="adminId" tabindex="1"></label><br>
		<label>비밀번호<input type="password" name="adminPwd" id="adminPwd" tabindex="2"></label><br>
		<button id="btn">Login</button>		
	</form>
	

</body>
</html>