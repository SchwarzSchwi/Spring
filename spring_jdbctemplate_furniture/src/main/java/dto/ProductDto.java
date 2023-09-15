package dto;

public class ProductDto {
	private String no,product_name,content,attach,
	 		p_size, price, sort, reg_info, reg_date;
	private int hit;
	
	
	//목록조회용
	public ProductDto(String no, String attach, String product_name, String reg_info, String reg_date, int hit) {
		this.no = no;
		this.attach = attach;
		this.product_name = product_name;
		this.reg_info = reg_info;
		this.reg_date = reg_date;
		this.hit = hit;
	}

	// index 목록 조회용
	public ProductDto(String no, String product_name, String attach, String price) {
		this.no = no;
		this.product_name = product_name;
		this.attach = attach;
		this.price = price;
	}


	public ProductDto(String no, String product_name, String content, String attach, String p_size, String price,
			String sort, String reg_info, String reg_date, int hit) {
		this.no = no;
		this.product_name = product_name;
		this.content = content;
		this.attach = attach;
		this.p_size = p_size;
		this.price = price;
		this.sort = sort;
		this.reg_info = reg_info;
		this.reg_date = reg_date;
		this.hit = hit;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNo() {
		return no;
	}

	public String getProduct_name() {
		return product_name;
	}

	public String getContent() {
		return content;
	}

	public String getAttach() {
		return attach;
	}

	public String getP_size() {
		return p_size;
	}

	public String getPrice() {
		return price;
	}

	public String getSort() {
		return sort;
	}

	public String getReg_info() {
		return reg_info;
	}

	public String getReg_date() {
		return reg_date;
	}

	public int getHit() {
		return hit;
	}
	
	
}
