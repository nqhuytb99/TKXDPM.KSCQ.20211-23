package dao;

import java.util.List;

import common.SearchQueryInfomation;
import model.BikeModel;

public interface IBikeDAO extends IGenericDAO<BikeModel>{
	List<BikeModel> findAll();

    int insert(BikeModel newBike);
    
    void update(BikeModel updateBike);
    
    List<BikeModel> findBikeByStation(Integer id);
    
    List<BikeModel> findBikeByStationAndSearchInfomation(Integer id, SearchQueryInfomation searchInfomation);
}
