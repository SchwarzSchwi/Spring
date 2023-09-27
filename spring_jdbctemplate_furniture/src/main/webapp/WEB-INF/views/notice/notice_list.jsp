<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common_header.jsp"%>
<script>
	function goSearch(){
		noti.method="post";
		noti.action="Notice";
		noti.submit();
	}
	function goPage(no){
		noti.t_nowPage.value = no;
		noti.method="post";
		noti.action="Notice";
		noti.submit();
	}
	function goWriteForm(){
		notice.t_gubun.value = "writeForm";
		notice.method="post";
		notice.action="Notice";
		notice.submit();
	}
</script>
<form name="notice">
	<input type="hidden" name="t_gubun">
</form>
		<div id="b_left">
			<%@ include file="../common_menu.jsp"%>
		</div>
		
		<div id="b_right">
			<p class="n_title">
				NOTICE
			</p>
			<form name="noti">
			<input type=hidden name="t_nowPage">
			<p class="select_box">
				<select name="t_select"class="sel_box">
					<option value="title"<c:if test="${select eq 'title'}">selected</c:if>>Title</option>
					<option value="content"<c:if test="${select eq 'condtent'}">selected</c:if>>Content</option>
				</select>
				<input type="text" name="t_search" value="${search}" class="sel_text">

				<button type="button" onclick="goSearch()" class="sel_button"><i class="fa fa-search"></i> SEARCH</button>
			</p>
			<table class="boardList">
				<colgroup>
					<col width="5%">
					<col width="60%">
					<col width="5%">
					<col width="10%">
					<col width="14%">
					<col width="6%">
				</colgroup>
				<thead>
					<tr>
						<th>No</th>
						<th>Title</th>
						<th>Attach</th>
						<th>Reg Name</th>
						<th>Reg Date</th>
						<th>Hit</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="number" value="${order}"></c:set>
					<c:forEach items="${dtos}" var="dto">
					<tr>
						<td>
							${number}
						</td>
						<td class="t_center"><a href="notice_view.html">${dto.getTitle()}</a></td>
						<td>
							<c:if test="${not empty dto.getAttach()}">
							<img src="images/clip.png">
							</c:if>
						</td>
						<td>${dto.getReg_name()}</td>
						<td>${dto.getReg_date()}</td>
						<td>${dto.getHit()}</td>
					</tr>
					</c:forEach>	
				</tbody>
			</table>
			</form>
							
				<div class="paging">
				<!--
				<a href=""><i class="fa fa-angle-double-left"></i></a>
				<a href=""><i class="fa fa-angle-left"></i></a>
				<a href="" class="active">1</a>
				<a href="">2</a>
				<a href="">3</a>
				<a href="">4</a>
				<a href="">5</a>
				<a href=""><i class="fa fa-angle-right"></i></a>
				<a href=""><i class="fa fa-angle-double-right"></i></a>
				-->
				${pageDisplay}
				<c:if test="${sessionLevel eq 'top'}">
				<a href="javascript:goWriteForm()" class="write">글쓰기</a>
				</c:if>
			</div>
		</div>	
	
		<div id="b_bottom">
			<%@ include file="../common_footer.jsp" %>	
		</div>	
	</div>	
</body>
</html>