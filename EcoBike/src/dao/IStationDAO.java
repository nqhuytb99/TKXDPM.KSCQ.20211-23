package dao;

import java.util.List;

import common.SearchQueryInfomation;
import model.StationModel;

public interface IStationDAO extends IGenericDAO<StationModel>{
	List<StationModel> findAll();

	StationModel findById(int id);
	
    int insert(StationModel newStation);
    
    void update(StationModel updateStation);
    
    List<StationModel> findByQuery(SearchQueryInfomation seacrhQuery);
    
    List<StationModel> findAllAvailable();
}
