<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!--  밸리 해주려면 이거선언* -->

<title>lectureReview_register.jsp</title>


<h2>수강후기|등록하기</h2>



<form method="POST" action="/UspaceAcademy/lectureReview/lecture_review_registerSuccess.do"> <!--  폼으로 묶기* -->



<hr/>
	<table border="1">
		<thead>
			<tr>
<!-- 				<td>글번호</td>
				<td>글쓴이</td> 회원정보에 이름가져와야됨 -->
				<td><!-- 강의과목 --></td><!--  코드테이블? -->
				<td><!-- 강의명 --></td><!--  코드테이블 -->
				<td><!-- 제목 --></td>
				<td><!-- 글내용 --></td>
<!-- 				<td>날짜</td>
				<td>조회수</td> -->
			</tr>
		</thead>
		<tbody>

				<tr>
					<!--  아래 select id에서 name으로 바꿔줌 * -->
					
					<select name="lectureSubject">
					<c:forEach items="${requestScope.codeType }" var="code"> <!--  컨트롤러* -->
					<option value="${code.codeId }">${code.codeName }</option> <!--  vo ??* -->
					</c:forEach>
					<span class="error"><form:errors path="lectureReview.lectureSubject"/></span>
					</select>
					
					<td><select name="lectureTitle"><option value="국어 고등1">국어 고등1</option><option value="영어 고등1">영어 고등1</option><option value="수학 고등1">수학 고등1</option></select><span class="error"><form:errors path="lectureReview.lectureTitle" delimiter="//"/></span></td>
					<td><input type="text" name="reviewTitle" size="70" placeholder="제목을 입력하세요" required="required"><span class="error"><form:errors path="lectureReview.reviewTitle" delimiter="//"/></span></td><!-- 작성  -->
					<td><textarea rows="20" cols="100" name="reviewContent" placeholder="입력하세요"></textarea><span class="error"><form:errors path="lectureReview.reviewContent" delimiter="//"/></span></td><!-- 작성  -->
				</tr>

		</tbody>	
	</table> 
	<input type="submit" value="수강후기등록완료">
	<input type="reset" value="초기화"/> 
</form>









