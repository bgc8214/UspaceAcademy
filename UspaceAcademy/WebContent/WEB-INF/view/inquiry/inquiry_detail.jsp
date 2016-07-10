<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
table#tb {
	width: 700px;
	heiht: 100px;
}
</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$("#insert").on("click", function(){
		if($("textarea[name=commentContent]").val()==""){
			alert("검색할 내용을 입력하세요!");
			
			return false;
		}
	});
});

</script>

<h3 class="pageTlt">상세보기</h3>
<hr>
<table class="table table-bordered form-table" id="tb">
	<tr>
		<th>글쓴이</th>
		<td>${requestScope.inquiryDetail.advancedId}</td>
		<th>글 등록일</th>
		<td>${requestScope.inquiryDetail.advancedDate}</td>
		<th>조회수</th>
		<td>${requestScope.inquiryDetail.advancedHit}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td colspan="6"> ${requestScope.inquiryDetail.advancedTitle}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="6"><textarea rows="20" cols="100" readonly="readonly" class="form-control">${requestScope.inquiryDetail.advancedContent}</textarea>
		</td>
	</tr>
</table>
<p>
<div align="right">
<a href="/UspaceAcademy/inquiry/inquiryList.do"><button class="btn btn-primary">전체 목록</button></a>
</div>
<p>

<h3 class="pageTlt">댓글 보기</h3>
<hr>
<table class="table table-bordered form-table">
<c:forEach items="${requestScope.commentList}" var="list">
<%-- <input name="commentNO" type="hidden" value="${list.commentNo }"> --%>
	<tr>
		<th>글쓴이</th>
		<td>${list.commentWriter}</td>
		<th>글 등록일</th>
		<td>${list.commentDate}</td>
	</tr>	
	<tr>
		<th>내용</th>
		<td colspan="3"><textarea rows="5" cols="50" readonly="readonly" class="form-control">${list.commentContent}</textarea>
			</td>
	</tr>
	<tr>
		<td colspan="6">		
			<c:choose>
				<c:when test="${sessionScope.memberType=='student'}">
					<c:if test="${list.commentWriter eq sessionScope.login_info.studentId}">
						<a href="/UspaceAcademy/inquiry/updateCommentForm.do?commentNo=${list.commentNo }
						&advancedNo2=${requestScope.inquiryDetail.advancedNo}">
						<button class="btn btn-warning">댓글 수정</button></a>
						<a href="/UspaceAcademy/inquiry/deleteComment.do?commentNo=${list.commentNo }
						&advancedNo2=${requestScope.inquiryDetail.advancedNo}"><button class="btn btn-danger">댓글 삭제</button></a><br>
					</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${sessionScope.memberType=='administrator'}">
						<a href="/UspaceAcademy/inquiry/deleteComment.do?commentNo=${list.commentNo }
						&advancedNo2=${requestScope.inquiryDetail.advancedNo}"><button class="btn btn-danger">댓글 삭제</button></a>
					</c:if>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>
</table>

<h3 class="pageTlt">댓글 작성</h3>
<hr>
<form action="/UspaceAcademy/inquiry/insertComment.do" >
<input type="hidden" value="${requestScope.inquiryDetail.advancedNo}" name="advancedNo2">
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

<c:choose>
	<c:when test="${sessionScope.memberType=='student'}">
		<c:if test="${requestScope.inquiryDetail.advancedId eq sessionScope.login_info.studentId}">
			<a href="/UspaceAcademy/inquiry/updateInquiryForm.do?advancedNo=${requestScope.inquiryDetail.advancedNo }">글 수정</a>&nbsp;&nbsp;&nbsp;
			<a href="/UspaceAcademy/inquiry/deleteInquiry.do?advancedNo=${requestScope.inquiryDetail.advancedNo }">글 삭제</a>&nbsp;&nbsp;&nbsp;
		</c:if>
	</c:when>
	<c:otherwise>
		<c:if test="${sessionScope.memberType=='administrator'}">
			<a href="/UspaceAcademy/inquiry/deleteInquiry.do?advancedNo=${requestScope.inquiryDetail.advancedNo }">글 삭제</a>&nbsp;&nbsp;&nbsp;
		</c:if>
	</c:otherwise>
</c:choose>

