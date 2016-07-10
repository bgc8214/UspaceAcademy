<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!--  밸리 해주려면 이거선언* -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %><!--  선언  -->
<style type="text/css">
table#tb {
	width: 800px;
	heiht: 100px;
}
</style>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jquery-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 	//폼체크
		  $("#ok").on("click",function(){
			 	 	if(!$("input[name=reviewTitle]").val()){
			 			alert("제목을 입력하세요");
			 			return false;
			 		}
			 		if($("textarea[name=reviewContent]").val()==""){
			 			alert("내용을 입력하세요");
			 			return false;
			 		}
			 	}); 
			 
	 
	$("#lectureSubject").on("change", function(){
	var tmp = $(this);
	$.ajax({
		"url":"/UspaceAcademy/lectureReview/selectLectureTitleByLectureSubject.do",
		"type":"POST", 
		"data":"lectureSubject="+tmp.val(),
		"dataType":"json",
		"success":function(list){
			for(var i=0; i<list.length; i++){
				var lectureTitle = list[i].lectureTitle;
				$("#lectureTitle").append("<option>"+lectureTitle+"</option>");
			}
		},
		"error":function(xhr,status,errorMsg){
			alert("오류가발생했습니다"+status+","+errorMsg);
		},
		"beforeSend":function(){
			$("#lectureTitle").empty();
		}
	});
});                 //과목에따른 강의명   //●오류: 오타조심!!
});                //●오류: 가로닫는거 위치랑 갯수 (위아래)조심!

</script>


<title>lectureReview_register.jsp</title>
<h3 class="pageTlt">수강후기|등록하기</h3>
<hr>
<form method="POST" action="/UspaceAcademy/lectureReview/lecture_review_registerSuccess.do"> <!--  폼으로 묶기* -->
<div align="center">
<table class="table table-bordered" id="tb">
	<tr>
		<th>아이디</th><!--  id추가 -->
		<td>${sessionScope.login_info.studentId}</td>
		<th>이름</th>
		<td>${sessionScope.login_info.studentName}</td>
		<!--  ●오류났던거 적기 - 글등록할때 회원이름 찍어야하는데 안찍힘 : memberController에 있는거 가져와야함->학생으로로 로그인 성공																					Student [studentId=id-2, studentPassword=2222, studentName=김수진, studentEmail=iidd2@kosta.com, studentPhoneNo=010-1111-2222, studentAddress=경기도 수원시 장안구 파장동] 	, requestScope가아니고 requestSession임 studentName 이름찍어야함-->
	</tr>
	<tr>
		<th>과목</th>
		<td>
			<!--  강의 과목 (코드테이블) --> <select name="lectureSubject"
				id="lectureSubject">
					<c:forEach items="${requestScope.codeType }" var="code">
						<!--  컨트롤러* -->
						<option value="${code.codeName}">${code.codeName }</option>
						<!--  vo ??* -->
					</c:forEach>
					<span class="error"><form:errors
							path="lectureReview.lectureSubject" /></span>
			</select>
		</td>	
			<th>강의명</th>
			<td>
				<!--  강의명(lecture(개설강좌)에서 가져옴) --> 
				<select id="lectureTitle" name="lectureTitle"> 	<!--  ●아래 select id에서 name으로 바꿔줌 * -->
					<c:forEach items="${requestScope.lectureTitle}" var="lectureReviewList">
						<!--  컨트롤러에서 보낸값 -->
						<option value="${lectureReviewList.lectureTitle}">${lectureReviewList.lectureTitle}</option>
					</c:forEach>
			</select>
			</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="3">
				<input type="text" name="reviewTitle" size="70" placeholder="제목을 입력하세요" required="required"><span class="error"><form:errors path="lectureReview.reviewTitle" delimiter="//"/></span>
				</td>
			</tr>
			<tr>
			<th>내용</th>
			<td colspan="3">
				<textarea class="form-control" rows="20" cols="100" name="reviewContent" placeholder="입력하세요"></textarea><span class="error"><form:errors path="lectureReview.reviewContent" delimiter="//"/></span>

			</table>

</div>


	<input  id="ok" type="submit" value="수강후기등록완료" class="btn btn-success">
	<input type="reset" value="초기화" class="btn btn-default"> 
</form>









