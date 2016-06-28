<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">
table{
border-right 1px solid #e3e3e3;
font-weight normal;



}
th, td{
border 0;
vertical-align top;
border-top 1px solid #e3e3e3;
border-right 1px solid #e3e3e3;
vertical-align middle;
}
}

</style>


<h3>내정보 | 과제게시판</h3>
<hr/>


<div class="boardList">
	<table border="1" summary="">
		<tr>
			<th>번호</th>
			<th>강의명</th>
			<th>제목</th>
			<th>강사명</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>마감일</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach var="assignment" items="${requestScope.assignment}">
		<tr>
			<td>${assignment.assignmentNo}</td>
			
			<td><%-- ${assignment.lectureNo} --%></td>
			
			
			<td><a href="/UspaceAcademy/assignment/assignment_detail.do?assignmentNo=${assignment.assignmentNo}">
					${assignment.assignmentTitle}</a></td>
			
			
			<td><%-- ${assignment.} --%>강사명</td>
			<td>${assignment.assignmentWriter}</td>
			<td>${assignment.assignmentDate}</td>
			<td>${assignment.assignmentDeadline}</td>
			<td>${assignment.assignmentHit}</td>
		</tr>
		</c:forEach>
	</table>
</div>






<!-- --------------------------------------------------------------------------------------------------------- -->

<!-- 강사만 과제글 등록가능 -->
<span class="lectureRegister">
	<c:if test="${sessionScope.memberType=='teacher'}">
		<a href="/UspaceAcademy/assignment/assignment_register.do?"><button>과제글 등록</button></a><!--  과제글 등록폼으로 이동 -->
<!-- 		<a href="/UspaceAcademy/assignment/assignment_register.do?codeType=teacherSubject"><button>수강후기 등록</button></a> 수강후기 등록폼으로 이동 --><!-- 코드타입 여기서 넘겨줌!!!!!!!!!!!!!, 이동하고자하는 컨트롤러(리퀘스트 매핑).do적기, controller에서 jsp로 전달하는 구문써야함 -->
	</c:if>																																											
</span>

<!-- --------------------------------------------------------------------------------------------------------- -->
<%-- <!-- 강사랑학생만 과제글 등록가능 -->
<span class="lectureRegister">
	<c:if test="${sessionScope.memberType=='teacher'}">
		<a href="/UspaceAcademy/assignment/assignment_register.do?"><button>과제글 등록</button></a><!--  과제글 등록폼으로 이동 -->
<!-- 		<a href="/UspaceAcademy/assignment/assignment_register.do?codeType=teacherSubject"><button>수강후기 등록</button></a> 수강후기 등록폼으로 이동 --><!-- 코드타입 여기서 넘겨줌!!!!!!!!!!!!!, 이동하고자하는 컨트롤러(리퀘스트 매핑).do적기, controller에서 jsp로 전달하는 구문써야함 -->
	</c:if>																																											
	<c:if test="${sessionScope.memberType=='student'}">
	<a href="/UspaceAcademy/assignment/assignment_register.do?"><button>과제글 등록</button></a><!--  과제글 등록폼으로 이동 -->
	</c:if>
</span> --%>







<!-- --------------------------------------------------------------------------------------------------------- -->
<p>
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/assignment/assignment_list.do?&page=${requestScope.paging.beginPage - 1}">
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
				<a href="/UspaceAcademy/assignment/assignment_list.do?&page=${page }">
					${page }
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/assignment/assignment_list.do?&page=${requestScope.paging.endPage + 1}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
<p>
<!-- --------------------------------------------------------------------------------------------------------- -->

















