<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>학생 가입폼</h2>
<form action="/UspaceAcademy/member/studentRegister.do" method="post">
<table>
	<tr>
		<th>ID</th>
		
		<td><input type="text" name="studentId"  value="${requestScope.member.id }"><span class="error">
		<form:errors path="member.id" delimiter="//"/> <!-- BindingResult의 에러메세지 출력 -->
		
		</span></td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="password" name="studentPassword"><span class="error"><form:errors path="member.password" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input type="text" name="studentName" value="${requestScope.member.name }"><span class="error"><form:errors path="member.name" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>이메일주소</th>
		<td><input type="text" name="studentEmail"  value="${requestScope.member.email }"><span class="error"></span><form:errors path="member.email" delimiter="//"/></td>
	</tr>
	<tr>
		<th>핸드폰 번호</th>
		<td><input type="text" name="studentPhoneNo" value="${requestScope.member.age }"><span class="error"><form:errors path="member.age" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input type="text" name="studentAddress" value="${requestScope.member.address }"><span class="error"><form:errors path="member.blogUrl" delimiter="//"/></span></td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="submit" value="가입">
		</td>
	</tr>
</table>
</form>
