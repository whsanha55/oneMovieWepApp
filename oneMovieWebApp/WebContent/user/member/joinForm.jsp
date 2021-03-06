<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html>
<html>
<head>
<title>회원가입</title>
<style>
	#jm {
		border: 1px solid darkgray;
		padding: 15px;
		margin-left: auto;
		margin-right: auto;

	}

	#jm th {
		padding-left: 5px;
		text-align: left;
	}

	#di {
		font-size: 30px;
		border-bottom: 3px solid skyblue;
		display: inline-block;
	}

	#sp {
		font-size: 12px;
	}
	
		#btt	{
		margin-left: auto;
		margin-right: auto;
	}
	
	#btt td {
		padding: 10px;
	}
	
	
</style>
</head>
<script src="https://code.jquery.com/jquery-3.2.1.js"></script> 
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	$(document).ready(function(){
	
		
		
		//회원가입 버튼 클릭
		$('#join').on('click', function(event){
			event.preventDefault();
			
			if(!checkForm()) {
				return;
			};
			
			emailAd = $('#emailAd').val();
			if(emailAd == "write") {
				emailAd = $('#emailAdWrite').val();
			}
			
			$.ajax({
			
				url: "${pageContext.request.contextPath}/join.do"
				,
				method: 'POST'
				,
				data: {
					memberId: $('#memberId').val() 	,
					memberPwd: $('#memberPwd').val()	,
					name: $('#name').val()	,
					email: $('#emailId').val() + "@" + emailAd	,
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
			var tel1 = $('#tel1').val();
			var tel2 = $('#tel2').val();
			var emailId = $('#emailId').val();
			var emailAd = $('#emailAd').val();
			if(emailAd == "write"){
				emailAd = $('#emailAdWrite').val();
			}
			var emailChk = $('#emailDuplication').val();
			
			//아이디 입력 확인
			if(id == "") {
				alert("아이디를 입력해주세요.");
				return false;
			}
			
			//아이디에 한글, 특문 사용 불가
			if(!checkId(id)) {
				alert("아이디는 영문, 숫자를 사용하여 6~15자리로 입력해주세요.");
				return false;
			}
			
			//아이디 중복 확인
			if(idChk != "checked"){
				alert("아이디 중복확인을 해주세요.");
				return false;
			}
			
			//비밀번호 입력 확인
			if(pwd == "" || pwdChk == "") {
				alert("비밀번호를 입력해주세요.");
				return false;
			}
			
			//비밀번호 일치
			if(pwd != pwdChk) {
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			
			//비밀번호 영문, 숫자 혼용 및 길이 확인
			if(!checkPwd(pwd)) {
				alert("비밀번호는 영문, 숫자를 혼합하여 8~25자리로 입력해주세요.");
				return false;
			}
			
			//이름 입력 확인
			if(name == "") {
				alert("이름을 입력해주세요.");
				return false;
			}
			
			//이름에 숫자, 특문 사용 불가
			if(checkName(name)) {
				alert("이름은 한글만 입력이 가능합니다.(최대 10자)");
				return false;
			}
			
			//이메일 검증식
			if(emailId == "" || emailAd == "" || !checkEmail(emailId, emailAd)){
				alert("이메일을 정확히 입력해주세요.");
				return false;
			}
			
			//이메일 중복 확인
			if(emailChk != "checked"){
				alert("이메일 중복확인을 해주세요.");
				return false;
			}
			
			//전화번호 입력 확인
			if(tel1 == "" || tel2 == "") {
				alert("전화번호를 입력해주세요.");
				return false;
			}
			
			//전화번호에 숫자만
			if(!checkPhone(tel1, tel2)){
				alert("전화번호는 숫자만 입력 가능합니다.");
				return false;
			}
			
			return true;	
					
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
	
		
		
		
		
		

	//아이디 중복 확인 버튼 클릭
	$('#checkId').on('click', function(event){
		event.preventDefault();
				
		if($('#memberId').val() == "") {
			alert("ID를 입력하세요.");
			return;
		}
		
		if(!checkId($('#memberId').val())) {
			alert("아이디는 영문, 숫자를 사용하여 6~15자리로 입력해주세요.");
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
		if(emailAd == "write") {
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

	
	
	

		

	

	
});	
	


</script>
<body>
	<div id="di">회원가입</div><br><br>
	<table id="jm">
	<form>
		<tr>
			<th><label>아이디</label></th>
			<td><input type="text" name="memberId" id="memberId" size="11" maxlength="15" tabindex="1">&nbsp;
				<button id="checkId">중복확인</button>&nbsp;
				<span id="sp">영문, 숫자 사용 가능 / 6~15자리로 입력해주세요.</span>
			    <input type="hidden" id="idDuplication" value="unchecked">
		</tr>
		<tr>
			<th><label>비밀번호</label></th>
			<td><input type="password" name="memberPwd" id="memberPwd" size="11" maxlength="25" tabindex="2">&nbsp;
				<span id="sp">영문과 숫자를 혼합하여 8~25자리로 입력해주세요.</span></td>
		</tr>
		<tr>
			<th><label>비밀번호 확인</label></th>
			<td><input type="password" name="checkPwd" id="checkPwd" size="11" maxlength="25" tabindex="3"></td>
		</tr>
		<tr>
		<th><label>이름</label></th>
		<td><input type="text" name="name" id="name" size="11" tabindex="4"></td>
		</tr>
		<tr>
			<th><label>이메일</label></th>
			<td><input type="text" name="emailId" id="emailId" size="11" tabindex="5">&nbsp;
			     <span id="at">@</span>&nbsp;	  	
				   <select name="emailAd" id="emailAd" tabindex="6">
				     <option value="gmail.com">gamil.com</option>
				     <option value="daum.net">daum.net</option>
				     <option value="naver.com">naver.com</option>
				  	 <option value="write">직접 입력</option>
				   </select>
				   <input type="text" id="emailAdWrite" name="emailAdWrite" />
				   &nbsp;<label><button id="checkEmail">중복확인</button></label>
				   <input type="hidden" id="emailDuplication" value="unchecked"></td>	
		</tr>
		<tr>
			<th><label>성별</label></th>
			<td><input type="radio" name="gender" id="M" value="M" checked tabindex="7">여성 &nbsp;
				<input type="radio" name="gender" id="F" value="F" tabindex="8">남성</td>
		</tr>	
		<tr>
			<th><label>전화번호</label></th>
			<td><select name="exchangeNumber" id="exchangeNumber" tabindex="9">
				     <option value="02">02</option>
				     <option value="010" selected>010</option>
				     <option value="011">011</option>
				     <option value="016">016</option>
				     <option value="017">017</option>
				     <option value="019">019</option>
				     <option value="031">031</option>
				     <option value="032">032</option>
				     <option value="033">033</option>
				     <option value="041">041</option>
				     <option value="042">042</option>
				     <option value="043">043</option>
				     <option value="044">044</option>
				     <option value="051">051</option>
				     <option value="052">052</option>
				     <option value="053">053</option>
				     <option value="054">054</option>
				     <option value="055">055</option>
				     <option value="061">061</option>
				     <option value="062">062</option>
				     <option value="063">063</option>
				     <option value="064">064</option> 
				   </select>&nbsp;
				   <span>-</span>&nbsp;
				   <input type="text" name="tel1" id="tel1" size="10" tabindex="10">&nbsp;
				   <span>-</span>&nbsp;
				   <input type="text" name="tel2" id="tel2" size="10" tabindex="11"></td>					
		</tr>

		<tr>
			<th rowspan="3"><label>주소</label></th>
			<td><input type="text" name="address1" id="address1" size="40" tabindex="12"></td>
		</tr>
		<tr>
			<td><input type="text" name="address2" id="address2" size="40" tabindex="13"></td>
		</tr>
		<tr>
			<td><input type="text" name="zipcode" id="zipcode" tabindex="14">
				&nbsp;&nbsp;&nbsp;<button id="findAdd">주소찾기</button></td>
		</tr>
	</table><br>
			<table id="btt">
			<tr>
				<td><button type="button" id="join">가입</button></td>
				<td><button type="button" id="reset">취소</button></td>
			</tr>
	</table>
			
	</form>

</body>
</html>