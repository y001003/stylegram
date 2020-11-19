<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript">
	$(document).ready(function(){
		var checkId = 0;
		$('#confirmId').click(function(){
			if($('#m_id').val()=='' && checkId == 0){
				$('#message_id').text('아이디를 입력해주세요.');
				return;
			}else if(checkId == 1){
				$('#m_id').val('');
				$('#m_id').prop("readonly", false);
				$('#message_id').text('');
				$('#confirmId').val('중복체크');
				checkId = 0;
				return;
			}
			
			$('#message_id').text('');
			
			$.ajax({
				url:'confirmId.do',
				type:'post',
				data:{m_id:$('#m_id').val()},
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result == 'idNotFound'){
						$('#message_id').text('사용 가능한 ID');
						$('#m_id').prop("readonly", true);
						$('#confirmId').val('다른아이디사용');
						checkId = 1;
					}else if(data.result == 'idDuplicated'){
						$('#message_id').text('중복된 ID');
						checkId = 0;
					}else{
						checkId = 0;
						alert('오류');
					}
				},
				error:function(){
					checkId = 0;
					alert('네트워크 오류');
				}
			})
		});
		
		/* $('#registerForm #m_id').keydown(function(){
			checkId=0;
			$('#message_id').text('');
		}); */
		
		$('#registerForm').submit(function(){
			if($('#m_passwd').val() != $('#passwdConfirm').val()){
				$('#message_passwd').text('비밀번호를 확인해주세요.');
				$('#m_passwd').focus();
				$('#passwdConfirm').val('');
				return false;
			}
			if(checkId==0){
				$('#message_id').text('중복체크 필수!');
				if($('#m_id').val()==''){
					$('#m_id').focus();
				}
				return false;
			}
		});
	});
</script>
<div id="body">
	<h2>회원가입</h2>
	<br>
	<form:form action="register.do" commandName="memberVO" id="registerForm">
		<span id="notempty">* 이 붙어있는 항목은 필수항목 입니다.</span>
		<ul>
			<li>
				<label for="m_id">아이디 *</label>
				<form:input path="m_id"/>
				<input type="button" id="confirmId" value="중복체크">
				<span id="message_id"></span>
				<form:errors path="m_id" cssClass="error-color"/>
			</li>
			<li>
				<label for="m_name">이름 *</label>
				<form:input path="m_name"/>
				<form:errors path="m_name" cssClass="error-color"/>     
			</li>
			<li>
				<label for="m_passwd">비밀번호 *</label>
				<form:password path="m_passwd"/>
				<form:errors path="m_passwd" cssClass="error-color"/>     
			</li>
			<li>
				<label for="passwdConfirm">비밀번호 확인 *</label>
				<input type="password" id="passwdConfirm" class="passwdConfirm">
				<span id="message_passwd"></span>
			</li>
			<li>
				<label for="m_nickname">닉네임</label>
				<form:input path="m_nickname"/>
				<form:errors path="m_nickname" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="등록">
			<input type="button" id="homebtn" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>