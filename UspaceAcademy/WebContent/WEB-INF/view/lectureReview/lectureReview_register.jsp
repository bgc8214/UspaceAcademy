<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->
<title>lectureReview_register.jsp</title>


<h2>수강후기|등록하기</h2>





<hr/>
	<table border="2">
		<thead>
			<tr>
				<td>글번호</td>
				<td>글쓴이</td><!--  회원정보에 이름가져와야됨 -->
				<td>강의과목</td><!--  코드테이블? -->
				<td>강의명</td><!--  코드테이블 -->
				<td>제목</td>
				<td>글내용</td>
				<td>날짜</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>

				<tr>
					<td>ㅇ</td><!--   -->
 					<td>ㅇ</td><!--   -->
					<td>ㅇ</td><!--   -->
					<td>ㅇ</td><!--   -->
					<td><input type="text" name="title" size="70" placeholder="제목을 입력하세요" required="required"></td><!-- 작성  -->
					<td><textarea rows="20" cols="100" name="content">입력하세요</textarea></td><!-- 작성  -->
					<td>ㅇ</td><!--   -->
					<td>ㅇ</td> <!--   -->
				</tr>

		</tbody>	
	</table> 
<input type="submit" value="수강후기등록"/> <input type="reset" value="초기화"/> 






<%-- 					<td>${requestScope.lectureListReview2.reviewNo}</td><!--   -->
 					<td>${requestScope.lectureListReview2.reviewWriter}</td><!--   -->
					<td>${requestScope.lectureListReview2.lectureTitle}</td><!--   -->
					<td>${requestScope.lectureListReview2.lectureSubject}</td><!--   -->
					<td>제목<input type="text" name="title" size="70" placeholder="제목을 입력하세요" required="required"></td><!-- 작성  -->
					<td>수강후기내용<textarea rows="20" cols="100" name="content">입력하세요</textarea></td><!-- 작성  -->
					<td>${requestScope.lectureListReview2.reviewDate}</td><!--   -->
					<td>${requestScope.lectureListReview2.reviewHit}</td> <!--   --> --%>



