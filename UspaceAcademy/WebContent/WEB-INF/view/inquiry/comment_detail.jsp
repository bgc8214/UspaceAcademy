<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

/*
	파일명이 잘못됐지만 이거 댓글 수정 jsp입니다.
*/


<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	
$(document).ready(effect);
function effect(){
	$("tr:eq(2)").css("background-color", "palegreen");
}

//폼체크
$("#modify").on("click", function(){
	if($("textarea[name=commentContent]").val()==""){
		alert("내용을 입력하세요!");
		
		return false;
	}
});

</script>

<h2 class="pageTlt">상세보기</h2>

<table border="" class="table table-bordered form-table">
	<tr>
		<td>
			no: ${requestScope.inquiryDetail.advancedNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			글쓴이: ${requestScope.inquiryDetail.advancedId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			글 등록일:${requestScope.inquiryDetail.advancedDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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

<h4 class="pageTlt">댓글 보기</h4>

<table class="table table-bordered form-table">
<c:forEach items="${requestScope.commentList}" var="list">
<%-- <input name="commentNO" type="hidden" value="${list.commentNo }"> --%>
	<tr>
		<td>
			<%-- 댓글 번호: ${list.commentNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --%>
			글쓴이: ${list.commentWriter}&nbsp;&nbsp;&nbsp;
			글 등록일:${list.commentDate}<br>
			내용:
			${list.commentContent}<br><br>
		</td>
	</tr>
</c:forEach>
</table>

<h4 class="pageTlt">댓글 수정</h4>
<form action="/UspaceAcademy/inquiry/updateComment.do" >
<input type="hidden" value="${requestScope.comment.commentNo}" name="commentNo">
<input type="hidden" value="${requestScope.inquiryDetail.advancedNo}" name="advancedNo2">

	<table>
		<tr>
			<td>
				<textarea id="commentContent" name="commentContent" cols="50" rows="5">${requestScope.comment.commentContent }</textarea>
				<input id="modify" type="submit" value="댓글 입력">
			</td>
		</tr>
	</table>
</form>

<p>

<a href="/UspaceAcademy/inquiry/inquiryList.do">전체 목록</a>