<%@ page contentType ="text/html;charset=utf-8"%>
<form action="/UspaceAcademy/member/login.do">
	ID : <input type="text" name="id"  ><font color="red">${requestScope.idError }</font><br>
	PASSWORD : <input type="password" name="password"><font color="red"> ${requestScope.passwordError }</font><br>
	<input type="submit" value="login">
</form>