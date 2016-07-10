<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!-- tiles 설정을 호출할 태그를 제공하는 태그 라이브러리. -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml-transitional.dtd">
<html xmlns="http://www.w3.org/1991/xhtml">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script src="/UspaceAcademy/js/common.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="/css/common.css"/> -->
<link href="/UspaceAcademy/css/common.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="/UspaceAcademy/jQuery/jquery.timepicker.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="/UspaceAcademy/js/common.js"/>
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
	padding-left : 250px;
	max-width : 1918px;
	text-align:center;
	height:38px;
}
 /*  
 nav{

		line-height: 50px;
		background-color:lightgray;
		padding: 15px;
		min-height:700px;
		width:250px;
		float:left;
	}   */
section{
	padding-left: 400px;
	min-height:722px;
	max-width : 1000px;
	float:left;
	max-width : 1918px;
	color: #191919; /* 글자색거의모든 */ 
}
footer{
	font: 13px 맑은고딕  bold;
	color: #8C8C8C;
	background-color:yellow;
 	padding-top:70px; 
	text-align:center;
	height:100px;
	max-width : 1918px;
	clear:both;
	text-align:center;
}
</style>
</head>
<body>

	<header>
		<tiles:insertAttribute name="header"/> <!-- name속성에 put-attribute 태그의 name 속성값을 호출. -->
	</header>
	
	<nav>
	<%-- 	<tiles:insertAttribute name="nav"/> --%>
	</nav>

	<section>
		<tiles:insertAttribute name="body"/>
	</section>
	
	<footer>
		<tiles:insertAttribute name="footer"/>
	</footer>
</body>
</html>


