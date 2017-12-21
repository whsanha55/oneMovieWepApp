<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
<title>회원 정보 수정</title>
</head>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
			
		var gender = ${requestScope.memer.genderer};
		if(gender == 'M') {
			$('#M').prop('checked', true);
		} else {
			$('#F').prop('checked', true);		
		}
				
		var email = ${requestScope.memer.email};
		
		var emailId = email.substring(0, lastIndexof('@')-1);
		$(document).getElementById("emailId").value = 
		
		
		var temp = email.substring(email.lastIndexOf('@'));
		$('#emailAd > option').each(function() {
			if($(this).val() == temp) {
				$(this).prop('selected', true);
			}
		});
		
		
		$('#modify').on('click', function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/modifyMember.do"		//컨트롤러... 
				,
				method: 'POST'
				,
				data: {
					memberId: $('#memberId').val()	,
					memberPwd: $('#memberPwd').val()	,
					name: $('#name').val()	,
					email: $('#emailId').val() + $('#emailAd').val()	,
					gender: $('#gender').val()	,
					phone: $('#exchangeNumber').val() + $('#tel1').val() + $('#tel2').val() 	,
					address: $('#address').val()
				}
				,
				dataType: 'json'
				,
				success: function(data){
					if(data.sucess == true) {
						alert(data.result);
						location.href = '${pageContext.request.contextPath}/mainLayout.jsp';					
					} 
				}
				,
				error: function(jqXHR) {
					alert('Error: ' + jqXHR)
				}
				
			});			
		});
		
		
		
		$('#withdraw').on('click', function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/withdraw.do"	 
				,
				method: 'POST'
				,
				data: {
					memberNo: '${sessionScope.memberNo}'	
					
				}
				,
				dataType: 'json'
				,
				success: function(data){
					alert(data.result);	
					location.href = data.url;
				}
				,
				error: function(jqXHR) {
					alert('Error: ' + jqXHR)
				}
				
			});			
		});
		
		
		
		

		$('#checkEmail').on('click', function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/"		//컨트롤러... 
				,
				method: 'POST'
				,
				data: {
					keyfield: 'email'	,				//get name?
					keyword: $('#emailId').val() + $('#emailAd').val()
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
		
		
		//비밀번호-비밀번호 확인 일치 여부 체크.. 추가해주쇼
		
		
	
		//$('#emailAdWrite').hide();
			
		$('#emailAd').change(function(){
				//직접 입력 선택 시 나타남
			if($('#emailAd').val() == "write") {
				$('#emailAdWrite').show();
			} else {
				$('#emailAdWrite').hide();
			}
				
		});
		
	
		
	});
	
</script>





<body>
	<div>회원 정보 수정</div>
	<form>
		<label>아이디<input type="text" name="memberId" id="memberId" value="${requestScope.member.memberId }" readonly></label>
		<label>비밀번호<input type="password" name="memberPwd" id="memberPwd" value="${requestScope.member.memberPwd }" maxlength="25"></label>&nbsp;
					<span>영문과 숫자를 포함하여 8~25자리로 입력해주세요.</span><br>
		<label>비밀번호 확인<input type="password" name="checkPwd" id="checkPwd" value="${requestScope.member.memberPwd }"></label><br>
		<label>이름<input type="text" name="name" id="name" value="${requestScope.member.name }"></label><br>
		<label>이메일<input type="text" name="emailId" id="emailId">&nbsp;
				   <select name="emailAd" id="emailAd">
				     <option value="write">직접 입력</option>
				     <option value="@gmail">@gamil.com</option>
				     <option value="@daum">@daum.net</option>
				     <option value="@naver">@naver.com</option>				     
				   </select></label>&nbsp;
				   <label><button>중복확인</button></label><br>
		<label>성별<input type="radio" name="gender" id="M" value="M">남성 &nbsp;
				  <input type="radio" name="gender" id="F" value="F">여성 </label><br>
		<label>전화번호<select name="exchangeNumber" id="exchangeNumber">
				     <option value="010">010</option>
				     <option value="02">02</option>
				     <option value="011">011</option>
				     <option value="031">031</option>			     
				   </select>&nbsp;
				   <input type="text" name="tel1" id="tel1">&nbsp;
				   <input type="text" name="tel2" id="tel2">					
		</label><br>
		<label>주소<input type="text" name="address" id="address" value="${requestScope.member.address }"></label><br>
		<button type="button" id="modify">정보 수정</button> &nbsp;&nbsp;&nbsp;
		<button type="reset">취소</button><br>
		<button type="button" id="withdraw">회원 탈퇴</button><br>
	</form>

</body>
</html>