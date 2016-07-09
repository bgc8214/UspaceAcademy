<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	button#studentListBtn {
		position :relative;
		position : absolute;
		right: 670px;
		top : 313px;
	}
</style>

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
		$("tr:eq(1)").css("background-color", "#FFD9EC");
	}

	
</script>

<h3 class="pageTlt">수강생 정보</h3>
<hr>
<table border="2" class="table table-borered">
	<thead>
		<tr>
			<th align="center">학생이름</th>
			<th align="center">이메일</th>
			<th align="center">전화번호</th>
			<th align="center">주소</th>
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
				<tr class="studentList">
					<td align="center">${student.studentName}</td>
					<td align="center">${student.studentEmail}</td>
					<td align="center">${student.studentPhoneNo}</td>
					<td align="center">${student.studentAddress}</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
	
</table>
	<div class="pageNav" align="center">
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/member/searchBystudentName.do?page=${requestScope.paging.beginPage-1}&name=${requestScope.name}" class="prevPage">
			<strong>이전</strong>
			</a>
		</c:when>
		<c:otherwise><strong>이전</strong></c:otherwise>
	</c:choose>
	<%--페이지 처리 --%>
	<c:forEach begin="${requestScope.paging.beginPage }" end="${requestScope.paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == requestScope.paging.page }">
			<strong>${page }</strong>
			</c:when>
			<c:otherwise>
				<a href="/UspaceAcademy/member/searchBystudentName.do?page=${page}&name=${requestScope.name}">
					<strong>${page }</strong>
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/member/searchBystudentName.do?&page=${requestScope.paging.endPage + 1}&name=${requestScope.name}" class="nextPage">
			<strong>다음</strong>
			</a>
		</c:when>
		<c:otherwise><strong>다음</strong></c:otherwise>
	</c:choose>
	</div>
<p>
<div>
<!-- 학생이름으로 검색  -->
<form action="/UspaceAcademy/member/searchBystudentName.do">
	<input type="text" name="name">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="btn" type="submit" value="이름으로 검색" class="btn btn-primary">
</form>
</div>


<a href="/UspaceAcademy/member/studentAll.do"><button class="btn btn-success" id="studentListBtn">학생정보목록</button></a>

