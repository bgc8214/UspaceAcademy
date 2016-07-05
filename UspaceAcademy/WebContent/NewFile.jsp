<%@page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="jquery/jquery.timepicker.min.css" type="text/css"/>
<!--     <script type="text/javascript" src="resources/jquery.js"></script>
    <script type="text/javascript" src="resources/jquery-ui.js"></script> -->
    <script type="text/javascript" src="jquery/jquery.timepicker.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $('#timepicker').timepicker({
                minTime: '2p',
                maxTime: '8p',
                scrollbar: true,
            });
        });
    </script>
</head>
<body>
   <h1 id="qunit-header">Core Tests</h1>
    <h2 id="qunit-banner"></h2>
    <h2 id="qunit-userAgent"></h2>
    <ol id="qunit-tests"></ol>

    <div id="main">
        <div>
            <input id="timepicker" class="timepicker"/>
        </div>
    </div>	
</body>
</html>