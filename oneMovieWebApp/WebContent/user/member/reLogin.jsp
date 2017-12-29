<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>회원 정보 수정</title>
<style>
	#lf {
        border: 1px solid #5b6a71;
        padding: 15px;
        padding-bottom: 5px;
        margin-left: auto;
		margin-right: auto;
	}
	
	#lf th, #lf td {
		padding: 15px;
	}

	#di {
		border-bottom: 3px solid #5b6a71;
		color: #4557bd;
		font-size: 30px;
		display: inline-block;
	}
	

	#sdt {
		margin-left: auto;
		margin-right: auto;
	}

	
	#sp {
		font-size: 12px;
	}
	
	#gogo {
		text-align: center;
	}
	
</style>

</head>
<script src="${pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script>
	$(document).ready(function() {
		
		$('#btn').on('click', function(){
			
			var id = $('#memberId').val();
			var pwd = $('#memberPwd').val();
			
			if(id=="" || pwd=="") {
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			
		});
	
	});



</script> 
<body>
	<br><table id="sdt">
		<tr>
			<td><div id="di">회원 정보 수정</div></td>
		</tr>
		<tr>
			<td><div id="sp">본인 확인을 위해 비밀번호를 입력해주세요.</div></td>
		</tr>
	</table><br>
	<table id="lf">
	<form action="${pageContext.request.contextPath }/auth/reLogin.do" method="post">
		<tr>
			<th><label>아이디</label></th>
			<td><input type="text" name="memberId" id="memberId" value=${sessionScope.memberId} readonly></td>
		</tr>
		<tr>
			<th><label>비밀번호</label></th>
			<td><input type="password" name="memberPwd" id="memberPwd"></td>
		</tr>
		<tr>
			<td colspan="2" id="gogo"><button id="btn" type="submit">개인정보수정/탈퇴 페이지로 이동</button></td>
		</tr>
	</form>
	</table>
</body>
</html>