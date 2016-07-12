<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
table#tb {
	width: 700px;
	heiht: 100px;
}
</style>

<h2>1:1문의 게시판</h2><br>

<table  class="table table-bordered" id="tb">
	<thead>
		<tr>
			<!-- <td>글번호</td> -->			
			<th>제목</th>
			<th>글쓴이</th>
			<th>글 등록일</th>
			<th>조회수</th>
		</tr>
	</thead>	
	<tbody>
		<input id="page" type="hidden" value="${param.page }">
			<c:forEach items="${requestScope.inquiryList}" var="list">
				<input type="hidden" id="secret" value="${list.advancedSecret}">
				<tr>
					<%-- <td>${list.advancedNo }</td> --%>
					<td>
						<c:choose>
						<c:when test="${list.advancedSecret}">
								<a id="detail" href="/UspaceAcademy/inquiry/selectByAdvancedNoWithComment.do?advancedNo=${list.advancedNo }
								&advancedSecret=${list.advancedSecret}" onclick="alert('비밀글 입니다.');">${list.advancedTitle } 
								<img width="15" height="15" src="/UspaceAcademy/image/lock.jpg"></a>
						</c:when>
						<c:otherwise>
							<a id="detail" href="/UspaceAcademy/inquiry/selectByAdvancedNoWithComment.do?advancedNo=${list.advancedNo }
						&advancedSecret=${list.advancedSecret}">${list.advancedTitle }</a>
						</c:otherwise>
						</c:choose>
					</td>
					<td>${list.advancedId }</td>
					<td>${list.advancedDate }</td>
					<td>${list.advancedHit }</td>
				</tr> 
	 		</c:forEach>
	</tbody>
	
</table>

<p>

<div align="center">
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/inquiry/searchByKeyword.do?page=${requestScope.paging.beginPage - 1}&searchType=${requestScope.searchType}&keyword=${requestScope.keyword}">
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
				<a href="/UspaceAcademy/inquiry/searchByKeyword.do?page=${page }&searchType=${requestScope.searchType}&keyword=${requestScope.keyword}">
					${page }
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/inquiry/searchByKeyword.do?page=${requestScope.paging.endPage + 1}&searchType=${requestScope.searchType}&keyword=${requestScope.keyword}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
	</div>
<p>

<div align="right">
<c:choose>
	<c:when test="${sessionScope.memberType=='student'}">
		<a href="/UspaceAcademy/inquiry/registerInquiryForm.do"><button class="btn btn-success">질문하기 등록</button></a>
	</c:when>
</c:choose>

<a href="/UspaceAcademy/inquiry/inquiryList.do"><button class="btn btn-info">전체 목록</button></a>
	</div>




<!-- 검색관련 -->
<form action="/UspaceAcademy/inquiry/searchByKeyword.do?page=${param.page }" method="post">
	<select name="searchType">
		<option value="advancedTitle">제목</option>
		<option value="advancedContent">내용</option>
		<!-- <option value="advancedId">글쓴이</option> -->
	</select>
	<input type="text" name="keyword">
	<input id="search" type="submit" value="검색" class="btn btn-info">
</form>

<br>
