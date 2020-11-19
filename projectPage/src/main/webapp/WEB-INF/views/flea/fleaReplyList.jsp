<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body>
	<c:if test="${commentCount != 0}">
		<!-- 댓글 목록 -->
		<table style="width:100%">
			<c:forEach var="row" items="${r_list}">
				<script type="text/javascript">
					$(function() {
						$(".updateReply${row.fr_num}").hide();

						$(".updateReply_btn${row.fr_num}").on("click",function() {
							$(".updateReply${row.fr_num}").toggle("fast");
						});

						$(".deleteReply_btn${row.fr_num}").click(function() {
							var checking = confirm("댓글을 삭제합니다.");
							if (checking == true) {
								alert("삭제성공");
								location.href = '${pageContext.request.contextPath}/flea/deleteReply.do?fr_num=${row.fr_num}';
							} else if (checking == false) {
												
							}
						});
					});
				</script>
				<tr>
					<td>
						<c:if test="${empty row.m_image}">
							<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${row.m_num}">
							<img src="${pageContext.request.contextPath}/resources/images/basicprofile.png" width="35px">
							</a>
						</c:if>
						<c:if test="${!empty row.m_image}">
							<a href="${pageContext.request.contextPath}/member/anotherPage.do?m_num=${row.m_num}">
							<img src="${pageContext.request.contextPath}/member/imageViewProfile.do?m_num=${row.m_num}" width="35px">
							</a>
						</c:if>						 
						<c:if test="${empty m_nickname}">
							${row.m_id}
						</c:if>
						<c:if test="${!empty m_nickname}">
							${row.m_nickname}
						</c:if>
						(<fmt:formatDate value="${row.fr_regdate}" pattern="yyyy-MM-dd"/>)
						<c:if test="${row.m_num == m_num}">
							<button id="updateReply_btn" class="updateReply_btn${row.fr_num}" name="updateReply_btn">수정</button>
							<button id="deleteReply_btn" class="deleteReply_btn${row.fr_num}" name="deleteReply_btn">삭제</button>
						</c:if>
						<br>
						${row.fr_content}
						<br>
					</td>
				</tr>
				<tr name="updateReply" id="updateReply" class="updateReply${row.fr_num}">
					<td>
						<form action="updateReply.do" name="updateReplyForm" id="updateReplyForm">
							<input type="hidden" name="fr_num" value="${row.fr_num}">
							<input type="text" name="updateContent" id="updateContent" value="${row.fr_content}">
							<input type="submit" value="전송">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>