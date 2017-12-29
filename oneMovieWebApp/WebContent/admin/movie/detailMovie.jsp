<%-- detailArticle.jsp --%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("LF", "\n"); %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세조회 화면</title>
<style>
	#main {
	   margin-left: auto;
	   margin-right: auto;
	   width: 700px;
	    font-size: 15px;
	    font-weight: normal;
	}
	
	table td:not(.photo) {
	   width: 300px;
	   padding: 20px;
	}
	
	tr, td {
	   border: none;
	}
	
	#actorPhoto{
	   width: 100px;
	}
	 
	.title {
	   font-weight: bold;
	   font-size: 20px;
	}
		.booking, .theater {
		display: inline-block;
		padding: 10px;
		border-radius: 10px;
		border-color: transparent;
		font-size: 15px;
		background: #bf0d0d;
		color: #fff;
		cursor: pointer;
	}
  
	#form1, #form2 {
		display: inline;
		margin: auto;
		width: 250px;
	}

	#modifyMovieBtn {
	  border-radius: 3px;
	  font-weight: 300;
	  border-color: transparent;
	  font-size: 15px;
	  background: #00bfff;
	  color: #fff;
	  cursor: pointer;
	}
	
	#resetMovieBtn {
		border-radius: 3px;
	  font-weight: 300;
	  border-color: transparent;
	  font-size: 15px;
	  background: #9e9e9e;
	  color: #fff;
	  cursor: pointer;
	}
	
<%--tab style--%>
	ul.tabs {
		margin-left: 70px;
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
		width: 150px;
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
	
	#tab2 {
		 width:100%; 
		 height:350px; 
		 overflow:auto;
	}
</style>

<script src="../../js/jquery-3.2.1.min.js"></script>
<script>
   $(function(){
   
      $(".tab_content").hide();
      $(".tab_content:first").show();
      
      $("ul.tabs li").click(function () {
           $("ul.tabs li").removeClass("active").css("color", "black"); 
           $(this).addClass("active").css("color", "white"); 
           $(".tab_content").hide()
           var activeTab = $(this).attr("rel");
           $("#" + activeTab).fadeIn() 
       });
      
      $('#imgtr  img').hide();
      $('#imgtr  img').first().show();
      $('#imgtr  img').css({ 'width': '500px', 'height': '300px' });
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
         <td rowspan="7" class="photo"><img
            src="${pageContext.request.contextPath}/user/movie/upload/${requestScope.movie.detailPhoto.moviePhotoOriginalFileName}"></td>
         <td class="title">${requestScope.movie.movieTitle }</td>
      </tr>
      <tr>
         <td>장르 : 
            <c:forEach var="genre" items="${requestScope.movie.genres }" varStatus="loop">
                ${pageScope.genre.genreName}
                <c:if test="${loop.index < fn:length(requestScope.movie.genres) - 1  }">
             		,
            	</c:if>
            </c:forEach>
           </td>
      </tr>
      <tr>
         <td>감독 : ${requestScope.movie.director}</td>
      </tr>
      <tr>
         <td>상영시간 : ${requestScope.movie.runningTime}분</td>
      </tr>
      <tr>
         <td>등급 : ${requestScope.movie.grade.gradeAge }</td>
      </tr>
      <tr>
         <td>국가 : ${requestScope.movie.nation.nationName }</td>
      </tr>
      <tr>
         <td ><a class='booking' href='${pageContext.request.contextPath}/memberBooking.do?movieNo=${pageScope.movie.movieNo}'>예매하기</a> &nbsp;&nbsp;&nbsp;&nbsp;<a class='theater' href='#'>상영시간표</a></td>
      </tr>
    </table>
    
	<div id="container">
    	<ul class="tabs">
        	<li class="active" rel="tab1">줄거리</li>
        	<li rel="tab2">배우</li>
        	<li rel="tab3">포토</li>
       	 	<li rel="tab4">동영상</li>
		</ul>
		<div class="tab_container" >
        	<div id="tab1" class="tab_content">${fn:replace(requestScope.movie.story, LF, "<br>") }</div>
        	<div id="tab2" class="tab_content">
				<c:if test="${fn:length(requestScope.movie.actors) > 0 }">
             		<table>
            			 <tr> 
                			<c:forEach var="actor" items="${requestScope.movie.actors }" varStatus="loop">
                    			<c:if test="${loop.index%2==0}">
                          			</tr><tr>
                   				 </c:if>
                   				 <td id="actorPhoto"><img src = "${pageContext.request.contextPath}/user/movie/upload/${pageScope.actor.actorPhoto.actorOriginalFileName}"></td>
                   				<td>${pageScope.actor.actorName }<br>${pageScope.actor.role.roleName} | ${pageScope.actor.characterName }역</td>
							</c:forEach>
             			</tr>
            	 	</table>    
				</c:if>
			</div>
        	<div id="tab3" class="tab_content">
				<table id="tabTable3">
            		 <tr id="imgtr"> 
						<th>
							<c:forEach var="photo" items="${requestScope.movie.photos }" varStatus="loop">
								<img src = "${pageContext.request.contextPath}/user/movie/upload/${pageScope.photo.moviePhotoOriginalFileName}">
                   			</c:forEach>
						</th>
             		</tr>
            	 </table> 
			</div>
        	<div id="tab4" class="tab_content" >
           		<iframe width="560" height="315" src="${requestScope.movie.videoUrl}"></iframe>
        	</div>
		</div>
	</div>
	
	
	<br><br>
 	<form id="form1" action="${pageContext.request.contextPath}/admin/movie/modifyMovieForm.do?movieNo=${requestScope.movie.movieNo}" method="post">
   			<button id="modifyMovieBtn" type="submit">수정하기</button>
    </form>
    <form id="form2" action="${pageContext.request.contextPath}/admin/movie/listMovie.do" method="post">
   			<button id="resetMovieBtn" type="submit">취소</button>
    </form>
 	
</body>
</html>