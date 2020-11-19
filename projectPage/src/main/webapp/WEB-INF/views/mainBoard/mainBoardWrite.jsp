<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainBoardView.css">
<script type="text/javascript">
	$(document).ready(function(){

		$('#topinfo').change(function(){
			if($('#topinfo').is(":checked")){
				$('#mb_topinfo').prop('type', 'text');
			}else{
				$('#mb_topinfo').prop('type', 'hidden');
			}
		});
		$('#pantsinfo').change(function(){
			if($('#pantsinfo').is(":checked")){
				$('#mb_pantsinfo').prop('type', 'text');
			}else{
				$('#mb_pantsinfo').prop('type', 'hidden');
			}
		});
		$('#capinfo').change(function(){
			if($('#capinfo').is(":checked")){
				$('#mb_capinfo').prop('type', 'text');
			}else{
				$('#mb_capinfo').prop('type', 'hidden');
			}
		});
		$('#shoesinfo').change(function(){
			if($('#shoesinfo').is(":checked")){
				$('#mb_shoesinfo').prop('type', 'text');
			}else{
				$('#mb_shoesinfo').prop('type', 'hidden');
			}
		});
		
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
		
		/* 사진 동시전송 */
		var i = 0;
		$("#add").click(function(){
			$("#addImage_container").append("<input type='file' name='uploadPicture' class='uploadPicture"+i+"' onchange='setThumbnail2(event,"+i+")' hidden>"
											/* +"<input type='button' value='삭제' class='deletePicture"+i+"' onclick='removeImg2("+i+")'>" */);
			$(".uploadPicture"+i).click();
			i++;
		});
		
	});

	/* 메인사진 미리보기 */
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
	/* 추가사진 미리보기 */
	function setThumbnail2(event,i){
		var reader = new FileReader();
		
		reader.onload = function(event){
			var img = document.createElement("img");
			img.setAttribute("src", event.target.result);
			img.setAttribute('class', 'addimg'+i);
			img.setAttribute('id', 'addimg');
			img.setAttribute('data-num', i);
			
			var delbtn = document.createElement("img");
			delbtn.setAttribute("src", "${pageContext.request.contextPath }/resources/images/X2.png");
			delbtn.setAttribute("class", "deletePicture"+i);
			delbtn.setAttribute("id", "deletePicture");
			delbtn.setAttribute("onclick", 'removeImg2('+i+')');
			
			document.querySelector("div#addImage_container").appendChild(delbtn);
			document.querySelector("div#addImage_container").appendChild(img);
			
			
			
		};
		reader.readAsDataURL(event.target.files[0]);
	};
	
	function removeImg(){
		$(".addimg").click(function(){
			$(this).remove();
			
			var classNum = '.uploadPicture'+$(this).attr('data-num');
			$(classNum).remove();
		});
	}
	/* 추가사진 삭제 */
	function removeImg2(i){
		$(".uploadPicture"+i).remove();
		$(".deletePicture"+i).remove();
		$(".addimg"+i).remove();
	}
</script>
<div id="body">
	<div id="main-form">
		<h2>글쓰기</h2>
		<form:form action="write.do" id="mainwriteForm" enctype="multipart/form-data" commandName="mainBoardVO">
			<form:hidden path="m_num" value="${m_num }"/>
			<ul>  
				<li>
					<label for="photo">메인사진</label>
					<a id="addPicture" href="#">
						<img src="${pageContext.request.contextPath }/resources/images/addpicture.png" class="addimg">
					</a>
					<input type="file" name="upload" id="upload" onchange="setThumbnail(event)" hidden/>
					<!-- 사진 미리보기창 -->
					<div id="image_container">
						
					</div>
					<a id="changeFilebtn" href="#">메인사진 변경</a>
				</li>
			</ul>
			<ul>
				<li class="addImages">
					<label for="picture">추가사진</label>
					<a href="#" id="add">
						<img src="${pageContext.request.contextPath }/resources/images/addpicture.png" class="addimg">
					</a>
					<!-- 추가사진 미리보기 -->
					<div id="addImage_container">
						
					</div>
					
				</li>
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
				<input type="submit" id="submit" value="전송">
				<input type="button" value="홈으로" id="homebtn" onclick="location.href='${pageContext.request.contextPath }/main/main.do'">
			</div>
		</form:form>
	
	</div>
</div>