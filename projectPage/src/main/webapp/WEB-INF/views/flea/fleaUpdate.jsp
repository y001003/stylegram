<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainBoardView.css">
<script type="text/javascript">
	$(document).ready(function(){
		if($('#fb_topcheck').val() != 1){
			$('#fb_topcheck').prop("checked", true);
		}
		if($('#fb_bottomcheck').val() != 1){
			$('#fb_bottomcheck').prop("checked", true);
		}
		if($('#fb_hatcheck').val() != 1){
			$('#fb_hatcheck').prop("checked", true);
		}
		if($('#fb_shoescheck').val() != 1){
			$('#fb_shoescheck').prop("checked", true);
		}
	});
	
	/* 사진 미리보기 기능 */
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
	
	/* 추가사진 미리보기 */
	function setThumbnail2(event,i){
		var reader = new FileReader();
		
		reader.onload = function(event){
			var img = document.createElement("img");
			img.setAttribute("src", event.target.result);
			img.setAttribute("width", "300px");
			img.setAttribute('class', 'addimg'+i);
			img.setAttribute('id', 'addimg');
			img.setAttribute('data-num', i);
			
			$("div#addImage_container"+i).empty();
			$("img.add_Pictures"+i).hide();
			document.querySelector("div#addImage_container"+i).appendChild(img);
			
		};
		reader.readAsDataURL(event.target.files[0]);
	};
	
	function checkNum(e){
		var keyVal = event.keyCode;
		
		if((keyVal >= 48) && (keyVal <= 57)){
			return true;
		}else{
			alert("가격은 숫자로만 입력 가능합니다");
			return false;
		}
	}
	
</script>
<div id="body">
	<h2>물품수정</h2>
	<form:form action="fleaUpdate.do" enctype="multipart/form-data" commandName="fleaVO">
		<form:hidden path="fb_num"/>
		<ul>
			<li>
				<label for="photo">메인사진 변경</label>
				<!-- 기존 사진 -->
				<div class="align-center" id="origin_picture">
					<img src="imageView.do?fb_num=${fleaVO.fb_num}" style="max-width:300px">
				</div>
				<!-- 미리보기 사진 -->
				<div id="image_container">
		
				</div>
				<input type="file" name="upload" id="upload" onchange="setThumbnail(event)"/>
			</li>
		</ul>
		<ul>
			<!-- 추가사진 수정 -->
			<c:if test="${!empty fleaPictures}">
				<li class="addImages">
					<label for="addPictures">추가사진 변경</label>
					<c:forEach var="fleaPictures" items="${fleaPictures}" varStatus="status">
						<label for="picture">사진 이름 : ${fleaPictures.i_filename}</label>
						<!-- 기존사진 -->
						<img src="imageView2.do?i_num=${fleaPictures.i_num}" style="max-width:300px" class="add_pictures${status.index}" id="add_pictures">
						<!-- 미리보기 사진 -->
						<div id="addImage_container${status.index}"></div>
						
						<input type="hidden" name="fleaPictureSubVO[${status.index}].i_num" value="${fleaPictures.i_num}">
						<input type="file" name="fleaPictureSubVO[${status.index}].uploadPicture" class="uploadPicture${status.index}" id="uploadPictures" onchange="setThumbnail2(event,${status.index })">
						<input type="button" value="삭제" class="delete_check${status.index}" >
						
						<!-- 삭제 확인 js -->
						<script type="text/javascript">
							$(".delete_check${status.index}").click(function(){
								var check = confirm("추가 사진을 삭제하시겠습니까?");
								if(check){
									location.href='deletePicture.do?i_num=${fleaPictures.i_num}';
								}else{
									
								}
							});
						</script>
						
					</c:forEach>
				</li>
			</c:if>
		</ul>
		<ul>
			<li>
				<label for="fb_title">제목</label>
				<form:input path="fb_title" required="required"/>
				<form:errors path="fb_title" cssClass="error-color"/>
			</li>
			<li>
				<label for="fb_price">가격</label>
				<form:input path="fb_price" onKeyPress="return checkNum(event)"/>
				<form:errors path="fb_price" cssClass="error-color"/>
			</li>
			<li>
				<label for="fb_content">내용</label>
				<form:textarea path="fb_content"/>
				<form:errors path="fb_content" cssClass="error-color"/>
			</li>
			<li class="category">
				<label>태그선택</label><br>
				<form:checkbox path="fb_topcheck" value="1"/><b>상의</b>
				<form:checkbox path="fb_bottomcheck" value="1"/><b>하의</b>
				<form:checkbox path="fb_hatcheck" value="1"/><b>모자</b>
				<form:checkbox path="fb_shoescheck" value="1"/><b>신발</b>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" id="homebtn" onclick="location.href='fleaList.do'">
		</div>
	</form:form>
</div>