<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
목록<br>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	for(var i=0;i<length(requestScope.inquiryList);i++){
		if(requestScope.inquiryList[i].advancedSecret=='1'){	
			$("#secret").on("click", function() {
				alert("비밀글 입니다.");					
			});
		}
	}
});

</script>
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
			<c:forEach items="${requestScope.inquiryList}" var="list">
				<tr>
					<td>${list.advancedNo }</td>
					

							<td><a id="secret" href="/UspaceAcademy/inquiry/selectByAdvancedNo.do?advancedNo=${list.advancedNo }
								&advancedSecret=${list.advancedSecret}">${list.advancedTitle }</a></td>
				

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
			<a href="/UspaceAcademy/inquiry/inquiryList.do?&page=${requestScope.paging.endPage + 1}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
<p>

<form action="/UspaceAcademy/inquiry/selectByTitle.do">
	<input type="text" name="advancedTitle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="제목으로 검색">
</form><br>

<a href="/UspaceAcademy/inquiry/codeList.do">1:1문의 등록</a>
	
	<%-- ${requestScope.list} --%>
	