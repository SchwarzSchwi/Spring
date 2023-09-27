package command.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.CommonExcute;
import common.CommonUtil;
import dao.NoticeDao;
import dto.NoticeDto;

public class NoticeList implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		NoticeDao dao = new NoticeDao();
		String select = request.getParameter("t_select");
		String search = request.getParameter("t_search");
		if(select == null) {
			select = "title";
			search = "";
		}
		/* paging 설정 start*/
		int total_count = dao.getTotalCount(select,search);
		int list_setup_count = 5;  //한페이지당 출력 행수 
		int pageNumber_count = 3;  //한페이지당 출력 페이지 갯수
		
		String nowPage = request.getParameter("t_nowPage");
		int current_page = 0; // 현재페이지 번호
		int total_page = 0;    // 전체 페이지 수
		
		if(nowPage == null || nowPage.equals("")) current_page = 1; 
		else current_page = Integer.parseInt(nowPage);
		
		total_page = total_count / list_setup_count;  // 몫 : 2
		int rest = 	total_count % list_setup_count;   // 나머지:1
		if(rest !=0) total_page = total_page + 1;     // 3
		
		int start = (current_page -1) * list_setup_count + 1;
		int end   = current_page * list_setup_count;
		/* paging 설정 end*/	
		int order = total_count - ((current_page -1) * list_setup_count);
	
		
		List<NoticeDto>dtos=
				dao.getNoticeListPage(select, search, start, end);
		String pageDisplay = CommonUtil.pageListPost(current_page, total_page, pageNumber_count);
				
		
		request.setAttribute("order", order);
		request.setAttribute("dtos", dtos);
		request.setAttribute("select", select);
		request.setAttribute("search", search);
		request.setAttribute("pageDisplay", pageDisplay);
	}

}
