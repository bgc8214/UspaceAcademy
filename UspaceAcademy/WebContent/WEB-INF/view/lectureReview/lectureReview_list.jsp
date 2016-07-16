
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->

<style type="text/css">
table#tb {
	width: 1000px;
	heiht: 100px;

}


/* body{
min-width:1400px;
}
body, code{
font:11px 'Nanum Gothic';
color: #000;
}
body{
display : block;
}

th, td{
border:0;
vertical-align:top;
}

.boardList table{
border: 1px solid #d7d5d5;
color: #353535;
font-size: 11px;
line-height:140%;
} */

</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#tbody1").on("click", "tr", function() {
			alert("상세페이지로 이동합니다.");
		})
	})
</script>
<h3 class="pageTlt">수강후기</h3>
<hr>

<table border='1' class="table table-bordered table-hover" id="tb">
		<thead>
			<tr>
				<th scope="col">글번호</th>
				<th scope="col">제목</th>
				<th scope="col">강의과목</th>
				<th scope="col">강의명</th>
				<th scope="col">아이디</th><!--  id추가  -->
				<th scope="col">이름</th>
				<th scope="col">날짜</th>
				<th scope="col">조회수</th>
			</tr>
		</thead>
		
		<tbody id="tbody1">
		
		<%-- <form><input id="page" type="hidden" value="${param.page}"></form> <!-- ?????????????지워????? --> --%>
		
			<c:forEach var="lectureListReview" items="${requestScope.lectureListReview}">
				<tr onclick="location.href='/UspaceAcademy/lectureReview/lecture_review_detail.do?reviewNo=${lectureListReview.reviewNo}'" style="cursor:pointer;">
					<td>${lectureListReview.reviewNo}</td><!--  글번호 -->
					<td><!--  한개값넘겨줄때 ? 물음표,  여러개 넘겨줄때 & 앤드.   -->${lectureListReview.reviewTitle}</td><!-- 제목  -->
					<td class="category">${lectureListReview.lectureSubject}</td><!-- 강의과목  -->
					<td class="left">${lectureListReview.lectureTitle}</td><!-- 강의명  -->  
					<td>${lectureListReview.reviewWriterId}</td><!--  id추가  -->
					<td>${lectureListReview.reviewWriter}</td><!-- 이름 -->
					<td>${lectureListReview.reviewDate}</td><!-- 날짜  -->
					<td>${lectureListReview.reviewHit}</td><!-- 조회수  -->
				</tr>
			</c:forEach>
		</tbody>	
	</table>



<!-- --------------------------------------------------------------------------------------------------------- -->
<p>
<div align="center">
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/lectureReview/lecture_review_list.do?page=${requestScope.paging.beginPage - 1}">
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
				<a href="/UspaceAcademy/lectureReview/lecture_review_list.do?page=${page }">
					${page }
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/lectureReview/lecture_review_list.do?&page=${requestScope.paging.endPage + 1}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
</div>
<p>
<!-- --------------------------------------------------------------------------------------------------------- -->

<!-- 관리자랑 학생일 경우만, 강의등록버튼클릭가능 -->
<span class="lectureRegister">
<%-- 	<c:if test="${sessionScope.memberType=='administrator'}">
		<a href="/UspaceAcademy/lectureReview/lecture_review_register.do?codeType=teacherSubject"><button>후기 등록</button></a><!--  수강후기 등록폼으로 이동 -->
	</c:if>	 --%>																																								<!-- 코드타입 여기서 넘겨줌!!!!!!!!!!!!!, 이동하고자하는 컨트롤러(리퀘스트 매핑).do적기, controller에서 jsp로 전달하는 구문써야함 -->
	<c:if test="${sessionScope.memberType=='student'}">
	<div align="right">
		<a href="/UspaceAcademy/lectureReview/lecture_review_register.do?codeType=teacherSubject"><button class="btn btn-success">후기 등록</button></a>
	</div>
	</c:if>
</span>
<!-- 전체 목록으로 돌아가기 -->
<a href="/UspaceAcademy/lectureReview/lecture_review_list.do?reviewNo"><button class="btn btn-primary">전체목록</button></a>

<!-- --------------------------------------------------------------------------------------------------------- -->
<p>
<!--  검색 기능 -->
<form action="/UspaceAcademy/lectureReview/lecture_review_search.do?page=${param.page}" method="post">
<select name="searchType">
	<option value="reviewSubject">강의과목</option>
	<option value="reviewTitle">제목</option>
	<option value="lectureTitle">강의명</option>
</select>
<input type="text" name="keyword"><!--  검색할때 keyword -->
<input type="submit" value="검색" class="btn btn-info">



</form>






