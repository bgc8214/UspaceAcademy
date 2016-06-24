<%@ page contentType="text/html;charset=utf-8"%>
<form action="/UspaceAcademy/member/login.do" method="post">
	<table>
		<tr>
			<td>ID :</td>
			<td><input type="text" name="id"  style="width:200px"><font color="red"> ${requestScope.idError }</font>
			</td>
		</tr>
		<tr>
			<td>PASSWORD :</td>
			<td><input type="password" name="password"  style="width:200px"><font
				color="red">  ${requestScope.passwordError }</font><br></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="login"></td>
		</tr>
	</table>
</form>
<a href="/UspaceAcademy/findMember.do">아이디 패스워드 찾기</a>





