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
		});
	});
</script>

<form action="/UspaceAcademy/teacher/updateSalary.do" method="post">
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
