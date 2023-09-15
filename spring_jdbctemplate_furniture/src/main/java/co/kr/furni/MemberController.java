package co.kr.furni;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import command.member.MemberLogin;
import command.member.MemberLogout;
import command.member.MemberMyinfo;
import command.member.MemberSave;
import common.CommonExcute;
import common.CommonToday;
import dao.MemberDao;

@Controller
public class MemberController {
	
	@RequestMapping("Member")
	public String member(HttpServletRequest request){
		String gubun = request.getParameter("t_gubun");
		if(gubun == null) gubun ="login";
		String viewPage ="";
		
		if(gubun.equals("login")) {
			viewPage="member/member_login";
			
		} else if(gubun.equals("join")) {
			viewPage="member/member_join";

		//회원가입
		} else if(gubun.equals("memberSave")) {
			CommonExcute member = new MemberSave();
			member.execute(request);
			
			viewPage="common_alert";
		
		//로그인
		} else if(gubun.equals("memberLogin")) {
			CommonExcute member = new MemberLogin();
			member.execute(request);
			viewPage="common_alert";
			
		//로그아웃
		} else if(gubun.equals("logout")) {
			CommonExcute member = new MemberLogout();
			member.execute(request);
			viewPage="common_alert";

		//내정보
		} else if(gubun.equals("myinfo")) {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("sessionId");
			if(id == null) {
				request.setAttribute("t_msg", "세션정보가 만료 되었습니다.");
				request.setAttribute("t_url", "Member");
				viewPage="common_alert";
			} else {
				CommonExcute member = new MemberMyinfo();
				member.execute(request);
				viewPage="member/member_myinfo";
			}
		}
		
		return viewPage; 
	}
	
	@RequestMapping("MemberCheckId")
	public void memberCheckId(HttpServletRequest request,
							  HttpServletResponse response){
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		int count = dao.getIdCount(id);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(count == 0 ) out.print("사용가능");
		else out.print("사용불가");
	}
}











