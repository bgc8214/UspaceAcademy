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

<table border="" class="table table-bordered form-table" id="tb">
	<tr>
		<th>No</th>
		<td>${requestScope.inquiryDetail.advancedNo}</td>
		<th>글쓴이</th>
		<td>${requestScope.inquiryDetail.advancedId}</td>
		<th>글 등록일</th>
		<td>${requestScope.inquiryDetail.advancedDate}</td>
		<th>조회수</th>
		<td>${requestScope.inquiryDetail.advancedHit}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td colspan="7">${requestScope.inquiryDetail.advancedTitle}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="7"><textarea rows="20" cols="100" class="form-control" readonly="readonly">${requestScope.inquiryDetail.advancedContent}</textarea>
		</td>
	</tr>
</table>

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
	<td colspan="3"><textarea rows="5" cols="50" readonly="readonly" class="form-control">${list.commentContent}</textarea></td>
	</tr>
</c:forEach>
</table>

<h3 class="pageTlt">댓글 수정</h3>
<hr>
<form action="/UspaceAcademy/inquiry/updateComment.do" method="post">
<input type="hidden" value="${requestScope.comment.commentNo}" name="commentNo">
<input type="hidden" value="${requestScope.inquiryDetail.advancedNo}" name="advancedNo2">

	<table>
		<tr>
			<td>
				<textarea id="commentContent" name="commentContent" cols="50" rows="5">${requestScope.comment.commentContent }</textarea>
			</td>
		</tr>
	</table>
<input id="modify" type="submit" value="댓글 수정" class="btn btn-warning">
</form>

<p>

<a href="/UspaceAcademy/inquiry/inquiryList.do">전체 목록</a>