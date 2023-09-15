<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp"%>	
<script type="text/javascript">

</script>	
		<div id="b_left">
			<P>MEMBER</P>
			<ul>
				<li><a href="javascript:goPage('login')">LOGIN</a></li>
				<li><a href="">ID / PASSWORD</a></li>
				<li><a href="javascript:goPage('join')"> CONTACK</a></li>
				<li><a ><span class="fnt"><i class="fas fa-apple-alt"></i></span>MYINFO</a></li>
			</ul>
		</div>
		
		<div id="b_right">
			<p class="n_title">
				MY INFORMATION
			</p>
			<form name="mem">
			<input type="hidden" name="t_gubun">
			<table class="boardForm">
			  <colgroup>
				<col width="200" />
				<col width="500" />
			  </colgroup>
			  <tbody>
				<tr>
				  <th><label for="id">I D</label></th>
				  <td>${dto.getId()}</td>
				</tr>
				<tr>
				  <th><label for="nana">성 명</label></th>
				  <td>${dto.getName()}</td>
				</tr>
				<tr>
				  <th>비빌번호</th>
				  <td>
				  		<c:forEach begin="1" end="${dto.getPassword_len()}">*</c:forEach>
				  </td>
				</tr>
				<tr>
				  <th>지역</th>
				  <td>
					<c:choose>
						<c:when test="${dto.getArea() eq '02'}">서울</c:when>
						<c:when test="${dto.getArea() eq '042'}">대전</c:when>
						<c:when test="${dto.getArea() eq '050'}">부산</c:when>
						<c:when test="${dto.getArea() eq '060'}">대구</c:when>
					</c:choose>
				  </td>
				</tr>	
				
				<tr>
				  <th>주소</th>
				  <td>${dto.getAddress()}</td>
				</tr>
				<tr>
				  <th>연락처</th>
				  <td>${dto.getMobile_1()} - ${dto.getMobile_2()} - ${dto.getMobile_3()} 
				  </td>
				</tr>
				<tr>
				  <th>남여구분</th>
				  <td>
				  		<c:if test="${dto.getGender() eq 'f'}">여성</c:if>
				  		<c:if test="${dto.getGender() eq 'm'}">남성</c:if>
				  </td>
				</tr>
				<tr>
				  <th>취미</th>
				  <td>
				  	<c:if test="${dto.getHobby_travel() eq 'y'}">여행 &nbsp;</c:if>
				  	<c:if test="${dto.getHobby_reading() eq 'y'}">독서 &nbsp;</c:if>
				  	<c:if test="${dto.getHobby_sports() eq 'y'}">운동</c:if>
				  </td>
				</tr>
				<tr>
				  <th>회원가입일</th>
				  <td>${dto.getReg_date()}</td>
				</tr>				
				<tr>
				  <th>최종정보수정일</th>
				  <td>${dto.getUpdate_date()}</td>
				</tr>				
				<tr>
				  <th>최근접속일</th>
				  <td>${dto.getLast_login_date()}</td>
				</tr>				
			  </tbody>
			</table>
			</form>
			<div class="buttonGroup_center">
				<a href="javascript:()" class="butt">정보수정</a>
				<a href="javascript:()" class="butt">비밀번호변경</a>
				<a href="javascript:()" class="butt">회원탈퇴</a>
			</div>	
		</div>	

		<div id="b_bottom">
			<%@ include file="../common_footer.jsp" %>	
		</div>	
	</div>	
</body>
</html>






    