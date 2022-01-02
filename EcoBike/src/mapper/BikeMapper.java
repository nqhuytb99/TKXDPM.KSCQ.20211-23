package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.BikeModel;

public class BikeMapper implements RowMapper<BikeModel> {
	@Override
    public BikeModel mapRow(ResultSet rs) {
		BikeModel bikeModel = new BikeModel();
        try {
        	bikeModel.setId(rs.getInt(BikeModel.ID));
        	bikeModel.setName(rs.getString(BikeModel.NAME));
        	bikeModel.setType(rs.getString(BikeModel.TYPE));
        	bikeModel.setWeight(rs.getInt(BikeModel.WEIGHT));
            bikeModel.setLicense_plate(rs.getString(BikeModel.LICENSE_PLATE));
            bikeModel.setManuafacturing_date(rs.getDate(BikeModel.MANUAFACTURING_DATE));
            bikeModel.setCost(rs.getInt(BikeModel.COST));
            bikeModel.setProducer(rs.getString(BikeModel.PRODUCER));
            bikeModel.setBattery(rs.getInt(BikeModel.BATTERY));
            bikeModel.setLoad_cycles(rs.getInt(BikeModel.LOAD_CYCLES));
            bikeModel.setTime_remaining(rs.getInt(BikeModel.TIME_REMAINING));
            bikeModel.setId_station(rs.getInt(BikeModel.ID_STATION));
            bikeModel.setStatus(rs.getInt(BikeModel.STATUS));
            return bikeModel;
        } catch (SQLException ex) {
            return null;
        }
    }
}
