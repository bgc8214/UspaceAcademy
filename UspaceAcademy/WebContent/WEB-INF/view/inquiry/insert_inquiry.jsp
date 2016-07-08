<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
span.errors{
	color:red
}

</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jquery-ui.min.js"></script>

<link href="/UspaceAcademy/jQuery/jquery-ui.min.css" rel="stylesheet">
<link href="/UspaceAcademy/jQuery/jquery-ui.structure.min.css" rel="stylesheet">
<link href="/UspaceAcademy/jQuery/jquery-ui.theme.min.css" rel="stylesheet">

<script type="text/javascript">

$(document).ready(function(){
	//폼체크
	$("#insert").on("click", function(){
		if(!$("input[name=advancedTitle]").val()){
			alert("제목을 입력하세요!");
			
			return false;
		}
		if($("textarea[name=advancedContent]").val()==""){
			alert("내용을 입력하세요!");
			
			return false;
		}
	});
});

</script>


<form action="/UspaceAcademy/inquiry/registerInquiry.do" method="post">
<table border="1">
	<tr>
		<td>
			제목: <input type="text" name="advancedTitle" size="70" value="${requestScope.inquiryValidate.advancedTitle }">
				<span class="error"><form:errors path="inquiry.advancedTitle" delimiter="//" /></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label>공개글 : <input type="radio" name="advancedSecret" value="false" checked="checked"></label>
				<span class="error"><form:errors path="inquiry.advancedSecret" delimiter="//"/></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label >비밀글 : <input type="radio" name="advancedSecret" value="true"></label>
				<span class="error"><form:errors path="inquiry.advancedSecret" delimiter="//"/></span>

		</td>
	</tr>
	<tr>
		<td>
			내용: <textarea rows="20" cols="100" name="advancedContent">${requestScope.inquiryValidate.advancedTitle }</textarea>
				<span class="error"> <form:errors path="inquiry.advancedContent" delimiter="//" /></span>
			
		</td>
	</tr>
	<tr>
		<td><input id="insert" type="submit" value="등록"><input type="reset" value="초기화"></td>
	</tr>	
</table>
</form>

<a href="/UspaceAcademy/inquiry/inquiryList.do">전체 목록보기</a>