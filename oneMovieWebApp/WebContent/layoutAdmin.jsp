<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!doctype html>
<html lang="en">
 <head>
    <meta charset="UTF-8">
    <title>2�� ���̾ƿ�</title>
    <style>
       body,a {
            font: 20px '����';
            color: white;
            font-weight: bolder;

        }

        header {
            width: 960px;
            height: 50px;
            margin-left: auto;
            margin-right: auto;
            background-color: aliceblue;
            
        }
       
        header>nav {
            float: right;
            margin-top: 10px;
            margin-right: 20px;
            font-size: 20px;
            color: black;
        }
        header>nav>span {
            margin-left: 10px;
        }
        header a {
            color: black;
            text-decoration: none;
            background-color: aquamarine;
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
            background-color: brown;
            float: left;
            
        }

        section article {
            width: 80%;
            height: 720px;
            background-color: powderblue;
            float: left;
            color: black;
            
            
        }
       
        section ul {
            list-style-type: none;
            padding: 0px;   

        }
        
        section li {
            margin-bottom: 100px;
            height: 40px;
            
            background-color: firebrick;
            text-align: center;
            
        }

        section a {
            text-decoration: none;
            font-size:30px;
            
        }
        section a:hover {
            color: yellow;
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
        <nav>
            <span>������ �������� �����Ͽ����ϴ�.</span>
            <span><a href="${pageContext.request.contextPath }/adminLogout.do">�α׾ƿ�</a></span>       
        </nav>
    </header>
    <section>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath }/manageMemberForm.do">ȸ������</a></li>
                <li><a href="${pageContext.request.contextPath }/adminBookingByMember.do">���Ű���</a></li>
                
                <li><a href="${pageContext.request.contextPath }/admin/movie/listMovie.do">��ȭ</a></li>
               
                <li><a href="#">����</a></li>
                

            </ul>
        </nav>
        <article>
            <jsp:include page="${param.article }" />
        </article>
    </section>

    <footer>Footer</footer>
</body>
</html>