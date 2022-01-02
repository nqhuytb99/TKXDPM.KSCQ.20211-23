package dao.impl;

import java.sql.Timestamp;
import java.util.List;

import dao.IPaymentDAO;
import model.PaymentModel;


public class PaymentDAO extends AbstractDAO<PaymentModel> implements IPaymentDAO {

	private String sql;

	@Override
	public List<PaymentModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(PaymentModel newPayment) {
		sql = "INSERT INTO payment(id_bike, deposit_card_number, id_station_rent, rent_time, deposit_price, status) VALUES (?,?,?,?,?,?)";
		return insert(sql, newPayment.getId_bike(), newPayment.getDeposit_card_number(), newPayment.getId_station_rent(), String.valueOf(new Timestamp(System.currentTimeMillis())),
				newPayment.getDeposit_price(), newPayment.getStatus());
		
	}

	@Override
	public void update(PaymentModel updatePayment) {
		// TODO Auto-generated method stub

	}

}
