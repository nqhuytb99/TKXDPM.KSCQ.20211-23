package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.PaymentModel;

public class PaymentMapper implements RowMapper<PaymentModel>{

	@Override
	public PaymentModel mapRow(ResultSet rs) {
		PaymentModel paymentModel = new PaymentModel();
		try {
			paymentModel.setId(rs.getInt(PaymentModel.ID));
			paymentModel.setId_station_rent(rs.getInt(PaymentModel.ID_STATION_RENT));
			paymentModel.setId_bike(rs.getInt(PaymentModel.ID_BIKE));
			paymentModel.setDeposit_card_number(rs.getString(PaymentModel.DEPOSIT_CARD_NUMBER));
			paymentModel.setRent_time(rs.getTimestamp(PaymentModel.RENT_TIME));
			paymentModel.setDeposit_price(rs.getInt(PaymentModel.DEPOSIT_PRICE));
			paymentModel.setStatus(rs.getInt(PaymentModel.STATUS));
			return paymentModel;
		} catch (SQLException e) {
			return null;
		}
	}

}
