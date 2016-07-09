<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">

$(document).ready(effect);
function effect(){
	$("tr:eq(2)").css("background-color", "palegreen");

}

//폼체크
$(document).ready(function(){
	$("#search").on("click", function(){
		if(!$("input[name=keyword]").val()){
			alert("검색할 내용을 입력하세요!");
			
			return false;
		}
	});
	
	$("#tbody1").on("click", "tr", function() {
	})
});

</script>

<h2 class="pageTlt">1:1문의 게시판</h2><br>

<table border='1' class="table table-bordered table-hover">
	<thead>
		<tr>
			<!-- <td>글번호</td> -->			
			<td>제목</td>
			<td>글쓴이</td>
			<td>글 등록일</td>
			<td>조회수</td>
		</tr>
	</thead>
			<!-- <img src="image\lock.jpg" width="9" height="12"> -->
	<tbody id="tbody1">
		<input id="page" type="hidden" value="${param.page }">
		<c:forEach items="${requestScope.inquiryList}" var="list">
			<input type="hidden" id="secret" value="${list.advancedSecret}">
			<tr>
				<%-- <td>${list.advancedNo }</td> --%>
				<td>
					<c:choose>
					<c:when test="${list.advancedSecret}">
						<c:if test="${sessionScope.memberType == 'student'}">
							<c:choose>
								<c:when test="${list.advancedId eq sessionScope.login_info.studentId}">
									<a href="/UspaceAcademy/inquiry/selectByAdvancedNoWithComment.do?advancedNo=${list.advancedNo }
									&advancedSecret=${list.advancedSecret}">${list.advancedTitle } 비밀글 </a>
								</c:when>
								<c:otherwise>
									<a href="/UspaceAcademy/inquiry/selectByAdvancedNoWithComment.do?advancedNo=${list.advancedNo }
									&advancedSecret=${list.advancedSecret}" onclick="alert('비밀글 입니다.');">${list.advancedTitle } 비밀글</a>
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${sessionScope.memberType == 'teacher' or sessionScope.memberType == null}">
							<a href="/UspaceAcademy/inquiry/selectByAdvancedNoWithComment.do?advancedNo=${list.advancedNo }
							&advancedSecret=${list.advancedSecret}" onclick="alert('비밀글 입니다.');">${list.advancedTitle } 비밀글 </a>
						</c:if>
					</c:when>
					<c:otherwise>
						<a href="/UspaceAcademy/inquiry/selectByAdvancedNoWithComment.do?advancedNo=${list.advancedNo }
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
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/inquiry/inquiryList.do?page=${requestScope.paging.beginPage - 1}">
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
				<a href="/UspaceAcademy/inquiry/inquiryList.do?page=${page }">
					${page }
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/inquiry/inquiryList.do?page=${requestScope.paging.endPage + 1}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
<p>

<!-- 검색관련 -->
<form action="/UspaceAcademy/inquiry/searchByKeyword.do?page=${param.page }" method="post">
	<select name="searchType">
		<option value="advancedTitle">제목</option>
		<option value="advancedContent">내용</option>
		<!-- <option value="advancedId">글쓴이</option> -->
	</select>
	<input type="text" name="keyword">
	<input id="search" type="submit" value="검색">
</form>

<br>

<c:choose>
	<c:when test="${sessionScope.memberType=='student'}">
		<a href="/UspaceAcademy/inquiry/registerInquiryForm.do">질문하기 등록</a>
	</c:when>
</c:choose>



	