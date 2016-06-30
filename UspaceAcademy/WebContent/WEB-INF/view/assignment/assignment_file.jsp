<%-- <%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>assignment_file.jsp</title>
</head>
<body>


사진 파일명 ${requestScope.imageName }
사진 크기 ${requsetScope.imageSize }byte
사진설명 ${requestScope.comment }
<p>
<img src="/UspaceAcademy/upImage/${requestScope.imageName}"/>  






<hr>
<!-- jstl 라이브러리 등록하기(pom.xml하고, 위에 taglib-->
<h2>글내용</h2>
글제목 ${requestScope.title }
글내용 ${requestScope.content }
파일<br>
<c:forEach items="${requestScope.fileNames }"  var="fileName">
<a href="/UspaceAcademy/uploadFile/${fileName }">${fileName }</a><br>
</c:forEach>
<hr>






<!--  DownloadView  -->
<h3>DownloadView를 이용해 다운처리</h3>
<c:forEach items="${requestScope.fileNames}" var="fileName">
<a href="/UspaceAcademy/download.do?fileName=${fileName }">${fileName }</a><!--  링크가 download.do로 걸림* -->
</c:forEach>






</body>
</html>



















 --%>