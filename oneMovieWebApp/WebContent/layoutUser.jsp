<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
 <head>
    <meta charset="UTF-8">
    <title>2단 레이아웃</title>
    <style>
        body {
            color: white;
            background-color: #5c87b2; 
            
        }
        header a, nav a {
            color: black;
        }

        header {
            width: 960px;
            height: 100px;
            margin-left: auto;
            margin-right: auto;
            background-color: aliceblue;
        }
        header h1 {
        
            display: inline-block;
            margin-top: 10px;
        }
        
        header a {
            color: black;
            text-decoration: none;
        }
        header h1 a {
            font-size: 2em;
            
        }
        header>nav {
            float: right;
            margin-top: 10px;
            margin-right: 30px;
            font-size: 20px; 
            width: 500px;
            text-align:right;
            
        }        
        
        section {
            width: 960px;
            height: auto;
            margin-left: auto;
            margin-right: auto;
            background-color: green;
            
           
        }

        section nav {
            width: 20%;
            height: 600px;
            background-color: burlywood;
            float: left;
            font-size: 1.5em;
            
        }
       
        section ul {
            list-style-type: none;
            padding-left: 10%;
            padding-bottom: 30px;

        }

        section a {
            text-decoration: none;
            
            
        }
        section a:hover {
            color: yellow;
        }

        section nav ul li {
            margin-bottom: 20px;
        }
        
        section article {
            width: 80%;
            height: 960px;
            background-color: white;
            float: left;
            color: black;
            
            
            
        }
        
        header > nav {
        	height: 100px;
        	width : auto;
        	margin: 0px;
        	float : right;
          }
         header > nav ul {
         	list-style-type : none;
         	display : inline-block;
         	margin-top: 0px;
         }
        header > nav  li {
        	display: inline-block;
        	margin : 20px;
        	text-align: right;
        }
        footer {
            width: 960px;
            height: 100px;
            margin-left: auto;
            margin-right: auto;
            background-color: deepskyblue;
            overflow: hidden;
        }
    </style>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath }/js/jquery-ui.min.css">
	<script src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
	
</head>

<body>
    <header>
       <h1><a href="${pageContext.request.contextPath }/layoutUser.jsp">One Movie</a></h1>
        <nav>
            <c:choose>
        		<c:when test='${empty sessionScope.memberId}'>
        			<jsp:include page="/header.jsp" />
        		</c:when>	
        		<c:when test='${!empty sessionScope.memberId}'>
        			<jsp:include page="/header2.jsp" />
        		</c:when> 
        	</c:choose>
        </nav>
    </header>
    <section>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath }/user/movie/listMovie.do">영화</a></li>

                <ul>
                    <li><a href="${pageContext.request.contextPath }/user/movie/listStateMovie.do?keyfield=now">현재 상영작</a></li>
                    <li><a href="${pageContext.request.contextPath }/user/movie/listStateMovie.do?keyfield=end">지난 상영작</a></li>
                    <li><a href="${pageContext.request.contextPath }/user/movie/listStateMovie.do?keyfield=future">상영 예정작</a></li>
                </ul>
                <li><a href="${pageContext.request.contextPath }/memberBooking.do">예매</a></li>
                <ul>
                <li><a href="${pageContext.request.contextPath }/memberBooking.do">예매</a></li>
                <ul>

                </ul>
                <li><a href="#">극장</a></li>
                <ul>
                    <li><a href="#">영화관 정보</a></li>
                </ul>

            </ul>
        </nav>
        <article> 
            <jsp:include page="${param.article }" />
        </article>
    </section>

    <footer>Footer</footer>
</body>
</html>
    