<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>비밀번호 찾기</title>
<style>
	#fit {
		border: 1px solid black;
		padding: 10px;
		margin-left: auto;
		margin-right: auto;
	}
	
	#fit th, #fit td {
		padding: 10px;
	}

	#sdt {
		margin-left: auto;
		margin-right: auto;
	}
	
	#tif {
		display: inline-block;
		border-bottom: 2px solid skyblue;
		font-size: 30px;
	}
	

</style>
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
	<br>
	<table id="sdt">
		<tr>
			<td><div id="tif">비밀번호 찾기</div></td>
		</tr>
		<tr>
			<td><div id="sp">아이디와 이메일을 입력해주세요.</div></td>
		</tr>
	</table><br>
	
	<table id="fit">
	<form>
		<tr>
			<th><label>아이디</labele></th>
			<td><input type="text" id="memberId"></td>
			<td rowspan="2"><button id="btn">확인</button></td>
		</tr>
		<tr>
			<th><label>이메일</label></th>
			<td><input type="text" id="email"></td>
		</tr>
	</form>
	</table>
	

</body>
</html>