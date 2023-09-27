package co.kr.furni;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import command.notice.NoticeList;
import command.notice.NoticeSave;
import common.CommonUtil;

@Controller
public class NoticeController {

	@RequestMapping("Notice")
	public String notice(HttpServletRequest request) {
		String gubun = request.getParameter("t_gubun");
		if(gubun == null)gubun = "list";
		String viewPage = "";
		
		HttpSession session = request.getSession();
		String sessionLevel = (String)session.getAttribute("sessionLevel");
		
		String dirInfo = request.getSession().getServletContext().getRealPath("/");
		
//		C:\Users\Choi\Desktop\it\spring workproject\spring_project\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\spring_jdbctemplate_furniture\
		
		System.out.println("dirInfo : "+dirInfo);
		
		//목록
		if(gubun.equals("list")) {
			NoticeList notice = new NoticeList();
			notice.execute(request);
			viewPage = "notice/notice_list";
		//등록폼	
		}else if (gubun.equals("writeForm")) {
			if(sessionLevel.equals("top")) {
				request.setAttribute("t_toDay", CommonUtil.getToday());
				viewPage = "notice/notice_write";
			}else {
				request.setAttribute("t_msg", "관리자 메뉴입니다.");
				request.setAttribute("t_url", "Notice");
				viewPage = "common_alert";
			}
		//등록저장	
		}else if(gubun.equals("noticeSave")) {
			NoticeSave notice = new NoticeSave();
			notice.execute(request);
			
			viewPage = "common_alert";
		}
		return viewPage;
	}
}
