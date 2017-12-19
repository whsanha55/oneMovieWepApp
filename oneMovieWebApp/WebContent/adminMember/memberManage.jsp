<%@ page contentType="text/html; charset=utf-8" %>


<!DOCTYPE html>
<html>
<head>
<title>회원 관리</title>
</head>
<script src="js/jquery-3.2.1.min.js"></script>
<script>
	

</script> 
   
<body>
<form>
	<select name="keyfield">
		<option value="name">이름</option>
		<option value="memberId">아이디</option>
	</select>
	<input type="text" name="keyword"> &nbsp;
	<button type="button">검색</button> &nbsp;&nbsp;&nbsp;
	<button type="button">전체조회</button>
	
	
</form>


<form>
	<select name="sortField">
		<option value="memberNo">회원번호</option>
		<option value="memberId">아이디</option>
		<option value="name">이름</option>
		<option value="email">이메일</option>
		<option value="address">주소</option>		
	</select> &nbsp;
	<button type="button">오름차순</button> &nbsp;
	<button type="button">내림차순</button>
</form>

<table>
	<tr>
		<th>회원번호</th>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>성별</th>
		<th>전화번호</th>
		<th>이메일</th>
		<th>주소</th>
		<th>탈퇴여부</th>
		<th>탈퇴일자</th>
	</tr>
</table>




</body>
</html>