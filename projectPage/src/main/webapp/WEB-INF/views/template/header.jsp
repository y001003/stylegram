<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="align-center">
	<div class="align-left" id="bar_logo">
		<a href="${pageContext.request.contextPath}/main/main.do">
		<img src="${pageContext.request.contextPath}/resources/images/mainlogo.png">
		</a>
	</div>
	<!-- <div class="align-center" id="bar_search">
		<form class="search_form">
			<ul class="search">
				<li>
					<input type="hidden" id="search_tag" autocomplete="off" name="keyword">
				</li>
				<li>
					<input type="search" id="search_body" placeholder="Search..." autocomplete="off" name="keyfield" required>
				</li>
			</ul>
		</form>
	</div> -->
	<div class="align-right" id="bar_menu">
		<div class="font-size">
			<c:if test="${following >= 0 && following < 5}"><div class="title1">뉴비</div></c:if>
			<c:if test="${following >= 5 && following < 10}"><div class="title2">인싸</div></c:if>
			<c:if test="${following >= 10 && following < 50}"><div class="title3">핵인싸</div></c:if>
	    <c:if test="${!empty m_id && empty m_nickname}">
	    <span><b>[${m_id}]</b></span>
	    </c:if>
	    <c:if test="${!empty m_id && !empty m_nickname}">
	    <span><b>[${m_nickname}]</b></span>
	    </c:if>
	    </div>
	    <c:if test="${!empty m_id && m_auth == 4}">
	    <a href="${pageContext.request.contextPath}/member/admin.do" id="nav_menu">관리자페이지</a>
	    </c:if>
		<c:if test="${!empty m_id && m_auth >= 2 && m_auth <= 3}">
		<a href="${pageContext.request.contextPath}/member/detail.do" id="nav_menu">회원정보</a>
		</c:if>
		<c:if test="${!empty m_id && m_auth == 2}">
		<a href="${pageContext.request.contextPath}/member/auth.do" id="nav_menu">본인인증</a>	
		</c:if>
		<c:if test="${!empty m_id}">
		<a href="${pageContext.request.contextPath}/member/logout.do" id="nav_menu">로그아웃</a>
		</c:if>
		<c:if test="${empty m_id}">
		<a href="${pageContext.request.contextPath}/member/register.do" id="nav_menu">회원가입</a>	
		<a href="${pageContext.request.contextPath}/member/login.do" id="nav_menu">로그인</a>
		</c:if>
	</div>
	<div class="align-right" id="bar_nav">
		<%-- <img src="${pageContext.request.contextPath}/resources/images/menu.png"/> --%>
		<div class="toolbar_wrap">
			<a href="javascript:void(0)" onclick="BarButtonAction();" id="bar_button">
				<img src="${pageContext.request.contextPath}/resources/images/hambugermenu.png" id="menubar">
			</a>
		</div>
		<div class="menu_background">
			<div class="menu_wrap">
				<div class="font-size">
					<c:if test="${following >= 0 && following < 5}"><div class="title1">뉴비</div></c:if>
					<c:if test="${following >= 5 && following < 10}"><div class="title2">인싸</div></c:if>
					<c:if test="${following >= 10 && following < 50}"><div class="title3">핵인싸</div></c:if>
				<br>
			    <c:if test="${!empty m_id && empty m_nickname}">
			    <span><b>[${m_id}]</b></span>
			    </c:if>
			    <c:if test="${!empty m_id && !empty m_nickname}">
			    <span><b>[${m_nickname}]</b></span>
			    </c:if>
			    </div>
			    <br><br>
			    <c:if test="${!empty m_id && m_auth == 4}">
			    <a href="${pageContext.request.contextPath}/member/admin.do" id="nav_menu">관리자페이지</a>
			    </c:if>
				<c:if test="${!empty m_id && m_auth >= 2 && m_auth <= 3}">
				<a href="${pageContext.request.contextPath}/member/detail.do" id="nav_menu">회원정보</a>
				</c:if>
				<br><br>
				<c:if test="${!empty m_id}">
				<a href="${pageContext.request.contextPath}/member/logout.do" id="nav_menu">로그아웃</a>
				<br><br>
				</c:if>
				<c:if test="${!empty m_id && m_auth == 2}">
				<a href="${pageContext.request.contextPath}/member/auth.do" id="nav_menu">본인인증</a>
				<br><br>
				</c:if>
				<br><br>
				<c:if test="${empty m_id}">
				<a href="${pageContext.request.contextPath}/member/register.do" id="nav_menu">회원가입</a>	
				<br><br>
				<a href="${pageContext.request.contextPath}/member/login.do" id="nav_menu">로그인</a>
				</c:if>
				<br><br><br><br>
				<!-- <form class="search_form">
					<ul class="search">
						<li>
							<input type="search" id="keyword" placeholder="Search..." autocomplete="off" required>
						</li>
					</ul>
				</form> -->
				<a href="#" id="close_menu_button" onclick="hideMenu();">
				<i class="fa fa-times" aria-hidden="true"></i>
				</a>
			</div>
			<div class="menu_list">
				<a href="${pageContext.request.contextPath}/main/main.do" id="nav_menu">MAIN</a><br><br>
				<a href="${pageContext.request.contextPath}/flea/fleaList.do" id="nav_menu">FLEA</a><br><br>
				<a href="${pageContext.request.contextPath}/qna/listQna.do" id="nav_menu">QNA</a><br><br>
				<a href="${pageContext.request.contextPath}/noticeboard/list.do" id="nav_menu">NOTICE</a><br><br><br><br><br>
			</div>
		</div>
		<a href="javascript:void(0)" id="cover_close_button" onclick="hideMenu();"></a>
		<a href="javascript:void(0)" id="cover_close_button2" onclick="hideMenu();">
		<div class="menu_background_rev">
		</div>
		</a>
	</div>
</div>