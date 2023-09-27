<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../Common_header.jsp"%>
				<!-- Main -->
					<div id="main">
						<div class="inner">
							<header>
							</header>
							<section class="">
								<div class="main_title" style="text-align:center;">
									<h1>판매 통계</h1>
								</div>
							</section>
							<section class="lines">
								<form name="admin">
									<table class="boardForm">
										<colgroup>
											<col width="100">
											<col width="100">
											<col width="152">
											<col width="352">
										</colgroup>
										<tr class="statics_text">
											<th colspan="2">총 판매 건 수</th>
											<td colspan="2">
												<input type="text" name="" value="" disabled style="border:none;">
											</td>
										</tr>
										<tr class="statics_text">
											<th colspan="2">총 매출액</th>
											<td colspan="2">
												<input type="text" name="" value="" disabled style="border:none;">
											</td>
										</tr>
										<tr>
											<th colspan="3" style="padding-top:0.75em;">
												제품별 판매 건수
											</th>
											<th style="padding-top:0.75em;">
												고객별 매출액 
											</th>
										</tr>
										<tr>
											<td colspan="3">
												<div style="width: 352px; height: 176px;">
													<canvas id="p_Count"></canvas>
												</div>
											</td>
										<script type="text/javascript">
								            var context = document
								                .getElementById('p_Count')
								                .getContext('2d');
								            var p_Count = new Chart(context, {
								                type: 'bar', // 차트의 형태
								                data: { // 차트에 들어갈 데이터
								                    labels: [
								                        //x 축
								                        '식료품','의류','앤티크','원예','음반','공구'
								                    ],
								                    datasets: [
								                        { //데이터
								                            label: 'test1', //차트 제목
								                            fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
								                            data: [
								                                21,19,25,20,23,26 //x축 label에 대응되는 데이터 값
								                            ],
								                            backgroundColor: [
								                                //색상
								                                'rgba(255, 99, 132, 0.2)',
								                                'rgba(54, 162, 235, 0.2)',
								                                'rgba(255, 206, 86, 0.2)',
								                                'rgba(75, 192, 192, 0.2)',
								                                'rgba(153, 102, 255, 0.2)',
								                                'rgba(255, 159, 64, 0.2)'
								                            ],
								                            borderColor: [
								                                //경계선 색상
								                                'rgba(255, 99, 132, 1)',
								                                'rgba(54, 162, 235, 1)',
								                                'rgba(255, 206, 86, 1)',
								                                'rgba(75, 192, 192, 1)',
								                                'rgba(153, 102, 255, 1)',
								                                'rgba(255, 159, 64, 1)'
								                            ],
								                            borderWidth: 1 //경계선 굵기
								                        }/* ,
								                        {
								                            label: 'test2',
								                            fill: false,
								                            data: [
								                                8, 34, 12, 24
								                            ],
								                            backgroundColor: 'rgb(157, 109, 12)',
								                            borderColor: 'rgb(157, 109, 12)'
								                        } */
								                    ]
								                },
								                options: {
								                    scales: {
								                        yAxes: [
								                            {
								                                ticks: {
								                                    beginAtZero: true
								                                }
								                            }
								                        ]
								                    }
								                }
								            });
								        </script>
											<td>
												<div style="width: 352px; height: 176px;">
													<canvas id="c_Cell"></canvas>
												</div>
											</td>
										</tr>
									<script type="text/javascript">
							            var context = document
							                .getElementById('c_Cell')
							                .getContext('2d');
							            var c_Cell = new Chart(context, {
							                type: 'bar', // 차트의 형태
							                data: { // 차트에 들어갈 데이터
							                    labels: [
							                        //x 축
							                        '1','2','3','4','5','6','7'
							                    ],
							                    datasets: [
							                        { //데이터
							                            label: 'test1', //차트 제목
							                            fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
							                            data: [
							                                21,19,25,20,23,26,25 //x축 label에 대응되는 데이터 값
							                            ],
							                            backgroundColor: [
							                                //색상
							                                'rgba(255, 99, 132, 0.2)',
							                                'rgba(54, 162, 235, 0.2)',
							                                'rgba(255, 206, 86, 0.2)',
							                                'rgba(75, 192, 192, 0.2)',
							                                'rgba(153, 102, 255, 0.2)',
							                                'rgba(255, 159, 64, 0.2)'
							                            ],
							                            borderColor: [
							                                //경계선 색상
							                                'rgba(255, 99, 132, 1)',
							                                'rgba(54, 162, 235, 1)',
							                                'rgba(255, 206, 86, 1)',
							                                'rgba(75, 192, 192, 1)',
							                                'rgba(153, 102, 255, 1)',
							                                'rgba(255, 159, 64, 1)'
							                            ],
							                            borderWidth: 1 //경계선 굵기
							                        }/* ,
							                        {
							                            label: 'test2',
							                            fill: false,
							                            data: [
							                                8, 34, 12, 24
							                            ],
							                            backgroundColor: 'rgb(157, 109, 12)',
							                            borderColor: 'rgb(157, 109, 12)'
							                        } */
							                    ]
							                },
							                options: {
							                    scales: {
							                        yAxes: [
							                            {
							                                ticks: {
							                                    beginAtZero: true
							                                }
							                            }
							                        ]
							                    }
							                }
							            });
							        </script>
										<tr>
											<th colspan="2">월별 통계</th>
											<td colspan="2"><input type="month" name="t_month" onchange="ajax()"></td>
										</tr>
									</table>
									<div id="hide" style="display:none;">
									<table class="boardForm">
										<colgroup>
											<col width="100">
											<col width="100">
											<col width="152">
											<col width="352">
										</colgroup>
										
											<tr class="statics_text ">
												<th colspan="2">월별 판매 건 수</th>
												<td colspan="2">
													<input type="text" name="t_count_m" disabled style="border:none;">
												</td>
											</tr>
											<tr class="statics_text ">
												<th colspan="2">월별 매출액</th>
												<td colspan="2">
													<input type="text" name="t_sell_m" disabled style="border:none;">
												</td>
											</tr>
											<tr >
												<th colspan="3">
													제품별 월 판매 건수
												</th>
												<th>
													고객별 월 매출액 
												</th>
											</tr>
											<tr >
												<td colspan="3">
													<div style="width: 352px; height: 176px;">
													<canvas id="p_Count_m"></canvas>
												</div>
												</td>
												<td>
													<div style="width: 352px; height: 176px;">
													<canvas id="c_Cell_m"></canvas>
												</div>
												</td>
											</tr>
										</table>
									</div>
								</form>
							</section>
						</div>
					</div>
<%@ include file="../Common_footer.jsp"%>