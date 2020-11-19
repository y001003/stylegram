<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="body">
	<h2 align="center">NOTICE</h2>
	<div class="align-right">
		<c:if test="${!empty m_id && m_auth == 4}">
		<input type="button" value="글쓰기" onclick="location.href='write.do'">
		</c:if>
	</div>
	<c:if test="${count == 0}">
	<div class="result-disply">등록 게시물 x</div>
	</c:if>
	<c:if test="${count > 0}">
	<table class="noticeboard"> 
		<tr>
			<th>글 번호</th>
			<th width="300" align="center">제목</th>
			<th>등록일</th>
		</tr>
		<c:forEach var="notice" items="${list}">
			<tr>
				<td>${notice.nb_num}</td>
				<td align="center"><a href="detail.do?nb_num=${notice.nb_num}">${notice.nb_title}</a></td>
				<td>${notice.nb_regdate}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<form id="search_form" action="list.do" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" class="keyfield">
					<option value="nb_title">제목</option>
					<option value="nb_content">내용</option>
					<option value="m_id">관리자 아이디</option>
				</select>
			</li>
			<li>
				<input type="search" size="16" name="keyword" id="keyword">
			</li>
		</ul>
	</form>
</div>