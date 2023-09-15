<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp"%>	
<script type="text/javascript">
	function goJoin(){
		if(checkValue(mem.t_id,"ID 입력!")) return;
		if(mem.yesId.value == ""){
			alert("사용 가능한 ID가 아닙니다.")
			mem.t_id.focus();
			return;
		}
		if(checkValue(mem.t_name,"성명 입력!")) return;
		if(checkValue(mem.t_password,"비밀번호 입력!")) return;
		if(checkValue(mem.t_password_confirm,"비밀번호 확인 입력!")) return;
		
		if(mem.t_password.value != mem.t_password_confirm.value){
			alert("비밀번호가 같지 않습니다!");
			return;
		}
		
		if(checkValue(mem.t_address,"주소 입력!")) return;
		if(checkValue(mem.t_mobile_1,"연락처 입력!")) return;
		if(checkValue(mem.t_mobile_2,"연락처 입력!")) return;
		if(checkValue(mem.t_mobile_3,"연락처 입력!")) return;
		if(checkValue(mem.t_gender,"성별 선택!")) return;
		
		mem.t_gubun.value="memberSave";
		mem.method="post";
		mem.action="Member";
		mem.submit();
	}
	
	function checkId(){
		if(checkValue(mem.t_id,"ID 입력 후 중복검사!")) return;
		$.ajax({
			type : "POST",
			url : "MemberCheckId",
			data: "t_id="+mem.t_id.value,
			dataType : "text",
			error : function(){
				alert('통신실패!!!!!');
			},
			success : function(data){
				//alert("=== "+data+" ===");
				//if(data =="사용가능")
				$("#idResult").text(data);	
				if(data == "사용불가"){
					$("#idResult").css("color","red");
					mem.yesId.value = "";
				} else {
					$("#idResult").css("color","black");
					mem.yesId.value = mem.t_id.value;
				}
			}
		});				
	}	
	
</script>	
		<div id="b_left">
			<P>MEMBER</P>
			<ul>
				<li><a href="javascript:goPage('login')">LOGIN</a></li>
				<li><a href="">ID / PASSWORD</a></li>
				<li><a href="javascript:goPage('join')"><span class="fnt"><i class="fas fa-apple-alt"></i></span> CONTACK</a></li>
			</ul>
		</div>
		
		<div id="b_right">
			<p class="n_title">
				MEMBER JOIN
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
				  <td>
					<input name="t_id" type="text" size="10" id="id" title="id입력하세요">
					<input type="button" onclick="checkId()" value="ID중복검사" class="checkB">
				 	<span id="idResult"></span>
				 	<input type="hidden" name="yesId">
				  </td>
				</tr>
				<tr>
				  <th><label for="nana">성 명</label></th>
				  <td><input name="t_name" type="text" size="8" id="nana"></td>
				</tr>
				<tr>
				  <th>비빌번호</th>
				  <td><input name="t_password" type="text" size="13"></td>
				</tr>
				<tr>
				  <th>비밀번호확인</th>
				  <td><input name="t_password_confirm" type="text" size="13"></td>
				</tr>
				<tr>
				  <th>지역</th>
				  <td>
					<select name="t_area" >
						<option value="02">서울</option>
						<option value="042">대전</option>
						<option value="050">부산</option>
						<option value="060">대구</option>        
					</select>	  
				  </td>
				</tr>	
				
				<tr>
				  <th>주소</th>
				  <td><input name="t_address" type="text" size="40"></td>
				</tr>
				<tr>
				  <th>연락처</th>
				  <td>
					<input name="t_mobile_1" type="text" size="3"> -
					<input name="t_mobile_2" type="text" size="4"> -
					<input name="t_mobile_3" type="text" size="4">
				  </td>
				</tr>
				<tr>
				  <th>남여구분</th>
				  <td>
					  <input type="radio" value="f" name="t_gender" class="middleCheck" /> 여&nbsp;&nbsp;        
					  <input type="radio" value="m" name="t_gender" class="middleCheck" /> 남        
				  </td>
				</tr>
				<tr>
				  <th>취미</th>
				  <td>
					  <input type="checkbox" value="y" name="t_hobby_travel" class="middleCheck" /> 여행&nbsp;&nbsp; 
					  <input type="checkbox" value="y" name="t_hobby_reading" class="middleCheck" /> 독서&nbsp;&nbsp; 
					  <input type="checkbox" value="y" name="t_hobby_sports" class="middleCheck" /> 운동
				  </td>
				</tr>
			  </tbody>
			</table>
			</form>
			<div class="buttonGroup_center">
				<a href="javascript:goJoin()" class="butt">JOIN</a>
			</div>	
		</div>	

		<div id="b_bottom">
			<%@ include file="../common_footer.jsp" %>	
		</div>	
	</div>	
</body>
</html>






    