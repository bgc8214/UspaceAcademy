<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
table {
	
}

span, td, th {
	padding: 5px;
}

span {
	font-size: .9em;
	color: red;
}


</style>
<script type="text/javascript">
function idCheck()
{
	window.open("/UspaceAcademy/studentIdCheck.do","아이디 중복 확인","width=400,height=200,resizable=no");
}
$(document).ready(function() {
	$("#btn").on("click", function() {
		if(!$("input[name=studentId]").val()) {
			alert("아이디는 필수입력사항");
			return false;
		}
		if(!$("input[name=studentPassword]").val()) {
			alert("비밀번호는 필수입력사항");
			return false;
		}
		if(!$("input[name=studentName").val()) {
			alert("이름은 필수입력사항");
			return false;
		}
		if(!$("input[name=studentEmail]").val()) {
			alert("이메일은 필수입력사항");
			return false;
		}
		if(!$("input[name=studentPhoneNo]").val()) {
			alert("전화번호는 필수입력사항");
			return false;
		}
		if(!$("input[name=studentAddress]").val()) {
			alert("주소는 필수입력사항");
			return false;
		}
		
	})
})

</script>
<h2>학생 가입폼</h2>
<form action="/UspaceAcademy/member/studentRegister.do" method="post" name="join_form">
	<table>
		<tr>
			<th>ID</th>
			<td><input type="text" name="studentId" onclick="idCheck();"
				value="${requestScope.student.studentId }"><span
				class="error"> <form:errors path="student.studentId"
						delimiter="//" /> <!-- BindingResult의 에러메세지 출력 -->

			</span></td>
		</tr>
		<tr>
			<th>패스워드</th>
			<td><input type="password" name="studentPassword"><span
				class="error"><form:errors path="student.studentPassword"
						delimiter="//" /></span></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="studentName"
				value="${requestScope.student.studentName }"><span
				class="error"><form:errors path="student.studentName"
						delimiter="//" /></span></td>
		</tr>
		<tr>
			<th>이메일주소</th>
			<td><input type="text" name="studentEmail"
				value="${requestScope.student.studentEmail }"><span
				class="error"></span>
			<form:errors path="student.studentEmail" delimiter="//" /></td>
		</tr>
		<tr>
			<th>핸드폰 번호</th>
			<td><input type="text" name="studentPhoneNo"
				value="${requestScope.student.studentPhoneNo }"><span
				class="error"><form:errors path="student.studentPhoneNo"
						delimiter="//" /></span></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="studentAddress"
				value="${requestScope.student.studentAddress }"><span
				class="error"><form:errors path="student.studentAddress"
						delimiter="//" /></span></td>
		</tr>

		<tr>
			<td colspan="2"><input id="btn" type="submit" value="가입"></td>
		</tr>
	</table>
</form>
