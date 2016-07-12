<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">
/* caption{/* 안됨
font-family: 맑은고딕;
color: #00F;
font-weight: bold;
font-size: 50px;
margin-bottom: 10px;
}
.table_list{/* table_list전체적용
width:100%;
font-size: 16px;
/* text-align: center;
}
.table_list thead tr th{ 
height :35px;
background: #F5F5F5;
color: #ffff;
font-weght: bold; 
}
.table_ list tbody tr td{
line-height: 35px;
border-bottom: 1px solid #c3c3c3;
text-align: center;
}
.table_ list tbody tr td .num{
font-famliy: Tahoma;
font-size: 20px;
color: #737373;
}
.table_ list tbody tr td .title{ 
text-align: center;
}
.table_ list tbody tr td .title a{ 
display: block;
float: left;
text-decoration: none;
color: #191919;
max-width: 320px; 
white-space: nowrap;
overflow: hidden;
text-overflow: ellipsis;
}
.table_list tbody tr td .title a:hover{
text-decoration: underline;
} */
</style>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#ex").on("click", "tr", function() {
			alert("상세페이지로 이동합니다");
		})
		
	});
	
/* 	$(document).ready(effect);
	function effect() {
		$("tr:eq(2)").css("background-color", "#6CC0FF");
	
	} */
</script>
			
			
<h3>내정보 | 과제게시판</h3>
<hr/>
<table id="tb" class="table table-bordered table-hover">
	<caption></caption>
	<thead>
		<tr>
		
<%-- 		<!--  보류 -->
		<select id="lectureTitle" name="lectureTitle"> 
					<c:forEach items="${requestScope.getLectureList}"  var="getLectureList"><!--  컨트롤러에서 보낸값 -->
					<option>${getLectureList.lectureTitle}</option>
					</c:forEach>
		</select> --%>
		
			<!-- <th scope="col">번호</th> -->
			<th scope="col">아이디</th>
			<th scope="col">이름</th>
			<th scope="col">제목</th>
			
			<th scope="col">작성일</th>
			<th scope="col">마감일</th>
			<th scope="col">조회수</th>
		</tr>
	<thead>
		
		<tbody id="ex">
		<c:forEach var="assignment" items="${requestScope.assignment}">
			
			<tr onclick="location.href='/UspaceAcademy/assignment/assignment_detail.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}'"  style="cursor:pointer;"  class="assignment">
				
				<!--  1.번호 -->
				<%-- <td class="num"  align="center">${assignment.assignmentNo}</td> --%>
				
				<!-- 3.아이디 -->
				<td class="title" align="center">${assignment.assignmentWriterId}</td>
				<!-- 4. 이름 -->
				<td class="title">${assignment.assignmentWriter}</td>
				
				<!-- 2.제목 -->
				<td class="title"  align="center">
				<c:if test="${assignment.replyLevel>1}">
						<c:forEach begin="1" end="${assignment.replyLevel-1}">
						 &nbsp;&nbsp;
						 </c:forEach>
					     └
				</c:if> 
				<!--  학생이고 , 내가 쓴글(로그인한 아이디랑 글쓴이의 아이디 비교)이라면 - 볼수있다 -->
				<!-- 학생은 자기가 쓴글&강사가 쓴글 만 볼 수 있게 하기 (강사는 모든글 볼수 있음) -->
				<!--  (1.강사는 모든글 볼수 있음) -->
				<!--  (2.학생이고 자기가 쓴글일경우만-상세들어가짐) -->
				<!--  (3.그냥글이면 보여짐 (답글이아니면) - 선생글 봐야해서 해줌 ) -->
				<c:choose>
				<c:when test="${sessionScope.memberType=='teacher'}">
				<a href='/UspaceAcademy/assignment/assignment_detail.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}'>${assignment.assignmentTitle}</a>
				</c:when>
  				<c:when test="${sessionScope.memberType=='student'&&sessionScope.login_info.studentId==assignment.assignmentWriterId}"><!-- eq -->
				<a href='/UspaceAcademy/assignment/assignment_detail.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}'>${assignment.assignmentTitle}</a>
				</c:when> 
				<c:when test="${assignment.replyLevel>1 == false}">
				<a href='/UspaceAcademy/assignment/assignment_detail.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}'>${assignment.assignmentTitle}</a>
				</c:when>
				<c:otherwise> <!--  위에 경우가 모두 아니라면 -->
				 ${assignment.assignmentTitle} <!-- </a> -->
				</c:otherwise>
				</c:choose>
				</td>
				
				<!-- 5.날짜 -->
				<td class="num">${assignment.assignmentDate}</td>
				<!-- 6.마감일 -->
				<td class="num">${assignment.assignmentDeadline}</td>
				<!-- 7.조회수 -->
				<td class="num">${assignment.assignmentHit}</td>
			</tr>
		</c:forEach>
	</tbody>		
</table>




<!-- --------------------------------------------------------------------------------------------------------- -->

<!-- 강사만 과제글 등록가능 -->
<span class="lectureRegister">
	<c:if test="${sessionScope.memberType=='teacher'}">
		<a href="/UspaceAcademy/assignment/assignment_register.do?lectureNo=${requestScope.lectureNo}"><button class="btn btn-success">과제 등록</button></a><!--  과제글 등록폼으로 이동 -->
	</c:if>																																											
</span>

<!-- --------------------------------------------------------------------------------------------------------- -->
<p>
<div align="center">
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/assignment/assignment_list.do?page=${requestScope.paging.beginPage - 1}&lectureNo=${requestScope.lectureNo}">
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
				<a href="/UspaceAcademy/assignment/assignment_list.do?&page=${page }&lectureNo=${requestScope.lectureNo}">
					${page }
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/assignment/assignment_list.do?&page=${requestScope.paging.endPage + 1}&lectureNo=${requestScope.lectureNo}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
	</div>	
<p>
<!-- --------------------------------------------------------------------------------------------------------- -->




