<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../Common_header.jsp"%>
				<!-- Main -->
					<div id="main">
						<div class="inner">
							<header>
							</header>
							<section class="">
								<div class="main_title">
									<h1>관리 페이지</h1>
									<input type="text" placeholder="검색어를 입력하세요" class="search" style="width:300px;float:right;margin-left:0.5em;">
									<select style="width:170px;float:right;margin-left:0.5em;">
										<option>가나다라마바사</option>
									</select>
								</div>
							</section>
							<section class="lines">
								<article>
									<table style="width:100%">
										<colgroup>
											<col width="10%">
											<col width="*">
											<col width="10%">
											<col width="15%">
											<col width="10%">
										</colgroup>
										<tr>
											<th>상품번호</th>
											<th>상품명</th>
											<th>가격</th>
											<th>등록일</th>
											<th>판매 건수</th>
										</tr>
										<tr>
											<td>001</td>
											<td>어디로든 문</td>
											<td style="text-align:left;">9,000,000,000￦</td>
											<td>2025-40-21</td>
											<td>3,214</td>
										</tr>
									</table>
								</article>
							</section>
							<div class="btn_group sell">
								<input type="button" value="판매 통계" onclick="location.href='manage_statistics.jsp'">
								<input type="button" value="신규 등록하기" onclick="location.href='manage_create.jsp'">
							</div>
						</div>
					</div>
<%@ include file="../Common_footer.jsp"%>