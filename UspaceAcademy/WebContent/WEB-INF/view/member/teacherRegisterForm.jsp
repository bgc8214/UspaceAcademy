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
	window.open("/UspaceAcademy/teacherIdCheck.do","아이디 중복 확인","width=400,height=200,resizable=no");
}
</script>
<h2>강사 가입폼</h2>
<form action="/UspaceAcademy/member/teacherRegister.do" method="post" name="join_form">
<table>
	<tr>
		<th>ID</th>
		
		<td><input type="text" name="teacherId" onclick="idCheck();" value="${requestScope.teacher.teacherId }"><span class="error">
		<form:errors path="teacher.teacherId" delimiter="//"/> <!-- BindingResult의 에러메세지 출력 -->
		
		</span></td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="password" name="teacherPassword"><span class="error"><form:errors path="teacherPassword" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input type="text" name="teacherName" value="${requestScope.teacher.teacherName }"><span class="error"><form:errors path="teacher.teacherName" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>이메일주소</th>
		<td><input type="text" name="teacherEmail"  value="${requestScope.teacher.teacherEmail }"><span class="error"></span><form:errors path="teacher.teacherEmail" delimiter="//"/></td>
	</tr>
	<tr>
		<th>핸드폰 번호</th>
		<td><input type="text" name="teacherPhoneNo" value="${requestScope.teacher.teacherPhoneNo }"><span class="error"><form:errors path="teacher.teacherPhoneNo" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input type="text" name="teacherAddress" value="${requestScope.teacher.teacherAddress }"><span class="error"><form:errors path="teacher.teacherAddress" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>과목</th>
		<th>
			<select name="teacherSubject">
			<c:forEach items="${requestScope.codeType }" var="code">
				<option value="${code.codeName }">${code.codeName }</option>
			
			</c:forEach>
			</select>
		</th>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="가입">
		</td>
	</tr>
</table>
</form>
