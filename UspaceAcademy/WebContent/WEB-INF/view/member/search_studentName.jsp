<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn").on("click", function() {
			if(!$("input[name=name]").val()){
				alert("이름을 입력하세요.");
				return false;
			}
		})
	});
	$(document).ready(effect);
	function effect() {
		$("tr:eq(2)").css("background-color", "#EAEAEA");
	}
</script>
<table border="4" width="1000">
	<thead>
		<tr>
			<th>학생이름</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>주소</th>
		<tr>
	</thead>
	<tbody>
		<c:if test="${requestScope.nameList.size()==0}">
			<tr>
				<td colspan="4" align="center">존재 하지 않은 이름으로 검색하였습니다.</td>
			</tr>
		</c:if>
		<c:if test="${requestScope.nameList != null}">
			<c:forEach items="${requestScope.nameList}" var="student">
				<tr>
					<td align="center">${student.studentName}</td>
					<td align="center">${student.studentEmail}</td>
					<td align="center">${student.studentPhoneNo}</td>
					<td align="center">${student.studentAddress}</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
	
</table>

	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/member/searchBystudentName.do?page=${requestScope.paging.beginPage-1}&name=${requestScope.name}">
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
				<a href="/UspaceAcademy/member/searchBystudentName.do?page=${page}&name=${requestScope.name}">
					${page}
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/member/searchBystudentName.do?&page=${requestScope.paging.endPage + 1}&name=${requestScope.name}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
<p>
<a href="/UspaceAcademy/member/studentAll.do"><button>학생정보목록</button></a><p>

<!-- 학생이름으로 검색  -->
<form action="/UspaceAcademy/member/searchBystudentName.do">
	<input type="text" name="name">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="btn" type="submit" value="이름으로 검색">
</form>
