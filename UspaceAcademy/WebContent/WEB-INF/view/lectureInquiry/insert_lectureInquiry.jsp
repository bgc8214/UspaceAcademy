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
			
/* 			var flag = false;
			$("input[type=radio]").each(function(index, item){
				
				if(item.checked==true){
					flag = true;
				}
			})
			if(!flag){
				alert("비밀글 여부를 선택하세요!");
				return false;
			}else{
				return true;
			} */
			
		});
	});
</script>
<h3 class="pageTlt">질문등록</h3>
<p>
<form action="/UspaceAcademy/lectureInquiry/registerLectureInquiry.do" method="post">
<input type="hidden" name="lectureNo2" value="${requestScope.lectureNo2 }">
<table class="table table-borered">
	<tr>
		<th>제목</th>
		<td><input type="text" name="advancedTitle" size="70" value="${requestScope.lectureInquiryValidate.advancedTitle }">
				<span class="error"><form:errors path="lectureInquiry.advancedTitle" delimiter="//" /></span></td>
		<td>
			<label>공개글<input type="radio" name="advancedSecret" value="false" checked="checked"></label> 	
				<span class="error"><form:errors path="lectureInquiry.advancedSecret" delimiter="//"/></span>
		</td>
		<td>
			<label >비밀글 : <input type="radio" name="advancedSecret" value="true"></label>
				<span class="error"><form:errors path="lectureInquiry.advancedSecret" delimiter="//"/></span>
		</td>
	</tr>
	<tr>
		<td colspan="6">
			<label>내용</label>
		</td>
	</tr>
	<tr>
		<td colspan="6">
			<textarea rows="20" cols="100" name="advancedContent" class="form-control">${requestScope.lectureInquiryValidate.advancedTitle }</textarea>
				<span class="error"> <form:errors path="lectureInquiry.advancedContent" delimiter="//" /></span>
		</td>
	</tr>
</table>
<div align="right">
<input id="insert" type="submit" value="등록" class="btn btn-success">&nbsp;&nbsp;&nbsp;<input type="reset" value="초기화" class="btn btn-default">
</div>
</form>

<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${requestScope.lectureNo2 }"><button class="btn btn-info">전체 목록보기</button></a>