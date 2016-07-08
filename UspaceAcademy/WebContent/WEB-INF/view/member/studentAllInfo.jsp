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
		$("#remove").on("click", function() {
			return confirm("강제탈퇴 시키시겠습니까?");
		})
	});
	
	$(document).ready(effect);
	function effect() {
		$("tr:eq(2)").css("background-color", "#6CC0FF");
	
	}

</script>
<h3 class="pageTlt">모든 학생의 정보</h3>
<hr>
<table class="table">
	<thead>
		<tr>
			<th>학생이름</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>회원 탈퇴</th>
		<tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.studentAllList}" var="student"> 
			<tr>
			
				<td align="center">${student.studentName}</td>
				<td align="center">${student.studentEmail}</td>
				<td align="center">${student.studentPhoneNo}</td>
				<td align="center">${student.studentAddress}</td>				
				<td><div id="remove"><a href="/UspaceAcademy/member/deleteStudentByAdmin.do?studentId=${student.studentId}"><button>학생 탈퇴</button></a></div></td>
			</tr>	
		</c:forEach>
		
	</tbody>
	
	
	
</table>
<p>
<%--◀이전 페이지 그룹 처리 --%>
<div class="pageNav" align="center">
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/member/studentAll.do?page=${requestScope.paging.beginPage-1}" class="prevPage">
			이전
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
				<a href="/UspaceAcademy/member/studentAll.do?page=${page}">
				<span><strong>${page}</strong></span>	
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/member/studentAll.do?&page=${requestScope.paging.endPage + 1}" class="nextPgae">
			다음
			</a>
		</c:when>
		<c:otherwise>다음</c:otherwise>
	</c:choose>
</div>
<p>
<div class="boardBottom" align="center">
<!-- 학생이름으로 검색  -->
<form action="/UspaceAcademy/member/searchBystudentName.do">
	<fieldset>
		<dd><input type="text" name="name" placeholder="이름을 입력하세요"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="btn" type="submit" value="이름으로 검색" class="btn btn-info"><dd>
	</fieldset>
	
</form>
</div>