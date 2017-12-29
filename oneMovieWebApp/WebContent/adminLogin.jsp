<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>관리자 로그인</title>
<style>

	body {
		background-color: #f7f4f4;
	}


	#lf {
		position: absolute;
		width: 300px;
		height: 150px;
		left: 50%;
        top: 40%;
        margin-left: -260px;
        margin-top: -200px;
        font-weight: bolder;
        border: 1px solid #5b6a71;
        padding: 15px;
        padding-bottom: 5px;
	}

	#title {
		border-bottom: 3px solid #5b6a71;
		position: absolute;
		font-size: 40px;
	 	color: #4557bd;
	 	left: 30%;
	 	top: 10%;
	}
	
	#alb {
		text-align: center;
	}
	
	
	


</style>
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
					if(data.key == "ok") {
						location.href = "${pageContext.request.contextPath}/admin/layoutAdmin.jsp";	
					} else {
						location.href = "${pageContext.request.contextPath}/adminLogin.jsp";
					}
					
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
	<div id="title">OneMovie 관리자 로그인</div><br>
	<table id="lf">	
		<form>
		<tr>
			<th><label>아이디</label></th>
			<td><input type="text" name="adminId" id="adminId" tabindex="1"></td>
			<td rowspan="2"></td>
		</tr>
		<tr>
			<th><label>비밀번호</label></th>
			<td><input type="password" name="adminPwd" id="adminPwd" tabindex="2"></td>
		</tr>
		<tr>
			<td id="alb" colspan="2"><button id="btn">Login</button></td>
		</tr>
		</form>
	</table>
	
	

</body>
</html>