<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>회원 로그인</title>
<style>
	#lf {
        border: 1px solid #5b6a71;
        padding: 15px;
        padding-bottom: 5px;
        margin-left: auto;
		margin-right: auto;
	}
	
	#lf th, #lf td {
		padding: 10px;
	}
	

	#title {
		border-bottom: 3px solid #5b6a71;
		color: #4557bd;
		font-size: 30px;
		display: inline-block;
	}
	
	#tt{
        margin-left: auto;
		margin-right: auto;
	}
	
	#alb {
		text-align: center;
	}

	
</style>


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
	<br><br><table id="tt">
		<tr>
			<td><div id="title">회원 로그인</div></td>
		</tr>
	</table><br>
	
	<table id="lf">
	<form>
		<tr>
			<th><label>아이디</label></th>
			<td><input type="text" name="memberId" id="memberId" tabindex="1"></td>
		</tr>
		<tr>
			<th><label>비밀번호</label></th>
			<td><input type="password" name="memberPwd" id="memberPwd" tabindex="2"></td>
		</tr>
	</form>
		<tr>
			<td id="alb"><button id="login">Login</button></td>
			<td><button id="findId" onclick="location.href='${pageContext.request.contextPath}/findIdForm.do'">ID 찾기</button>&nbsp;
			<button id="findPwd" onclick="location.href='${pageContext.request.contextPath}/findPwdForm.do'">비밀번호 찾기</button></td>
		</tr>
		
	</table>

</body>
</html>