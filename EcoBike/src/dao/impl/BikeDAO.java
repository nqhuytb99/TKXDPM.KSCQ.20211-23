package dao.impl;

import java.util.List;

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

}

