<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h3>내정보 | 과제게시판 | 상세보기</h3>
<hr/>
<style type="text/css">
table{
table-layout: fixed;
border:1px solid #dcdcdc;
color:#353535;
line-height:180%;
}
.notice_content{
	font-size:18px;
    position: relative;
    display: inline-block;
    padding: 40px;
    width: 700px;
    background: #ffffff;
    border: 1px solid #d4d4d4;
    float: left;
    text-align: left;
    min-height: 580px;
    margin-bottom: 20px;

}
td{
    padding: 15px 0;
    border-top: 1px solid #eee;
    text-align: center;
    padding: 15px 0;
}
.notice_content h5.notice_line {
    font-size: 35px;
    font-family: 'Nanum Gothic Bold';
    color: #474747;
    position: relative;
    padding: 0 195px 15px 0;
}
</style>











<div class="notice_content">
<h5 class="notic_line">${assignment.assignmentTitle}</h5>
<p>

<table  board="1" class="table_list" summary="영주" cellpacing="0">
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
<th scope="col">조회수</th>	
<td>${assignment.assignmentHit}</td>
</tr>



<!-- <tr>
<th scope="col">강의명</th>
</tr>
 -->

<%-- <tr>
<th scope="col">제목</th>	
<td>${assignment.assignmentTitle}</td>
</tr> --%>

<tr>
<th scope="col">내용</th>	
<td>${assignment.assignmentContent}</td>
</tr>

<tr>
<th scope="col">파일받기</th><!--  jstl 라이브러리 등록하기(pom.xml하고, 위에 taglib-->
<td>
<a href="/UspaceAcademy/uploadFile/${assignment.assignmentFile }">${assignment.assignmentFile }</a>

</td>
</tr>
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



<!-- --------------------------------------------------------------------------------------------------------- -->
<!-- 강사이고 강사본인이 쓴 글일경우만    -    삭제버튼,수정버튼 클릭가능(로그인한아이디랑 작성글아이디비교 -->
<span class="assignmentRegister">
	<c:if test="${sessionScope.memberType=='teacher'&&assignment.assignmentWriterId==sessionScope.login_info.teacherId}"><!-- ok 강사 본인글만 삭제,수정할 수 있음 -->
<a href="/UspaceAcademy/assignment/assignment_delete.do?assignmentNo=${assignment.assignmentNo}"><button>삭제버튼</button></a><!-- 삭제할때 No값 넘겨줘야함*  -->
<a href="/UspaceAcademy/assignment/assignment_modifyForm.do?assignmentNo=${assignment.assignmentNo}"><button>수정버튼</button></a><!-- 수정할때도 No값 넘겨줘야함*  -->
<!-- &&sessionScope.login_info.studentId==assignment.assignmentWriterId  -->
<%-- <c:if test="${sessionScope.memberType=='teacher'&&sessionScope.memberType=='teacher'==requestScope.assignment.assignmentWriterId}"> --%>

	</c:if>
<c:if test="${sessionScope.memberType=='student'}">
<%-- <c:if test="${sessionScope.memberType=='student'&&assignment.assignmentWriter==requestScope.memberType.teacher}"> --%>
<a href="/UspaceAcademy/assignment/assignment_replyRegister.do?assignmentNo=${assignment.assignmentNo}"><button>답글달기</button></a>
	
	</c:if>																																											
</span>



<!-- --------------------------------------------------------------------------------------------------------- -->	


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











