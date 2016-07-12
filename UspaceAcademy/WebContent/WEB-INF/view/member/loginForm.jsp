<%@ page contentType="text/html;charset=utf-8"%>
<h3 class="pageTlt">로그인</h3>
<hr>
<div align="center">
<form action="/UspaceAcademy/member/login.do" method="post">
<table id="tb">
	<tr>
		<td><label>ID</label></td>
		<td><input type="text" name="id"  style="width:200px" placeholder="아이디를 입력하세요"><font color="red"> ${requestScope.idError }</font></td>
	</tr>
	<tr>
		<td><label>PASSWORD</label></td>
		<td><input type="password" name="password"  style="width:200px" placeholder="비밀번호를 입력하세요"><font color="red">  ${requestScope.passwordError }</font></td>
	</tr>
</table>

	<div align="center">
		<input type="submit" value="login" class="btn btn-success">
	</div>
</form>
</div>

<a href="/UspaceAcademy/findMember.do"><button class="btn btn-default">아이디 패스워드 찾기</button></a>





