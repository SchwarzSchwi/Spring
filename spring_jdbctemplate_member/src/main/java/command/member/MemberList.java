package command.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.CommonExecute;
import dao.MemberDao;
import dto.MemberDto;

public class MemberList implements CommonExecute {

	@Override
	public void execute(HttpServletRequest req) {
		MemberDao dao = new MemberDao();
		List<MemberDto>dtos = dao.getMemberList("id", "");
	}

}
