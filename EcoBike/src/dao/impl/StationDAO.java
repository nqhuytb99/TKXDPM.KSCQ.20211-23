package dao.impl;

import java.util.List;

import dao.IStationDAO;
import mapper.StationMapper;
import model.StationModel;

public class StationDAO extends AbstractDAO<StationModel> implements IStationDAO {

	private String sql;
	@Override
	public List<StationModel> findAll() {
		sql = "SELECT * FROM station";
		return query(sql, new StationMapper());
	}

	@Override
	public int insert(StationModel newStation) {
		sql = " INSERT INTO station ( name , address, num_of_bikes, num_of_ebikes, num_of_twinbikes, num_of_empty_docks ) VALUES (?, ?,?,?,?,?)";
        return insert(sql, newStation.getName(), newStation.getAddress(), newStation.getNum_of_bikes(), newStation.getNum_of_ebikes(), newStation.getNum_of_twinbikes(), newStation.getNum_of_empty_docks());

	}

	@Override
	public void update(StationModel updateStation) {
		sql = " UPDATE station SET name = ?, address =?, num_of_bikes =?, num_of_ebikes = ?, num_of_twinbikes = ?, num_of_empty_docks =? WHERE id = ?";
        update(sql, updateStation.getName(), updateStation.getAddress(), updateStation.getNum_of_bikes(), updateStation.getNum_of_ebikes(), updateStation.getNum_of_twinbikes(), updateStation.getNum_of_empty_docks(), updateStation.getId());

	}

	@Override
	public StationModel findById(int id) {
		sql = "select * from station where id = ?";
        List<StationModel> stations = query(sql, new StationMapper(), id);
        return stations.isEmpty() ? null : stations.get(0);
	}

}

