<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
table#tb {
	width:700px;
	heigth:100px;	
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
/* 		$(".studentList").on("mouseover", function() {
			$(this).css("background-color", "#FAF4C0");
			this.style.cursor = 'pointer';
		});
		$(".studentList").on("mouseout", function() {
			$(this).css("background-color", "white");
		}) */
	});
</script>
<h3 class="pageTlt">강사 정보</h3>
<hr>
<table class="table table-bordered" id="tb">
	<thead>
		<tr>
			<th>강사이름</th>
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
			<c:forEach items="${requestScope.nameList}" var="teacher">
				<tr class="teacherList">
					<td align="center">${teacher.teacherName}</td>
					<td align="center">${teacher.teacherEmail}</td>
					<td align="center">${teacher.teacherPhoneNo}</td>
					<td align="center">${teacher.teacherAddress}</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
	
</table>

	<%--◀이전 페이지 그룹 처리 --%>
	<div class="pageNav" align="center">
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/member/searchByteacherName.do?page=${requestScope.paging.beginPage-1}&name=${requestScope.name}">
			이전
			</a>
		</c:when>
		<c:otherwise>이전</c:otherwise>
	</c:choose>
	<%--페이지 처리 --%>
	<c:forEach begin="${requestScope.paging.beginPage }" end="${requestScope.paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == requestScope.paging.page }">
			 <strong>${page }</strong>
			</c:when>
			<c:otherwise>
				<a href="/UspaceAcademy/member/searchByteacherName.do?page=${page}&name=${requestScope.name}">
					<strong>${page}</strong>
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/member/searchByteacherName.do?&page=${requestScope.paging.endPage + 1}&name=${requestScope.name}">
			다음
			</a>
		</c:when>
		<c:otherwise>다음</c:otherwise>
	</c:choose>
</div>
<p>
<div align="right">
	<a href="/UspaceAcademy/member/teacherAll.do"><button class="btn btn-success">강사정보목록</button></a><p>
</div>

<div align="left">
<!-- 학생이름으로 검색  -->
<form action="/UspaceAcademy/member/searchByteacherName.do">
	<input type="text" name="name">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="btn" type="submit" value="이름으로 검색" class="btn btn-info">
</form>
</div>


