package dao.impl;

import java.util.ArrayList;
import java.util.List;

import common.SearchQueryInfomation;
import dao.IBikeDAO;
import mapper.BikeMapper;
import model.BikeModel;

public class BikeDAO extends AbstractDAO<BikeModel> implements IBikeDAO {

	private String sql;

	@Override
	public List<BikeModel> findAll() {
		sql = "SELECT * FROM bike";
		return query(sql, new BikeMapper());
	}

	@Override
	public int insert(BikeModel newBike) {
		sql = "INSERT INTO bike(name, type, weight, license_plate, manuafacturing_date, cost, producer, battery, load_cycles, time_remaining, id_station, status) VALUES (?,?,?,?,?,?,?,?,?,?,?, ?)";
		return insert(sql, newBike.getName(), newBike.getType(), newBike.getWeight(), newBike.getLicense_plate(),
				newBike.getManuafacturing_date(), newBike.getCost(), newBike.getProducer(), newBike.getBattery(),
				newBike.getLoad_cycles(), newBike.getTime_remaining(), newBike.getId_station(), newBike.getStatus());
	}

	@Override
	public void update(BikeModel updateBike) {
		sql = " UPDATE bike SET name = ?, type =?, weight =?, license_plate = ?, manuafacturing_date = ?, cost =?, producer = ?, battery = ?, load_cycles = ?, time_remaining = ?, id_station = ?, status = ? WHERE id = ?";
        update(sql, updateBike.getName(), updateBike.getType(), updateBike.getWeight(), updateBike.getLicense_plate(),
				updateBike.getManuafacturing_date(), updateBike.getCost(), updateBike.getProducer(), updateBike.getBattery(),
				updateBike.getLoad_cycles(), updateBike.getTime_remaining(), updateBike.getId_station(), updateBike.getStatus(), updateBike.getId());

	}

	@Override
	public List<BikeModel> findBikeByStation(Integer id) {
		sql = "SELECT * FROM bike WHERE id_station = ? and status = 0";
		return query(sql, new BikeMapper(), id);
	}

	@Override
	public List<BikeModel> findBikeByStationAndSearchInfomation(Integer id, SearchQueryInfomation searchInfomation) {
		if(searchInfomation == null) return findBikeByStation(id);
		if(!searchInfomation.haveSearchQuery()) return findBikeByStation(id);
		
		StringBuilder sql = new StringBuilder();
		List<Object> paramaters = new ArrayList<>();
		sql.append("SELECT * FROM bike WHERE id_station = ? and status = 0");
		paramaters.add(id);
		
		if(searchInfomation.haveCheckTypeBike()) {
			sql.append(" and (type = ?)");
			paramaters.add(searchInfomation.getTypeBike());
		}
		if(searchInfomation.haveCheckPrice()) {
			String sub_query = setUpPriceRangeQuerySearch(
					searchInfomation.getMaxPrice(), 
					searchInfomation.getMinPrice(), 
					paramaters
				);
			sql.append(" and" + sub_query);
		}
		return query(sql.toString(), new BikeMapper(), paramaters.toArray());
	}
	
	private String setUpPriceRangeQuerySearch(Integer maxPrice, Integer minPrice, List<Object> paramaters) {
		// Khong dien maxprice
		if(maxPrice < minPrice) {
			paramaters.add(minPrice);
			return " (cost > ?)";
		}

		paramaters.add(minPrice);
		paramaters.add(maxPrice);
		return " (cost between ? and ?)";
	}
}
