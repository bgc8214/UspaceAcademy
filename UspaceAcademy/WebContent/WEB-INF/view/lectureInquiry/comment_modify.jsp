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

<table border="1">
	<tr>
		<td>
			no: ${requestScope.lectureInquiryDetail.advancedNo}<br>
			글쓴이: ${requestScope.lectureInquiryDetail.advancedId}<br>
			글 등록일:${requestScope.lectureInquiryDetail.advancedDate}<br>
			조회수: ${requestScope.lectureInquiryDetail.advancedHit}
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

<h2 align="center">댓글 수정</h2>
<form action="/UspaceAcademy/lectureInquiry/updateComment.do" >
<input type="hidden" value="${requestScope.lectureInquiryDetail.advancedNo}" name="advancedNo2">
<input type="hidden" value="${requestScope.lectureInquiryDetail.lectureNo2}" name="lectureNo2">
<input type="hidden" value="${requestScope.comment.commentNo }" name="commentNo">
	<table>
		<tr>
			<td>			
				<textarea name="commentContent" value="${requestScope.comment.commentContent }"></textarea>
				<input type="submit" value="수정">
			</td>
		</tr>
	</table>
</form>

<p>

<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }">전체 목록</a>