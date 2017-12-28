<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>비밀번호 찾기</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		
		$('#btn').on('click', function(event){
			event.preventDefault();
			
			var id = $('#memberId').val();
			var email = $('#email').val();
				
			if(id=="" || email=="") {
				alert("아이디/이메일을 입력해주세요.")
				return false;
			}
			
			
			$.ajax({
				url: "${pageContext.request.contextPath}/findPwd.do"		 
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
					alert(data.result);
				}
				,
				error: function(jqXHR) {
					alert('Error: ' + jqXHR + "에러 발생")
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
		<label>아이디<input type="text" id="memberId"></label><br>
		<label>이메일<input type="text" id="email"></label><br>
		<button id="btn">확인</button><br>
	</form>
	

</body>
</html>