<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
table#tx {
	width:450px;
}
</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">

//폼체크
$("#modify").on("click", function(){
	if($("textarea[name=commentContent]").val()==""){
		alert("내용을 입력하세요!");
		
		return false;
	}
});

</script>

<h3 class="pageTlt">상세보기</h3>
<hr>
<table class="table table-bordered form-table" id="tb">
	<tr>
		<th>No</th>
		<td>${requestScope.lectureInquiryDetail.advancedNo}</td>
		<th>글쓴이</th>
		<td>${requestScope.lectureInquiryDetail.advancedId}</td>
		<th>글 등록일</th>
		<td>${requestScope.lectureInquiryDetail.advancedDate}</td>
		<th>조회수</th>
		<td>${requestScope.lectureInquiryDetail.advancedHit}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td colspan="7">${requestScope.lectureInquiryDetail.advancedTitle}</td>
	</tr>
	<tr>
		<th>내용</th> 
		<td colspan="7">
			<textarea rows="5" cols="50" class="form-control" readonly="readonly">${requestScope.lectureInquiryDetail.advancedContent}</textarea>
		</td>
	</tr>
</table>
<div align="right">
	<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }"><button class="btn btn-primary">전체 목록</button></a>
</div>
<p>

<h3 class="pageTlt">댓글보기</h3>
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
		<td colspan="3"><textarea rows="5" cols="10" class="form-control" readonly="readonly">${list.commentContent}</textarea>
			
		</td>
	</tr>
</c:forEach>
</table>

<h3 class="pageTlt">댓글 수정</h3>
<hr>
<form action="/UspaceAcademy/lectureInquiry/updateComment.do" >
<input type="hidden" value="${requestScope.comment.commentNo}" name="commentNo">
<input type="hidden" value="${requestScope.lectureInquiryDetail.advancedNo}" name="advancedNo2">
<input type="hidden" value="${requestScope.lectureInquiryDetail.lectureNo2}" name="lectureNo2">

	<table>
		<tr>
			<td>
				<textarea id="commentContent" name="commentContent" cols="50" rows="5"></textarea> 
			</td>
		</tr>
	</table>
	<input id="modify" type="submit" value="댓글 입력" class="btn btn-success">
</form>

<p>

