<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#btn").on("click", function() {
		if(!$("input[name=keyword]").val()){
			alert("키워드를 입력하세요.");
			return false;
		}
	})
	$("#ex").on("click", "tr", function() {
			alert("상세페이지로 이동합니다");
	})
	$(".noticeList").on("mouseover", function() {
		$(this).css("background-color", "#FFD9FA");
	});
	$(".noticeList").on("mouseout", function() {
		$(this).css("background-color", "white");
	});
});
	$(document).ready(effect);
	function effect() {
		$("tr:eq(2)").css("background-color", "#E8D9FF");
	}
</script>
<h3 class="pageTlt">검색된 페이지</h3>
<hr>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>공지제목</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody id="ex">
			<c:if test="${requestScope.noticeSearch.size() != 0}">
			<c:forEach var="notice" items="${requestScope.noticeSearch}">
				<tr onclick="location.href='/UspaceAcademy/notice/noticeDetail.do?no=${notice.basicNo}&page=${page}&keyword=${requestScope.keyword}'" style="cursor:pointer;" class="noticeList">
					<td>${notice.basicTitle}</td>
					<td>${notice.basicDate}</td>
					<td>${notice.basicHit}</td>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${requestScope.noticeSearch.size() ==0}">
				<tr class="faqList">
					<td colspan="3">조회된 결과값이 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table><p>
	
	<%--◀이전 페이지 그룹 처리 --%>
	<div class="pageNav" align="center">
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/notice/noticeSearch.do?page=${requestScope.paging.beginPage-1}&keyword=${requestScope.keyword}" class="prevPage">
			이전
			</a>
		</c:when>
		<c:otherwise>이전</c:otherwise>
	</c:choose>
	<%--페이지 처리 --%>
	<c:forEach begin="${requestScope.paging.beginPage }" end="${requestScope.paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == requestScope.paging.page }">
			 <span><strong>${page}</strong></span> 
			</c:when>
			<c:otherwise>
				<a href="/UspaceAcademy/notice/noticeSearch.do?page=${page}&keyword=${requestScope.keyword}">
				<span><strong>${page}</strong></span>
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/notice/noticeSearch.do??&page=${requestScope.paging.endPage + 1}&keyword=${requestScope.keyword}" class="nextPage">
			다음
			</a>
		</c:when>
		<c:otherwise>다음</c:otherwise>
	</c:choose>
</div>
<p>

<a href="/UspaceAcademy/notice/list.do?type=공지사항"><button class="btn btn-success">공지사항리스트</button></a>

<!-- 공지사항 제목+내용으로 검색 -->
<div class="boardBottom" style="position: absolute; right: 750px; bottom: 300px;">
	<form action="/UspaceAcademy/notice/noticeSearch.do" method="post">
		<fieldset>
			<input type="text" name="keyword" placeholder="제목+내용을 입력하세요"/>
			<input id="btn" type="submit" value="제목+내용으로 검색" class="btn btn-info">
		</fieldset>
	</form>
</div>
<p>

