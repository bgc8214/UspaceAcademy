<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<form action="/UspaceAcademy/member/updateTeacher.do" method="post">
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn").on("click", function() { 
			if(!$("input[name=teacherName]").val()) {
				alert("이름은 필수입력사항!");
				return false;
			}
			if(!$("input[name=teacherPassword]").val()) {
				alert("비밀번호는 필수입력사항!");
				return false;
			}
			if(!$("input[name=teacherEmail]").val()) {
				alert("email은 필수입력사항!");
				return false;
			}
			if(!$("input[name=teacherPhoneNo]").val()) {
				alert("전화번호는 필수입력사항!");
				return false;
			}
			if(!$("input[name=teacherAddress]").val()) {
				alert("주소는 필수입력사항!");
				return false;
			}
		})
	})
</script>
아이디 : <input type="text"  name="teacherId" value="${sessionScope.login_info.teacherId}" readonly="readonly"><br>
이름 : <input type="text"	name="teacherName" value="${sessionScope.login_info.teacherName}"><span class="error"><form:errors path="updateForm.teacherName" delimiter=","/></span><br>
비밀번호 : <input type="password" name="teacherPassword" value="${sessionScope.login_info.teacherPassword}"><span class="error"><form:errors path="updateForm.teacherPassword" delimiter=","/></span><br>
이메일 : <input type="text" name="teacherEmail" value="${sessionScope.login_info.teacherEmail}"><span class="error"><form:errors path="updateForm.teacherEmail" delimiter="//"/></span><br>
전화번호 : <input type="text" name="teacherPhoneNo" value="${sessionScope.login_info.teacherPhoneNo}"><span class="error"><form:errors path="updateForm.teacherPhoneNo" delimiter=","/></span><br>
주소 : <input type="text"	name="teacherAddress" value="${sessionScope.login_info.teacherAddress}"><span class="error"><form:errors path="updateForm.teacherAddress" delimiter=","/></span><br>
과목 : <select name="teacherSubject">
		<option value="${sessionScope.login_info.teacherSubject}">${sessionScope.login_info.teacherSubject}</option>
	</select><span class="error"><form:errors path="updateForm.teacherSubject" delimiter="," /></span><p>
	<input id="btn" type="submit" value="수정">
</form>