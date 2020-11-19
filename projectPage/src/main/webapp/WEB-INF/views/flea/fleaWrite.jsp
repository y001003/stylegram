<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainBoardView.css">
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
	<div id="main-form">
		<h2>글쓰기</h2>
		<form:form action="fleaWrite.do" enctype="multipart/form-data" commandName="fleaVO">
			<ul>
				<li>
					<label for="upload">사진</label>
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
					<label for="fb_title">제목</label>
					<form:input path="fb_title" required="required"/>
					<form:errors path="fb_title" cssClass="error-color"/>
				</li>
				<li class="category">
					<form:checkbox path="fb_topcheck" value="checked"/><b>상의</b>
					<form:checkbox path="fb_bottomcheck" value="checked"/><b>하의</b>
					<form:checkbox path="fb_hatcheck" value="checked"/><b>모자</b>
					<form:checkbox path="fb_shoescheck" value="checked"/><b>신발</b>
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
			</ul>
			<div class="align-center">
				<input type="submit" value="전송">
				<input type="button" value="목록" id="homebtn" onclick="location.href='fleaList.do'">
			</div>
		</form:form>
	</div>
</div>