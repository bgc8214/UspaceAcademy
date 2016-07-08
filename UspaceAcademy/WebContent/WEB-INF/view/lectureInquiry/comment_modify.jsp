<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	
$(document).ready(effect);
function effect(){
	$("tr:eq(2)").css("background-color", "palegreen");
	$("tr:eq(3)").css("background-color", "sky-blue");
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
			no: ${requestScope.lectureInquiryDetail.advancedNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			글쓴이: ${requestScope.lectureInquiryDetail.advancedId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			글 등록일:${requestScope.lectureInquiryDetail.advancedDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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

<h4 class="pageTlt">댓글보기</h4>

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
<form action="/UspaceAcademy/lectureInquiry/updateComment.do" >
<input type="hidden" value="${requestScope.comment.commentNo}" name="commentNo">
<input type="hidden" value="${requestScope.lectureInquiryDetail.advancedNo}" name="advancedNo2">
<input type="hidden" value="${requestScope.lectureInquiryDetail.lectureNo2}" name="lectureNo2">

	<table>
		<tr>
			<td>
				<textarea id="commentContent" name="commentContent" cols="50" rows="5"></textarea>
				<input id="modify" type="submit" value="댓글 입력">
			</td>
		</tr>
	</table>
</form>

<p>

<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }">전체 목록</a>