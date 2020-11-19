<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainBoardView.css">
<script type="text/javascript">
	$(document).ready(function(){
		if($('#mb_topinfo').val() != ''){
			$('#topinfo').prop("checked", true);
			$('#mb_topinfo').prop('type', 'text');
		}else{
			$('#mb_topinfo').prop('type', 'hidden');
		}
		$('#topinfo').change(function(){
			if($('#topinfo').is(":checked")){
				$('#mb_topinfo').prop('type', 'text');
			}else{
				$('#mb_topinfo').prop('type', 'hidden');
			}
		});
		if($('#mb_pantsinfo').val() != ''){
			$('#pantsinfo').prop("checked", true);
			$('#mb_pantsinfo').prop('type', 'text');
		}else{
			$('#mb_pantsinfo').prop('type', 'hidden');
		}
		$('#pantsinfo').change(function(){
			if($('#pantsinfo').is(":checked")){
				$('#mb_pantsinfo').prop('type', 'text');
			}else{
				$('#mb_pantsinfo').prop('type', 'hidden');
			}
		});
		if($('#mb_capinfo').val() != ''){
			$('#capinfo').prop("checked", true);
			$('#mb_capinfo').prop('type', 'text');
		}else{
			$('#mb_capinfo').prop('type', 'hidden');
		}
		$('#capinfo').change(function(){
			if($('#capinfo').is(":checked")){
				$('#mb_capinfo').prop('type', 'text');
			}else{
				$('#mb_capinfo').prop('type', 'hidden');
			}
		});
		if($('#mb_shoesinfo').val() != ''){
			$('#shoesinfo').prop("checked", true);
			$('#mb_shoesinfo').prop('type', 'text');
		}else{
			$('#mb_shoesinfo').prop('type', 'hidden');
		}
		$('#shoesinfo').change(function(){
			if($('#shoesinfo').is(":checked")){
				$('#mb_shoesinfo').prop('type', 'text');
			}else{
				$('#mb_shoesinfo').prop('type', 'hidden');
			}
		});
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
</script>
<div id="body">
	<h2>글수정</h2>
	
	<form:form action="update.do" enctype="multipart/form-data" commandName="mainBoardVO">
		<form:hidden path="mb_num"/>

		<ul>
			<li>
				<label for="photo">사진변경</label>
				<!-- 기존 사진 -->
				<div class="align-center" id="origin_picture">
					<img src="imageView.do?mb_num=${mainBoardVO.mb_num}" style="max-width:300px">
				</div>
				<!-- 미리보기 사진 -->
				<div id="image_container"></div>				
				<input type="file" name="upload" id="upload" onchange="setThumbnail(event)"/>
			</li>
		</ul>
		<ul>
			<!-- 추가사진 수정 -->
			<c:if test="${!empty mainPictures }">
			<li class="addImages">
				<label for="addPictures">추가사진 변경</label>
				<c:forEach var="mainPictures" items="${mainPictures }" varStatus="status">
					<label for="picture">사진 이름 : ${mainPictures.i_filename }</label>
					<!-- 기존사진 -->
					<img src="imageView2.do?i_num=${mainPictures.i_num}" style="max-width:300px" class="add_pictures${status.index }" id="add_pictures">
					<!-- 미리보기 사진 -->
					<div id="addImage_container${status.index }"></div>
					
					<input type="hidden" name="mainPictureSubVO[${status.index }].i_num" value="${mainPictures.i_num }">
					<input type="file" name="mainPictureSubVO[${status.index }].uploadPicture" class="uploadPicture${status.index }" id="uploadPictures" onchange="setThumbnail2(event,${status.index })">
					<input type="button" value="삭제" class="delete_check${status.index }" >
					<!-- 삭제 확인 js -->
					<script type="text/javascript">
						$(".delete_check${status.index}").click(function(){
							var check = confirm("추가 사진을 삭제하시겠습니까?");
							if(check){
								location.href='deletePicture.do?i_num=${mainPictures.i_num }';
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
				<label for="title">제목</label>
				<form:input path="mb_title" required="required"/><form:errors path="mb_title" cssClass="error-color"/>
			</li>
			<li>
				<label for="content">내용</label>
				<form:textarea path="mb_content"/><form:errors path="mb_content" cssClass="error-color"/>
			</li>
			<li class="category">
				<label for="tag">태그선택</label><br>
				<input type="checkbox" id="topinfo" value="상의"><b>상의</b>
				<input type="checkbox" id="pantsinfo" value="하의"><b>하의</b>
				<input type="checkbox" id="capinfo" value="모자"><b>모자</b>
				<input type="checkbox" id="shoesinfo" value="신발"><b>신발</b>
			</li>
			<li>
				<form:hidden path="mb_topinfo" placeholder="상의정보 (ex: https://smartstore.naver.com/store/products/4101500072)" required="required"/>
					<form:hidden path="mb_pantsinfo" placeholder="하의정보 (ex: https://smartstore.naver.com/store/products/4101500072)" required="required"/>
					<form:hidden path="mb_capinfo" placeholder="모자정보 (ex: https://smartstore.naver.com/store/products/4101500072)" required="required"/>
					<form:hidden path="mb_shoesinfo" placeholder="신발정보 (ex: https://smartstore.naver.com/store/products/4101500072)" required="required"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="홈으로" id="homebtn" onclick="location.href='${pageContext.request.contextPath }/main/main.do'">
		</div>
	</form:form>

</div>