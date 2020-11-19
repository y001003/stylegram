<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="body">
	<c:if test="${empty memberVO.m_nickname}">
	<h2>${memberVO.m_id}의 정보수정</h2>
	</c:if>
	<c:if test="${!empty memberVO.m_nickname}">
	<h2>${memberVO.m_nickname}의 정보수정</h2>
	</c:if>
	<form:form action="manageDetail.do" commandName="memberVO">
		<form:hidden path="m_num"/>
		<ul>
			<li>
				<label for="m_auth">회원등급</label> 
				<form:radiobutton path="m_auth" value="1"/><b>정지회원</b> 
				<form:radiobutton path="m_auth" value="2"/><b>일반회원</b> 
				<form:radiobutton path="m_auth" value="3"/><b>인증회원</b>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="변경">
			<input type="button" value="관리자페이지" id="homebtn" onclick="location.href='${pageContext.request.contextPath}/member/managePage.do'">
		</div>
	</form:form>
</div>