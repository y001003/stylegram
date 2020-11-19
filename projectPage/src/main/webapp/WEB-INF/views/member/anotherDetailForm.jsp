<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="body">
	<c:if test="${!empty member.m_nickname}">
		<h2>${member.m_nickname}님 정보</h2>
		</c:if>
		<c:if test="${empty member.m_nickname}">
		<h2>${member.m_id}님 정보</h2>
		</c:if>
	<hr size="1" width="100%">
	<div id="detail_profile">
	<c:if test="${empty member.m_image}">
		<img src="${pageContext.request.contextPath}/resources/images/basicprofile.png">
	</c:if>
	<c:if test="${!empty member.m_image}">
		<img src="imageViewProfile.do?m_num=${member.m_num}">
	</c:if>
	</div>
	<div id="detail_list">
		<ul>
		<c:if test="${member.m_public == 0}">
			<li>
			<c:if test="${!empty member.m_nickname}">
			${member.m_nickname}
			</c:if>
			<c:if test="${empty member.m_nickname}">
			해당 회원
			</c:if>
			님이 정보를 비공개 하였습니다.</li>
		</c:if>
		<c:if test="${member.m_public == 1}"> 
			<li>이름 : ${member.m_name}</li>
			<c:if test="${!empty member.m_nickname}">
			<li>닉네임 : ${member.m_nickname}</li>
			</c:if>
			<c:if test="${!empty member.m_phone}">
			<li>전화번호 : ${member.m_phone}</li>
			</c:if>
			<c:if test="${!empty member.m_address}">
			<li>주소 : ${member.m_address}</li>
			</c:if>
			<li>가입일 : ${member.m_regdate}</li>
		</c:if>
		</ul>
	</div>
	<div id="followbtn" class="align-right">
	<c:if test="${followCheck == 0}">
		<a href="${pageContext.request.contextPath}/member/follow.do?m_num=${member.m_num}">
			<img src="${pageContext.request.contextPath}/resources/images/heart2.png">
		</a>
	</c:if>
	<c:if test="${followCheck == 1}">
		<a href="${pageContext.request.contextPath}/member/unfollow.do?m_num=${member.m_num}">
			<img src="${pageContext.request.contextPath}/resources/images/heart1.png">
		</a>
	</c:if>
	</div>
	<hr size="1" width="100%">
	<div class="detailpage miniboard">
		<c:if test="${!empty member.m_nickname}">
		<h3>${member.m_nickname}님 게시글</h3>
		</c:if>
		<c:if test="${empty member.m_nickname}">
		<h3>${member.m_id}님 게시글</h3>
		</c:if>
		<table>
			<tr>
				<th>제목</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="writeList" items="${writeList}">
			<tr>
				<td><a href="${pageContext.request.contextPath}/mainBoard/detail.do?mb_num=${writeList.mb_num}">${writeList.mb_title}</a></td>
				<td>${writeList.mb_regdate}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="detailpage">
		<c:if test="${!empty member.m_nickname}">
		<h3>${member.m_nickname}님 팔로워 목록</h3>
		</c:if>
		<c:if test="${empty member.m_nickname}">
		<h3>${member.m_id}님 팔로워 목록</h3>
		</c:if>
		<input type="button" value="${followerCount}" class="countBtn" readonly>
		<table>
			<c:forEach var="followerList" items="${followerList}">
			<tr>
				<td>
				<c:if test="${empty followerList.m_image}">
					<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${followerList.m_num}">
					<img src="${pageContext.request.contextPath}/resources/images/basicprofile.png">
					</a>
				</c:if>
				<c:if test="${!empty followerList.m_image}">
					<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${followerList.m_num}">
					<img src="imageViewProfile.do?m_num=${followerList.m_num}">
					</a>
				</c:if>
				</td>
				<td>
				<c:if test="${!empty followerList.m_nickname}">
					${followerList.m_nickname}
				</c:if>
				<c:if test="${empty followerList.m_nickname}">
					${followerList.m_id}
				</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="detailpage">
		<c:if test="${!empty member.m_nickname}">
		<h3>${member.m_nickname}님 팔로잉 목록</h3>
		</c:if>
		<c:if test="${empty member.m_nickname}">
		<h3>${member.m_id}님 팔로잉 목록</h3>
		</c:if>
		<input type="button" value="${followingCount}" class="countBtn" readonly>
		<table>
			<c:forEach var="followingList" items="${followingList}">
			<tr>
				<td>
				<c:if test="${empty followingList.m_image}">
					<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${followingList.m_num}">
					<img src="${pageContext.request.contextPath}/resources/images/basicprofile.png">
					</a>
				</c:if>
				<c:if test="${!empty followingList.m_image}">
					<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${followingList.m_num}">
					<img src="imageViewProfile.do?m_num=${followingList.m_num}">
					</a>
				</c:if>
				</td>
				<td>
				<c:if test="${!empty followingList.m_nickname}">
					${followingList.m_nickname}
				</c:if>
				<c:if test="${empty followingList.m_nickname}">
					${followingList.m_id}
				</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</div>