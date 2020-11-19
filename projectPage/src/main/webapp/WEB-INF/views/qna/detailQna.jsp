<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
$(document).ready(function(){
	listComment("1");
	
	//댓글쓰기
    $("#writeCommentForm").submit(function(){
        var qr_content=$("#qr_content").val(); //댓글 내용
        var qb_num="${Qna.mb_num}"; //게시물 번호
        var param={ "qr_content": qr_content, "qb_num": qb_num};
        
        
         
        var data = $(this).serialize();
        //var param="replytext="+replytext+"&bno="+bno;
        $.ajax({
            type: "post", //데이터를 보낼 방식
            url: "insertComment.do", //데이터를 보낼 url
            data: data, //보낼 데이터
            success: function(){ //데이터를 보내는것이 성공했을시 출력되는 메시지
                alert("댓글이 등록되었습니다.");
                listComment(); //댓글 목록 출력
            }
        });
        
        return;
    });
	
  //댓글 목록 출력 함수
    function listComment(num){
        $.ajax({
            type: "post", //get방식으로 자료를 전달한다
            url: "listComment.do?qb_num=${qna.qb_num}&curPage="+num, //컨트롤러에 있는 list.do로 맵핑하고 게시판 번호도 같이 보낸다.
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

});

</script>
<!-- 스크립트 방식으로 글 삭제 -->
<script type="text/javascript">
window.onload=function(){
	deleteQna.onclick=function(){
		var delete_check = confirm("삭제 하시겠습니까?")
		if(delete_check == true){
			alert("삭제되었습니다.");
			location.href='deleteQna.do?qb_num=${qna.qb_num}'
		}else if(delete_check == false){
			return;
		}
	}
}
</script>

<div id="body">
	<h2>${qna.qb_title}</h2>
	<ul class="writeinfo">
	
	<!-- 페이지 상단 프로필  -->
	<li> 
		<c:if test="${empty qna.m_image}">
			<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${qna.m_num}">
			<img src="${pageContext.request.contextPath}/resources/images/basicprofile.png" width="50px">
			</a>
		</c:if>
		<c:if test="${!empty qna.m_image}">
			<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${qna.m_num}">
			<img src="${pageContext.request.contextPath}/member/imageViewProfile.do?m_num=${qna.m_num}" width="50px">
			</a> 
		</c:if>		
		</li>
		<c:if test="${empty qna.m_nickname}">
		<li>작성자 : ${qna.m_id} </li>
		</c:if>
		<c:if test="${!empty qna.m_nickname}">
		<li>작성자 : ${qna.m_nickname} </li>
		</c:if>
		<li>작성일 : ${qna.qb_regdate}</li>
		<li>수정일 : ${qna.qb_modifydate}</li>
	</ul>
	<div class="align-right">
		<c:if test="${m_num == qna.qb_usernum || m_auth == 4}">
		<input type="button" value="수정" onclick="location.href='modifyQna.do?qb_num=${qna.qb_num}'">
		<input type="button" value="삭제" id="deleteQna" onclick="location.href='deleteQna.do?qb_num=${qna.qb_num}'">
		</c:if>
		<input type="button" value="목록" id="homebtn" onclick="location.href='${pageContext.request.contextPath}/qna/listQna.do'">
	</div>
	<hr size="1" width="100%">
	<div id="viewPhoto" class="align-center">
	<c:if test="${!empty qna.qb_photo}">
		<img src="imageView.do?qb_num=${qna.qb_num}">
	</c:if>
	</div>
	<p class="qb_content">
		${qna.qb_content}
	</p>
	<hr size="1" width="100%">
	<!-- 댓글 -->
	<div class="comment">
		댓글
	</div>
	<table id="listComment" border="1">
	
	</table>
	<!-- 댓글 쓰기 폼 -->
	<c:if test="${!empty m_id}">
	<form id="writeCommentForm">
	<input type="hidden" name="qb_num" value="${qna.qb_num}">
	<input type="hidden" name="m_num" value="${m_num}">
		<div class="commentname">
			<c:if test="${empty m_nickname}">
				<div>${m_id}</div>
			</c:if>
			<c:if test="${!empty m_nickname}">
				<div>${m_nickname}</div>
			</c:if>
		</div>
		<div class="commentcontent">
			<input type="text" name="qr_content" id="qr_content">					
		</div>
		<div class="commentsubmit">
			<button type="submit" name="btnComment" id="btnComment">댓글등록</button>
		</div>
	</form>		
	</c:if>
</div>