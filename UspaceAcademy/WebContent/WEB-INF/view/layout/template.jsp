<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!-- tiles 설정을 호출할 태그를 제공하는 태그 라이브러리. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	//버튼에 마우스 모양 손가락 써주기
	$("button").on("mouseover",function(){
		this.style.cursor = 'pointer';
	});
	$("input[type='button']").on("mouseover",function(){
		this.style.cursor = 'pointer';
	});
	$("input[type='submit']").on("mouseover",function(){
		this.style.cursor = 'pointer';
	});
	$("input[type='reset']").on("mouseover",function(){
		this.style.cursor = 'pointer';
	});
	$("input[type='checkbox']").on("mouseover",function(){
		this.style.cursor = 'pointer';
	});
	$("html").on("mouseover","button",function(){
		this.style.cursor = 'pointer';
	});
	
	
	
})

</script>

<style type="text/css">
header{
	min-width : 1080px;
	background-color:white;
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
	min-width : 1080px;
	color: #191919; /* 글자색거의모든 */ 
}
footer{
	font: 13px 맑은고딕;
	color: #8C8C8C;
	background-color:#F8F8F8;
	padding-top:20px;
	text-align:center;
	height:100px;
	min-width : 1080px;
	clear:both;
	text-align:center;
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


