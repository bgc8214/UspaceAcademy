<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	$(document).ready(function(){
		//폼체크
		$("#insert").on("click", function(){
			if(!$("input[name=teacherSalary]").val()){
				alert("숫자를 입력하세요!");
				
				return false;
			}			
			if(isNaN($("input[name=teacherSalary]").val())){
				alert("숫자만 입력하세요!");
				return false;
			}
		});
		
	    $("#resetButton").click(function() {  
	    	$("input[name=teacherSalary]").val('');
	    }); 
	});
</script>

<form action="/UspaceAcademy/member/updateSalary.do" method="post">
<input type="hidden" name="teacherId" value="${requestScope.teacherDetail.teacherId }">
<table border="1">
	<tr>
		<td>
			아이디: ${requestScope.teacherDetail.teacherId }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			이름: ${requestScope.teacherDetail.teacherName }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			과목: ${requestScope.teacherDetail.teacherSubject }
		</td>
	</tr>
	<tr>
		<td>
			월급: <input type="text" name="teacherSalary" size="70" value="${requestScope.teacherDetail.teacherSalary }">
			<span class="error"> <form:errors path="teacher.teacherSalary" delimiter="//" /></span>
		</td>
	</tr>
	<tr>
		<td><input id="insert" type="submit" value="등록"><input id="resetButton" type="button" value="초기화"></td>
	</tr>	
</table>
</form>
