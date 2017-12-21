<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>아이디 찾기</title>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('#btn').on('click', function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/findId.do"		 
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
					var text = "입력하신 정보의 아이디는 " + data + " 입니다.";	
					$('#result').text(text);
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
	<div id="title">ID 찾기</div><br>
	<div id="note">가입 당시 작성한 이름과 이메일 주소를 입력해주세요.</div>
	<form>
		<label>이름<input type="text" name="memberId" id="memberId"></label><br>
		<label>이메일<input type="text" name="email" id="email"></label><br>
		<button id="btn">확인</button><br>
	</form>
	<div id="result"></div>

</body>
</html>