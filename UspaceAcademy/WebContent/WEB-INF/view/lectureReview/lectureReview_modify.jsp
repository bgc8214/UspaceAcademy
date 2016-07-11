<!--  처음에 뜨는 html소스 이런거 다 삭제해주고 시작* -->
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!--  밸리 해주려면 이거선언* -->
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


<title>lectureReview_modify.jsp</title>
<h3 class="pageTlt">수강후기|수정</h3>


<form method="POST" action="/UspaceAcademy/lectureReview/lecture_review_modify.do"> <!--  폼으로 묶기* -->

 <input type="hidden" name="reviewNo" value="${requestScope.lectureListReview.reviewNo}">
 <input type="hidden" name="reviewWriterId" value="${requestScope.lectureListReview.reviewWriterId}"><!--  id추가 -->
<input type="hidden" name="reviewWriter" value="${requestScope.lectureListReview.reviewWriter}">
<input type="hidden" name="reviewDate" value="${requestScope.lectureListReview.reviewDate }">
<input type="hidden" name="reviewHit" value="${requestScope.lectureListReview.reviewHit }">
 <table class="table table-bordered" id="tb">
			<tr>
				<th>과목</th>
				<td>
				<select name="lectureSubject" id="lectureSubject">
					<c:forEach items="${requestScope.codeType }" var="code"> <!--  컨트롤러* -->
					<option value="${code.codeName }">${code.codeName }</option> <!--  vo ??* -->
					</c:forEach>
					<span class="error"><form:errors path="lec.lectureSubject"/></span>
				</select>
				</td>
				<th>강의명</th>
				<td>
					<!--  강의명(lecture(개설강좌)에서 가져옴) -->
					<select id="lectureTitle" name="lectureTitle">
					<c:forEach items="${requestScope.lectureTitle}"  var="lectureReviewList">
					<option value="${lectureReviewList.lectureTitle}">${lectureReviewList.lectureTitle}</option>
					</c:forEach>
					</select>
				</td>
			</tr>
				<tr>
					<th>제목</th>
					<td colspan="3"><input type="text" name="reviewTitle" value="${requestScope.lectureListReview.reviewTitle }" name="title" size="70" placeholder="제목을 입력하세요" required="required"><span class="error"><form:errors path="lec.reviewTitle" delimiter="//"/></span></td><!-- 작성  -->
				</tr>
				<tr>
					<th>내용</th>	
					<td colspan="3"><textarea rows="20" cols="100" name="reviewContent" placeholder="입력하세요">${requestScope.lectureListReview.reviewContent }</textarea><span class="error"><form:errors path="lec.reviewContent" delimiter="//"/></span></td><!-- 작성  -->
				</tr>
		</tbody>	
	</table> 
	<div align="right">
	<input id="ok" type="submit" value="후기 수정 완료" class="btn btn-warning">
	<input type="reset" value="초기화" class="btn btn-default"> 
	</div>
</form>









