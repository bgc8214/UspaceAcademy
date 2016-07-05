<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
목록<br>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	for(var i=0;i<length(requestScope.inquiryList);i++){
		if(requestScope.lectureInquiryList[i].advancedSecret=='1'){	
			$("#secret").on("click", function() {
				alert("비밀글 입니다.");					
			});
		}
	}
});

</script>
<table border='1'>
	<thead>
		<tr>
			<td>글번호</td>			
			<td>제목</td>
			<td>글쓴이</td>
			<td>글 등록일</td>
			<td>조회수</td>
		</tr>
	</thead>
			
	<tbody>
		<form>
		<input id="page" type="hidden" value="${param.page }">
			<c:forEach items="${requestScope.lectureInquiryList}" var="list" varStatus="no">
				<tr>
<%-- 				<input type="hidden" value="${list.advancedNo }" name="advancedNo"> --%>
					<td>${list.advancedNo }</td>
					<td><a href="/UspaceAcademy/lectureInquiry/selectByAdvancedNoWithComment.do?advancedNo=${list.advancedNo }
						&advancedSecret=${list.advancedSecret}&lectureNo2=${list.lectureNo2 }">${list.advancedTitle }</a></td>
					<td>${list.advancedId }</td>
					<td>${list.advancedDate }</td>
					<td>${list.advancedHit }</td>
				</tr> 
	 		</c:forEach>
	 	</form>	
	</tbody>
</table>

<p>
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/inquiry/lectureInquiryList.do?page=${requestScope.paging.beginPage - 1}&lectureNo2=${requestScope.lectureNo2}">
			◀
			</a>
		</c:when>
		<c:otherwise>◀</c:otherwise>
	</c:choose>
	<%--페이지 처리 --%>
	<c:forEach begin="${requestScope.paging.beginPage }" end="${requestScope.paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == requestScope.paging.page }">
			 [${page }]
			</c:when>
			<c:otherwise>
				<a href="/UspaceAcademy/inquiry/lectureInquiryList.do?page=${page }&lectureNo2=${requestScope.lectureNo2}">
					${page }
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/inquiry/lectureInquiryList.do?page=${requestScope.paging.endPage + 1}&lectureNo2=${requestScope.lectureNo2}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
<p>

<!-- 검색관련 -->
<form action="/UspaceAcademy/lectureInquiry/searchByKeyword.do?page=${param.page }" method="post">
<input type="hidden" value="${requestScope.lectureNo2 }" name="lectureNo2">
<select name="searchType">
	<option value="advancedTitle">제목</option>
	<option value="advancedContent">내용</option>
	<!-- <option value="advancedId">글쓴이</option> -->
</select>
<input type="text" name="keyword">
<input type="submit" value="검색">
</form>

<br>

<a href="/UspaceAcademy/lectureInquiry/registerLectureInquiryForm.do?lectureNo2=${requestScope.lectureNo2 }">글 작성하기</a>
	