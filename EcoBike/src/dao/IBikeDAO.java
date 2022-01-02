package dao;


import java.util.List;

import model.BikeModel;

public interface IBikeDAO extends IGenericDAO<BikeModel>{
	List<BikeModel> findAll();

    int insert(BikeModel newBike);
    
    void update(BikeModel updateBike);
}