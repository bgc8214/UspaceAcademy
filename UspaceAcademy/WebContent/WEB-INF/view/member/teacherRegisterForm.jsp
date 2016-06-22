<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>강사 가입폼</h2>
<form action="/UspaceAcademy/member/teacherRegister.do" method="post">
<table>
	<tr>
		<th>ID</th>
		
		<td><input type="text" name="teacherId"  value="${requestScope.member.id }"><span class="error">
		<form:errors path="member.id" delimiter="//"/> <!-- BindingResult의 에러메세지 출력 -->
		
		</span></td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="password" name="teacherPassword"><span class="error"><form:errors path="member.password" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input type="text" name="teacherName" value="${requestScope.member.name }"><span class="error"><form:errors path="member.name" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>이메일주소</th>
		<td><input type="text" name="teacherEmail"  value="${requestScope.member.email }"><span class="error"></span><form:errors path="member.email" delimiter="//"/></td>
	</tr>
	<tr>
		<th>핸드폰 번호</th>
		<td><input type="text" name="teacherPhoneNo" value="${requestScope.member.age }"><span class="error"><form:errors path="member.age" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input type="text" name="teacherAddress" value="${requestScope.member.address }"><span class="error"><form:errors path="member.blogUrl" delimiter="//"/></span></td>
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
${requestScope.codeType[0].codeType }
${requestScope.codeType[1].codeType }
${requestScope.codeType[2].codeType }
</form>
