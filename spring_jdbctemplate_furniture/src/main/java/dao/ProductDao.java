package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import common.DBConnection;
import dto.ProductDto;

public class ProductDao {
	Connection 			con = null;
	PreparedStatement 	ps  = null;
	ResultSet 			rs  = null;
	

	// product 번호 만들기
	public String getMaxNo(){
		String newNo    ="";
		String query ="select nvl(max(no),'P000')\r\n"+ 
				      " from bike_홍길동_product";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();	
			if(rs.next()) {
				String no = rs.getString(1); 
				no = no.substring(1);
				int num = Integer.parseInt(no); 
				num = num + 1; 
				
				DecimalFormat df = new DecimalFormat("P000");
				newNo = df.format(num);
			}
		}catch(Exception e) {
			System.out.println("product getMaxNo() 오류:"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}		
		return newNo;
	}
	
	// 제품 등록
	public int productSave(ProductDto dto) {
		int result = 0;
		String query ="insert into bike_홍길동_product\r\n" + 
				"(no, product_name, content, attach, \r\n" + 
				"	p_size, price, sort, reg_id, reg_date)\r\n" + 
				"values\r\n" + 
				"('"+dto.getNo()+"','"+dto.getProduct_name()+"','"+dto.getContent()+"','"+dto.getAttach()+"',\r\n" + 
				"   '"+dto.getP_size()+"','"+dto.getPrice()+"','"+dto.getSort()+"','"+dto.getReg_info()+"',to_date('"+dto.getReg_date()+"','yyyy-MM-dd hh24:mi:ss'))";
		try {
			con 	= DBConnection.getConnection();
			ps  	= con.prepareStatement(query);
			result  = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println(" productSave() 오류:"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}			
		return result;
	}

	// 목록
	public ArrayList<ProductDto> getProductList(String select,String search,
											int start, int end) {
		ArrayList<ProductDto> dtos = new ArrayList<ProductDto>();
		String query ="select * from(\r\n" + 
						"select tbl.*, rownum as rnum\r\n" + 
						"from\r\n" + 
						"(" +
						"select a.no, a.attach, a.product_name, b.name,\r\n" + 
						"       to_char(a.reg_date,'yyyy-MM-dd') as reg_date,\r\n" + 
						"       a.hit\r\n" + 
						"from bike_홍길동_product a,\r\n" + 
						"     bike_홍길동_member b\r\n" + 
						" where a.reg_id = b.id\r\n" + 
						" and "+select+" like '%"+search+"%' "+
						" order by to_number(a.sort) desc, a.no desc"+
						") tbl) \r\n" + 
						"where rnum >="+start+" and rnum <="+end+"" ;
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {		
				String no 		= rs.getString("no");
				String attach 	= rs.getString("attach");
				String product_name = rs.getString("product_name");
				String reg_info = rs.getString("name");
				String reg_date = rs.getString("reg_date");
				int hit         = rs.getInt("hit");
				
				ProductDto dto = new ProductDto(no, attach, product_name, reg_info, reg_date, hit);
				dtos.add(dto);
			}
		}catch(Exception e) {
			System.out.println(" getProductList() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}			
		return dtos;
	}

	
	// 인덱스 제품 목록
	public ArrayList<ProductDto> getProductIndex() {
		ArrayList<ProductDto> dtos = new ArrayList<ProductDto>();
		String query ="select * from(\r\n" + 
						"select tbl.*, rownum as rnum\r\n" + 
						"from\r\n" + 
						"(" +
						"select no, attach, product_name , to_char(price,'999,999') as price " + 
						"from bike_홍길동_product \r\n" + 
						" order by to_number(sort) desc, no desc"+
						") tbl) \r\n" + 
						"where rnum >=1 and rnum <=4" ;
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {		
				String no 		= rs.getString("no");
				String attach 	= rs.getString("attach");
				String product_name = rs.getString("product_name");
				String price 	= rs.getString("price");
				
				ProductDto dto = new ProductDto(no, product_name, attach,  price);
				dtos.add(dto);
			}
		}catch(Exception e) {
			System.out.println(" getProductIndex() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}			
		return dtos;
	}	
	
	
	
	// 조회 count 페이징용
	public int getTotalCount(String select, String search) {
		int count =0;
		String query ="select count(*) total_count " + 
				"from bike_홍길동_product a,\r\n" + 
				"     bike_홍길동_member b\r\n" + 
				" where a.reg_id = b.id\r\n" + 
				" and "+select+" like '%"+search+"%' ";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("total_count");
			}
		}catch(Exception e) {
			System.out.println(" getTotalCount() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}					
		return count;
	}

	// 상세보기
	public ProductDto getProductView(String no) {
		ProductDto dto = null;
		String query ="select a.product_name, a.content, a.attach,\r\n" + 
				"        a.p_size, a.price, a.sort, a.hit, \r\n" + 
				"        b.name, to_char(a.reg_date,'yyyy-MM-dd') reg_date\r\n" + 
				"from bike_홍길동_product a,\r\n" + 
				"     bike_홍길동_member b\r\n" + 
				"where a.reg_id = b.id\r\n" + 
				"and a.no ='"+no+"'";
		try {
			con = DBConnection.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				String product_name = rs.getString("product_name");
				String content 		= rs.getString("content");
				String attach 		= rs.getString("attach");
				String p_size 		= rs.getString("p_size");
				String price 		= rs.getString("price");
				String sort 		= rs.getString("sort");
				int hit 			= rs.getInt("hit");
				String reg_info		= rs.getString("name");
				String reg_date 	= rs.getString("reg_date");
				
				dto = new ProductDto(no, product_name, content, 
									 attach, p_size, price, sort, 
									 reg_info, reg_date, hit);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("getProductView() 오류: " +query);
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}		
		
		return dto;
	}
	
	// 조회수 증가
	public void setHit(String no) {
		String query =" update bike_홍길동_product\r\n" + 
						" set hit = hit + 1\r\n" + 
						" where no ='"+no+"'  ";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			int result = ps.executeUpdate();
			if(result != 1) {
				System.out.println("Product hit count 오류~~");
			}
		}catch(Exception e) {
			System.out.println("setHit()오류 :"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}			
	}

	// 수정 저장
	public int productUpdate(ProductDto dto) {
		int result = 0;
		String query ="update bike_홍길동_product\r\n" + 
				"set product_name ='"+dto.getProduct_name()+"',\r\n" + 
				"    content ='"+dto.getContent()+"',\r\n" + 
				"    attach='"+dto.getAttach()+"',\r\n" + 
				"    p_size='"+dto.getP_size()+"',\r\n" + 
				"    price='"+dto.getPrice()+"',\r\n" + 
				"    sort='"+dto.getSort()+"',\r\n" + 
				"    reg_id='"+dto.getReg_info()+"',\r\n" + 
				"    reg_date='"+dto.getReg_date()+"'\r\n" + 
				"where no='"+dto.getNo()+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("productUpdate() 오류:"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}			
		
		return result;
	}
	
	// 삭제
	public int productDelete(String no) {
		int result = 0;
		String query =" delete from bike_홍길동_product "+
					  " where no ='"+no+"'";
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			result = ps.executeUpdate();
		}catch(Exception e) {
			System.out.println("productDelete() 오류:"+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}			
		return result;
	}

	// index 용 목록
	public ArrayList<ProductDto> getIndexProductList() {
		ArrayList<ProductDto> dtos = new ArrayList<ProductDto>();
		String query ="select * from(\r\n" + 
						"select tbl.*, rownum as rnum\r\n" + 
						"from\r\n" + 
						"(" +
						"select no, attach, product_name,\r\n" + 
						"       to_char(price,'999,999') price \r\n" + 
						"from bike_홍길동_product \r\n" + 
						" order by to_number(sort) desc, no desc"+
						") tbl) \r\n" + 
						"where rnum >=1 and rnum <=6" ;
		try {
			con = DBConnection.getConnection();
			ps  = con.prepareStatement(query);
			rs  = ps.executeQuery();
			while(rs.next()) {		
				String no 		= rs.getString("no");
				String attach 	= rs.getString("attach");
				String product_name = rs.getString("product_name");
				String price 	= rs.getString("price");
				
				ProductDto dto = 
						new ProductDto(no, product_name, attach, price);  
				dtos.add(dto);
			}
		}catch(Exception e) {
			System.out.println(" getIndexProductList() 오류 : "+query);
			e.printStackTrace();
		}finally {
			DBConnection.closeDB(con, ps, rs);
		}			
		return dtos;
	}	
}

















