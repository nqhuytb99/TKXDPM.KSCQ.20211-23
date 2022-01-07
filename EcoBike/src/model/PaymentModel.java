package model;

import java.sql.Timestamp;

public class PaymentModel {
	public static final String ID = "id";
	public static final String ID_BIKE = "id_bike";
	public static final String DEPOSIT_CARD_NUMBER = "deposit_card_number";
	public static final String PAYMENT_CARD_NUMBER = "payment_card_number";
	public static final String ID_STATION_RENT = "id_station_rent";
	public static final String ID_STATION_RETURN = "id_station_return";
	public static final String RENT_TIME = "rent_time";
	public static final String RETURN_TIME = "return_time";
	public static final String DEPOSIT_PRICE= "deposit_price";
	public static final String RENT_PRICE = "rent_price";
	public static final String STATUS = "status";
	
	private Integer id;
	private Integer id_bike;
	private String deposit_card_number;
	private String payment_card_number;
	private Integer id_station_rent;
	private Integer id_station_return;
	private Timestamp rent_time;
	private Timestamp return_time;
	private Integer deposit_price;
	private Integer rent_price;
	private Integer status;
	private BikeModel bikeModel;
	private StationModel stationModel;
	private StationModel returnStation;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_bike() {
		return id_bike;
	}
	public void setId_bike(Integer id_bike) {
		this.id_bike = id_bike;
	}
	public String getDeposit_card_number() {
		return deposit_card_number;
	}
	public void setDeposit_card_number(String deposit_card_number) {
		this.deposit_card_number = deposit_card_number;
	}
	public String getPayment_card_number() {
		return payment_card_number;
	}
	public void setPayment_card_number(String payment_card_number) {
		this.payment_card_number = payment_card_number;
	}
	public Integer getId_station_rent() {
		return id_station_rent;
	}
	public void setId_station_rent(Integer id_station_rent) {
		this.id_station_rent = id_station_rent;
	}
	public Integer getId_station_return() {
		return id_station_return;
	}
	public void setId_station_return(Integer id_station_return) {
		this.id_station_return = id_station_return;
	}
	public Timestamp getRent_time() {
		return rent_time;
	}
	public void setRent_time(Timestamp rent_time) {
		this.rent_time = rent_time;
	}
	public Timestamp getReturn_time() {
		return return_time;
	}
	public void setReturn_time(Timestamp return_time) {
		this.return_time = return_time;
	}
	public Integer getDeposit_price() {
		return deposit_price;
	}
	public void setDeposit_price(Integer deposit_price) {
		this.deposit_price = deposit_price;
	}
	public Integer getRent_price() {
		return rent_price;
	}
	public void setRent_price(Integer rent_price) {
		this.rent_price = rent_price;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	public BikeModel getBikeModel() {
		return bikeModel;
	}
	
	public void setBikeModel(BikeModel bikeModel) {
		this.bikeModel = bikeModel;
	}
	
	public StationModel getStationModel() {
		return stationModel;
	}
	
	public void setStationModel(StationModel stationModel) {
		this.stationModel = stationModel;
	}
	
	
	
	public StationModel getReturnStation() {
		return returnStation;
	}
	
	public void setReturnStation(StationModel returnStation) {
		this.returnStation = returnStation;
	}
	
	@Override
	public String toString() {
		return "PaymentModel [id=" + id + ", id_bike=" + id_bike + ", deposit_card_number=" + deposit_card_number
				+ ", payment_card_number=" + payment_card_number + ", id_station_rent=" + id_station_rent
				+ ", id_station_return=" + id_station_return + ", rent_time=" + rent_time + ", return_time="
				+ return_time + ", deposit_price=" + deposit_price + ", rent_price=" + rent_price + ", status=" + status
				+ "]";
	}
	
}
