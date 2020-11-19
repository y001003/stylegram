<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div id="body">
	<h2>${notice.nb_title}</h2>
	<ul class="noticeinfo">
		<li>등록일 : ${notice.nb_regdate}</li>
	</ul>
	<c:if test="${fn:endsWith(notice.nb_filename1,'.jpg') || 
	              fn:endsWith(notice.nb_filename1,'.JPG') ||
	              fn:endsWith(notice.nb_filename1,'.gif') ||
	              fn:endsWith(notice.nb_filename1,'.GIF') ||
	              fn:endsWith(notice.nb_filename1,'.png') ||
	              fn:endsWith(notice.nb_filename1,'.PNG')}">
		<div class="align-center">
			<img src="imageView.do?nb_num=${notice.nb_num}&file_num=1" style="max-width:500px">
		</div> 
	</c:if>
	<c:if test="${fn:endsWith(notice.nb_filename2,'.jpg') || 
	              fn:endsWith(notice.nb_filename2,'.JPG') ||
	              fn:endsWith(notice.nb_filename2,'.gif') ||
	              fn:endsWith(notice.nb_filename2,'.GIF') ||
	              fn:endsWith(notice.nb_filename2,'.png') ||
	              fn:endsWith(notice.nb_filename2,'.PNG')}">
		<div class="align-center">
			<img src="imageView.do?nb_num=${notice.nb_num}&file_num=2" style="max-width:500px">
		</div>
	</c:if>
	<p class="noticecontent">
		${notice.nb_content}
	</p>
	<hr size="1" width="100%">
	<div class="align-right">
		<c:if test="${!empty m_id && m_auth == 4}">
			<input type="button" value="수정" onclick="location.href='update.do?nb_num=${notice.nb_num}'">
			<input type="button" value="삭제" onclick="location.href='delete.do?nb_num=${notice.nb_num}'">
		</c:if>
		<input type="button" value="목록" onclick="location.href='list.do'">
	</div>
</div>