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
#main{
   margin-left: auto;
   margin-right: auto;
}

table td:not(.photo) {
   width: 300px;
   padding: 30px;
}
  
.title {
   font-weight: bold;
   font-size: 20px;
}

tr, td {
   border: none;
}

ul.tabs {
    float: left;
    list-style: none;
    height: 32px;
    width: 100%;
    font-size:20px;
}
ul.tabs li {
    float: left;
    text-align:center;
    cursor: pointer;
    width:200px;
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
         $('#imgtr  img').css({ 'width': '300px', 'height': '300px' });
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
         <td rowspan="6" class="photo"><img
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

   </table>
<div id="container">
    <ul class="tabs">
        <li class="active" rel="tab1">줄거리</li>
        <li rel="tab2">배우</li>
        <li rel="tab3">포토</li>
    </ul>
    <div class="tab_container" >
          <% pageContext.setAttribute("LF", "\n"); %>
        <div id="tab1" class="tab_content">${fn:replace(requestScope.movie.story, LF, "<br>") }</div>
        <div id="tab2" class="tab_content" style="width:100%; height:350px; overflow:auto">
           <c:if test="${fn:length(requestScope.movie.actors) > 0 }">
             <table>
             <tr> 
                <c:forEach var="actor" items="${requestScope.movie.actors }" varStatus="loop">
                    <c:if test="${loop.index%3==0}">
                          </tr><tr>
                    </c:if>
                    <td><img src = "${pageContext.request.contextPath}/user/movie/upload/${pageScope.actor.actorPhoto.actorOriginalFileName}"></td>
                   <td>${pageScope.actor.actorName }<br>${pageScope.actor.role.roleName} | ${pageScope.actor.characterName }역</td>
                   
                </c:forEach>
             </tr>
             </table>    
          </c:if>
        </div>
        <div id="tab3" class="tab_content">
             <table style="margin-left: auto; margin-right: auto;" >
             <tr id="imgtr"> 
             	<th>
                	<c:forEach var="photo" items="${requestScope.movie.photos }" varStatus="loop">
                    <img src = "${pageContext.request.contextPath}/user/movie/upload/${pageScope.photo.moviePhotoOriginalFileName}">
                   </c:forEach>
                  </th>
             </tr>
             </table> 
        </div>
    </div>
</div>
</body>
</html>