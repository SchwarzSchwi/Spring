package co.kr.furni;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import command.member.MemberExit;
import command.member.MemberLogin;
import command.member.MemberLogout;
import command.member.MemberMyinfo;
import command.member.MemberPasswordUpdate;
import command.member.MemberSave;
import command.member.MemberUpdate;
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
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionId");
		
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
			if(id == null) {
				request.setAttribute("t_msg", "세션정보가 만료 되었습니다.");
				request.setAttribute("t_url", "Member");
				viewPage="common_alert";
			} else {
				CommonExcute member = new MemberMyinfo();
				member.execute(request);
				viewPage="member/member_myinfo";
			}

		//내정보 수정 폼
		} else if(gubun.equals("updateForm")){
			if(id == null) {
				request.setAttribute("t_msg", "세션정보가 만료 되었습니다.");
				request.setAttribute("t_url", "Member");
				viewPage="common_alert";
			} else {
				CommonExcute member = new MemberMyinfo();
				member.execute(request);
				viewPage="member/member_update";
			}			
		//내정보수정저장	
		}else if(gubun.equals("memberUpdate")) {
			CommonExcute member = new MemberUpdate();
			member.execute(request);
			viewPage="common_alert";
		//비밀번호 수정 폼
		}else if(gubun.equals("passwordUpdateForm")) {
			viewPage="member/password_update";
		//비밀번호 수정 저장
		}else if(gubun.equals("passwordUpdate")) {
			CommonExcute member = new MemberPasswordUpdate();
			member.execute(request);
			viewPage="common_alert";
		//회원탈퇴	
		}else if(gubun.equals("memberExit")) {
			CommonExcute member = new MemberExit();
			member.execute(request);
			viewPage="common_alert";
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
	@RequestMapping("MemberCheckPassword")
	public void memberCheckPassword(HttpServletRequest request,
							  HttpServletResponse response){
		MemberDao dao = new MemberDao();
		String id = request.getParameter("t_id");
		String pw = request.getParameter("t_pw");

		
		try {
			pw = dao.encryptSHA256(pw);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = dao.getLoginName(id, pw);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(name.equals(""))out.print("no");
		else out.print("yes");
	}
}











