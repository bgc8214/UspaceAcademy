<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">
caption{
font-family: 궁서;
color: #00F;
font-weight: bold;
font-size: 30px;
margin-bottom: 10px;
}
table_list{
width:100%;
font-size: 12px;
}
table_list thead tr th{ 
height :35px;
background: #494949;
color: #ffff;
font-weght: bold; 
}
table_ list tbody tr td{
line-height: 35px;
border-bottom: 1px solid #c3c3c3;
text-align: center;
}
table_ list tbody tr td .num{
font-famliy: Tahoma;
font-size: 11px;
color: #737373;
}
table_ list tbody tr td .title{ 
text-align: left;
}
table_ list tbody tr td .title a{ 
display: block;
float: left;
text-decoration: none;
color: #191919;
max-width: 320px; 
white-space: nowrap;
overflow: hidden;
text-overflow: ellipsis;
}
table_list tbody tr td .title a:hover{
text-decoration: underline;
}



</style>


<h3>내정보 | 과제게시판</h3>
<hr/>


<table class="table_list" summary="영주" cellpacing="0">
	<caption></caption>
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">아이디</th>
			<th scope="col">이름</th><!--    -->
			<th scope="col">작성일</th><!--    -->
			<th scope="col">마감일</th><!--    -->
			<th scope="col">조회수</th><!--    -->
		</tr>
	<thead>
		
		
		<c:forEach var="assignment" items="${requestScope.assignment}">
			<tr>
				<td class="num">${assignment.assignmentNo}</td><!--  번호  -->

				<!--  -->
				<td class="title">
				<c:if test="${assignment.replyLevel>1}">
						<c:forEach begin="1" end="${assignment.replyLevel-1}">
						 &nbsp;&nbsp;
						 </c:forEach>
					     └
				</c:if> 
				<!--  학생이고 , 내가 쓴글(로그인한 아이디랑 글쓴이의 아이디 비교)이라면 - 볼수있다 -->
				<!-- 학생은 자기가 쓴글&강사가 쓴글 만 볼 수 있게 하기 (강사는 모든글 볼수 있음) -->
				<c:choose>
				<c:when test="${sessionScope.memberType=='teacher'}"><!--  (강사는 모든글 볼수 있음) -->
				<a href='/UspaceAcademy/assignment/assignment_detail.do?assignmentNo=${assignment.assignmentNo}'>${assignment.assignmentTitle}</a>
				</c:when>
  				<c:when test="${sessionScope.memberType=='student'&&sessionScope.login_info.studentName==assignment.assignmentWriter}"><!-- eq -->
				<a href='/UspaceAcademy/assignment/assignment_detail.do?assignmentNo=${assignment.assignmentNo}'>${assignment.assignmentTitle}</a>
				</c:when> 
				<c:otherwise> <!--  위에 경우가 모두 아니라면 -->
				${assignment.assignmentTitle}
				</c:otherwise>
				</c:choose>
				
				
				
				</td>
				<!--  -->
				<td class="title">${assignment.assignmentWriterId}</td>
				<td class="title">${assignment.assignmentWriter}</td>
				<td class="num">${assignment.assignmentDate}</td>
				<td class="num">${assignment.assignmentDeadline}</td>
				<td class="num">${assignment.assignmentHit}</td>
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






































































<%-- <%@ page contentType="text/html;charset=utf-8"%>
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
			
			<td>${assignment.lectureNo}</td>
			
			
			<td><a href="/UspaceAcademy/assignment/assignment_detail.do?assignmentNo=${assignment.assignmentNo}">
					${assignment.assignmentTitle}</a></td>
			
			
			<td>${assignment.}강사명</td>
			<td>${assignment.assignmentWriter}</td>
			<td>${assignment.assignmentDate}</td>
			<td>${assignment.assignmentDeadline}</td>
			<td>${assignment.assignmentHit}</td>
		</tr>
		</c:forEach>
	</table>
</div> --%>








