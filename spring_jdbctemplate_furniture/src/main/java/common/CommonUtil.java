package common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommonUtil {
	
	//세션ID
	public static String getSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionId");
		return id;
	}
	
	//news 첨부 경로
	public static String getFile_dir_news(){
		String newsDir ="E:/track_11/work_project/web_servlet_bike/WebContent/attach/news/";		
		return newsDir;
	}
	//공지사항 첨부 경로
	public static String getFile_dir_notice(){
		String noticeDir ="C:/Users/Choi/Desktop/it/spring workproject/spring_project/spring_jdbctemplate_furniture/src/main/webapp/WEB-INF/views/attach/notice/";		
		return noticeDir;
	}
	//product
	public static String getFile_dir_product(HttpServletRequest request) {
		//String productDir ="E:/track_11/work_project/web_servlet_bike/WebContent/attach/product/";
		String  productDir ="E:/track_11/work_project/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web_servlet_bike/attach/product/";
		
	/*
 * 
E:\track_11\work_project\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\web_servlet_bike\attach\product 
 */
		//productDir = "E:/track_11/work_project/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web_servlet_bike/attach/product/";
		//productDir = request.getSession().getServletContext().getRealPath("/")+"/attach/product/";
		return productDir;
	}		
	
	//오늘날짜
	public static String getToday(){
		Date time = new Date();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String today = format1.format(time);
		return today;
	}
	
	// 오늘 날짜 시분초 
	public static String getTodayTime(){
		Date time = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = format.format(time);
		return today;
	}	
	
	// 페이지 설정
	public static String pageListPost(int current_page,int total_page, int pageNumber_count){
		int pagenumber;    //화면에 보여질 페이지 인덱스수
		int startpage;     //화면에 보여질 시작 페이지 번호
		int endpage;       //화면에 보여질 마지막 페이지 번호
		int curpage;       //이동하고자 하는 페이지 번호
		
		String strList=""; //리턴될 페이지 인덱스 리스트

		pagenumber = pageNumber_count;   //한 화면의 페이지 인덱스수
		
		//시작 페이지 번호 구하기
		startpage = ((current_page - 1)/ pagenumber) * pagenumber + 1;
		//마지막 페이지 번호 구하기
		endpage = (((startpage -1) + pagenumber) / pagenumber)*pagenumber;
		//총페이지수가 계산된 마지막 페이지 번호보다 작을 경우
		//총페이지수가 마지막 페이지 번호가 됨
		
		if(total_page <= endpage)  endpage = total_page;
					
		//첫번째 페이지 인덱스 화면이 아닌경우
		if(current_page > pagenumber){
			curpage = startpage -1;  //시작페이지 번호보다 1적은 페이지로 이동
			strList = strList +"<a href=javascript:goPage('"+curpage+"') ><i class='fa fa-angle-double-left'></i></a>";
		}
						
		//시작페이지 번호부터 마지막 페이지 번호까지 화면에 표시
		curpage = startpage;
		while(curpage <= endpage){
			if(curpage == current_page){
				strList = strList +"<a class='active'>"+current_page+"</a>";
			} else {
				strList = strList +"<a href=javascript:goPage('"+curpage+"')>"+curpage+"</a>";
			}
			curpage++;
		}
		//뒤에 페이지가 더 있는 경우
		if(total_page > endpage){
			curpage = endpage+1;
			strList = strList + "<a href=javascript:goPage('"+curpage+"') ><i class='fa fa-angle-double-right'></i></a>";
		}
		return strList;
	}
	
	
}
