<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<h2 align="center">댓글 보기</h2>

<table>
<c:forEach items="${requestScope.commentList}" var="list">
	<tr>
		<td>
			글쓴이: ${list.commentWriter}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			글 등록일:${list.commentDate}<br>
			내용<br> ${list.commentContent}<br><br>
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
			<input type="submit" value="입력">
		</td>
	</tr>
	<tr>	
		<td>
			<a href="/UspaceAcademy/inquiry/updateComment.do"><button>댓글 수정</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="/UspaceAcademy/inquiry/deleteComment.do"><button>댓글 삭제</button></a>
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

