<%@ page contentType ="text/html;charset=utf-8"%>
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
	});
	$(document).ready(effect);
	function effect() {
		$("tr:eq(2)").css("background-color", "#EAEAEA");
	}
</script>
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

<!-- 공지사항 제목+내용으로 검색 -->
<form action="/UspaceAcademy/notice/noticeSearch.do" method="post">
	<input type="text" name="keyword">&nbsp;&nbsp;&nbsp;
	<input id="btn" type="submit" value="제목+내용으로 검색">
</form>
<p>


<!-- 관리자용 공지사항 등록 버튼 -->
<span class="notciceRegister">
	<c:if test="${sessionScope.memberType=='administrator'}">
		<a href="/UspaceAcademy/notice/codeList.do?codeNames=공지사항"><button class="registerBtn">공지사항 등록</button></a>
	</c:if>
</span>
	
	
	
	

