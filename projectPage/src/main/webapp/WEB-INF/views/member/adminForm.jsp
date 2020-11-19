<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="body">
	<h2>관리자페이지</h2>
	<br>
	<div class="adminPage">
		<div class="part">
			<h3>회원관리</h3>
			<input type="button" value="회원관리" onclick="location.href='${pageContext.request.contextPath}/member/managePage.do'">
		</div>
		<hr size="1" width="100%">
		<div class="part">
			<h3>스토어 관리</h3>
			<input type="button" value="상품등록" onclick="location.href='${pageContext.request.contextPath}/store/insertProduct.do'">
		</div>
	</div>
</div>