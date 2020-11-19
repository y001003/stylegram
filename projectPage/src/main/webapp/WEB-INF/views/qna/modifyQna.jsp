<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$(document).ready(function(){
	if($('#qb_topinfo').val() != 0){
		$('#qb_topinfo').prop("checked", true);
	}
	if($('#qb_pantsinfo').val() != 0){
		$('#qb_pantsinfo').prop("checked", true);
	}
	if($('#qb_capinfo').val() != 0){
		$('#qb_capinfo').prop("checked", true);
	}
	if($('#qb_shoesinfo').val() != 0){
		$('#qb_shoesinfo').prop("checked", true);
	}
});

/* 그림 미리보기 기능 */
function setThumbnail(event){
	var reader = new FileReader();
	reader.onload = function(event){
		var img = document.createElement("img");
		img.setAttribute("src", event.target.result);
		img.setAttribute("width", "300px");
		
		$("div#image_container").empty();
		document.querySelector("div#image_container").appendChild(img);
		$("#addPicture").hide();
		$("#changeFilebtn").show();
		$("#origin_picture").hide();
	};
	reader.readAsDataURL(event.target.files[0]);
};

</script>

<div id="body">
	<h2>게시글 수정</h2><!-- 구현 후 삭제 -->
	
	<form:form action="modifyQna.do" enctype="multipart/form-data" commandName="qnaVO">
	<form:hidden path="qb_num"/>
	
	<c:if test="${!empty qnaVO.qb_filename}">
		<div class="align-center" id="origin_picture">
			<img src="imageView.do?qb_num=${qnaVO.qb_num}" style="max-width:500px">
		</div>
		<div id="image_container"></div>
	</c:if>
		<ul>
			<li>
				<label for="photo">사진 변경</label>
				<input type="file" name="upload" id="upload" onchange="setThumbnail(event)">
			</li>
			<li>
				<label for="title" >제목</label>
				<form:input path="qb_title" required="required"/>
				<form:errors path="qb_title" cssClass="error-color"/>
			</li>
			
			<li>
				<label>내용 설명</label>
				<form:textarea path="qb_content"/>
			</li>
			<li class="category">
				<label for="tag">태그선택</label><br>
				<form:checkbox path="qb_topinfo" value="1"/><b>상의</b>
				<form:checkbox path="qb_pantsinfo" value="1"/><b>하의</b>
				<form:checkbox path="qb_capinfo" value="1"/><b>모자</b>
				<form:checkbox path="qb_shoesinfo" value="1"/><b>신발</b>
			</li>
		</ul>
		<div>
			<input type="submit" value="수정">
			<input type="button" value="목록" id="homebtn" onclick="location.href='${pageContext.request.contextPath}/qna/listQna.do'">
		</div>
	</form:form>
	</div>