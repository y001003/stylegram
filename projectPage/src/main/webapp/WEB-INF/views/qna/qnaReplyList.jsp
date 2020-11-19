<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body>
	<c:if test="${commentCount != 0}">
	<!-- 댓글 목록 -->
	<table style="width:100%">
	<c:forEach var="row" items="${c_list}">
		<script type="text/javascript">
		$(function(){
			$(".updateCom${row.qr_num}").hide();
			$(".updateComment_btn${row.qr_num}").on("click",function(){
				$(".updateCom${row.qr_num}").toggle("fast");
			});
			$(".deleteComment_btn${row.qr_num }").click(function(){
				var checking = confirm("댓글을 삭제합니다");
				if(checking == true){
					alert("삭제성공");
					location.href='${pageContext.request.contextPath }/qna/deleteComment.do?qr_num=${row.qr_num }';
				}else if(checking == false){
				
				}
				
			});
		});
		</script>
		<tr>
			<td>
				<c:if test="${empty row.m_image}">
					<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${row.m_num}">
						<img alt="" src="${pageContext.request.contextPath}/resources/images/basicprofile.png" width="35px">
					</a>
				</c:if>
				<c:if test="${!empty row.m_image}">
					<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${row.m_num}">
					<img src="${pageContext.request.contextPath}/member/imageViewProfile.do?m_num=${row.m_num}" width="35px">
					</a>
				</c:if>
				<c:if test="${empty row.m_nickname}">
					${row.m_id}
				</c:if>
				<c:if test="${!empty row.m_nickname}">
					${row.m_nickname}
				</c:if>				
				(<fmt:formatDate value="${row.qr_regdate }" pattern="yyyy-MM-dd"/>)
				<c:if test="${row.m_num == m_num }">
				<button id="updateComment_btn" class="updateComment_btn${row.qr_num }" name="updateComment_btn">수정</button>
				<button id="deleteComment_btn" class="deleteComment_btn${row.qr_num }" name="deleteComment_btn">삭제</button>
				</c:if>
				<br>
				${row.qr_content }
				<br>
			</td>
			
		</tr>
		<tr name="updateCom" id="updateCom" class="updateCom${row.qr_num }">
			<td>
				<form action="updateComment.do" name="updateComForm" id="updateComForm">
					<input type="hidden" name="qr_num" value="${row.qr_num }">
					<input type="text" name="updateText" id="updateText" value="${row.qr_content }">
					<input type="submit" value="수정">
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
