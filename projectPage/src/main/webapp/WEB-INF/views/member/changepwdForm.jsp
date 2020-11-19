<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	$(document).ready(function(){
	//비밀번호 변경 체크
	$('#m_passwd').keyup(function(){
		if($('#passwdConfirm').val() !='' && $('#passwdConfirm').val()!=$(this).val()){
			$('#message_passwd').text('비밀번호 불일치');
		}else if($('#passwdConfirm').val() !='' && $('#passwdConfirm').val()==$(this).val()){
			$('#message_passwd').text('비밀번호 일치');
		}
	});
	
	$('#passwdConfirm').keyup(function(){
		if($('#m_passwd').val() !='' && $('#m_passwd').val()!=$(this).val()){
			$('#message_passwd').text('비밀번호 불일치');
		}else if($('#m_passwd').val() !='' && $('#m_passwd').val()==$(this).val()){
			$('#message_passwd').text('비밀번호 일치');
		}
	});
	$('#changepwdForm').submit(function(){
		if($('#old_passwd').val()==''){
			$('#message_passwd').text('현재 비밀번호를 입력하세요!');
			$('#old_passwd').focus();
			return false;
		}
		if($('#m_passwd').val()==''){
			$('#message_passwd').text('변경할 비밀번호를 입력하세요!');
			$('#m_passwd').focus();
			return false;
		}
		if($('#m_passwd').val()!=$('#passwdConfirm').val()){
			$('#message_passwd').text('비밀번호 불일치');
			return false;
		}
	});
	
	});
</script>
<div id="body">
	<h2>비밀번호 변경</h2>
	<form:form action="changepwd.do" commandName="memberVO" id="changepwdForm" class="alignspan">
		<form:hidden path="m_num"/>
		<ul>
			<li>
				<label for="old_passwd">현재 비밀번호</label>
				<form:password path="old_passwd"/>
				<form:errors path="old_passwd" cssClass="error-color"/>
			</li>
			<li>
				<label for="m_passwd">변경 비밀번호</label>
				<form:password path="m_passwd"/>
				<form:errors path="m_passwd" cssClass="error-color"/>
			</li>
			<li>
				<label for="passwdConfirm">변경 비밀번호 확인</label>
				<input type="password" id="passwdConfirm">
				<span id="message_passwd" class="message_passwd"></span>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="홈으로" id="homebtn" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>                              
	</form:form>
</div>