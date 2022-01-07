package model;

public class StationModel {
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String ADDRESS = "address";
	public static final String NUM_OF_BIKES = "num_of_bikes";
	public static final String NUM_OF_EBIKES = "num_of_ebikes";
	public static final String NUM_OF_TWINBIKES = "num_of_twinbikes";
	public static final String NUM_OF_EMPTY_DOCKS = "num_of_empty_docks";
	
	private Integer id;
	
	private String name;
	
	private String address;
	
	private Integer num_of_bikes;
	
	private Integer num_of_ebikes;
	
	private Integer num_of_twinbikes;
	
	private Integer num_of_empty_docks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getNum_of_bikes() {
		return num_of_bikes;
	}

	public void setNum_of_bikes(Integer num_of_bikes) {
		this.num_of_bikes = num_of_bikes;
	}

	public Integer getNum_of_ebikes() {
		return num_of_ebikes;
	}

	public void setNum_of_ebikes(Integer num_of_ebikes) {
		this.num_of_ebikes = num_of_ebikes;
	}

	public Integer getNum_of_twinbikes() {
		return num_of_twinbikes;
	}

	public void setNum_of_twinbikes(Integer num_of_twinbikes) {
		this.num_of_twinbikes = num_of_twinbikes;
	}

	public Integer getNum_of_empty_docks() {
		return num_of_empty_docks;
	}

	public void setNum_of_empty_docks(Integer num_of_empty_docks) {
		this.num_of_empty_docks = num_of_empty_docks;
	}

	@Override
	public String toString() {
		return "StationModel [id=" + id + ", name=" + name + ", address=" + address + ", num_of_bikes=" + num_of_bikes
				+ ", num_of_ebikes=" + num_of_ebikes + ", num_of_twinbikes=" + num_of_twinbikes
				+ ", num_of_empty_docks=" + num_of_empty_docks + "]";
	}
	
	public String getShortInfomation() {
		return name + " : " + address;
	}
	
}
