package controller;

import dao.IStationDAO;
import dao.impl.StationDAO;
import model.StationModel;

public class StationController {
	private IStationDAO stationDAO;
	
	public StationController() {
		stationDAO = new StationDAO();
	}
	public int saveStation(StationModel station) {
		return stationDAO.insert(station);
	}
	public void updateStation(StationModel updateStation) {
		stationDAO.update(updateStation);
	}
	 
	public StationModel findById(int id) {
		return stationDAO.findById(id);
	}
}

