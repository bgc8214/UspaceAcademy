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
		$("#ex").on("click", "tr", function() {
			alert("상세페이지로 이동합니다");
		})
		
	});
	
	$(document).ready(effect);
	function effect() {
		var message = $
		$("tr:eq(2)").css("background-color", "#6CC0FF");
	
	}

</script>
<h3 class="pageTlt">공지사항</h3>
<hr>
	<table class="table table-bordered table-hover" align="center">
		<thead>
			<tr>
				<th>No</th>
				<th>공지제목</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody id="ex">
			<c:forEach var="notice" items="${requestScope.noticeList}">
				<tr onclick="location.href='/UspaceAcademy/notice/noticeDetail.do?no=${notice.basicNo}&page=${page}'" style="cursor:pointer;" class="noticeList">
					<td align="center">${notice.basicNo}</td>
					<td align="center">${notice.basicTitle}</td>
					<td align="center">${notice.basicDate}</td>
					<td align="center">${notice.basicHit}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table>
	<p>	

<%--◀이전 페이지 그룹 처리 --%>
<div class="pageNav" align="center">
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/notice/list.do?page=${requestScope.paging.beginPage-1}&type=공지사항" class="prevPage">
			<!-- ◀ -->이전
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
				<a href="/UspaceAcademy/notice/list.do?page=${page}&type=공지사항">
				<span><strong>${page}</strong></span>	
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/notice/list.do?&page=${requestScope.paging.endPage + 1}&type=공지사항" class="nextPage">
			<!-- ▶ -->다음
			</a>
		</c:when>
		<c:otherwise>다음</c:otherwise>
	</c:choose>
</div>
<p>

 <!-- 공지사항 제목+내용으로 검색 -->
<div class="boardBottom" align="center">
	<form action="/UspaceAcademy/notice/noticeSearch.do" method="post">
		<fieldset>
			<dd><input type="text" name="keyword" placeholder="제목+내용을 입력하세요." />
			<input id="btn" type="submit" value="제목+내용으로 검색" class="btn btn-info"></dd>
		</fieldset>
	
	</form>
</div>



<!-- 관리자용 공지사항 등록 버튼 -->
<span class="notciceRegister">
	<c:if test="${sessionScope.memberType=='administrator'}">
		<a href="/UspaceAcademy/notice/codeList.do?codeNames=공지사항"><button class="btn btn-success">공지사항 등록</button></a>
	</c:if>
</span>


<p>
