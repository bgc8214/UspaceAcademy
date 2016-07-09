<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
span.errors{
	color:red
}

</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jquery-ui.min.js"></script>

<link href="/UspaceAcademy/jQuery/jquery-ui.min.css" rel="stylesheet">
<link href="/UspaceAcademy/jQuery/jquery-ui.structure.min.css" rel="stylesheet">
<link href="/UspaceAcademy/jQuery/jquery-ui.theme.min.css" rel="stylesheet">

<script type="text/javascript">
	$(document).ready(function(){
		//폼체크
		$("#insert").on("click", function(){
			if(!$("input[name=advancedTitle]").val()){
				alert("제목을 입력하세요!");
				
				return false;
			}
			if($("textarea[name=advancedContent]").val()==""){
				alert("내용을 입력하세요!");
				
				return false;
			}
			
/* 			var flag = false;
			$("input[type=radio]").each(function(index, item){
				
				if(item.checked==true){
					flag = true;
				}
			})
			if(!flag){
				alert("비밀글 여부를 선택하세요!");
				return false;
			}else{
				return true;
			} */
			
		});
	});
</script>

<form action="/UspaceAcademy/teacher/insertSalary.do" method="post">
<table border="1">
	<tr>
		<td>
			아이디: ${teacherDetail.teacherId }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			이름: ${teacherDetail.teacherName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			과목: ${teacherDetail.teacherSubject }
		</td>
	</tr>
	<tr>
		<td>
			월급: <input type="text" name="teacherSalary" size="70">
		</td>
	</tr>
	<tr>
		<td><input id="insert" type="submit" value="등록"><input type="reset" value="초기화"></td>
	</tr>	
</table>
</form>

<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${requestScope.lectureNo2 }">전체 목록보기</a>