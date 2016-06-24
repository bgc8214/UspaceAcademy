<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(document).ready(function(){
		$(".inquiryComment").on("click", function(){
			$.ajax({
				"url":"/UspaceAcademy/inquiry/insertComment.do",
				"type":"POST",
				"data":"commentNo",
				"dataType":"json",
				"success":function(){
					
				},
				"error":function(xhr, status, errorMsg){
					alert("오류가 발생했습니다. " + status + ", " + errorMsg);
				}
	/* 			"beforeSend":function(){
					
				} */
			})
		})
	})
})
</script>
<form method="post" action="/UspaceAcademy/comment/insertCommentform.do">
	<input type="hidden" name="commentDate" value="">
	<textarea name="commentContent" rows="25" cols="50"></textarea>
	<input type="submit" value="댓글 등록">
</form>