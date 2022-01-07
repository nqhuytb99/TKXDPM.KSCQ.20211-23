package common;

public class SearchQueryInfomation {
	private String nameAddressSearch;
	private Integer minPrice;
	private Integer maxPrice;
	private String typeBike;
	
	public SearchQueryInfomation(String nameAddressSearch, Integer minPrice, Integer maxPrice, String typeBike) {
		super();
		this.nameAddressSearch = nameAddressSearch;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.typeBike = typeBike;
	}

	public String getNameAddressSearch() {
		return nameAddressSearch;
	}

	public void setNameAddressSearch(String nameAddressSearch) {
		this.nameAddressSearch = nameAddressSearch;
	}

	public Integer getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}

	public Integer getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getTypeBike() {
		return typeBike;
	}

	public void setTypeBike(String typeBike) {
		this.typeBike = typeBike;
	}
	
	public boolean haveSearchQuery() {
		return haveCheckSearchQuery() || haveCheckTypeBike() || haveCheckPrice();
	}
	
	public boolean haveCheckSearchQuery() {
		return !nameAddressSearch.equals("");
	}
	
	public boolean haveCheckTypeBike() {
		return !typeBike.equals("all");
	}
	
	public boolean haveCheckPrice() {
		return minPrice != 0 || maxPrice != 0;
	}
}
