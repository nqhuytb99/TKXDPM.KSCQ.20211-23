package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.StationModel;

public class StationMapper implements RowMapper<StationModel> {
	@Override
    public StationModel mapRow(ResultSet rs) {
		StationModel stationModel = new StationModel();
        try {
        	stationModel.setId(rs.getInt(StationModel.ID));
        	stationModel.setName(rs.getString(StationModel.NAME));
        	stationModel.setAddress(rs.getString(StationModel.ADDRESS));
        	stationModel.setNum_of_bikes(rs.getInt(StationModel.NUM_OF_BIKES));
        	stationModel.setNum_of_ebikes(rs.getInt(StationModel.NUM_OF_EBIKES));
        	stationModel.setNum_of_twinbikes(rs.getInt(StationModel.NUM_OF_TWINBIKES));
        	stationModel.setNum_of_empty_docks(rs.getInt(StationModel.NUM_OF_EMPTY_DOCKS));
        	return stationModel;
        } catch (SQLException ex) {
            return null;
        }
    }
}
