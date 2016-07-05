<%@ page contentType ="text/html;charset=utf-8"%>

<script type="text/javascript" src="http://openapi.map.naver.com/openapi/v2/maps.js?clientId=Jt9x7JfeaD89Dftwr7Zc"></script> 
<div id='map' style="position:relative; left:50px; top:0; border:1px solid #ccc; "></div> 
<script type="text/javascript">

var oSeoulCityPoint = new nhn.api.map.LatLng(37.4020566, 127.1066640); // 마커 좌표
var defaultLevel = 11; 
 var oMap = new nhn.api.map.Map(document.getElementById('map'), { 
 point : oSeoulCityPoint, 
 zoom : 12, 
 enableWheelZoom : true, 
 enableDragPan : true, 
 enableDblClickZoom : false, 
 mapMode : 0, 
 activateTrafficMap : false, 
 activateBicycleMap : false, 
 minMaxLevel : [ 1, 14 ], 
 size : new nhn.api.map.Size(750, 500) }); // 지도 사이즈
 
var oSlider = new nhn.api.map.ZoomControl(); 
 oMap.addControl(oSlider); 
 oSlider.setPosition({ 
 top : 10, 
 left : 10 
 }); 

 var oMapTypeBtn = new nhn.api.map.MapTypeBtn(); 
 oMap.addControl(oMapTypeBtn); 
 oMapTypeBtn.setPosition({ 
 top : 10, 
 right : 10 
 });

var oSize = new nhn.api.map.Size(28, 37); 
 var oOffset = new nhn.api.map.Size(14, 37); 
 var oIcon = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset); 

 var oInfoWnd = new nhn.api.map.InfoWindow(); 	// info window 생성
 oInfoWnd.setVisible(false); // infoWindow 표시 여부 지정
 oMap.addOverlay(oInfoWnd); 

 oInfoWnd.setPosition({ 
 top : 20, 
 left :20 
 }); 

 var oLabel = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언. 
oMap.addOverlay(oLabel); // - 마커 라벨 지도에 추가. 기본은 라벨이 보이지 않는 상태로 추가됨. 

oInfoWnd.attach('changeVisible', function(oCustomEvent) { 
 	if (oCustomEvent.visible) { 
 		oLabel.setVisible(false); 
 	} 
}); 

 var oPolyline = new nhn.api.map.Polyline([], { 
 strokeColor : '#f00', // - 선의 색깔 
strokeWidth : 5, // - 선의 두께 
strokeOpacity : 0.5 // - 선의 투명도 
}); // - polyline 선언, 첫번째 인자는 선이 그려질 점의 위치. 현재는 없음. 
oMap.addOverlay(oPolyline); // - 지도에 선을 추가함. 

oMap.attach('mouseenter', function(oCustomEvent) { 

 var oTarget = oCustomEvent.target; 
 // 마커위에 마우스 올라간거면 
if (oTarget instanceof nhn.api.map.Marker) { 
	var oMarker = oTarget; 
 	oLabel.setVisible(true, oMarker); // - 특정 마커를 지정하여 해당 마커의 title을 보여준다. 
 	} 
}); 


oMap.attach('click"', function(oCustomEvent) { 
 var oPoint = oCustomEvent.point;
 var oTarget = oCustomEvent.target; 
 oInfoWnd.setVisible(false);
 
 if(oTarget instanceof nhn.api.map.Marker) {
	 if(oCustomEvent.clickCoveredMarker) {
		 return;
	 }
	 
	 oInfoWnd.setContent('<DIV style="border-top:1px solid; border-bottom:2px groove black; border-left:1px solid; border-right:2px groove black;margin-bottom:1px;color:black;background-color:white; width:auto; height:auto;">'+
             '<span style="color: #000000 !important;display: inline-block;font-size: 12px !important;font-weight: bold !important;letter-spacing: -1px !important;white-space: nowrap !important; padding: 2px 2px 2px 2px !important">' + 
             'Hello World <br /> ' + oTarget.getPoint()
             +'<span></div>');
             oInfoWnd.setPoint(oTarget.getPoint());
             oInfoWnd.setVisible(true);
             oInfoWnd.setPosition({right : 15, top : 30});
             oInfoWnd.autoPosition();
             return;
 }
});
 

var tMarker = new nhn.api.map.Marker(oIcon, { title : 'UspaceAcademy', point:oSeoulCityPoint }); 
 oMap.addOverlay(tMarker); 
 </script> 




<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<img id="image" src="image/roadIntroduce.jpg">

<c:if test="${sessionScope.memberType=='administrator' }">
	<form method="post" enctype="multipart/form-data"
		action="/UspaceAcademy/roadIntroduceUpload.do">
		교체할 사진 : <input type="file" name="upImage"><br> <input
			type="submit" value="교체">
	</form>
</c:if>
 --%>
