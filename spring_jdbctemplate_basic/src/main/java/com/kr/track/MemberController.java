package com.kr.track;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import command.member.MemberDelete;
import command.member.MemberList;
import command.member.MemberSave;
import command.member.MemberUpdate;
import command.member.MemberView;
import common.CommonExecute;
import common.CommonTemplate;
import dto.MemberDto;

@Controller
public class MemberController {
	
	@Autowired
	MemberDto memDto;
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	public void setTemplate() {
		CommonTemplate.setTemplate(template);
	}
	
	
	@RequestMapping("Member")
	public String member(HttpServletRequest req) {
		
//		CommonTemplate tem = new CommonTemplate();
		String gubun = req.getParameter("t_gubun");
		if(gubun == null)gubun = "list";
		String viewPage = "";
		
		//list
		if(gubun.equals("list")) {
			MemberList member = new MemberList();
			member.execute(req);
			viewPage = "/memberMvc/member_list";
		//writeForm	
		}else if(gubun.equals("writeForm")) {
			viewPage ="/memberMvc/member_write";
		//memberSave	
		}else if(gubun.equals("memberSave")) {
			CommonExecute member = new MemberSave();
			member.execute(req);
			viewPage = "/common_alert";
		//memberView	
		}else if(gubun.equals("memberView")) {
			CommonExecute member = new MemberView();
			member.execute(req);
			viewPage = "/memberMvc/member_view";
		//memberUpdateForm	
		}else if(gubun.equals("memberUpdateForm")) {
			CommonExecute member = new MemberView();
			member.execute(req);
			viewPage="/memberMvc/member_update";
		//memberUpdate	
		}else if(gubun.equals("memberUpdate")) {
			CommonExecute member = new MemberUpdate();
			member.execute(req);
			viewPage="/common_alert";
		//memberDelete	
		}else if(gubun.equals("memberDelete")) {
			CommonExecute member = new MemberDelete();
			member.execute(req);
			viewPage="/common_alert";
		}	
		return viewPage;
			
//		String query="select id,name,age,\r\n" + 
//				"    to_char (reg_date,'yyyy-mm-dd') as reg_date\r\n" + 
//				"from H_최선우_MEMBER\r\n" + 
//				"where id = '101'";
//		
//		RowMapper<MemberDto> memDto = new BeanPropertyRowMapper<>(MemberDto.class);
//		MemberDto dto = template.queryForObject(query, memDto);
//		
//		System.out.println("ID:"+dto.getId());
//		System.out.println("name:"+dto.getName());
//		System.out.println("age:"+dto.getAge());
//		System.out.println("reg_date:"+dto.getReg_date());
		
//		String query ="select id,name,age,\r\n" + 
//				"    to_char (reg_date,'yyyy-mm-dd') as reg_date\r\n" + 
//				" from H_최선우_MEMBER\r\n" + 
//				" order by id desc";
//		RowMapper<MemberDto> memDtoList = new BeanPropertyRowMapper<>(MemberDto.class);
//		ArrayList<MemberDto>dtos = (ArrayList<MemberDto>) template.query(query, memDtoList);
//		
//		System.out.println("회원수:"+dtos.size());
//		
//		query="delete from h_최선우_member\r\n"  +
//				"where id ='101'";
//		
//		int result =template.update(query);
//		
//		System.out.println("삭제:"+result);
//		
//		return "home";
	}
	
}
