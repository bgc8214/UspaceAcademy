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
	
	$("#remove").on("click", function() {
		return confirm("강제탈퇴 시키시겠습니까?");
	})
});

</script>
<h3 class="pageTly">모든 강사 정보</h3>
<hr>
<table class="table table-warning">
	<thead>
		<tr class="bg-info">
			<th>강사이름</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>강의하는 과목</th>
			<th>회원 탈퇴</th>
		<tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.teacherAllList}" var="teacher"> 
			<tr>
				<td align="center">${teacher.teacherName}</td>
				<td align="center">${teacher.teacherEmail}</td>
				<td align="center">${teacher.teacherPhoneNo}</td>
				<td align="center">${teacher.teacherAddress}</td>

				<td><a href="/UspaceAcademy/member/selectAllByTeacherId2.do?teacherId=${teacher.teacherId}">${teacher.teacherSubject}</a></td>
				<td><a href="/UspaceAcademy/member/deleteTeacherByAdmin.do?teacherId=${teacher.teacherId}"><button id="remove">강사 탈퇴</button></a></td>
			</tr>	
		</c:forEach>
		
	</tbody>
</table>
<p>
<div class="pageNav" align="center">
<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/member/teacherAll.do?page=${requestScope.paging.beginPage-1}" class="prevPage">
					<span><strong>이전</strong></span>
			</a>
		</c:when>
		<c:otherwise><span><strong>이전</strong></span></c:otherwise>
	</c:choose>
	<%--페이지 처리 --%>
	<c:forEach begin="${requestScope.paging.beginPage }" end="${requestScope.paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == requestScope.paging.page }">
			 	<span><strong>${page }</strong></span>
			</c:when>
			<c:otherwise>
				<a href="/UspaceAcademy/member/teacherAll.do?page=${page}">
					<span><strong>${page }</strong></span>
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/member/teacherAll.do?&page=${requestScope.paging.endPage + 1}" class="nextPage">
				<span><strong>다음</strong></span>
			</a>
		</c:when>
		<c:otherwise><span><strong>다음</strong></span></c:otherwise>
	</c:choose>
</div>
<p>

<div class="boardBottom" align="center">
<!-- 학생이름으로 검색  -->
<form action="/UspaceAcademy/member/searchByteacherName.do">

<input type="text" name="name">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="btn" type="submit" value="이름으로 검색" class="btn btn-info">

</form>
</div>