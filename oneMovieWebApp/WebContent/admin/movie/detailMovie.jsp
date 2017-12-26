<%-- detailArticle.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세조회 화면</title>
<style>
#main {
	margin-left: auto;
	margin-right: auto;
}

table td:not (.photo ) {
	width: 500px;
	padding: 30px;
}

#form1, #form2 {
		display: inline;
}


	
<%--tab style--%>
ul.tabs {
	margin-left: 200px;
	float: left;
	list-style: none;
	height: 32px;
	width: 100%;
	font-size: 20px;
}

ul.tabs li {
	float: left;
	text-align: center;
	cursor: pointer;
	width: 200px;
	height: 31px;
	border: 1px solid #eee;
	font-weight: bold;
	background: #9e9e9e24;
	overflow: hidden;
	position: relative;
}

ul.tabs li.active {
	background: #545e63;
	border-bottom: 1px solid #9e9e9e24;
}

.tab_content {
	margin: auto;
	width: 70%;
	font-size: 15px;
	display: none;
}
</style>
<script src="../../js/jquery-3.2.1.min.js"></script>
<script>
   $(function(){
   
      $(".tab_content").hide();
      $(".tab_content:first").show();
      
      $("ul.tabs li").click(function () {
           $("ul.tabs li").removeClass("active").css("color", "black"); //li속에 active클래스 속성을 삭제
           $(this).addClass("active").css("color", "white"); //클릭한 li요소에 active클래스 속성을 추가
           $(".tab_content").hide()
           var activeTab = $(this).attr("rel"); //rel속성값을 가져옴
           $("#" + activeTab).fadeIn() //서서히 나타남
       });
      
      $('#imgtr  img').hide();
        $('#imgtr  img').first().show();
         $('#imgtr  img').css('width',800);
         setInterval(function() {
            $('#imgtr').append($('#imgtr  img').first());
            $('#imgtr  img').last().hide();
            $('#imgtr  img').first().fadeIn().show();
         }, 2000);      
   });
   
 	


</script>
</head>
<body>
	<table border="1" id="main">
		<tr>
			<td rowspan="5" class="photo"><img
				src="${pageContext.request.contextPath}/admin/movie/upload/${requestScope.movie.photo.moviePhotoOriginalFileName}"></td>
			<td>제목</td>
			<td>${requestScope.movie.movieTitle }</td>
		</tr>
		<%-- <tr>
			<td>장르</td>
			<td>${requestScope.movieGenres.genre.genreName }</td>
		</tr> --%>
		<tr>
			<td>감독</td>
			<td>${requestScope.movie.director}</td>
		</tr>
		<tr>
			<td>상영시간</td>
			<td>${requestScope.movie.runningTime}분</td>
		</tr>
		<tr>
			<td>등급</td>
			<td>${requestScope.movie.grade.gradeAge }</td>
		</tr>
		<tr>
			<td>국가</td>
			<td>${requestScope.movie.nation.nationName }</td>
		</tr>

	</table>
	<br>
	<br>
	<div id="container">
		<ul class="tabs">
			<li class="active" rel="tab1">줄거리</li>
			<li rel="tab2">배우</li>
			<li rel="tab3">포토</li>
		</ul>
		<div class="tab_container">
			<% pageContext.setAttribute("LF", "\n"); %>
			<div id="tab1" class="tab_content">${fn:replace(requestScope.movie.story, LF, "<br>") }</div>
			<div id="tab2" class="tab_content">
				<c:if test="${fn:length(requestScope.movie.actors) > 0 }">
					<table>
						<tr>
							<c:forEach var="actor" items="${requestScope.movie.actors }"
								varStatus="loop">
								<c:if test="${loop.index%3==0}">
						</tr>
						<tr>
							</c:if>
							<td><img
								src="${pageContext.request.contextPath}/admin/movie/upload/${pageScope.actor.actorPhoto.actorOriginalFileName}"></td>
							<td>${pageScope.actor.actorName }<br>${pageScope.actor.role.roleName}
								| ${pageScope.actor.characterName }역
							</td>

							</c:forEach>
						</tr>
					</table>
				</c:if>
			</div>
			<div id="tab3" class="tab_content">
				<table style="margin-left: auto; margin-right: auto;">
					<tr id="imgtr">
						<td><c:forEach var="photo"
								items="${requestScope.movie.photos }" varStatus="loop">
								<img
									src="${pageContext.request.contextPath}/admin/movie/upload/${pageScope.photo.moviePhotoOriginalFileName}">
							</c:forEach></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	
	
	<br><br>
 	<form id="form1" action="${pageContext.request.contextPath}/admin/movie/modifyMovieForm.do?movieNo=${requestScope.movie.movieNo}" method="post">
   			<button id="modifyMovieBtn" type="submit">수정하기</button>
    </form>
    <form id="form2" action="${pageContext.request.contextPath}/admin/movie/detailMovie.do" method="post">
   			<button id="resetMovieBtn" type="submit">취소</button>
    </form>
 	
</body>
</html>