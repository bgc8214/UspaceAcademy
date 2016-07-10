<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->
<style type="text/css">
table#tb {
	width: 700px;
	heiht: 100px;
}
#th {
	width:100px;
}

</style>
<h3 class="pageTlt">수강후기|  상세보기</h3>
<hr>
<table class="table table-bordered form-table" id="tb">
<tbody></tbody>
	<tr>
		<th>글번호</th>
		<td>${requestScope.lectureListReview.reviewNo}</td><!--  오류난거메모 : 상세페이지니까 반복문 돌리면 안됨 -->
		<th>아이디</th>
		<td>${requestScope.lectureListReview.reviewWriterId}</td>
		<th>이름</th>
		<td>${requestScope.lectureListReview.reviewWriter}</td>
	</tr>

	<tr>
		<th>강의과목</th>
		<td>${requestScope.lectureListReview.lectureSubject}</td>
		<th>강의명</th>
		<td>${requestScope.lectureListReview.lectureTitle}</td>
		<th>날짜</th>
		<td>${requestScope.lectureListReview.reviewDate}</td>
	</tr>
</table>



<table class="table table-bordered form-table" id="tb">
	<tr>
		<th id="th">제목</th>
		<td>${requestScope.lectureListReview.reviewTitle}</td>
	</tr>

	<tr>
		<th>내용</th>
		<td>${requestScope.lectureListReview.reviewContent}<br><br><br><br><br><br></td>
	</tr>
</table>



<input type="hidden" name="reviewHit" value="${requestScope.lectureListReview.reviewHit}">
	
	
	
	
<!-- --------------------------------------------------------------------------------------------------------- -->

<!-- 관리자랑 학생(본인글)일 경우만    -    삭제버튼,수정버튼 클릭가능 -->
<span class="lectureRegister">
	<c:if test="${sessionScope.memberType=='administrator'}">
<a href="/UspaceAcademy/lectureReview/lecture_review_delete.do?reviewNo=${lectureListReview.reviewNo}"><button class="btn btn-danger">삭제버튼</button></a><!-- 삭제할때 No값 넘겨줘야함*  -->
<a href="/UspaceAcademy/lectureReview/lecture_review_modifyForm.do?reviewNo=${lectureListReview.reviewNo}&codeType=teacherSubject"><button class="btn btn-warning">수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->
<%-- <a href="/UspaceAcademy/lectureReview/lecture_review_modifyForm.do?reviewNo=${lectureListReview.reviewNo}&codeType=teacherSubject"><button>수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->            --%> 
	</c:if>																																											
	<c:if test="${sessionScope.memberType=='student'&&sessionScope.login_info.studentId==lectureListReview.reviewWriterId}">
<a href="/UspaceAcademy/lectureReview/lecture_review_delete.do?reviewNo=${lectureListReview.reviewNo}"><button class="btn btn-danger">삭제버튼</button></a><!-- 삭제할때 No값 넘겨줘야함*  -->
<a href="/UspaceAcademy/lectureReview/lecture_review_modifyForm.do?reviewNo=${lectureListReview.reviewNo}&codeType=teacherSubject"><button class="btn btn-warning">수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->
	</c:if>
</span>
<a href="/UspaceAcademy/lectureReview/lecture_review_list.do?reviewNo"><button class="btn btn-primary">전체목록</button></a>
<!-- --------------------------------------------------------------------------------------------------------- -->

