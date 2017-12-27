<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
</head>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script> 
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	$(document).ready(function(){
		

	//아이디 중복 확인 버튼 클릭
	$('#checkId').on('click', function(event){
		event.preventDefault();
				
		if($('#memberId').val() == "") {
			alert("ID를 입력하세요.");
			return;
		}
		
		$.ajax({
			url: '${pageContext.request.contextPath}/duplicate.do'
			,
			method: 'POST'
			,
			data: {
				keyfield: "member_id"	,
				keyword: $('#memberId').val()
			},
			dataType: 'json'
			,
			success: function(data) {
				alert(data.result);
				if(data.key == "ok") {
					$('#idDuplication').val("checked");		
				}
			},
			error: function(jqXHR, textStatus, error) {
				alert("Error : " + jqXHR.status + "," + error);
			},			
		})
		
		
	});
	
	
	$('#emailAdWrite').hide();
	//이메일 직접 입력 선택 시 나타남
	$('#emailAd').change(function(){
		if($('#emailAd').val() == "write") {
			$('#emailAdWrite').show();
		} else {
			$('#emailAdWrite').hide();
		}
	});
		
	

	//이메일 중복 확인 버튼 클릭
	$('#checkEmail').on('click', function(event){
		event.preventDefault();
		
		var emailId = $('#emailId').val();
		var emailAd = $('#emailAd').val();
		var emailAdWrite = $('#emailAdWrite').val();	
		if(emailAdWrite != "") {
			emailAd = $('#emailAdWrite').val();
		}		
		
		var email = emailId + '@' + emailAd;
		
		if(emailId == "" || emailAd == "") {
			alert("이메일을 입력하세요.");
			return;
		}
		
		$.ajax({
			url: '${pageContext.request.contextPath}/duplicate.do'
			,
			method: 'POST'
			,
			data: {
				keyfield: "email"	,
				keyword: email
			}
			,
			dataType: 'json'
			,
			success: function(data) {
				alert(data.result);
				if(data.key == "ok") {
					$('#emailDuplication').val("checked");		
				}
			},
			error: function(jqXHR, textStatus, error) {
				alert("Error : " + jqXHR.status + "," + error);
			}			
		})
	
	});

	
	//아이디가 새롭게 입력될 경우 중복확인 unchecked로 변경
	$('#memberId').keydown(function(){
		$('#idDuplication').val('unchecked');
	});
	
	
	//이메일이 새롭게 입력될 경우 중복확인 unchecked로 변경
	$('#emailId').keydown(function(){
		$('#emailDuplication').val('unchecked');
	});
	
	$('#emailAd').change(function(){
		$('#emailDuplication').val('unchecked');
	});
	
	$('#emailAdWrite').keydown(function(){
		$('#emailDuplication').val('unchecked');
	});
	
	
	
	//주소 찾기
	$('#findAdd').on('click', function(event){
		event.preventDefault();

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
		
		
	});

	
	
	//취소 버튼 클릭
	$('#reset').on('click', function(event){
		event.preventDefault();
		if(confirm("회원 가입을 중단하시겠습니까?")) {
			location.href = '${pageContext.request.contextPath}/layoutUser.jsp';
		} else {
			return;
		}
	});

	
	
	
	//회원가입 버튼 클릭
	$('#join').on('click', function(event){
		event.preventDefault();
		
		checkForm();		
		
		$.ajax({
		
			url: "${pageContext.request.contextPath}/join.do"
			,
			method: 'POST'
			,
			data: {
				memberId: $('#memberId').val() 	,
				memberPwd: $('#memberPwd').val()	,
				name: $('#name').val()	,
				email: $('#emailId').val() + "@" + $('#emailAd').val()	,
				gender: $(':input:radio[name=gender]:checked').val()	,
				phone:	$('#exchangeNumber').val() + $('#tel1').val() + $('#tel2').val()	,
				address1: $('#address1').val()	,
				address2: $('#address2').val()	,
				zipcode: $('#zipcode').val()
			},
			dataType: 'json'
			,
			success: function(data) {
				alert(data.result);
				location.href="${pageContext.request.contextPath}/layoutUser.jsp";
			},
		    error: function(jqXHR, textStatus, error) {
				alert("Error : " + jqXHR.status + "," + error);		    	
		    },		
		});
	});
		

	

	
	
	//회원가입폼 입력 내용 체크 
	function checkForm() {
		
		var id = $('#memberId').val();
		var idChk = $('#idDuplication').val();
		var pwd = $('#memberPwd').val();
		var pwdChk = $('#checkPwd').val();
		var name = $('#name').val();
		var exNum = $('#exchangeNumber').val();
		var tel1 = $('#tel1').val();
		var tel2 = $('#tel2').val();
		var emailId = $('#emailId').val();
		var emailAd = $('#emailAd').val();
		var emailChk = $('#emailDuplication').val();
		
		//아이디 입력 확인
		if(id == "") {
			alert("아이디를 입력해주세요.");
			return;
		}
		
		//아이디에 한글, 특문 사용 불가
		if(!checkId(id)) {
			alert("아이디는 영문, 숫자를 사용하여 6~15자리로 입력해주세요.");
			return;
		}
		
		//아이디 중복 확인
		if(idChk != "checked"){
			alert("아이디 중복확인을 해주세요.");
			return;
		}
		
		//비밀번호 입력 확인
		if(pwd == "" || pwdChk == "") {
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
		if(name == "") {
			alert("이름을 입력해주세요.");
			return;
		}
		
		//이름에 숫자, 특문 사용 불가
		if(checkName(name)) {
			alert("이름은 한글만 입력이 가능합니다.(최대 10자)");
			return;
		}
		
		//이메일 검증식
		if(emailId == "" || emailAd == "" || !checkEmail(emailId, emailAd)){
			alert("이메일을 정확히 입력해주세요.");
			return;
		}
		
		//이메일 중복 확인
		if(emailChk != "checked"){
			alert("이메일 중복확인을 해주세요.");
			return;
		}
		
		//전화번호 입력 확인
		if(exNum == "" || tel1 == "" || tel2 == "") {
			alert("전화번호를 입력해주세요.");
			return;
		}
		
		//전화번호에 숫자만
		if(!checkPhone(tel1, tel2)){
			alert("전화번호는 숫자만 입력 가능합니다.");
			return;
		}
		
			
				
	}//end of checkForm
	
	
	

	
	

	
	
	
	
	

	
	
	//아이디 유효성 검사: 영문 또는 숫자 사용
	function checkId(id) {
		var leng = /^[a-zA-Z0-9]{6,15}$/;	//영문, 숫자, 6~15자 제한
		return leng.test(id);
	}	

	
	
	//비밀번호 유효성 검사
	function checkPwd(pwd){
		var leng = /^[a-zA-Z0-9]{8,25}$/;	//영문, 숫자, 8~25자 제한
		var num = pwd.search(/[0-9]/g);		//숫자 0-9
		var eng = pwd.search(/[a-z]/ig);	//영문 소문자
		
		if(!leng.test(pwd) || num < 0 || eng < 0) {
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
		var leng2 = /^[a-zA-Z0-9]([-_.]?[0-9a-zA-Z])*.[a-zA-z]{2,3}$/i;
		
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

	
	
	
	
});	
	


</script>
<body>
	<div>회원가입</div>
	<form>
		<label>아이디</label><input type="text" name="memberId" id="memberId" maxlength="15" tabindex="1">&nbsp;
		<label><button id="checkId">중복확인</button></label>&nbsp;
					<span>영문, 숫자 사용 가능 / 6~15자리로 입력해주세요.</span><br>
			   <input type="hidden" id="idDuplication" value="unchecked">	
					
		<label>비밀번호<input type="password" name="memberPwd" id="memberPwd" maxlength="25" tabindex="2"></label>&nbsp;
					<span>영문과 숫자를 포함하여 8~25자리로 입력해주세요.</span><br>
					
		<label>비밀번호 확인<input type="password" name="checkPwd" id="checkPwd" maxlength="25" tabindex="3"></label><br>
		
		<label>이름<input type="text" name="name" id="name" tabindex="4"></label><br>
		
		<label>이메일</label>
			   <input type="text" name="emailId" id="emailId" tabindex="5">&nbsp;
			   <span id="at">@</span>&nbsp;	  	
				   <select name="emailAd" id="emailAd" tabindex="6">
				     <option value="gmail.com">gamil.com</option>
				     <option value="daum.net">daum.net</option>
				     <option value="naver.com">naver.com</option>
				  	 <option value="write">직접 입력</option>
				   </select>
				   <input type="text" id="emailAdWrite" name="emailAdWrite" />
				   &nbsp;<label><button id="checkEmail">중복확인</button></label><br>
				   <input type="hidden" id="emailDuplication" value="unchecked">	
				   
		<label>성별<input type="radio" name="gender" id="M" value="M" checked tabindex="7">남성 &nbsp;
				  <input type="radio" name="gender" id="F" value="F" tabindex="8">여성 </label><br>
				  
		<label>전화번호<select name="exchangeNumber" id="exchangeNumber" tabindex="9">
				     <option value="02">02</option>
				     <option value="010">010</option>
				     <option value="011">011</option>
				     <option value="031">016</option>
				     <option value="031">017</option>
				     <option value="031">019</option>
				     <option value="031">031</option>
				     <option value="031">032</option>
				     <option value="031">033</option>
				     <option value="031">041</option>
				     <option value="031">042</option>
				     <option value="031">043</option>
				     <option value="031">044</option>
				     <option value="031">051</option>
				     <option value="031">052</option>
				     <option value="031">053</option>
				     <option value="031">054</option>
				     <option value="031">055</option>
				     <option value="031">061</option>
				     <option value="031">062</option>
				     <option value="031">063</option>
				     <option value="031">064</option> 
				   </select>&nbsp;
				   <span>-</span>&nbsp;
				   <input type="text" name="tel1" id="tel1" tabindex="10">&nbsp;
				   <span>-</span>&nbsp;
				   <input type="text" name="tel2" id="tel2" tabindex="11">					
		</label><br>
		<label>주소<input type="text" name="address1" id="address1" tabindex="12"><br>
				  <input type="text" name="address2" id="address2" tabindex="13"></label><br>
				  <input type="text" name="zipcode" id="zipcode" tabindex="14">&nbsp;&nbsp;&nbsp;
				  <button id="findAdd">주소찾기</button><br><br>
		
		<button type="button" id="join">회원가입</button> &nbsp;&nbsp;&nbsp;
		<button type="button" id="reset">취소</button><br>
	</form>

</body>
</html>