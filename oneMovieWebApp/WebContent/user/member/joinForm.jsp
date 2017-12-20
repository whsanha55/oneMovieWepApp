<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
</head>
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

				}
				,
				error: function(jqXHR) {
					alert('Error: ' + jqXHR)
				}
				
			});			
		});
		
		
		
		$('#checkId').on('click', function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/"		//컨트롤러... 
				,
				method: 'POST'
				,
				data: {
					keyfield: 'memberId'	,				//get name?
					keyword: $('#memberId').val()
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
		
		
		$(finction(){
			//직접 입력 텍스트필드 숨김
			$('#emailAdWrite').hide();
			
			$('#emailAd').change(function(){
				//직접 입력 선택 시 나타남
				if($('#emailAd').val() == "write") {
					$('#emailAdWrite').show();
				} else {
					$('#emailAdWrite').hide();
				}
				
			});
		});
		

		
	});
	


</script>
<body>
	<div>회원가입</div>
	<form>
		<label>아이디<input type="text" name="memberId" id="memberId"></label>&nbsp;
		<label><button id="checkId">중복확인</button></label>&nbsp;
					<span>영문, 숫자 사용 가능 / 6~15자리로 입력해주세요.</span><br>
					
		<label>비밀번호<input type="password" name="memberPwd" id="memberPwd"></label>&nbsp;
					<span>영문과 숫자를 포함하여 8~25자리로 입력해주세요.</span><br>
					
		<label>비밀번호 확인<input type="password" name="checkPwd" id="checkPwd"></label><br>
		
		<label>이름<input type="text" name="name" id="name"></label><br>
		
		<label>이메일</label>
			   <input type="text" name="emailId" id="emailId">&nbsp;
				   <select name="emailAd" id="emailAd">
				     <option value="@gmail.com">@gamil.com</option>
				     <option value="@daum.net">@daum.net</option>
				     <option value="@naver.com">@naver.com</option>
				  	 <option value="write">직접 입력</option>
				   </select>
				   <input type="text" id="emailAdWrite" name="emailAdWrite" />
				   &nbsp;<label><button id="checkEmail">중복확인</button></label><br>
				   
		<label>성별<input type="radio" name="gender" id="gender" value="M">남성 &nbsp;
				  <input type="radio" name="gender" id="gender" value="F">여성 </label><br>
				  
		<label>전화번호<select name="exchangeNumber" id="exchangeNumber">
				     <option value="010">010</option>
				     <option value="02">02</option>
				     <option value="011">011</option>
				     <option value="031">031</option>			     
				   </select>&nbsp;
				   <input type="text" name="tel1" id="tel1">&nbsp;
				   <input type="text" name="tel2" id="tel2">					
		</label><br>
		
		<label>주소<input type="text" name="address" id="address"></label><br>
		
		<button id="btn">회원가입</button> &nbsp;&nbsp;&nbsp;
		<button type="reset">취소</button><br>
	</form>

</body>
</html>