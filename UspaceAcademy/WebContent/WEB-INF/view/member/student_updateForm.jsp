<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	$(document).ready(function() {
		$("#btn").on("click", function() {
			if(!$("input[name=studentName]").val()) {
				alert("이름은 필수입력사항!");
				return false;
			}
			if(!$("input[name=studentPassword]").val()) {
				alert("비밀번호는 필수입력사항!");
				return false;
			}
			if(!$("input[name=studentEmail]").val()) {
				alert("email은 필수입력사항!");
				return false;
			}
			if(!$("input[name=studentPhoneNo]").val()) {
				alert("이메일은 필수입력사항!");
				return false;
			}
			if(!$("input[name=studentAddress]").val()) {
				alert("주소는 필수입력사항!");
				return false;
			}
		})
	})
</script>
<form action="/UspaceAcademy/member/updateStudent.do" method="post">
아이디  : <input type="text" name="studentId" value="${sessionScope.login_info.studentId}" readonly="readonly"> <span class="error"><form:errors path="updateForm.studentId" delimiter=","/></span><br>
이름 : <input type="text" name="studentName" value="${sessionScope.login_info.studentName}"> <span class="error"><form:errors path="updateForm.studentName" delimiter=","/></span><br>
비밀번호  : <input type="password" name="studentPassword" value="${sessionScope.login_info.studentPassword}"> <span class="error"><form:errors path="updateForm.studentPassword" delimiter=","/></span><br>
이메일 : <input type="text" name="studentEmail" value="${sessionScope.login_info.studentEmail}"> <span class="error"><form:errors path="updateForm.studentEmail" delimiter=","/></span><br>
전화번호 : <input type="text" name="studentPhoneNo" value="${sessionScope.login_info.studentPhoneNo}"> <span class="error"><form:errors path="updateForm.studentPhoneNo" delimiter=","/></span><br>
주소 : <input type="text" name="studentAddress" value="${sessionScope.login_info.studentAddress}"> <span class="error"><form:errors path="updateForm.studentAddress" delimiter=","/></span><br>	
	<input id="btn" type="submit" value="수정">
</form>
