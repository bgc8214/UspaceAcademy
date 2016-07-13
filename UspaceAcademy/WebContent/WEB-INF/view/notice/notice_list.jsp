<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
table#tb {
	width: 700px;
	heiht: 100px;

}
</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn").on("click", function() {
			if(!$("input[name=keyword]").val()){
				alert("제목 또는 내용을 입력하세요.");
				return false;
			}
		})
		$("#ex").on("click", "tr", function() {
			alert("상세페이지로 이동합니다");
		})
		$(".noticeList").on("mouseover", function() {
			$(this).css("background-color", "#D8EFF1");
		});
		$(".noticeList").on("mouseout", function() {
			$(this).css("background-color", "white");
		});
		
	});
</script>
<h3 class="pageTlt">공지사항</h3>
<hr>
	<table class="table table-bordered" id="tb">
		<thead>
			<tr>
				<th>공지제목</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody id="ex">
			<c:forEach var="notice" items="${requestScope.noticeList}">
				<tr onclick="location.href='/UspaceAcademy/notice/noticeDetail.do?no=${notice.basicNo}&page=${page}'" style="cursor:pointer;" class="noticeList">
					<td align="center">${notice.basicTitle}</td>
					<td align="center">${notice.basicDate}</td>
					<td align="center">${notice.basicHit}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table>
	<p>	

<%--◀이전 페이지 그룹 처리 --%>
<div class="btn-toolbar" align="center" role="toolbar">
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/notice/list.do?page=${requestScope.paging.beginPage-1}&type=공지사항">
			<div class="btn-group" role="group" aria-label="이전"></div>
			</a>
		</c:when>
		<c:otherwise>이전</c:otherwise>
	</c:choose>
	<%--페이지 처리 --%>
	<c:forEach begin="${requestScope.paging.beginPage }" end="${requestScope.paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == requestScope.paging.page }">
			${page}
			</c:when>
			<c:otherwise>
				<a href="/UspaceAcademy/notice/noticeList.do?page=${page}&type=공지사항">
				${page}
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/notice/noticeList.do?&page=${requestScope.paging.endPage + 1}&type=공지사항">
			다음
			</a>
		</c:when>
		<c:otherwise>다음</c:otherwise>
	</c:choose>
</div>
<p>

<!-- 관리자용 공지사항 등록 버튼 -->
<span class="notciceRegister">
	<c:if test="${sessionScope.memberType=='administrator'}"> 
	<div align="right">
		<a href="/UspaceAcademy/notice/codeList.do?codeNames=공지사항"><button class="btn btn-success">공지사항 등록</button></a>
	</div>
	</c:if>
</span>



 <!-- 공지사항 제목+내용으로 검색 -->
<div class="fixed">
	<form action="/UspaceAcademy/notice/noticeSearch.do" method="post">
		<fieldset>
			<input type="text" name="keyword" placeholder="제목+내용을 입력하세요." size="23" />
			<input id="btn" type="submit" value="제목+내용으로 검색" class="btn btn-info">
		</fieldset>
	</form>
</div>	
<p>
