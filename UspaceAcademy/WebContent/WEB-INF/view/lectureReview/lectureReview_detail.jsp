<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->

<h3>수강후기|상세보기</h3>


<hr/>
	<table border="2">
		<thead>
			<tr>
				<td>글번호</td>
				<td>아이디</td>
				<td>이름</td>
				<td>강의과목</td>
				<td>강의명</td>
				<td>제목</td>
				<td>글내용</td>
				<td>날짜</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td>${requestScope.lectureListReview.reviewNo}</td><!--  오류난거메모 : 상세페이지니까 반복문 돌리면 안됨 -->
 					<td>${requestScope.lectureListReview.reviewWriterId}</td>
 					<td>${requestScope.lectureListReview.reviewWriter}</td>
					<td>${requestScope.lectureListReview.lectureSubject}</td>
					<td>${requestScope.lectureListReview.lectureTitle}</td>
					<td>${requestScope.lectureListReview.reviewTitle}</td>
					<td>${requestScope.lectureListReview.reviewContent}</td>
					<td>${requestScope.lectureListReview.reviewDate}</td>
					<td>${requestScope.lectureListReview.reviewHit}</td> 
				</tr>

		</tbody>	
	</table>
	

	
<!-- --------------------------------------------------------------------------------------------------------- -->

<!-- 관리자랑 학생(본인글)일 경우만    -    삭제버튼,수정버튼 클릭가능 -->
<span class="lectureRegister">
	<c:if test="${sessionScope.memberType=='administrator'}">
<a href="/UspaceAcademy/lectureReview/lecture_review_delete.do?reviewNo=${lectureListReview.reviewNo}"><button>삭제버튼</button></a><!-- 삭제할때 No값 넘겨줘야함*  -->
<a href="/UspaceAcademy/lectureReview/lecture_review_modifyForm.do?reviewNo=${lectureListReview.reviewNo}&codeType=teacherSubject"><button>수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->
<%-- <a href="/UspaceAcademy/lectureReview/lecture_review_modifyForm.do?reviewNo=${lectureListReview.reviewNo}&codeType=teacherSubject"><button>수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->            --%> 
	</c:if>																																											
	<c:if test="${sessionScope.memberType=='student'&&sessionScope.login_info.studentId==lectureListReview.reviewWriterId}">
<a href="/UspaceAcademy/lectureReview/lecture_review_delete.do?reviewNo=${lectureListReview.reviewNo}"><button>삭제버튼</button></a><!-- 삭제할때 No값 넘겨줘야함*  -->
<a href="/UspaceAcademy/lectureReview/lecture_review_modifyForm.do?reviewNo=${lectureListReview.reviewNo}&codeType=teacherSubject"><button>수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->
	</c:if>
</span>
<a href="/UspaceAcademy/lectureReview/lecture_review_list.do?reviewNo"><button>전체목록</button></a>
<!-- --------------------------------------------------------------------------------------------------------- -->

