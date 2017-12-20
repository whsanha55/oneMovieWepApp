<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>비밀번호 찾기</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('#btn').on('click', function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/"		//컨트롤러... 
				,
				method: 'POST'
				,
				data: {
					memberId: $('#memberId').val()	,
					email: $('#email').val()
				}
				,
				dataType: 'json'
				,
				success: function(data){
					window.open('pwdInfo.html', 'width=400, height=500, left=100, top=100');
				}
				,
				error: function(jqXHR) {
					alert('Error: ' + jqXHR)
				}
				
			});			
		});		
	});


</script> 
</head>
<body>
	<div id="title">비밀번호 찾기</div><br>
	<div id="note">아이디와 이메일을 입력해주세요.</div>
	<form>
		<label>이름<input type="text" name="memberId"></label><br>
		<label>이메일<input type="text" name="email"></label><br>
		<button>확인</button><br>
	</form>
	

</body>
</html>