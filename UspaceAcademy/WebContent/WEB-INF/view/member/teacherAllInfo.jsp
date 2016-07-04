<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn").on("click", function() {
			if(!$("input[name=name]").val()){
				alert("이름을 입력하세요.");
				return false;
			}
		})
	});
</script>
<h2>모든 강사의 정보</h2><p>
<table border="4" width="1000">
	<thead>
		<tr>
			<th>강사이름</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>주소</th>
		<tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.teacherAllList}" var="teacher"> 
			<tr>
				<td align="center">${teacher.teacherName}</td>
				<td align="center">${teacher.teacherEmail}</td>
				<td align="center">${teacher.teacherPhoneNo}</td>
				<td align="center">${teacher.teacherAddress}</td>
			</tr>	
		</c:forEach>
		
	</tbody>
</table>
<p>
<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/member/teacherAll.do?page=${requestScope.paging.beginPage-1}">
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
				<a href="/UspaceAcademy/member/teacherAll.do?page=${page}">
					${page }
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/member/teacherAll.do?&page=${requestScope.paging.endPage + 1}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
<p>

<!-- 학생이름으로 검색  -->
<form action="/UspaceAcademy/member/searchByteacherName.do">
	<input type="text" name="name">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="btn" type="submit" value="이름으로 검색">
</form>