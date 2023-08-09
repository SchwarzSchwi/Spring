<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>
<html> 
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--
	******************************************** 
		title : 풀스텍 홍길동
	******************************************** 
 -->	
	<title>TRACK11 최선우</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">	
	<link href="css/common.css" rel="stylesheet">
	<link href="css/layout.css" rel="stylesheet">	
<script type="text/javascript">
	function goSearch(){
		snack.method="post";
		snack.action="Snack";
		snack.submit();
	}
	function goView(p_code){
		view.t_gubun.value = "snackView";
		view.t_p_code.value = p_code;
		view.method="post";
		view.action="Snack";
		view.submit();
	}
	function goWriteForm(){
		view.t_gubun.value = "writeForm";
		view.method="post";
		view.action="Snack";
		view.submit();
	}
</script>
</head>
<body>
<form name="view">
	<input type="hidden" name="t_p_code">
	<input type="hidden" name="t_gubun">
</form>
	<div class="container">

		<div class="leftmargin">
			<img src="images/jsl_logo.png"><h1>TRACK11 최선우 SNACK</h1>
		</div>		
		<div class="search_wrap">
			<div class="record_group">
				<p>총게시글 : <span>${t_dtos.size()}</span>건</p>
			</div>
			<form name="snack">
			<div class="search_group">
				<select name=t_select class="select">
					<option value="p_name" <c:if test="${t_select eq 'p_name'}">selected</c:if>>제품명</option>
					<option value="m_name" <c:if test="${t_select eq 'm_name'}">selected</c:if>>제조사명</option>
				</select> 
				<input name="t_search" value="${t_search}" type="text" class="search_word">
				<button onclick="goSearch()" class="btn_search"><i class="fa fa-search"></i><span class="sr-only">검색버튼</span></button>
			</div>
			</form>
		</div>
	</div>
	<div class="board_list">
		<table class="board_table">
			<colgroup>
				<col width="25%">
				<col width="25%">
				<col width="25%">
				<col width="25%">
			</colgroup>
			<thead>
				<tr>
					<th>제품번호</th>
					<th>제품명</th>
					<th>제조사명</th>
					<th>가격</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${t_dtos}" var="dto"> 
				<tr>
					<td><a href="Snack?t_p_code=${dto.getP_code()}&t_gubun=snackView">${dto.getP_code()}</a></td>
					<td><a href="javascript:goView('${dto.getP_code()}')">${dto.getP_name()}</a></td>
					<td>${dto.getM_name()}</td>
					<td>${dto.getStrPrice()}</td>
				</tr>	
			</c:forEach>
			</tbody>
		</table>
		<div class="paging">
			<a href="javascript:goWriteForm()" class="write">제품등록</a>
		</div>
	</div>
 </body>
</html>







