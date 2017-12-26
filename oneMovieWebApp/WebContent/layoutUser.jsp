<%@ page contentType="text/html; charset=EUC-KR"%>

<!doctype html>
<html lang="en">
 <head>
    <meta charset="UTF-8">
    <title>2�� ���̾ƿ�</title>
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
        
        
        footer {
            width: 960px;
            height: 100px;
            margin-left: auto;
            margin-right: auto;
            background-color: deepskyblue;
            overflow: hidden;
        }
    </style>
</head>

<body>
    <header>
       <h1><a href="#">One Movie</a></h1>
        <nav>
            <a href="${pageContext.request.contextPath }/memberLoginForm.do">�α���</a>
            <a href="${pageContext.request.contextPath }/joinForm.do">ȸ������</a>     
        </nav>
    </header>
    <section>
        <nav>
            <ul>
                <li><a href="#">��ȭ</a></li>
                <ul>
                    <li><a href="#">���� ����</a></li>
                    <li><a href="#">���� ����</a></li>
                    <li><a href="#">�� ������</a></li>
                </ul>
                <li><a href="#">����</a></li>
                <ul>

                </ul>
                <li><a href="#">����</a></li>
                <ul>
                    <li><a href="#">��ȭ�� ����</a></li>
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
    