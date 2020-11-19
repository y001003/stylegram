<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.0.min.js"></script>
<script type="text/javascript">
	function BarButtonAction(){
		showMenu();
	}
	
	function showMenu(){
		$(".menu_wrap").animate({
			right:"0px"},
			500,
			function(){
				$("#cover_close_button").css("display", "block");
				$(".menu_wrap").css("display", "block");
		});
		$(".menu_background").animate({
			right:"0px"},
			500,
			function(){
				$(".menu_background").css("display", "block");
		});
		$(".menu_background_rev").animate({
			left:"0px"},
			500,
			function(){
				$("#cover_close_button2").css("display", "block");
				$(".menu_background_rev").css("display", "block");	
		});
	}
	
	function hideMenu(){
		$(".menu_wrap").animate({
			right: "-10%"},
			500,
			function(){
				$("#cover_close_button").css("display", "none");
		});
		$(".menu_background").animate({
			right: "-10%"},
			500,
			function(){
				$(".menu_background").css("display", "none");
		});
		$(".menu_background_rev").animate({
			left:"-10%"},
			500,
			function(){
				$(".menu_background_rev").css("display", "none");	
		});
	}
</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
<style>
@font-face{
	font-family: mainfont;
	src:url("${pageContext.request.contextPath}/resources/css/Jalnan.ttf") format("truetype");
}
@media(max-width:1200px){
	div#bar_search{
		visibility:hidden;
	}
	div#bar_menu{
		display:none;
	}
	div#bar_nav{
		display:inline-block;
	}
}
@media(max-width:1120px){
	div#body div.detailpage table .hidemenu{
		display:none;
	}
}
@media(max-width:760px){
	div#menu_list{
		display: none;
	}
	#bar_nav .menu_background{
		margin-top:2px;
	}
	#bar_nav .menu_background_rev{
		margin-top:2px;
	}
	div.menu_list{
		display:inline-block;
	}
}
</style>
</head>
<body>
<div id="main">
	<div id="fix_menu">
		<div id="main_header">
			<tiles:insertAttribute name="header"/>
		</div>
		<div id="main_menu">
			<tiles:insertAttribute name="menu"/>
		</div>
	</div>
	<div id="main_body">
		<tiles:insertAttribute name="body"/>
	</div>
</div>	
</body>
</html>