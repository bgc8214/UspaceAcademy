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

<table>
<c:forEach items="${requestScope.commentList}" var="list">
<%-- <input name="commentNO" type="hidden" value="${list.commentNo }"> --%>
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
						<a href="/UspaceAcademy/lectureInquiry/updateCommentForm.do?commentNo=${list.commentNo }
						&advancedNo2=${requestScope.lectureInquiryDetail.advancedNo}&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2}">
						<button>댓글 수정</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="/UspaceAcademy/lectureInquiry/deleteComment.do?commentNo=${list.commentNo }&advancedNo2=${requestScope.lectureInquiryDetail.advancedNo}
						&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2}"><button>댓글 삭제</button></a><br>
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
<form action="/UspaceAcademy/lectureInquiry/insertComment.do" >
<input type="hidden" value="${requestScope.lectureInquiryDetail.advancedNo}" name="advancedNo2">
<input type="hidden" value="${requestScope.lectureInquiryDetail.lectureNo2}" name="lectureNo2">
	<table>
		<tr>
			<td>
				<textarea id="commentContent" name="commentContent"></textarea>
				<input type="submit" value="댓글 입력">
			</td>
		</tr>
	</table>
</form>

<p>

<c:choose>
	<c:when test="${sessionScope.memberType=='student'}">
		<c:if test="${requestScope.lectureInquiryDetail.advancedId eq sessionScope.login_info.studentId}">
			<a href="/UspaceAcademy/lectureInquiry/updateLectureInquiryForm.do?advancedNo=${requestScope.lectureInquiryDetail.advancedNo }
			&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }">강의질문 수정</a>
			<a href="/UspaceAcademy/lectureInquiry/deleteLectureInquiry.do?advancedNo=${requestScope.lectureInquiryDetail.advancedNo }
			&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }">강의질문 삭제</a>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="${sessionScope.memberType=='administrator'}">
			<a href="/UspaceAcademy/lectureInquiry/deleteLectureInquiry.do?advancedNo=${requestScope.lectureInquiryDetail.advancedNo }
			&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }">강의질문 삭제</a>
		</c:if>
	</c:otherwise>
</c:choose>

<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }">전체 목록</a>