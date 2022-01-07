package controller;

import java.util.List;

import common.SearchQueryInfomation;
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
	
	public List<StationModel> findAll(){
		return stationDAO.findAll();
	}
	
	public List<StationModel> findByQuery(SearchQueryInfomation seacrhQuery) {
		return stationDAO.findByQuery(seacrhQuery);
	}
	
	public List<StationModel> findAllAvailable(){
		return stationDAO.findAllAvailable();
	}
}
