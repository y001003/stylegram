<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="body">
	<h2>회원정보</h2>
	<hr size="1" width="100%">
	<div id="detail_profile">
	<c:if test="${empty member.m_image}">
		<img src="${pageContext.request.contextPath}/resources/images/basicprofile.png">
	</c:if>
	<c:if test="${!empty member.m_image}">
	<a href="profileDelete.do">
		<img src="imageView.do">
	</a>
	<span>프로필 사진 클릭 시<br> 프로필 사진 삭제</span>
	</c:if>
	</div>
	<div id="detail_list">
		<ul>
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
		</ul>
	</div>
	<div class="align-right">
	<c:if test="${m_auth >= 3}">
		<input type="button" value="회원정보수정" id="updatebtn" onclick="location.href='${pageContext.request.contextPath}/member/update.do'">
	</c:if>
		<input type="button" value="비밀번호변경" id="changepwd" onclick="location.href='${pageContext.request.contextPath}/member/changepwd.do'">
		<input type="button" value="회원탈퇴" id="deletebtn" onclick="location.href='${pageContext.request.contextPath}/member/delete.do'">  
		<input type="button" value="내 구매내역" id="purchaselist" onclick="location.href='${pageContext.request.contextPath}/store/purchaseList.do'">  
	</div>
	<hr size="1" width="100%" class="lastline">
	<div class="detailpage miniboard">
		<h3>내 게시글</h3>
		<table>
			<tr>
				<th>제목</th>
				<th class="hidemenu">작성일</th>
			</tr>
			<c:forEach var="writeList" items="${writeList}">
			<tr>
				<td><a href="${pageContext.request.contextPath}/mainBoard/detail.do?mb_num=${writeList.mb_num}">${writeList.mb_title}</a></td>
				<td class="hidemenu">${writeList.mb_regdate}</td>
			</tr>
			</c:forEach>
		</table>
	</div> 
	<div class="detailpage">
		<h3 class="detailh3">내 팔로워 목록</h3>
		<input type="button" value="${followerCount}" class="countBtn" readonly>
		<table class="detailtable">
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
		<h3 class="detailh3">내 팔로잉 목록</h3>
		<input type="button" value="${followingCount}" class="countBtn" readonly>
		<table class="detailtable">
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