<%-- <%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
var tmp;
$(document).ready(function(){
	$(".inquiryList").on("click", function(){
		tmp = $(this);
		$.ajax({
			"url":"/UspaceAcademy/comment/exInsertComment.do",
			"type":"POST",
			"data":"commentNo="+$("#commentNo").val(),
			"dataType":"text",
			"success":function(inquiry){
/* 				$("#commentContent").val("");
				$("#commentContent").focus(); 
				$("#cmtTarget").html(inquiry);*/
				
				var txt = "번호 : "+inquiry[0].advancedNo +" 제목 : "+inquiry[0].advancedTitle+"<br>";
				//if(tmp.next().children().eq(0).text()==""){
					tmp.next().children().eq(0).append(txt);
					if(inquiry[1]=="inquiry"){
						var txt = tmp.children().eq(0).text();
						var temp =
						tmp.next().children().eq(0).append($(temp));
					}
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
		<td>no: ${requestScope.inquiryDetail.advancedNo}<br> 글쓴이: ${requestScope.inquiryDetail.advancedId}<br>글 등록일:${requestScope.inquiryDetail.advancedDate}
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
			내용: ${requestScope.inquiryDetail.advancedContent}
		</td>
	</tr>
</table>

<p>
<c:forEach items="requestScope.inquiryComment" var="inquiryComment">
<!-- 	<a id="insertComment" href="/UspaceAcademy/inquiry/insertComment.do">댓글 쓰기</a>
	<textarea rows="" cols=""></textarea> -->
</c:forEach>
<!-- <form method="post" action="/UspaceAcademy/inquiry/insertComment.do"> -->
<input type="text" id="commentContent"><br>
<input type="button" value="댓글 등록" id="btn">
<div id="cmtTarget"></div>
<!-- </form> -->
<p>
<a href="/UspaceAcademy/inquiry/updateByAdvancedNo.do?advancedNo=${requestScope.inquiryDetail.advancedNo }">1:1문의 수정</a>
<a href="/UspaceAcademy/inquiry/deleteByAdvancedNo.do?advancedNo=${requestScope.inquiryDetail.advancedNo }">1:1문의 삭제</a>
<a href="/UspaceAcademy/inquiry/inquiryList.do">1:1문의 목록</a>
 --%>