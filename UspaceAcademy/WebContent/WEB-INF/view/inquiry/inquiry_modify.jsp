<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

$(document).ready(function(){
	//폼체크
	$("#modify").on("click", function(){
		if(!$("input[name=advancedTitle]").val()){
			alert("제목을 입력하세요!");
			
			return false;
		}
		if($("textarea[name=advancedContent]").val()==""){
			alert("내용을 입력하세요!");
			
			return false;
		}
	});
	
    $("#resetButton").click(function() {  
    	$("input[name=advancedTitle]").val('');
    	$("textarea[name=advancedContent]").val('');
    }); 
});

</script>

<form action="/UspaceAcademy/inquiry/updateInquiry.do" method="post" name="updateInquiry">
<input type="hidden" name="advancedNo" value="${requestScope.inquiryDetail.advancedNo }">

<table border="1">
	<tr>
		<td>
			제목: <input type="text" name="advancedTitle" size="70" value="${requestScope.inquiryDetail.advancedTitle }">
			<span class="error"> <form:errors path="updateInquiry.advancedTitle" delimiter="//" /></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<label>공개글 : <input type="radio" name="advancedSecret" value="false" checked="checked"></label>
			<span class="error"><form:errors path="updateInquiry.advancedSecret" delimiter="//"/></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	<label>비밀글 : <input type="radio" name="advancedSecret" value="true"></label>
			<span class="error"><form:errors path="updateInquiry.advancedSecret" delimiter="//"/></span>			
		</td>
	</tr>	
	<tr>
		<td>
			내용: <textarea rows="20" cols="100" name="advancedContent">${requestScope.inquiryDetail.advancedContent }</textarea>
			<span class="error"> <form:errors path="updateInquiry.advancedContent" delimiter="//" /></span>
		</td>
	</tr>
	<tr>
		<td align="center"><input id="modify" type="submit" value="등록"> <input id="resetButton" type="button" value="초기화"></td>
	</tr>	
</table>
</form>

<a href="/UspaceAcademy/inquiry/inquiryList.do">전체 목록보기</a>