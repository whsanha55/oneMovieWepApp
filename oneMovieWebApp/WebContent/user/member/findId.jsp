<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>아이디 찾기</title>
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
	
	
	#res {
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
			
			var id = $('#name').val();
			var email = $('#email').val();
				
			if(id=="" || email=="") {
				alert("이름/이메일을 입력해주세요.")
				return false;
				} 
						
			$.ajax({
				url: "${pageContext.request.contextPath}/findId.do"		 
				,
				method: 'POST'
				,
				data: {
					name: $('#name').val()	,
					email: $('#email').val()
				}
				,
				dataType: 'json'
				,
				success: function(data){
					var text = data.result;	
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
	<br>
	<table id="sdt">
		<tr>
			<td><div id="tif">ID 찾기</div></td>	
		</tr>
		<tr>
			<td><div id="sp">가입 당시 작성한 이름과 이메일 주소를 입력해주세요.</div></td>
		</tr>
	</table><br>
	<table id="fit">
	<form>
		<tr>
			<th><label>이름</label></th>
			<td><input type="text" name="name" id="name"></td>
			<td rowspan ="2"><button id="btn">확인</button></td>
		</tr>
		<tr>
			<th><label>이메일</label></th>
			<td><input type="text" name="email" id="email"></td>
		</tr>
		
	</form>
	</table><br>
	<table id="res">
		<tr>
		 <td><div id="result"></div></td>
		</tr>
	</table>

</body>
</html>