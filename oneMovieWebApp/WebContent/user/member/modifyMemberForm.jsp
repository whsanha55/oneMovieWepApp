<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
<title>회원 정보 수정</title>
</head>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js?autoload=false"></script>
<script>
	$(document).ready(function() {
		
		//성별 라디오버튼 처리
		var gender = ${requestScope.member.gender};
		if(gender == 'M') {
			$('#M').prop('checked', true);
		} else {
			$('#F').prop('checked', true);
		}
		
		//전화번호 나눠서 뿌리기
		var phone = ${requestScope.member.phone};
		var exNum = phone.substr(0, 2);
			if(exNum != '02') {
				var exNum = phone.substr(0, 3);	
			} 
		var tel2 = phone.substr(-1, 4);
		var tel1 = phone.substr(0, exNum.length).substr(-1, 4);
				
		$('#exchangeNumber > option').each(function(){
			if($(this).val() == exNum) {
				$(this).prop('selected', true);
			}
		});

		$('#tel1').val(tel1);
		$('#tel2').val(tel2);
		
		
		//이메일 나눠서 뿌리기
		var email = ${requestScope.member.email};
		
		var emailId = email.substring(0, lastIndexOf('@')-1);
		$('#emailId').val(emailId);

		var emailAd = email.substring(email.lastIndexOf('@')+1);
		var array = new array();
		
		$('#emailAd > option').each(function(){
			array.push($(this).val());
			if($(this).val() == emailAd) {
				$(this).prop('selected', true);
			}
		});

		var temp = array.indexOf(emailAd);
		if(temp == -1) {
			$('#emailAd').val("write");
			$('#emailAdWrite').show();
			$('#emailAdWrite').val(emailAd);	
		}
			
		

		
		
		
		//이메일 직접 입력 선택 시 나타남
		$('#emailAd').change(function(){
			if($('#emailAd').val() == "write") {
				$('#emailAdWrite').show();
			} else {
				$('#emailAdWrite').hide();
			}
		});
		
		
		
		//이메일 중복 확인 버튼 클릭
		$('#checkEmail').on('click', function(){
			checkEmail();
		});
		
		//주소 찾기 버튼 클릭
		$('#findAdd').on('click', function(){
			daumAddress();
		});
		
		
		//회원 정보 수정 버튼 클릭
		$('#modify').on('click', function(){
			checkForm();
			$.ajax({
				url: '${pageContext.request.contextPath}/modifyMember.do'
				,
				method: 'POST'
				,
				data: {
					memberId: $('#memberId').val() 	,
					memberPwd: $('#memberPwd').val()	,
					name: $('#name').val()	,
					email: $('#emailId').val() + "@" + $('#emailAd').val()	,
					gender: $('#gender').val()	,
					phone:	$('#exchangeNumber').val() + $().val('#tel1') + $('#tel2').val()	,
					address1: $('#address1').val()	,
					address2: $('#address2').val()	,
					zipcode: $('zipcode').val
				},
				dataType: 'json'
				,
				success: function(data){
					alert(data);
					location.href="${pageContext.request.contextPath}/layout.jsp";
				},
				error: function(jqXHR, textStatus, error){
					alert("Error : " + jqXHR.status + "," + error);
				}
			});
		});
		
		
		//취소 버튼 클릭
		$('#reset').on('click', function(){
			var confirm = confirm("회원 정보 수정을 취소하시겠습니까?");
			if(confirm) {
				location.href = '${pageContext.request.contextPath}/layout.jsp';
			}
		});
		
		
		//탈퇴 버튼 클릭
		$('#withdraw').on('click', function(){
			//팝업창으로 탈퇴 의사 재확인
			var confirm = confirm("정말로 탈퇴하시겠습니까? 탈퇴를 원하시면 '확인'을 눌러주세요.");
			if(!confirm) {
				return;
			}
						
			$.ajax({
				url:'${pageContext.request.contextPath}/withdraw.do'
				,
				method: 'POST'
				,
				data: {					
				},
				dataType: 'json'
				,
				success: function(data) {
					alert(data);
					location.href="${pageContext.request.contextPath}/layout.jsp";
				},
				error: function(jqXHR, textStatus, error){
					alert("Error: " + jqXHR.status + ", " + error);
				}
				
			});
		});
		
		
		
		
		//정보수정폼 입력 내용 체크
		function checkForm() {
			var pwd = $('#memberPwd').val();
			var pwdChk = $('#checkPwd').val();
			var name = $('#name').val();
			var exNum = $('#exchangeNumber').val();
			var tel1 = $('#tel1').val();
			var tel2 = $('#tel2').val();
			var emailId = $('#emailId').val();
			var emailAd = $('#emailAd').val();
			var emailChk = $('#emailDuplication').val();
			
			
			//비밀번호 입력 확인
			if(pwd == null || pwdChk == null) {
				alert("비밀번호를 입력해주세요.");
				return;
			}
			
			//비밀번호 일치
			if(pwd != pwdChk) {
				alert("비밀번호가 일치하지 않습니다.");
				return;
			}
			
			//비밀번호 영문, 숫자 혼용 및 길이 확인
			if(!checkPwd(pwd)) {
				alert("비밀번호는 영문, 숫자를 혼합하여 8~25자리로 입력해주세요.");
				return;
			}
			
			//이름 입력 확인
			if(name == null) {
				alert("이름을 입력해주세요.");
				return;
			}
			
			//이름에 숫자, 특문 사용 불가
			if(checkName(name)) {
				alert("이름은 한글만 입력이 가능합니다.(최대 10자)");
				return;
			}
			
			//이메일 검증식
			if(emailId == null || emailAd == null || !checkEmail(emailId, emailAd)){
				alert("이메일을 정확히 입력해주세요.");
				return;
			}
			
			//이메일 중복 확인
			if(emailChk != "ckecked"){
				alert("이메일 중복확인을 해주세요.");
				return;
			}
			
			//전화번호 입력 확인
			if(exNum == null || tel1 == null || tel2 == null) {
				alert("전화번호를 입력해주세요.");
				return;
			}
			
			//전화번호에 숫자만
			if(!checkPhone(tel1, tel2)){
				alert("전화번호는 숫자만 입력 가능합니다.");
				return;
			}

		}//end of checkForm
		
		
		
		//이메일 중복 체크 함수
		function checkEmail(){
			var emailId = $('#emailId').val();
			var at = $('#at').val();
			var emailAd = $('#emailAd').val();
			var email = emailId.concat(at, emailAd);
			
			if(emailId == null || emailAd == null) {
				alert("이메일을 입력하세요.")
				return;
			}
			
			$.ajax({
				url: '${pageContext.request.contextPath}/duplicate.do'
				,
				method: 'POST'
				,
				data: {
					email: email
				}
				,
				dataType: 'json'
				,
				success: function(data) {
					alert(data.result);
					if(data.key.equals("ok")) {
						$('#emailDuplication').val("checked");		
					}
				},
				error: function(jqXHR, textStatus, error) {
					alert("Error : " + jqXHR.status + "," + error);
				}			
			})
			
		} //end of checkEmail
		
		
		
		//비밀번호 유효성 검사
		function checkPwd(pwd){
			var leng = /^[a-zA-Z0-9]{8,25}$/;	//영문, 숫자, 8~25자 제한
			var num = pwd.search(/[0-9]/g);		//숫자 0-9
			var eng = pwd.search(/[a-z]/ig);		//영문 소문자
			
			if(!leng.test(pwd) || num <= 0 || eng <= 0) {
				return false;
			} else {
				return true;	
			} 
			
		}
		
		
		//이름 유효성 검사
		function checkName(name) {
			var kor = /[a-zA-Z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
			return kor.test(name);
		}
		
		//이메일 유효성 검사
		function checkEmail(emailId, emailAd) {
			var leng1 = /^[a-zA-Z0-9]([-_.]?[0-9a-zA-Z])*$/;
			var leng2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-z]{2,3}$/i;
			
			if(!leng1.test(emailId) || !leng2.test(emailAd)) {
				return false;
			} else {
				return true;
			}
		}
		
		
		//전화번호 유효성 검사
		function checkPhone(tel1, tel2) {
			var num1 = /^[0-9]{3,4}$/;
			var num2 = /^[0-9]{4}$/;
			
			if(!num1.test(tel1) || !num2.test(tel2)) {
				return false;
			} else {
				return true;	
			}		
		}
		
		
		//아이디가 새롭게 입력될 경우 중복확인 unchecked로 변경
		function checkIdDup() {
			$('#idDuplication').val('unchecked');
		}
		
		//이메일이 새롭게 입력될 경우 중복확인 unchecked로 변경
		function checkEmailDup() {
			$('#emailDuplication').val('unchecked');
		}
		
		
		

		//다음 API
		function daumAddress() {
			new daum.Postcode({
				oncomplete: function(data){
				
				var fullAddr = "";	//최종 주소
				var extraAddr = "";  //조합형 주소
				
				//사용자가 선택한 주소 타입에 따른 값을 가져온다.
				if(data.userSelectedType == 'R') {	//도로명 주소 선택
					fullAddr = data.roadAddress;
				}else {	//지번 주소 선택
					fullAddr = data.jibunAddress;
				}
				
				//사용자가 선택한 주소가 도로명 타입일 때 조합한다.
				if(data.userSelectedType == 'R') {
					//법정동명이 있을 경우 추가한다.
					if(data.bname != '') {
						extraAddr += data.bname;
					}
					
					//건물명이 있을 경우 추가한다.
					if(data.buildingName !== '') {
						extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);	
					}
					
					//조합형 주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
					fullAddr += (extraAddr != '' ? '(' + extraAddr + ')' : '');				
				}
				
				
				//주소를 address1 필드에, 우편번호를 zipcode 필드에 넣는다.
				$('#address1').val(fullAddr);
				$('#zipcode').val(data.zonecode);	//5자리 새 우편번호 사용
				
				//커서를 상세주소 필드로 이동한다.
				$('#address2').focus();
			
			  }
		   }).open();
		}//end of daumAddress
		
		
		
		
		
		
		
		
	});
	
