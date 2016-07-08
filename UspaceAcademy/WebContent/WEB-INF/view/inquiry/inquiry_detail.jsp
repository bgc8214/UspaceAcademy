<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	
$(document).ready(effect);
function effect(){
	$("tr:eq(2)").css("background-color", "palegreen");
}

$(document).ready(function(){
	$("#insert").on("click", function(){
		if($("textarea[name=commentContent]").val()==""){
			alert("검색할 내용을 입력하세요!");
			
			return false;
		}
	});
});

</script>

<h2 class="pageTlt">상세보기</h2>

<table border="" class="table table-bordered form-table">
	<tr>
		<td>
			<%-- no: ${requestScope.inquiryDetail.advancedNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; --%>
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
			내용<br><br>
			 ${requestScope.inquiryDetail.advancedContent}
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
			내용<br>
			${list.commentContent}<br><br>
			
			<c:choose>
				<c:when test="${sessionScope.memberType=='student'}">
					<c:if test="${list.commentWriter eq sessionScope.login_info.studentId}">
						<a href="/UspaceAcademy/inquiry/updateCommentForm.do?commentNo=${list.commentNo }
						&advancedNo2=${requestScope.inquiryDetail.advancedNo}">
						<button>댓글 수정</button></a>
						<a href="/UspaceAcademy/inquiry/deleteComment.do?commentNo=${list.commentNo }
						&advancedNo2=${requestScope.inquiryDetail.advancedNo}"><button>댓글 삭제</button></a><br>
					</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${sessionScope.memberType=='administrator'}">
						<a href="/UspaceAcademy/inquiry/deleteComment.do?commentNo=${list.commentNo }
						&advancedNo2=${requestScope.inquiryDetail.advancedNo}"><button>댓글 삭제</button></a>
					</c:if>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>
</table>

<h4 align="center">댓글 작성</h4>
<form action="/UspaceAcademy/inquiry/insertComment.do" >
<input type="hidden" value="${requestScope.inquiryDetail.advancedNo}" name="advancedNo2">
	<table>
		<tr>
			<td>
				<textarea id="commentContent" name="commentContent" cols="50" rows="5"></textarea>
				<input id="insert" type="submit" value="댓글 입력">
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

<a href="/UspaceAcademy/inquiry/inquiryList.do">전체 목록</a>