package dao;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import common.CommonTemplate;
import dto.MemberDto;

public class MemberDao {
	
	JdbcTemplate temp = CommonTemplate.getTemplate();

	
	//정보삭제
	public int memberDelete(String id) {
		String query="delete from h_최선우_member\r\n" + 
				"where id = '"+id+"'";
		return temp.update(query);
	}
	//정보수정
	public int memberUpdate(MemberDto dto) {
		String query ="update h_최선우_member\r\n" + 
				"set name = '"+dto.getName()+"',\r\n" + 
				"    age = "+dto.getAge()+",\r\n" + 
				"    reg_date = '"+dto.getReg_date()+"'\r\n" + 
				"where id = '"+dto.getId()+"'";
		return temp.update(query);
	}
	//상세조회
	public MemberDto getMemberView(String id) {
		String query="select id,name,age,\r\n" + 
				"    to_char (reg_date,'yyyy-mm-dd') as reg_date\r\n" + 
				"from \"H_최선우_MEMBER\"\r\n" + 
				"where id = '"+id+"'";
		RowMapper<MemberDto> memDto = new BeanPropertyRowMapper<>(MemberDto.class);
		MemberDto dto = temp.queryForObject(query, memDto);
		
		return dto;
	}
	//회원목록
	public ArrayList<MemberDto> getMemberList(String select, String search){
		ArrayList<MemberDto> dto = new ArrayList<>();
	String query ="select id,name,age,\r\n" + 
			"    to_char (reg_date,'yyyy-mm-dd') as reg_date\r\n" + 
			" from \"H_최선우_MEMBER\"\r\n" + 
			" where "+select+" like '%"+search+"%'" +
			" order by id desc";
	RowMapper<MemberDto> memDto = new BeanPropertyRowMapper<MemberDto>(MemberDto.class);
	
	ArrayList<MemberDto> dtos = (ArrayList<MemberDto>)temp.query(query, memDto);

	return dtos;
	}
	//회원등록
	public int memberSave(MemberDto dto){
		String query ="insert into h_최선우_member\r\n" + 
				"(id,name,age,reg_date)\r\n" + 
				"values\r\n" + 
				"('"+dto.getId()+"','"+dto.getName()+"',"+dto.getAge()+",'"+dto.getReg_date()+"')";
//		int result = temp.update(query);
		return temp.update(query);
	}
	//상세보기
}
