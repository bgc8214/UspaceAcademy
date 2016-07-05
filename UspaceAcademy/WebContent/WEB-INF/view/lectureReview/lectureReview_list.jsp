
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->

<h3>수강후기</h3>


<hr/>

 	<table border="2">
		<thead>
			<tr>
				<td>글번호</td>
				<td>아이디</td><!--  id추가  -->
				<td>이름</td>
				<td>강의과목</td>
				<td>강의명</td>
				<td>제목</td>
				<td>날짜</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
		
		<%-- <form><input id="page" type="hidden" value="${param.page}"></form> <!-- ?????????????지워????? --> --%>
		
			<c:forEach var="lectureListReview" items="${requestScope.lectureListReview}">
				<tr>
					<td>${lectureListReview.reviewNo}</td>
					<td>${lectureListReview.reviewWriterId}</td><!--  id추가  -->
					<td>${lectureListReview.reviewWriter}</td>
					
					<td>${lectureListReview.lectureSubject}</td>
					<td>${lectureListReview.lectureTitle}</td>
					<td><a href="/UspaceAcademy/lectureReview/lecture_review_detail.do?reviewNo=${lectureListReview.reviewNo}"> <!--  한개값넘겨줄때 ? 물음표,  여러개 넘겨줄때 & 앤드.   -->
							${lectureListReview.reviewTitle}</a></td>
					
					<td>${lectureListReview.reviewDate}</td>
					<td>${lectureListReview.reviewHit}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table>

<!-- --------------------------------------------------------------------------------------------------------- -->
<p>
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
<p>
<!-- --------------------------------------------------------------------------------------------------------- -->

<!-- 관리자랑 학생일 경우만, 강의등록버튼클릭가능 -->
<span class="lectureRegister">
<%-- 	<c:if test="${sessionScope.memberType=='administrator'}">
		<a href="/UspaceAcademy/lectureReview/lecture_review_register.do?codeType=teacherSubject"><button>후기 등록</button></a><!--  수강후기 등록폼으로 이동 -->
	</c:if>	 --%>																																										<!-- 코드타입 여기서 넘겨줌!!!!!!!!!!!!!, 이동하고자하는 컨트롤러(리퀘스트 매핑).do적기, controller에서 jsp로 전달하는 구문써야함 -->
	<c:if test="${sessionScope.memberType=='student'}">
		<a href="/UspaceAcademy/lectureReview/lecture_review_register.do?codeType=teacherSubject"><button>후기 등록</button></a>
	</c:if>
</span>

<!-- --------------------------------------------------------------------------------------------------------- -->
<p>
<!--  검색 기능 -->
<form action="/UspaceAcademy/lectureReview/lecture_review_search.do?page=${param.page}" method="post">
<select name="searchType">
	<option value="reviewSubject">강의과목</option>
	<option value="reviewTitle">제목</option>
</select>
<input type="text" name="keyword">
<input type="submit" value="검색">
</form>






