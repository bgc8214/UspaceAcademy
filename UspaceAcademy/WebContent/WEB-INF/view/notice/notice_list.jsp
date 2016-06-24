<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<hr>
	<table border="2">
		<thead>
			<tr>
				<td>No</td>
				<td>공지제목</td>
				<td>등록일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>	
			<c:forEach var="notice" items="${requestScope.noticeList}">
				<tr id="tb">
					<td>${notice.basicNo}</td>
					<td><a href="/UspaceAcademy/notice/noticeDetail.do?no=${notice.basicNo}&page=${page}">${notice.basicTitle}</a></td>
					<td>${notice.basicDate}</td>
					<td>${notice.basicHit}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table><p>
	


<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/notice/list.do?page=${requestScope.paging.beginPage-1}&type=공지사항">
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
				<a href="/UspaceAcademy/notice/list.do?page=${page}&type=공지사항">
					${page }
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/notice/list.do?&page=${requestScope.paging.endPage + 1}&type=공지사항">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
<p>


<!-- 관리자용 공지사항 등록 버튼 -->
<span class="lectureRegister">
	<c:if test="${sessionScope.memberType=='administrator'}">
		<a href="/UspaceAcademy/notice/codeList.do?codeNames=공지사항"><input type="button" value="공지사항등록"></a>
	</c:if>
</span>
	
	
	
	

