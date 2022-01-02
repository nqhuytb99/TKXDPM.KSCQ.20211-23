package controller;

import java.util.List;

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
}

