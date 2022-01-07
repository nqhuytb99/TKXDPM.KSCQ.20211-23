package dao.impl;

import java.util.ArrayList;
import java.util.List;

import common.SearchQueryInfomation;
import dao.IStationDAO;
import mapper.StationMapper;
import model.StationModel;

public class StationDAO extends AbstractDAO<StationModel> implements IStationDAO {

	private String sql;
	@Override
	public List<StationModel> findAll() {
		String sql = "SELECT * FROM station";
		return query(sql, new StationMapper());
	}

	@Override
	public int insert(StationModel newStation) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO station");
		sql.append("(name, address, num_of_bikes, num_of_ebikes, num_of_twinbikes, num_of_empty_docks)");
		sql.append(" VALUES (?,?,?,?,?,?)");
		return insert(sql.toString(), 
				newStation.getName(), newStation.getAddress(), newStation.getNum_of_bikes(),
				newStation.getNum_of_ebikes(), newStation.getNum_of_twinbikes(), newStation.getNum_of_empty_docks());
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

	@Override
	public List<StationModel> findByQuery(SearchQueryInfomation seacrhQuery) {
		if(seacrhQuery == null) return findAllAvailable();
		if(!seacrhQuery.haveSearchQuery()) return findAllAvailable();
		
		String and = " ";
		StringBuilder sql = new StringBuilder();
		List<Object> paramaters = new ArrayList<>();
		sql.append("select * from station where id in (");
		sql.append("select distinct s.id from station s inner join bike b on s.id = b.id_station where");
		if(seacrhQuery.haveCheckSearchQuery()) {
			sql.append(" (s.name like ? or s.address like ?)");
			and = " and";
			paramaters.add("%" + seacrhQuery.getNameAddressSearch() + "%");
			paramaters.add("%" + seacrhQuery.getNameAddressSearch() + "%");
		}

		if(seacrhQuery.haveCheckTypeBike()) {
			sql.append(and + " (b.type = ?)");
			and = " and";
			paramaters.add(seacrhQuery.getTypeBike());
		}
		if(seacrhQuery.haveCheckPrice()) {
			String sub_query = setUpPriceRangeQuerySearch(
					seacrhQuery.getMaxPrice(), 
					seacrhQuery.getMinPrice(), 
					paramaters
				);
			sql.append(and + sub_query);
			and = " and";
		}
		sql.append(and + " (b.status = 0)");
		sql.append(");");
		return query(sql.toString(), new StationMapper(), paramaters.toArray());
	}

	@Override
	public List<StationModel> findAllAvailable() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from station where id in (");
		sql.append("select distinct s.id from station s inner join bike b on s.id = b.id_station");
		sql.append(" where b.status = 0");
		sql.append(");");
		
		return query(sql.toString(), new StationMapper());
	}

	private String setUpPriceRangeQuerySearch(Integer maxPrice, Integer minPrice, List<Object> paramaters) {
		// Trường hợp user không điền maxPrice
		if(maxPrice < minPrice) {
			paramaters.add(minPrice);
			return " (b.cost > ?)";
		}

		paramaters.add(minPrice);
		paramaters.add(maxPrice);
		return " (b.cost between ? and ?)";
	}
}
