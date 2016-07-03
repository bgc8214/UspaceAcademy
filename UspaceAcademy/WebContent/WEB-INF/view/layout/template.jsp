<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!-- tiles 설정을 호출할 태그를 제공하는 태그 라이브러리. -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml-transitional.dtd">
<html xmlns="http://www.w3.org/1991/xhtml">
<head>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="/js/common.js"></script>
<link rel="stylesheet" type="text/css" href="js/common.js"/>
<link rel="stylesheet" href="css/common.css">
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
	min-width : 1080px;
}
footer{
	background-color:skyblue;
	padding-top:20px;
	text-align:center;
	height:50px;
	min-width : 1080px;
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


