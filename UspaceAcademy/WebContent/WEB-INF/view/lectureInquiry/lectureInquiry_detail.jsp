<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
table#tx {
	width:500px;
}
</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#insert").on("click", function(){
		if($("textarea[name=commentContent]").val()==""){
			alert("댓글 내용을 입력하세요!");
			
			return false;
		}
	});
});
</script>

<h3 class="pageTlt">상세보기</h3>
<hr>
<table class="table table-bordered form-table">
	<tr>
		<th>No </th>
		<td>${requestScope.lectureInquiryDetail.advancedNo}</td>
		<th>등록일</th>
		<td>${requestScope.lectureInquiryDetail.advancedDate}</td>
		<th>조회수</th><td> ${requestScope.lectureInquiryDetail.advancedHit}</td>
	</tr>	
	<tr>
		<th>제목</th>
		<td colspan="3">${requestScope.lectureInquiryDetail.advancedId}</td>	
		<th>글쓴이</th>
		<td>${requestScope.lectureInquiryDetail.advancedId}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="5">
			<textarea rows="20" cols="100" class="form-control" readonly="readonly">${requestScope.lectureInquiryDetail.advancedContent}</textarea>
		</td>
	</tr>
</table>
<p>
<div align="left">
	<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }"><button class="btn btn-info">전체 목록</button></a>
</div>

<!--  -->
<c:choose>
	<c:when test="${sessionScope.memberType=='student'}">
		<c:if test="${requestScope.lectureInquiryDetail.advancedId eq sessionScope.login_info.studentId}">
		<div align="right">
			<a href="/UspaceAcademy/lectureInquiry/updateLectureInquiryForm.do?advancedNo=${requestScope.lectureInquiryDetail.advancedNo }
			&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }"><button class="btn btn-warning">글 수정</button></a>&nbsp;&nbsp;&nbsp;
			<a href="/UspaceAcademy/lectureInquiry/deleteLectureInquiry.do?advancedNo=${requestScope.lectureInquiryDetail.advancedNo }
			&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }"><button class="btn btn-danger">글 삭제</button></a>&nbsp;&nbsp;&nbsp;
		</div>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="${sessionScope.memberType=='administrator'}">
			<div align="right">
			<a href="/UspaceAcademy/lectureInquiry/deleteLectureInquiry.do?advancedNo=${requestScope.lectureInquiryDetail.advancedNo }
			&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }"><button class="btn btn-danger">글 삭제</button></a>&nbsp;&nbsp;&nbsp;
			</div>
		</c:if>
	</c:otherwise>
</c:choose>
<p>


<h4 class="pageTlt">댓글 보기</h4>
<hr>
<table class="table table-bordered form-table" id="tx">
<c:forEach items="${requestScope.commentList}" var="list">
	<tr>
		<th>글쓴이</th>
		<td>${list.commentWriter}</td>
		<th>글 등록일</th>
		<td>${list.commentDate}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="4"><textarea rows="5" cols="30" class="form-control" readonly="readonly">${list.commentContent}</textarea></td>
	</tr>
	<tr>
		<td colspan="5">
			<div align="center">
			<c:choose>
				<c:when test="${sessionScope.memberType=='student'}">
					<c:if test="${list.commentWriter eq sessionScope.login_info.studentId}">
						<a href="/UspaceAcademy/lectureInquiry/updateCommentForm.do?commentNo=${list.commentNo }
						&advancedNo2=${requestScope.lectureInquiryDetail.advancedNo}&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2}">
						<button class="btn btn-warning">댓글 수정</button></a>
						<a href="/UspaceAcademy/lectureInquiry/deleteComment.do?commentNo=${list.commentNo }&advancedNo2=${requestScope.lectureInquiryDetail.advancedNo}
						&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2}"><button class="btn btn-danger">댓글 삭제</button></a><br>
					</c:if>
				</c:when>
				
				<c:when test="${sessionScope.memberType=='teacher'}">
					<c:if test="${list.commentWriter eq sessionScope.login_info.teacherId}">
						<a href="/UspaceAcademy/lectureInquiry/updateCommentForm.do?commentNo=${list.commentNo }
						&advancedNo2=${requestScope.lectureInquiryDetail.advancedNo}&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2}">
						<button class="btn btn-warning">댓글 수정</button></a>
						<a href="/UspaceAcademy/lectureInquiry/deleteComment.do?commentNo=${list.commentNo }&advancedNo2=${requestScope.lectureInquiryDetail.advancedNo}
						&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2}"><button class="btn btn-danger">댓글 삭제</button></a><br>
					</c:if>
				</c:when>
				
				<c:otherwise>
					<c:if test="${sessionScope.memberType=='administrator'}">
						<a href="/UspaceAcademy/lectureInquiry/updateCommentForm.do?commentNo=${list.commentNo }
						&advancedNo2=${requestScope.lectureInquiryDetail.advancedNo}&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2}">
						<button class="btn btn-warning">댓글 수정</button></a>
						<a href="/UspaceAcademy/inquiry/deleteComment.do?commentNo=${list.commentNo }&advancedNo2=${requestScope.lectureInquiryDetail.advancedNo}
						&lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2}"><button class="btn btn-danger">댓글 삭제</button></a>
					</c:if>
				</c:otherwise>
			</c:choose>
			</div>
		</td>
	</tr>
</c:forEach>
</table>
<p>

<h3 class="pageTlt">댓글작성</h3>
<form action="/UspaceAcademy/lectureInquiry/insertComment.do" >
<input type="hidden" value="${requestScope.lectureInquiryDetail.advancedNo}" name="advancedNo2">
<input type="hidden" value="${requestScope.lectureInquiryDetail.lectureNo2}" name="lectureNo2">
	<table>
		<tr>
			<td>
				<textarea id="commentContent" name="commentContent" cols="50" rows="5" class="form-control"></textarea>
				<input id="insert" type="submit" value="댓글 입력" class="btn btn-success">
			</td>
		</tr>
	</table>
</form>

<p>


