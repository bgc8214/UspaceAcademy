<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
var tmp;
$(document).ready(function(){
 	$("#insertComment").on("click", function(){
		$.ajax({
			"url":"/UspaceAcademy/inquiry/insertComment.do",
			"type":"POST",
			"data":{"commentContent":$("#commentContent").val()},
			"dataType":"text", 
			"success":function(commentContent){
/*  				$("#commentContent").val("");
				$("#commentContent").focus(); 
				$("#cmtTarget").html(inquiry);
				
 				var txt = comment[0].commentContent + "<br>";
				//if(tmp.next().children().eq(0).text()==""){
					tmp.next().children().eq(0).append(txt);
					if(inquiry[1]=="inquiry"){
						var txt = tmp.children().eq(0).text();
						var temp =
						tmp.next().children().eq(0).append($(temp));
					} */
					var txt = "commentContent: " + commentContent;
					$("#cmtTarget").html(txt);
/* 					tmp.next().children().eq(0).append($(temp)); */
			},
			"error":function(xhr, status, errorMsg){
				alert("오류가 발생했습니다. " + status + ", " + errorMsg);
			}
/*  			"beforeSend":function(){
				
			}  */
 		});
	});
});

</script>

<table>
	<tr>
		<td>
			no: ${requestScope.inquiryDetail.advancedNo}<br>
			글쓴이: ${requestScope.inquiryDetail.advancedId}<br>
			글 등록일:${requestScope.inquiryDetail.advancedDate}<br>
			조회수: ${requestScope.inquiryDetail.advancedHit}
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

<h2 align="center">댓글 보기</h2>
<%-- <input type="hidden" id="commentDetail" name="commentDetail" value="${requestScope.CommentDetail}"> --%>

<table>
<c:forEach items="${requestScope.commentList}" var="list">
<input name="commentNO" type="hidden" value="${list.commentNo }">
	<tr>
		<td>
			댓글 번호: ${list.commentNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			글쓴이: ${list.commentWriter}<br>
			글 등록일:${list.commentDate}<br>
			내용<br>
			${list.commentContent}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			
			<c:choose>
				<c:when test="${sessionScope.memberType=='student'}">
					<c:if test="${list.commentWriter eq sessionScope.login_info.studentId}">
						<a href="/UspaceAcademy/inquiry/updateComment.do"><button>댓글 수정</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/UspaceAcademy/inquiry/deleteComment.do?commentNo=${list.commentNo }&advancedNo=${requestScope.inquiryDetail.advancedNo}"><button>댓글 삭제</button></a><br>
					</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${sessionScope.memberType=='administrator'}">
						<a href="/UspaceAcademy/inquiry/deleteComment.do"><button>댓글 삭제</button></a>
					</c:if>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>
</table>


<h2 align="center">댓글 작성</h2>
<form action="/UspaceAcademy/inquiry/insertComment.do" >
<input type="hidden" value="${requestScope.inquiryDetail.advancedNo}" name="advancedNo">
<input type="hidden" value="${requestScope.inquiryDetail.advancedId}" name="commentWriter">
<table>
	<tr>
		<td>
			<textarea id="commentContent" name="commentContent"></textarea>
			<input type="submit" value="댓글 입력">
		</td>
	</tr>

</table>
</form>

<!-- </form> -->
<p>

<c:choose>
	<c:when test="${sessionScope.memberType=='student'}">
		<c:if test="${requestScope.inquiryDetail.advancedId eq sessionScope.login_info.studentId}">
			<a href="/UspaceAcademy/inquiry/updateByAdvancedNo.do?advancedNo=${requestScope.inquiryDetail.advancedNo }&advancedType=1:1문의">1:1문의 수정</a>
			<a href="/UspaceAcademy/inquiry/deleteByAdvancedNo.do?advancedNo=${requestScope.inquiryDetail.advancedNo }&advancedType=1:1문의">1:1문의 삭제</a>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="${sessionScope.memberType=='administrator'}">
			<a href="/UspaceAcademy/inquiry/deleteByAdvancedNo.do?advancedNo=${requestScope.inquiryDetail.advancedNo }&advancedType=1:1문의">1:1문의 삭제</a>
		</c:if>
	</c:otherwise>
</c:choose>
<a href="/UspaceAcademy/inquiry/inquiryList.do?advancedType=1:1문의">1:1문의 목록</a>

