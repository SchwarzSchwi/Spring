package common;

import javax.servlet.http.HttpServletRequest;

public class CommonToday implements CommonExcute {

	@Override
	public void execute(HttpServletRequest request) {
		String toDay = CommonUtil.getToday();
		String toDayTime = CommonUtil.getTodayTime();
		request.setAttribute("t_toDay",toDay);
		request.setAttribute("t_toDayTime",toDayTime);

	}

}
