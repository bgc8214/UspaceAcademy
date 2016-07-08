<!--  처음에 뜨는 html소스 이런거 다 삭제해주고 시작* -->
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!--  밸리 해주려면 이거선언* -->
<style type="text/css">


</style>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jquery-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#lectureSubject").on("change",function(){
		var tmp = $(this);
		$.ajax({
			"url":"/UspaceAcademy/lectureReview/selectLectureTitleByLectureSubject.do",
			"type":"POST",
			"data":"lectureSubject="+tmp.val(),
			"dataType":"json",
			"success":function(list){
				for(var i = 0; i<list.length; i++){
					var lectureTitle = list[i].lectureTitle;
					$("#lectureTitle").append("<option>"+lectureTitle+"</option>");
				}
			}, //오류메세지
			"error":function(xhr,status,errorMsg){
				alert("오류가발생했습니다"+status+","+errorMsg);
			},
			"beforeSend":function(){
				$("#lectureTitle").empty();
			}
		})
	})
});


</script>







<title>lectureReview_modify.jsp</title>
<h2>수강후기|수정</h2>



<form method="POST" action="/UspaceAcademy/lectureReview/lecture_review_modify.do"> <!--  폼으로 묶기* -->

 <input type="hidden" name="reviewNo" value="${requestScope.lectureListReview.reviewNo}">
 <input type="hidden" name="reviewWriterId" value="${requestScope.lectureListReview.reviewWriterId}"><!--  id추가 -->
<input type="hidden" name="reviewWriter" value="${requestScope.lectureListReview.reviewWriter}">
<input type="hidden" name="reviewDate" value="${requestScope.lectureListReview.reviewDate }">
<input type="hidden" name="reviewHit" value="${requestScope.lectureListReview.reviewHit }">
 
<hr/>
			<tr>
				<td><!-- 강의과목 --></td>
				<td><!-- 강의명 --></td>
				<td><!-- 제목 --></td>
				<td><!-- 글내용 --></td>
			</tr>
		<tbody>

				<tr>
				<select name="lectureSubject" id="lectureSubject">
					<c:forEach items="${requestScope.codeType }" var="code"> <!--  컨트롤러* -->
					<option value="${code.codeName }">${code.codeName }</option> <!--  vo ??* -->
					</c:forEach>
					<span class="error"><form:errors path="lec.lectureSubject"/></span>
					</select>
					
					<!--  강의명(lecture(개설강좌)에서 가져옴) -->
					<select id="lectureTitle" name="lectureTitle">
					<c:forEach items="${requestScope.lectureTitle}"  var="lectureReviewList">
					<option value="${lectureReviewList.lectureTitle}">${lectureReviewList.lectureTitle}</option>
					</c:forEach>
					</select>
					


					<td><input type="text" name="reviewTitle" value="${requestScope.lectureListReview.reviewTitle }" name="title" size="70" placeholder="제목을 입력하세요" required="required"><span class="error"><form:errors path="lec.reviewTitle" delimiter="//"/></span></td><!-- 작성  -->
					<td><textarea rows="20" cols="100" name="reviewContent" placeholder="입력하세요">${requestScope.lectureListReview.reviewContent }</textarea><span class="error"><form:errors path="lec.reviewContent" delimiter="//"/></span></td><!-- 작성  -->
				</tr>

		</tbody>	
	</table> 
	<input type="submit" value="후기 수정 완료">
	<input type="reset" value="초기화"/> 
</form>









