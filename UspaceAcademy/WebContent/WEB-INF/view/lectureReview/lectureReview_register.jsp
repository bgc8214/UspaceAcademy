<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!--  밸리 해주려면 이거선언* -->

<title>lectureReview_register.jsp</title>


<h2>수강후기|등록하기</h2>



<form method="POST" action="/UspaceAcademy/lectureReview/lecture_review_registerSuccess.do"> <!--  폼으로 묶기* -->



<hr/>
			<!--글번호,글쓴이,강의과목,강의명,제목,글내용,날짜,조회수 -->
		
					글쓴이 : ${sessionScope.login_info.studentName}  <!--  오류났던거 적기 - 글등록할때 회원이름 찍어야하는데 안찍힘 : memberController에 있는거 가져와야함->학생으로로 로그인 성공
																							Student [studentId=id-2, studentPassword=2222, studentName=김수진, studentEmail=iidd2@kosta.com, studentPhoneNo=010-1111-2222, studentAddress=경기도 수원시 장안구 파장동] 
																							, requestScope가아니고 requestSession임 studentName 이름찍어야함-->

					<!--  아래 select id에서 name으로 바꿔줌 * -->
					
					<select name="lectureSubject">
					<c:forEach items="${requestScope.codeType }" var="code"> <!--  컨트롤러* -->
					<option value="${code.codeName}">${code.codeName }</option> <!--  vo ??* -->
					</c:forEach>
					<span class="error"><form:errors path="lectureReview.lectureSubject"/></span>
					</select>
					
					<select name="lectureTitle"><option value="국어 고등1">국어 고등1</option><option value="영어 고등1">영어 고등1</option><option value="수학 고등1">수학 고등1</option></select><span class="error"><form:errors path="lectureReview.lectureTitle" delimiter="//"/></span>
					<input type="text" name="reviewTitle" size="70" placeholder="제목을 입력하세요" required="required"><span class="error"><form:errors path="lectureReview.reviewTitle" delimiter="//"/></span>
					<textarea rows="20" cols="100" name="reviewContent" placeholder="입력하세요"></textarea><span class="error"><form:errors path="lectureReview.reviewContent" delimiter="//"/></span>




	<input type="submit" value="수강후기등록완료">
	<input type="reset" value="초기화"/> 
</form>









