<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$(".inquiryComment").on("click", function(){
		$.ajax({
			"url":"/UspaceAcademy/inquiry/insertComment.do",
			"type":"POST",
			"data":"commentNo",
			"dataType":"json",
			"success":function(){
				
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
			no: ${requestScope.inquiryDetail.advancedNo}<br> 글쓴이: ${requestScope.inquiryDetail.advancedId}<br>글 등록일:${requestScope.inquiryDetail.advancedDate}
			<br>조회수: ${requestScope.inquiryDetail.advancedHit}
		</td>
	</tr>
	<tr>
		<td>
			제목: ${requestScope.inquiryDetail.advancedTitle}
		</td>
	</tr>
	<tr>
		<td>
			내용: ${requestScope.inquiryDetail.advancedId}
		</td>
	</tr>
</table>

<p>
<c:forEach items="requestScope.inquiryComment" var="inquiryComment">
	<a href="/UspaceAcademy/inquiry/insertComment.do">댓글 쓰기</a>
</c:forEach>

<p>
<a href="/UspaceAcademy/inquiry/updateByAdvancedNo.do?advancedNo=${requestScope.inquiryDetail.advancedNo }">1:1문의 수정</a>
<a href="/UspaceAcademy/inquiry/deleteByAdvancedNo.do?advancedNo=${requestScope.inquiryDetail.advancedNo }">1:1문의 삭제</a>
<a href="/UspaceAcademy/inquiry/inquiryList.do">1:1문의 목록</a>
