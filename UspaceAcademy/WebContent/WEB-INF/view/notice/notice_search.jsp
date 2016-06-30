<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	$(document).ready(effect);
	function effect() {
		$("tr:eq(2)").css("background-color", "#EAEAEA");
	}
</script>
<hr>
	<table border="2" width="600">
		<thead>
			<tr>
				<th>No</th>
				<th>공지사항제목</th>
				<th>내용</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${requestScope.noticeSearch.size() != 0}">
			<c:forEach var="notice" items="${requestScope.noticeSearch}">
				<tr class="noticeList">
					<td>${notice.basicNo}</td>
					<td><a href="/UspaceAcademy/notice/noticeDetail.do?no=${notice.basicNo}&page=${page}&keyword=${requestScope.keyword}">${notice.basicTitle}</a></td>
					<td>${notice.basicContent}</td>
					<td>${notice.basicDate}</td>
					<td>${notice.basicHit}</td>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${requestScope.noticeSearch.size() ==0}">
				<tr class="faqList">
					<td align="center">
						<td align="center">조회된 결과값이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table><p>
	
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/notice/noticeSearch.do?page=${requestScope.paging.beginPage-1}&keyword=${requestScope.keyword}">
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
				<a href="/UspaceAcademy/notice/noticeSearch.do?page=${page}&keyword=${requestScope.keyword}">
					${page }
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/notice/noticeSearch.do??&page=${requestScope.paging.endPage + 1}&keyword=${requestScope.keyword}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
<p>

<a href="/UspaceAcademy/notice/list.do?type=${requestScope.noticeSearch[0].basicType}"><button>공지사항리스트</button></a>