</script>





<body>
	<div>회원 정보 수정</div>
	<form>
		<label>아이디<input type="text" name="memberId" id="memberId" value="${requestScope.member.memberId }" readonly></label>
		
		<label>비밀번호<input type="password" name="memberPwd" id="memberPwd" value="${requestScope.member.memberPwd }" maxlength="25" tabindex="1"></label>&nbsp;
					<span>영문과 숫자를 포함하여 8~25자리로 입력해주세요.</span><br>
		
		<label>비밀번호 확인<input type="password" name="checkPwd" id="checkPwd" value="${requestScope.member.memberPwd }" maxlength="25" tabindex="2"></label><br>
		
		<label>이름<input type="text" name="name" id="name" value="${requestScope.member.name }" tabindex="3"></label><br>
		
		<label>이메일<input type="text" name="emailId" id="emailId" onkeydown="checkEmailDup()" tabindex="4">&nbsp;
				   <span id="at">@</span>&nbsp;
				   <select name="emailAd" id="emailAd" tabindex="5">
				     <option value="write">직접 입력</option>
				     <option value="gmail">gamil.com</option>
				     <option value="daum">daum.net</option>
				     <option value="naver">naver.com</option>				     
				   </select></label>&nbsp;
				   <label><button>중복확인</button></label><br>
				   <input type="text" id="emailAdWrite" name="emailAdWrite" />
				   &nbsp;<label><button id="checkEmail">중복확인</button></label><br>
				   <input type="hidden" id="emailDuplication" value="checked">	
				   
		<label>성별<input type="radio" name="gender" id="M" value="M" checked tabindex="6">남성 &nbsp;
				  <input type="radio" name="gender" id="F" value="F" tabindex="7">여성 </label><br>
				  
		<label>전화번호<select name="exchangeNumber" id="exchangeNumber" tabindex="8"> 
				     <option value="010">010</option>
				     <option value="02">02</option>
				     <option value="011">011</option>
				     <option value="031">031</option>			     
				   </select>&nbsp;
				   <span>-</span>&nbsp;
				   <input type="text" name="tel1" id="tel1" tabindex="9">&nbsp;
				   <span>-</span>&nbsp;
				   <input type="text" name="tel2" id="tel2" tabindex="10">					
		</label><br>
		
		<label>주소<input type="text" name="address1" id="address1" value="${requestScope.member.address1 }" tabindex="11"><br>
				  <input type="text" name="address2" id="address2" value="${requestScope.member.address2} }" tabindex="12"></label><br>
				  <input type="text" name="zipcode" id="zipcode" value="${requestScope.member.zipcode }" tabindex="13">&nbsp;&nbsp;&nbsp;
				  <button id="findAdd">주소찾기</button><br><br>
		
		
		<button type="button" id="modify">정보 수정</button> &nbsp;&nbsp;&nbsp;
		<button type="button" id="reset">취소</button><br>
		<button type="button" id="withdraw">회원 탈퇴</button><br>
	</form>

</body>
</html>