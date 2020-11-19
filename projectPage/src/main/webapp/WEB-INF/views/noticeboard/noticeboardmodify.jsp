<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" 
               uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"
                uri="http://java.sun.com/jsp/jstl/core" %>
<div id="body">
	<h2>글수정</h2>
	<form:form action="update.do" 
	   enctype="multipart/form-data" commandName="noticeVO">
		<form:hidden path="nb_num"/>
		<ul>
			<li>
				<label for="nb_title">제목</label>
				<form:input path="nb_title"/>
				<form:errors path="nb_title" cssClass="error-color"/>
			</li>
			<li>
				<label for="nb_content">내용</label>
				<form:textarea path="nb_content"/>
				<form:errors path="nb_content" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload1">파일 업로드1</label>
				<input type="file" name="upload1" id="upload1">
				<c:if test="${!empty noticeVO.nb_filename1}">
				<br>
				<span>(${noticeVO.nb_filename1})파일이 등록되어 있습니다.
				다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>
			</li>
			<li>
				<label for="upload2">파일 업로드2</label>
				<input type="file" name="upload2" id="upload2">
				<c:if test="${!empty noticeVO.nb_filename2}">
				<br>
				<span>(${noticeVO.nb_filename2})파일이 등록되어 있습니다.
				다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" id="homebtn" onclick="location.href='list.do'">
		</div>	   
	</form:form>
</div>               
