<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<img id="image" src="image/academyIntroduce.jpg">


<c:if test="${sessionScope.memberType=='administrator' }">
	<form method="post" enctype="multipart/form-data"
		action="/UspaceAcademy/academyIntroduceUpload.do">
		교체할 사진 : <input type="file" name="upImage"><br> <input
			type="submit" value="교체">
	</form>
</c:if>