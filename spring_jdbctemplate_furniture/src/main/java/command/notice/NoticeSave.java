package command.notice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.CommonExcute;
import common.CommonUtil;
import dao.NoticeDao;
import dto.NoticeDto;

public class NoticeSave implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		NoticeDao dao = new NoticeDao();
		int maxSize = 1024 * 1024 * 10;
		MultipartRequest mpr = null;
		try {
			mpr =
				new MultipartRequest(request, CommonUtil.getFile_dir_notice(), maxSize, "utf-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String no = dao.getMaxNo();
		String title = mpr.getParameter("t_title");
		String content = mpr.getParameter("t_content");
		String attach = mpr.getFilesystemName("t_attach");
		if(attach== null)attach ="";
		String reg_id = CommonUtil.getSessionId(request);
		String reg_date = CommonUtil.getTodayTime();
		
		NoticeDto dto = new NoticeDto(no, title, content, attach, reg_id, reg_date);
		
		int result = dao.noticeSave(dto);
		String msg = "등록 되었습니다.";
		if(result != 1)msg="등록실패";
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", "Notice");
	}

}
