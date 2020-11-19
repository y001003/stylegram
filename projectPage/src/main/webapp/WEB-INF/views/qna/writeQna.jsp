<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	$(document).ready(function(){
		/* 사진 미리보기 기능 */
		$("#changeFilebtn").hide();
		$("#addPicture").click(function(){
			$("#upload").click();
		});
		$("#changeFilebtn").click(function(){
			$("#image_container").empty();
			$("#changeFilebtn").hide();	
			$("#addPicture").show();
			$("#upload").click();
		});
	});
	function setThumbnail(event){
		var reader = new FileReader();
		
		reader.onload = function(event){
			var img = document.createElement("img");
			img.setAttribute("src", event.target.result);
			img.setAttribute("width", "300px");
			
			document.querySelector("div#image_container").appendChild(img);
			$("#addPicture").hide();
			$("#changeFilebtn").show();
		};
		reader.readAsDataURL(event.target.files[0]);
	};


</script>


<div id="body">
	<h2>글쓰기</h2>
	<form:form action="writeQna.do" enctype="multipart/form-data" commandName="qnaVO">
		<ul>
			<li>
				<label for="upload">사진 추가</label>
				<a id="addPicture" href="#">
						<img src="${pageContext.request.contextPath }/resources/images/addpicture.png" class="addimg">
					</a>
					<input type="file" name="upload" id="upload" onchange="setThumbnail(event)" hidden/>
					<!-- 사진 미리보기창 -->
					<div id="image_container">

					</div>
					<a id="changeFilebtn" href="#">다른사진으로 변경</a>
			</li>
			<li>
				<label for="qb_title">제목</label>
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
			<input type="submit" value="올리기">
			<input type="button" value="취소" id="homebtn" onclick="location.href='listQna.do'">
		</div>
	</form:form>
</div>