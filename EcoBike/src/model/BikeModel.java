package model;

import java.util.Date;

public class BikeModel {
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String WEIGHT = "weight";
	public static final String LICENSE_PLATE = "license_plate";
	public static final String MANUAFACTURING_DATE = "manuafacturing_date";
	public static final String COST = "cost";
	public static final String PRODUCER = "producer";
	public static final String BATTERY = "battery";
	public static final String LOAD_CYCLES = "load_cycles";
	public static final String TIME_REMAINING = "time_remaining";
	public static final String ID_STATION = "id_station";
	public static final String STATUS = "status";
	
	private Integer id;
	
	private String name;
	
	private String type;
	
	private Integer weight;
	
	private String license_plate;
	
	private Date manuafacturing_date;
	
	private Integer cost;
	
	private String producer;
	
	private Integer battery;
	
	private Integer load_cycles;
	
	private Integer time_remaining;
	
	private Integer id_station;

	private Integer status;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getLicense_plate() {
		return license_plate;
	}

	public void setLicense_plate(String license_plate) {
		this.license_plate = license_plate;
	}

	public Date getManuafacturing_date() {
		return manuafacturing_date;
	}

	public void setManuafacturing_date(Date manuafacturing_date) {
		this.manuafacturing_date = manuafacturing_date;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Integer getBattery() {
		return battery;
	}

	public void setBattery(Integer battery) {
		this.battery = battery;
	}

	public Integer getLoad_cycles() {
		return load_cycles;
	}

	public void setLoad_cycles(Integer load_cycles) {
		this.load_cycles = load_cycles;
	}

	public Integer getTime_remaining() {
		return time_remaining;
	}

	public void setTime_remaining(Integer time_remaining) {
		this.time_remaining = time_remaining;
	}

	public Integer getId_station() {
		return id_station;
	}

	public void setId_station(Integer id_station) {
		this.id_station = id_station;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BikeModel [id=" + id + ", name=" + name + ", type=" + type + ", weight=" + weight + ", license_plate="
				+ license_plate + ", manuafacturing_date=" + manuafacturing_date + ", cost=" + cost + ", producer="
				+ producer + ", battery=" + battery + ", load_cycles=" + load_cycles + ", time_remaining="
				+ time_remaining + ", id_station=" + id_station +
						", status=" + status + 
						"]";
	}

	
	
}
