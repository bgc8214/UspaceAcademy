<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->
<style type="text/css">


</style>
<h3>수강후기|상세보기</h3>
<hr/>
<table border="1">
	<tr>
		<td>글번호: ${requestScope.lectureListReview.reviewNo}&nbsp;<!--  오류난거메모 : 상세페이지니까 반복문 돌리면 안됨 -->
			아이디: ${requestScope.lectureListReview.reviewWriterId}&nbsp;
			이름:${requestScope.lectureListReview.reviewWriter}&nbsp;
		</td>
	</tr>

	<tr>
		<td>강의과목:${requestScope.lectureListReview.lectureSubject}&nbsp;
			강의명:${requestScope.lectureListReview.lectureTitle}&nbsp;
			날짜:${requestScope.lectureListReview.reviewDate}&nbsp;</td>
	</tr>

	<tr>
		<td>제목: ${requestScope.lectureListReview.reviewTitle}</td>
	</tr>

	<tr>
		<td>내용<br> ${requestScope.lectureListReview.reviewContent}
		</td>
	</tr>

</table>



<input type="hidden" name="reviewHit" value="${requestScope.lectureListReview.reviewHit}">
	
	
	
	
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

