<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<h3>내정보 | 과제게시판 | 상세보기</h3>
<hr/>
<!--    -->
<input type="hidden" name="replyStep"  value="${assignment.replyStep}">
<input type="hidden" name="replyLevel"  value="${assignment.replyLevel}">
<input type="hidden" name="replyFamily"  value="${assignment.replyFamily}">

<!--  번호 - 히든값 -->
<input type="hidden" name="assignmentNo" value="${assignment.assignmentNo}">

<div class="boardList">
	<table border="1" summary="">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>제목</th>
			<th>작성일</th>
			<th>마감일</th>
			<th>조회수</th>
			<th>내용</th>
		</tr>
		<tr>
			<td>${assignment.assignmentWriterId}</td>
			<td>${assignment.assignmentWriter}</td>
			<td>${assignment.assignmentTitle}</td>
			<td>${assignment.assignmentDate}</td>
			<td>${assignment.assignmentDeadline}</td>
			<td>${assignment.assignmentHit}</td>
			<td>${assignment.assignmentContent}</td>
		</tr>

	</table>
</div>

<!-- --------------------------------------------------------------------------------------------------------- -->
<!-- 강사만 경우만    -    삭제버튼,수정버튼 클릭가능 -->
<span class="assignmentRegister">
	<c:if test="${sessionScope.memberType=='teacher'}">
<a href="/UspaceAcademy/assignment/assignment_delete.do?assignmentNo=${assignment.assignmentNo}"><button>삭제버튼</button></a><!-- 삭제할때 No값 넘겨줘야함*  -->
<a href="/UspaceAcademy/assignment/assignment_modifyForm.do?assignmentNo=${assignment.assignmentNo}"><button>수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->
<%-- <c:if test="${sessionScope.memberType=='teacher'&&sessionScope.memberType=='teacher'==requestScope.assignment.assignmentWriterId}"> --%>
<%-- <a href="/UspaceAcademy/lectureReview/lecture_review_modifyForm.do?reviewNo=${lectureListReview.reviewNo}&codeType=teacherSubject"><button>수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->            --%> 

	</c:if>
<c:if test="${sessionScope.memberType=='student'}">
<%-- <c:if test="${sessionScope.memberType=='student'&&assignment.assignmentWriter==requestScope.memberType.teacher}"> --%>
<a href="/UspaceAcademy/assignment/assignment_replyRegister.do?assignmentNo=${assignment.assignmentNo}"><button>답글달기</button></a>
	
	</c:if>																																											
</span>
<!-- --------------------------------------------------------------------------------------------------------- -->
<%-- 
사진 파일명 ${requestScope.imageName }
사진 크기 ${requsetScope.imageSize }byte
사진설명 ${requestScope.comment }
<p>
<img src="/UspaceAcademy/upImage/${requestScope.imageName}"/>  






<hr>
<!-- jstl 라이브러리 등록하기(pom.xml하고, 위에 taglib-->
<h2>글내용</h2>
글제목 ${requestScope.title }
글내용 ${requestScope.content }
파일<br>
<c:forEach items="${requestScope.fileNames }"  var="fileName">
<a href="/UspaceAcademy/uploadFile/${fileName }">${fileName }</a><br>
</c:forEach>
<hr>






<!--  DownloadView  -->
<h3>DownloadView를 이용해 다운처리</h3>
<c:forEach items="${requestScope.fileNames}" var="fileName">
<a href="/UspaceAcademy/download.do?fileName=${fileName }">${fileName }</a><!--  링크가 download.do로 걸림* -->
</c:forEach>
 --%>
<!-- --------------------------------------------------------------------------------------------------------- -->








































<%-- 
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<h3>내정보 | 과제게시판 | 상세보기</h3>
<hr/>
<!--    -->
<input type="hidden" name="replyStep"  value="${assignment.replyStep}">
<input type="hidden" name="replyLevel"  value="${assignment.replyLevel}">
<input type="hidden" name="replyFamily"  value="${assignment.replyFamily}">


<div class="boardList">
	<table border="1" summary="">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성일</th>
			<th>마감일</th>
			<th>조회수</th>
		</tr>
		<tr>
			<td>${assignment.assignmentNo}</td>
			<td>${assignment.assignmentWriterId}</td>
			<td>${assignment.assignmentWriter}</td>
			<td>${assignment.assignmentTitle}</td>
			<td>${assignment.assignmentContent}</td>
			<td>${assignment.assignmentDate}</td>
			<td>${assignment.assignmentDeadline}</td>
			<td>${assignment.assignmentHit}</td>
		</tr>

	</table>
</div>

<!-- --------------------------------------------------------------------------------------------------------- -->
<!-- 강사만 경우만    -    삭제버튼,수정버튼 클릭가능 -->
<span class="assignmentRegister">
	<c:if test="${sessionScope.memberType=='teacher'}">
<a href="/UspaceAcademy/assignment/assignment_delete.do?assignmentNo=${assignment.assignmentNo}"><button>삭제버튼</button></a><!-- 삭제할때 No값 넘겨줘야함*  -->
<a href="/UspaceAcademy/assignment/assignment_modifyForm.do?assignmentNo=${assignment.assignmentNo}"><button>수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->
<a href="/UspaceAcademy/lectureReview/lecture_review_modifyForm.do?reviewNo=${lectureListReview.reviewNo}&codeType=teacherSubject"><button>수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->            
	</c:if>
<c:if test="${sessionScope.memberType=='student'&&assignment.assignmentWriter==requestScope.memberType.student}">
<a href="/UspaceAcademy/assignment/assignment_replyRegister.do?assignmentNo=${assignment.assignmentNo}"><button>답글달기</button></a>
	
	</c:if>																																											
</span>
<!-- --------------------------------------------------------------------------------------------------------- -->

사진 파일명 ${requestScope.imageName }
사진 크기 ${requsetScope.imageSize }byte
사진설명 ${requestScope.comment }
<p>
<img src="/UspaceAcademy/upImage/${requestScope.imageName}"/>  






<hr>
<!-- jstl 라이브러리 등록하기(pom.xml하고, 위에 taglib-->
<h2>글내용</h2>
글제목 ${requestScope.title }
글내용 ${requestScope.content }
파일<br>
<c:forEach items="${requestScope.fileNames }"  var="fileName">
<a href="/UspaceAcademy/uploadFile/${fileName }">${fileName }</a><br>
</c:forEach>
<hr>






<!--  DownloadView  -->
<h3>DownloadView를 이용해 다운처리</h3>
<c:forEach items="${requestScope.fileNames}" var="fileName">
<a href="/UspaceAcademy/download.do?fileName=${fileName }">${fileName }</a><!--  링크가 download.do로 걸림* -->
</c:forEach>

<!-- --------------------------------------------------------------------------------------------------------- -->


 --%>


