package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.CommonExcute;
import common.CommonUtil;
import dao.MemberDao;

public class MemberExit implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		MemberDao dao = new  MemberDao();
		String id = CommonUtil.getSessionId(request);
		String exitDate = CommonUtil.getTodayTime();
		
		int result = dao.memberExit(id, exitDate);
		String msg = "탈퇴되었습니다";
		if(result == 1) {
			HttpSession session = request.getSession();
		}else {
			msg = "탈퇴처리오류";
		}
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "/furni/");
	}
	

}
