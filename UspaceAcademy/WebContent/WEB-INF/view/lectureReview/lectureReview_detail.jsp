<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->

<h5>수강후기|상세보기</h5>


<hr/>
	<table border="2">
		<thead>
			<tr>
				<td>글번호</td>
				<td>글쓴이</td>
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
 					<td>${requestScope.lectureListReview.reviewWriter}</td>
					<td>${requestScope.lectureListReview.lectureTitle}</td>
					<td>${requestScope.lectureListReview.lectureSubject}</td>
					<td>${requestScope.lectureListReview.reviewTitle}</td>
					<td>${requestScope.lectureListReview.reviewContent}</td>
					<td>${requestScope.lectureListReview.reviewDate}</td>
					<td>${requestScope.lectureListReview.reviewHit}</td> 
				</tr>

		</tbody>	
	</table>

<a href="/UspaceAcademy/lectureReview/lecture_review_delete.do?reviewNo=${lectureListReview.reviewNo}">삭제버튼</a><!-- 삭제할때 No값 넘겨줘야함*  -->
<a href="/UspaceAcademy/lectureReview/lecture_review_modifyForm.do?reviewNo=${lectureListReview.reviewNo}">수정버튼</a><!-- 수정할때도 No값 넘겨줘야함*  -->



