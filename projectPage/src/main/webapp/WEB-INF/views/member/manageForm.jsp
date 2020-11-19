<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="body">
	<h2>회원 목록</h2>
	<div class="align-right">
		* 아이디 클릭시 변경가능
	</div>
	<c:if test="${count == 0}">
	아무도 없오
	</c:if>
	<c:if test="${count > 0}">
	<table id="memberList">
		<tr>
			<th>아이디</th>
			<th>닉네임</th>
			<th>가입일</th>
			<th>등급</th>
		</tr>
		<c:forEach var="memberList" items="${memberList}">
		<c:if test="${memberList.m_auth != 4}">
		<tr>
			<td><a href="${pageContext.request.contextPath}/member/manageDetail.do?m_num=${memberList.m_num}">${memberList.m_id}</a></td>
			<c:if test="${empty memberList.m_nickname}">
			<td>닉네임 미등록</td>
			</c:if>
			<c:if test="${!empty memberList.m_nickname}">
			<td>${memberList.m_nickname}</td>
			</c:if>
			<td>${memberList.m_regdate}</td>
			<td>
			<c:if test="${memberList.m_auth == 0}">탈퇴회원</c:if>
			<c:if test="${memberList.m_auth == 1}">정지회원</c:if>
			<c:if test="${memberList.m_auth == 2}">일반회원</c:if>
			<c:if test="${memberList.m_auth == 3}">인증회원</c:if>
			</td>
		</tr>
		</c:if>
		</c:forEach>
	</table>
	<div class="align-right">
    	${pagingHtml}
	</div>
	</c:if>
</div>