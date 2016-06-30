<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
tr.dummy, td.dummy{
	border:0px;
}
</style>
<script type="text/javascript">
var tmp;//이벤트소스를 저장하기 위한 변수
$(document).ready(function(){	
	$("#btn").on("click",function(){
		tmp = $(this);
		$.ajax({
			"url":"/UspaceAcademy/inquiry/exInsertComment.do", //요청 URL
			"type":"POST", //HTTP 요청방식
			"data":{"advancedNo":${reqeustScope.inquiryList.advancedNo}}, //요청 파라미터 설정 - queryString(n=v&n=v) /  Javascript 객체{n:v,n:v}
			"dataType":"json", //응답데이터 타입 지정. text는 default
			"success":function(list){
				var txt1 = ${reqeustScope.inquiryList.advancedNo};
				$("div").prepend(txt1)
			},
			"error":function(xhr, status, errorMsg){
				alert("오류가 발생했습니다."+status+", "+errorMsg);
			},
			"beforeSend":function(){
				/*if(!$("#id").val()){	
					alert("조회할 ID를 입력하세요");
					$("#id").focus();
					return false;
				}*/
			}
		});		
	});//강의목록 클릭했을 때 세부정보 표시
});
</script>

<div id="cmtTarget">댓글보기</div><br>
${sessionScope.login_info }<br>
<input type="text" id="commentContent">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="댓글 등록" id="btn">
