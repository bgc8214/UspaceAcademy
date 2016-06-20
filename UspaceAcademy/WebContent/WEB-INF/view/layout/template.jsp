<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!-- tiles 설정을 호출할 태그를 제공하는 태그 라이브러리. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
header{
	background-color:skyblue;
	text-align:center;
	padding: 5px;
}
/* nav{
	line-height: 50px;
	background-color:lightgray;
	padding: 15px;
	min-height:700px;
	width:250px;
	float:left;
} */
section{
	padding: 15px;
	min-height:400px;
	float:left;
}
footer{
	background-color:skyblue;
	padding-top:20px;
	text-align:center;
	height:50px;
	clear:both;
}
</style>
</head>
<body>

	<header>
		<tiles:insertAttribute name="header"/> <!-- name속성에 put-attribute 태그의 name 속성값을 호출. -->
	</header>
	
	<%-- <nav>
		<tiles:insertAttribute name="nav"/>
	</nav>
	 --%>
	<section>
		<tiles:insertAttribute name="body"/>
	</section>
	
	<footer>
		<tiles:insertAttribute name="footer"/>
	</footer>
</body>
</html>


