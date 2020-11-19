<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('m_postcode').value = data.zonecode;
            document.getElementById("m_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("m_detailaddress").focus();
        }
    }).open();
}
</script>
<div id="body">
	<h2>회원정보수정</h2>
	<form:form action="update.do" enctype="multipart/form-data" commandName="memberVO">
		<span id="notempty">* 이 붙어있는 항목은 필수항목 입니다.</span>
		<form:hidden path="m_num"/>
		<ul>
			<li>
				<label for="upload">프로필사진</label>
				<input type="file" name="upload" id="upload" class="upload">
			</li>
			<li>
				<label for="m_nickname">닉네임</label>
				<form:input path="m_nickname"/>
				<form:errors path="m_nickname" cssClass="error-color"/>     
			</li>
			<li>
				<label for="m_postcode">우편번호 *</label>
				<form:input path="m_postcode"/>
				<form:errors path="m_postcode" cssClass="error-color"/>     
			</li>
			<li>
				<input type="button" id="findpostcode" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
			</li>
			<li>
				<label for="m_address">주소 *</label>
				<form:input path="m_address"/>
				<form:errors path="m_address" cssClass="error-color"/>     
			</li>
			<li>
				<label for="m_detailaddress">상세주소 *</label>
				<form:input path="m_detailaddress"/>
				<form:errors path="m_detailaddress" cssClass="error-color"/>     
			</li>
			<li class="public">
				<label for="m_public">개인정보 공개여부 *</label>
				<form:radiobutton path="m_public" value="0"/><b>비공개</b>
				<form:radiobutton path="m_public" value="1"/><b>공개</b>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="홈으로" id="homebtn" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>