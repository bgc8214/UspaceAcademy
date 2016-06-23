<!--  처음에 뜨는 html소스 이런거 다 삭제해주고 시작* -->


<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->

<title>lectureReview_modify.jsp</title>


<h2>수강후기|수정</h2>



<form method="POST" action="/UspaceAcademy/lectureReview/lecture_review_modify.do"> <!--  폼으로 묶기* -->

 <input type="hidden" name="reviewNo" value="${requestScope.lectureListReview.reviewNo}">
<input type="hidden" name="reviewWriter" value="${requestScope.lectureListReview.reviewWriter}">
<input type="hidden" name="reviewDate" value="${requestScope.lectureListReview.reviewDate }">
<input type="hidden" name="reviewHit" value="${requestScope.lectureListReview.reviewHit }">
 
<hr/>
		<thead>
			<tr>
				<td><!-- 강의과목 --></td>
				<td><!-- 강의명 --></td>
				<td><!-- 제목 --></td>
				<td><!-- 글내용 --></td>
			</tr>
		</thead>
		<tbody>

				<tr>
					<td><select name="lectureSubject"><option>국어</option><option>영어</option><option>수학</option></select></td>
					<td><select name="lectureTitle"><option value="국어 고등1">국어 고등1</option><option value="영어 고등1">영어 고등1</option><option value="수학 고등1">수학 고등1</option></select></td>
					<td><input type="text" value="${requestScope.lectureListReview.reviewTitle }" name="title" size="70" placeholder="제목을 입력하세요" required="required"></td><!-- 작성  -->
					<td><textarea rows="20" cols="100" name="content" placeholder="입력하세요">${requestScope.lectureListReview.reviewContent }</textarea></td><!-- 작성  -->
				</tr>

		</tbody>	
	</table> 
	<input type="submit" value="수강후기수정완료">
</form>
<input type="reset" value="초기화"/> 








