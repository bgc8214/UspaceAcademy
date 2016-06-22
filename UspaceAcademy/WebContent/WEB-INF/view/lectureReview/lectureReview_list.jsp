
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->

<h5>수강후기</h5>


<hr/>
	<table border="2">
		<thead>
			<tr>
				<td>글번호</td>
				<td>글쓴이</td>
				<td>강의과목</td>
				<td>강의명</td>
				<td>제목</td>
				<td>날짜</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="lectureListReview" items="${requestScope.lectureListReview}">
				<tr>
					<td>${lectureListReview.reviewNo}</td>
					<td>${lectureListReview.reviewWriter}</td>
					<td>${lectureListReview.lectureTitle}</td>
					<td>${lectureListReview.lectureSubject}</td>
					
					<td><a href="/UspaceAcademy/lectureReview/lecture_review_detail.do?reviewNo=${lectureListReview.reviewNo} "> <!--  한개값넘겨줄때 ? 물음표,  여러개 넘겨줄때 & 앤드.   -->
							${lectureListReview.reviewTitle}</a></td>
					
					<td>${lectureListReview.reviewDate}</td>
					<td>${lectureListReview.reviewHit}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table>

<input type="submit" value="수강후기등록"/>
<a href="/UspaceAcademy/lectureReview/lecture_review_register.do">수강후기 등록</a>  <!--  controller에서 jsp로 전달하는 구문써야함 -->
















