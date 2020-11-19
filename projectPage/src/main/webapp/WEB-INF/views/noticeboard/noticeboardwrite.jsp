<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="body">
	<h2>글 쓰기</h2>
	<form:form action="write.do" enctype="multipart/form-data" commandName="noticeVO">
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
				<label for="upload1">파일 업로드</label>
				<input type="file" name="upload1" id="upload1">
			</li>
			<li>
				<label for="upload2">파일 업로드2</label>
				<input type="file" name="upload2" id="upload2">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="게시">
			<input type="button" value="목록" id="homebtn" onclick="location.href='list.do'">
		</div>
	</form:form>
</div>