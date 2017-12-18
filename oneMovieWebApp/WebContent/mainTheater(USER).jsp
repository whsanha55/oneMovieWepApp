<%-- mainTheater(USER).jsp --%>
<%--  사용자 화면에서 극장관리 선택시 메인화면 --%>
<%--  수정 진행 중 지금 dao로 넘어감 --%>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>영화관 정보</title>
<style>
body{
	font:italic 30px "휴먼편지체";
	text-align:center;
}
div>ul >li {
	
	font-size:40px;
	display: inline-block;
	list-style: none;
	padding-left: 30px;
	padding-right: 30px;
	margin:0;
	background-color: darkGray;
}
div> ol> li{

	font-size:20px;
}
a{
	text-decoration:none;
}
hr{
	size:30px;
	width:50%;
}
div > img{
  align :center;
}
#ul1{
	list-style-image:url(./image/dot.png);
}
table{
	border: 1px solid black;
	border-collapse:collapse;
}
th, td{
	border:1px solid black;
	padding:10px;
}
</style>
</head>
<body>
	<div id="#div1"><ul>
		<li><a href="#">강남</a></li>
		<li><a href="#">홍대</a></li>
		<li><a href="#">삼성</a></li>
	</ul></div>
	<hr>
	<div>Theater</div>
	<hr>
	<div><img src ="./image/theater_img.jpg" alt ="theater"></div>
	<div><ol>
		<li>층별 안내
			<ul id= "ul1">	
				<li>지하 2층 : <span>&nbsp;</span></li>
				<li>로비층 : <span>&nbsp;</span></li>
				<li>2층 : <span>&nbsp;</span></li>
				<li>4층 : <span>&nbsp;</span></li>
			</ul></li>
		<li>관람료</li>
		<table>
			<th>구분</th>
			<th>전체</th>
			<tr>
				<td>월~일</td>
				<td>10,000원</td>
			</tr>
		</table>
		<li>위치정보</li>
		서울 특별시 강남구 역삼동		
	</ol></div>
</body>
</html>