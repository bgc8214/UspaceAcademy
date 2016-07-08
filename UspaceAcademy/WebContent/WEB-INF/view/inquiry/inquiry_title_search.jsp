<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table width="500" border='1'>
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
			<c:forEach items="${requestScope.selectByTitle}" var="list" >
				<tr>
					<td>${list.advancedNo }</td>
					<td><a href="/UspaceAcademy/inquiry/selectByAdvancedNo.do?advancedNo=${list.advancedNo }">${list.advancedTitle }</a></td>
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
			<a href="/UspaceAcademy/inquiry/selectByTitle.do?page=${requestScope.paging.beginPage - 1}&advancedTitle=${requestScope.advancedTitle}">
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
				<a href="/UspaceAcademy/inquiry/selectByTitle.do?page=${page }&advancedTitle=${requestScope.advancedTitle}">
					${page }
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/inquiry/selectByTitle.do?&page=${requestScope.paging.endPage + 1}&advancedTitle=${requestScope.advancedTitle}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
<p>

<a href="/UspaceAcademy/inquiry/inquiryList.do?advancedType=1:1문의">1:1문의 목록</a>