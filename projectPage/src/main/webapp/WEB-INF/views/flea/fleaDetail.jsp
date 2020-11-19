<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainBoardView.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.flexslider-min.js"></script>
<script type="text/javascript">
	window.onload=function(){
		var del = document.getElementById('delete');
		del.onclick=function(){
			var delConfirm = confirm("등록된 물품을 삭제하시겠습니까?");
			if(delConfirm){
				alert("삭제완료!");
				location.href='fleaDelete.do?fb_num=${flea.fb_num}'
			}else{
				return false;
			}
		}
	}
</script>
<script>
	$(document).ready(function(){
		replyList("1");
		
		//댓글 등록
		$("#writeReplyForm").submit(function(){
			var fr_content = $("#fr_content").val();//댓글 내용
			var fb_num = "${flea.fb_num}";//게시물 번호
			var param = {"fr_content":fr_content, "fb_num":fb_num};
			
			var data = $(this).serialize();
			
			$.ajax({
				type:"post", //데이터 전송 방식
				url:"insertReply.do", //데이터 전송할 url
				data:data, //전송할 데이터
				success:function(){ //데이터 전송 성공
					alert("댓글이 등록되었습니다.");
					replyList(); //댓글 목록 출력
				}
			});
			return;
		});
		
		//댓글 목록 출력
		function replyList(num){
			$.ajax({
				type:"post",
				url:"replyList.do?fb_num=${flea.fb_num}&curPage"+num, //맵핑
				success:function(result){ //데이터 전송 성공
					$("#replyList").html(result);
				}
			});
		}
		
		function changeDate(date){
	    	date = new Date(parseInt(date));
	    	year = date.getFullYear();
	    	month = date.getMonth()+1;
	    	day = date.getDate();
	    	hour = date.getHours();
	    	minute = date.getMinutes();
	    	second = date.getSeconds();
	    	strDate = year+"-"+month+"-"+day;
	    	return strDate;
	    }
		
		$('.flexslider').flexslider({
	        animation: "slide",
	        controlNav: "thumbnails",
	        keyboard: true,
	        pauseOnAction: true,
	        pausePlay: true
		});
		
	});
</script> 
<div id="body">
	<!-- 본문 상단 -->
	<h2>${flea.fb_title}</h2>
	<ul class="writeinfo">
		<!-- 프로필 -->
		<li>
		<c:if test="${empty flea.m_image}">
			<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${flea.fb_usernum}">
			<img src="${pageContext.request.contextPath}/resources/images/basicprofile.png" width="50px">
			</a>
		</c:if>
		<c:if test="${!empty flea.m_image}">
			<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${flea.fb_usernum}">
			<img src="${pageContext.request.contextPath}/member/imageViewProfile.do?m_num=${flea.fb_usernum}" width="50px">
			</a> 
		</c:if>			
		</li>
		<c:if test="${empty flea.m_nickname}">
		<li>작성자 : ${flea.m_id} </li>
		</c:if>
		<c:if test="${!empty flea.m_nickname}">
		<li>작성자 : ${flea.m_nickname} </li>
		</c:if>
		<li>작성일 : ${flea.fb_regdate}</li>
		<li>수정일 : ${flea.fb_modifydate}</li>
	</ul>
	<div class="align-right">
		<%-- 글 수정 및 삭제를 하려면 로그인한 후 로그인 아이디와 작성자 아이디가 일치해야 함 --%>	
		<c:if test="${m_num == flea.fb_usernum || m_auth == 4}">
			<input type="button" value="수정" onclick="location.href='fleaUpdate.do?fb_num=${flea.fb_num}'">
			<input id="delete" type="button" value="삭제">
		</c:if>
		<input type="button" value="목록" onclick="location.href='fleaList.do'">
	</div>
	<hr size="1" width="100%">
	
	<!-- 사진 뷰어 flexslider -->
	<div class="align-center flexslider">
		<ul class="slides">
			<!-- 메인 사진 -->
			<c:if test="${!empty flea.fb_photo}">
				<li data-thumb="imageView.do?fb_num=${flea.fb_num}">
					<img src="imageView.do?fb_num=${flea.fb_num}">
				</li>
			</c:if>
			<!-- 추가 사진들 -->
			<c:if test="${!empty fleaPictures}">
				<c:forEach var="fleaPictures" items="${fleaPictures}" varStatus="status">
					<li data-thumb="imageView2.do?i_num=${fleaPictures.i_num}">
						<img src="imageView2.do?i_num=${fleaPictures.i_num}">
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
	
	<!-- 본문 내용 -->
	<p class="fb_content">
		${flea.fb_content}
	</p>
	<hr size="1" width="100%">
	<!-- 댓글 -->
	<div class="comment">
		댓글
	</div>
	<table id="replyList" border="1">
	
	</table>
	<!-- 댓글 쓰기 -->
	<c:if test="${!empty m_id}">
	<form id="writeReplyForm">
	<input type="hidden" name="fb_num" value="${flea.fb_num}">
	<input type="hidden" name="n_num" value="${m_num}">
		<div class="commentname">
			<c:if test="${empty m_nickname}">
				<div>${m_id}</div>
			</c:if>
			<c:if test="${!empty m_nickname}">
				<div>${m_nickname}</div>
			</c:if>
		</div>
		<div class="commentcontent">
			<input type="text" name="fr_content" id="fr_content">
		</div>
		<div class="commentsubmit">
			<button type="submit" name="writeReply_btn" id="writeReply_btn">댓글등록</button>
		</div>
	</form>
	</c:if>
</div>