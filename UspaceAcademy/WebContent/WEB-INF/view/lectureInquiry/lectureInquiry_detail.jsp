<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("#btn").on("click", function(){
		$.ajax({
			"url":"/UspaceAcademy/comment/exInsertComment.do",
			"type":"POST",
			"data":"commentContent="+$("#commentContent").val(),
			"dataType":"text",
			"success":function(txt){
/* 				$("#commentContent").val("");
				$("#commentContent").focus(); */
				$("#cmtTarget").html(txt);
			},
			"error":function(xhr, status, errorMsg){
				alert("오류가 발생했습니다. " + status + ", " + errorMsg);
			}
/* 			"beforeSend":function(){
				
			} */
		})
	})
})

</script>

<table>
	<tr>
		<td>
			no: ${requestScope.lectureInquiryDetail.advancedNo}<br> 글쓴이: ${requestScope.lectureInquiryDetail.advancedId}<br>글 등록일:${requestScope.lectureInquiryDetail.advancedDate}
			<br>조회수: ${requestScope.lectureInquiryDetail.advancedHit}
		</td>
	</tr>
	<tr>
		<td>
			제목: ${requestScope.lectureInquiryDetail.advancedTitle}
		</td>
	</tr>
	<tr>
		<td>
			내용: ${requestScope.lectureInquiryDetail.advancedContent}
		</td>
	</tr>
</table>

<p>
<%-- <c:forEach items="requestScope.inquiryComment" var="inquiryComment"> --%>
<!-- 	<a id="insertComment" href="/UspaceAcademy/inquiry/insertComment.do">댓글 쓰기</a>
	<textarea rows="" cols=""></textarea> -->
<%-- </c:forEach> --%>
<!-- <form method="post" action="/UspaceAcademy/inquiry/insertComment.do"> -->
<input type="text" id="commentContent"><br>
<input type="button" value="댓글 등록" id="btn">
<div id="cmtTarget"></div>
<!-- </form> -->
<p>
<a href="/UspaceAcademy/lectureInquiry/updateLIByAdvancedNo.do?advancedNo=${requestScope.lectureInquiryDetail.advancedNo }">강의질문하기 수정</a>
<a href="/UspaceAcademy/lectureInquiry/deleteLIByAdvancedNo.do?advancedNo=${requestScope.lectureInquiryDetail.advancedNo }">강의질문하기 삭제</a>
<a href="/UspaceAcademy/inquiry/inquiryList.do">1:1문의 목록</a>