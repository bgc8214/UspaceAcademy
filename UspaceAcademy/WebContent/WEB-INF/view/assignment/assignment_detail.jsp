<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h3>내정보 | <a href="/UspaceAcademy/assignment/assignment_list.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}">과제게시판</a> | 상세보기</h3>
<hr/>
<style type="text/css">
#thx {
	width: 150px;
}
table{
/* table-layout: fixed;
border:1px solid #dcdcdc;
color:#353535;
line-height:180%; */
}
.notice_content{
/* 	font-size:18px;
    position: relative;
    display: inline-block;
    padding: 40px;
    width: 700px;
    background: #ffffff;
    border: 1px solid #d4d4d4;
    float: left;
    text-align: left;
    min-height: 580px;
    margin-bottom: 20px; */

}
td{
/*     padding: 15px 0;
    border-top: 1px solid #eee;
    text-align: center;
    padding: 15px 0; */
}
</style>




<table class="table table-bordered form-table" id="tb">
	<caption></caption>
<tbody>

<tr>
<th scope="col">아이디</th>	
<td>${assignment.assignmentWriterId}</td>
<th scope="col">이름</th>	
<td>${assignment.assignmentWriter}</td>
</tr>
	
	
<tr>
<th scope="col">작성일</th>	
<td>${assignment.assignmentDate}</td>
<th scope="col">마감일</th>	
<td>${assignment.assignmentDeadline}</td>
</tr>

<th scope="col">조회수</th>	
<td>${assignment.assignmentHit}</td>

<th scope="col">파일받기</th><!--  jstl 라이브러리 등록하기(pom.xml하고, 위에 taglib-->
<td>
<a href="/UspaceAcademy/uploadFile/${assignment.assignmentFile }">${assignment.assignmentFile }</a>

</tr>
</table>




<table class="table table-bordered form-table">
<tr>
<th id="thx">제목</th>	
<td>${assignment.assignmentTitle}</td>
</tr> 

<tr>
<th scope="col">내용</th>	
<td>${assignment.assignmentContent}<br><br><br></td>
</tr>


</td>
</tr>
</table>
							<%-- <h4>DownloadView를 이용해 다운처리</h4> com.uspaceacademy.view패키지에있음
							<c:forEach items="${requestScope.fileNames}" var="fileName">
							<a href="/UspaceAcademy/assignment/download.do?fileName=${fileName }">${fileName }</a><!--  링크가 download.do로 걸림* -->
							</c:forEach> --%>





</tbody>
</table>
</div>



<!--   하든값 -->
<input type="hidden" name="replyStep"  value="${assignment.replyStep}">
<input type="hidden" name="replyLevel"  value="${assignment.replyLevel}">
<input type="hidden" name="replyFamily"  value="${assignment.replyFamily}">
<input type="hidden" name="assignmentNo" value="${assignment.assignmentNo}">
<input type="hidden" name="lectureNo" value="${assignemnt.lectureNo}">


<!-- --------------------------------------------------------------------------------------------------------- -->

<!-- 강사이고 강사본인이 쓴 글일경우만    -    삭제버튼,수정버튼 클릭가능(로그인한아이디랑 작성글아이디비교 -->
<span class="assignmentRegister">
	<c:if test="${sessionScope.memberType=='teacher'&&assignment.assignmentWriterId==sessionScope.login_info.teacherId}"><!-- ok 강사 본인글만 삭제,수정할 수 있음 -->
<a href="/UspaceAcademy/assignment/assignment_modifyForm.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}"><button class="btn btn-warning">수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->
<a href="/UspaceAcademy/assignment/assignment_delete.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}"><button class="btn btn-danger">삭제버튼</button></a><!-- 삭제할때 No값 넘겨줘야함*  -->
</c:if>

<c:choose>
	<c:when test="${sessionScope.memberType=='student'&&sessionScope.login_info.studentId==assignment.assignmentWriterId}">
<a href="/UspaceAcademy/assignment/assignment_modifyFormStudent.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}"><button class="btn btn-warning">수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->
<a href="/UspaceAcademy/assignment/assignment_delete.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}"><button class="btn btn-danger">삭제버튼</button></a><!-- 삭제할때 No값 넘겨줘야함*  -->
	</c:when> 	
	<c:when test="${sessionScope.memberType=='student'&&assignment.replyLevel>1 == false}"> <!--  학생만답글달수있고 & & 강사글에만 답글달기 기능있음(저뜻 : 답글false-> 그냥글일경우만 : 답글버튼있다 -->
	<a href="/UspaceAcademy/assignment/assignment_replyRegister.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}"><button class="btn btn-success">답글달기</button></a>																																									
	</c:when> 
</c:choose>

</span>
<!-- 전체볼수있는 - 버튼 -->
<a href="/UspaceAcademy/assignment/assignment_list.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}"><button class="btn btn-primary">전체목록</button></a>
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

