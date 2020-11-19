<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mainBoardView.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.flexslider-min.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){
	listComment("1");
	
	//댓글쓰기
    $("#writeCommentForm").submit(function(){
        var mr_content=$("#mr_content").val(); //댓글 내용
        var mb_num="${mainBoard.mb_num}"; //게시물 번호
        var param={ "mr_content": mr_content, "mb_num": mb_num};
        
        
        
        var data = $(this).serialize();
        //var param="replytext="+replytext+"&bno="+bno;
        $.ajax({
            type: "post", //데이터를 보낼 방식
            url: "insertComment.do", //데이터를 보낼 url
            data: data, //보낼 데이터
            success: function(){ //데이터를 보내는것이 성공했을시 출력되는 메시지
                listComment(); //댓글 목록 출력
            }
        });
        
        return;
    });
	
  //댓글 목록 출력 함수
    function listComment(num){
        $.ajax({
            type: "post", //get방식으로 자료를 전달한다
            url: "listComment.do?mb_num=${mainBoard.mb_num}&curPage="+num, //컨트롤러에 있는 list.do로 맵핑하고 게시판 번호도 같이 보낸다.
            success: function(result){ //자료를 보내는것이 성공했을때 출력되는 메시지
                //result : responseText 응답텍스트(html)
                $("#listComment").html(result);
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
    /* flexslider Options: https://gist.github.com/warrendholmes/9481310 */
    /* 
    .flex-control-nav.flex-control-thumbs li img{
        float:left;
        display:inline;
        width:10%;
        height: 100px;
     }
     .flex-control-nav.flex-control-thumbs li{	
        display:inline;
        width:25%;
     }
    */

    $('#pop').click(function(){
    	$("#pop").css("visibility","invisible");
    });


});

function goDetail(num) {
	$("#pop").append("<hr size='1' width='100%''>");
	$("#pop").append("<img src='imageView.do?mb_num="+num+"' onclick='remove()' style='max-width:490px;'>");
	$("#pop").append("<br><hr size='1' width='100%''>");
	$("#pop").css("visibility","visible");
	$('#pop').css("top",(($(window).height() - $('#pop').outerHeight()) / 2.05) );
    $('#pop').css("left",(($(window).width() - $('#pop').outerWidth()) / 2) ); 
}
function goDetail2(num) {
	$("#pop").append("<hr size='1' width='100%''>");
	$("#pop").append("<img src='imageView2.do?i_num="+num+"' onclick='remove()' style='max-width:490px;'>");
	$("#pop").append("<br><hr size='1' width='100%''>");
	$("#pop").css("visibility","visible");
	$('#pop').css("top",(($(window).height() - $('#pop').outerHeight()) / 2.05) );
    $('#pop').css("left",(($(window).width() - $('#pop').outerWidth()) / 2) ); 

}
function remove(){
	$("#pop").empty();
    $('body').css("background", "rgb(247, 248, 249)");
}
</script>
<style>

</style>
<script type="text/javascript">
window.onload=function(){
	delete_btn.onclick=function(){
		var delete_check = confirm("진짜 삭제하끄야???")
		if(delete_check==true){
			alert("삭제하였습니다!");
			location.href='delete.do?mb_num=${mainBoard.mb_num}'
		}else if(delete_check==false){
			return;
		}
	}
}
</script>

<div id="body">
	<!-- 본문 상단 -->
	<h2 class="title">${mainBoard.mb_title}</h2>
	<ul class="writeinfo">
		<!-- 프로필 -->
		<li> 
		<c:if test="${empty mainBoard.m_image}">
			<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${mainBoard.m_num}">
			<img src="${pageContext.request.contextPath}/resources/images/basicprofile.png" width="50px">
			</a>
		</c:if>
		<c:if test="${!empty mainBoard.m_image}">
			<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${mainBoard.m_num}">
			<img src="${pageContext.request.contextPath}/member/imageViewProfile.do?m_num=${mainBoard.m_num}" width="50px">
			</a> 
		</c:if>		
		</li>
		<c:if test="${empty mainBoard.m_nickname}">
		<li>작성자 : ${mainBoard.m_id} </li>
		</c:if>
		<c:if test="${!empty mainBoard.m_nickname}">
		<li>작성자 : ${mainBoard.m_nickname} </li>
		</c:if>
		<li>작성일 : ${mainBoard.mb_regdate} </li>
		<li>수정일 : ${mainBoard.mb_modifydate}</li>
	</ul>
	<div class="align-right">
		<c:if test="${mainBoard.m_num == m_num || m_auth == 4}">
		<input type="button" value="수정" id="update_btn" onclick="location.href='update.do?mb_num=${mainBoard.mb_num}'">
		<input type="button" value="삭제" id="delete_btn" onclick="location.href='delete.do?mb_num=${mainBoard.mb_num}'">
		</c:if>
		<input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath }/main/main.do'">
	</div>
	<hr size="1" width="100%">
	
	<!-- 사진 뷰어 flexslider -->
	<div class="align-center flexslider">
	<ul class="slides">
		<!-- 메인 사진 -->
		<c:if test="${!empty mainBoard.mb_photo}">
			<li data-thumb="imageView.do?mb_num=${mainBoard.mb_num}">
				<img src="imageView.do?mb_num=${mainBoard.mb_num}" onclick="goDetail(${mainBoard.mb_num})" class="mainImg">
			</li>
	
		</c:if>
		<!-- 추가 사진들 -->
		<c:if test="${!empty mainPictures}">
		<c:forEach var="mainPictures" items="${mainPictures }" varStatus="status"> 
			<li data-thumb="imageView2.do?i_num=${mainPictures.i_num}">
				<img src="imageView2.do?i_num=${mainPictures.i_num}" onclick="goDetail2(${mainPictures.i_num})">
			</li>
		</c:forEach>
		</c:if>
	</ul>
	</div>
	<!-- 팝업 -->
	<div id="pop" style="visibility:hidden;">
	    
	</div>	
	<!-- 본문 내용 -->
	<p class="mb_content">
		${mainBoard.mb_content}
	</p>
	<div id="likes">
	<b>( ${likeCount} )</b>
	<c:if test="${!empty m_id}">
	<c:if test="${likeCheck == 0}">
		<a href="${pageContext.request.contextPath}/like/like.do?mb_num=${mainBoard.mb_num}">
			<img src="${pageContext.request.contextPath}/resources/images/likes.png">
		</a>
	</c:if>
	<c:if test="${likeCheck == 1}">
		<a href="${pageContext.request.contextPath}/like/unlike.do?mb_num=${mainBoard.mb_num}">
			<img src="${pageContext.request.contextPath}/resources/images/unlikes.png">
		</a>
	</c:if>
	</c:if>
	</div>
	<p id="categoryinfo">
		<c:if test="${!empty mainBoard.mb_topinfo}">
		<a href="${mainBoard.mb_topinfo}" target="_blank">
			<img src="${pageContext.request.contextPath}/resources/images/tshirts.png" width="40px" class="top">
			<b>상의</b>
		</a>
		</c:if>
		<c:if test="${!empty mainBoard.mb_pantsinfo}">
		<a href="${mainBoard.mb_pantsinfo}" target="_blank">
			<img src="${pageContext.request.contextPath}/resources/images/pants2.png" width="45px" class="pants">
			<b>하의</b>
		</a>
		</c:if>
		<c:if test="${!empty mainBoard.mb_capinfo}">
		<a href="${mainBoard.mb_capinfo}" target="_blank">
			<img src="${pageContext.request.contextPath}/resources/images/cap.png" width="45px" class="cap">
			<b>모자</b>
		</a>
		</c:if>
		<c:if test="${!empty mainBoard.mb_shoesinfo}">
		<a href="${mainBoard.mb_shoesinfo}" target="_blank">
			<img src="${pageContext.request.contextPath}/resources/images/shoes.png" width="45px" class="shoes">
			<b>신발</b>
		</a>
		</c:if>
	</p>
	<hr size="1" width="100%">
	<!-- 댓글 -->
	<div class="comment">
		댓글
	</div>
		<!-- 댓글 리스트 -->
	<table id="listComment" border="1">
	
	</table>
		<!-- 댓글 쓰기 폼 -->
	<c:if test="${!empty m_id}">
	<form id="writeCommentForm">
	<input type="hidden" name="mb_num" value="${mainBoard.mb_num}">
	<input type="hidden" name="m_num" value="${m_num}">
		<!-- 댓글작성 -->
		<div class="commentname">
			<c:if test="${empty m_nickname}">
				<div>${m_id}</div>
			</c:if>
			<c:if test="${!empty m_nickname}">
				<div>${m_nickname}</div>
			</c:if>
		</div>
		<div class="commentcontent">
			<input type="text" name="mr_content" id="mr_content">					
		</div>
		<div class="commentsubmit">
			<button type="submit" name="btnComment" id="btnComment">댓글등록</button>
		</div>
	</form>		
	</c:if>
</div>