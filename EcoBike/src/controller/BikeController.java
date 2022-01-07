package controller;

import java.util.List;

import common.SearchQueryInfomation;
import dao.IBikeDAO;
import dao.impl.BikeDAO;
import model.BikeModel;

public class BikeController {
	private IBikeDAO bikeDAO;

	public BikeController() {
		this.bikeDAO = new BikeDAO();
	}

	public List<BikeModel> getAllBike() {
		return this.bikeDAO.findAll();
	}
	
	public void updateBike(BikeModel bike) {
		bikeDAO.update(bike);
	}
	
	public List<BikeModel> getBikeByStation(Integer id) {
		return bikeDAO.findBikeByStation(id);
	}
	
	public List<BikeModel> findBikeByStationAndSearchInfomation(Integer id, SearchQueryInfomation searchInfomation){
		return bikeDAO.findBikeByStationAndSearchInfomation(id, searchInfomation);
	}
}
