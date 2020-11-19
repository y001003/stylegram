<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="body">
	<h2>로그인</h2>
	<form:form action="login.do" commandName="memberVO">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="m_id">아이디</label>
				<form:input path="m_id"/>
				<form:errors path="m_id" cssClass="error-color"/>
			</li>
			<li>
				<label for="m_passwd">비밀번호</label>
				<form:password path="m_passwd"/>
				<form:errors path="m_passwd" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="로그인">
			<input type="button" id="homebtn" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>