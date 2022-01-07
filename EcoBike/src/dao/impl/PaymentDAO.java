package dao.impl;

import java.sql.Timestamp;
import java.util.List;

import dao.IPaymentDAO;
import mapper.PaymentMapper;
import model.PaymentModel;
import utils.Utils;

public class PaymentDAO extends AbstractDAO<PaymentModel> implements IPaymentDAO {

	private String sql;

	@Override
	public List<PaymentModel> findAll() {
		// TODO Auto-generated method stub
		sql = "SELECT * FROM payment where status = 1";
		return query(sql, new PaymentMapper());
	}

	@Override
	public int insert(PaymentModel newPayment) {
		sql = "INSERT INTO payment(id_bike, deposit_card_number, id_station_rent, rent_time, deposit_price, status) VALUES (?,?,?,?,?,?)";
		return insert(sql, newPayment.getId_bike(), newPayment.getDeposit_card_number(), newPayment.getId_station_rent(), String.valueOf(new Timestamp(System.currentTimeMillis())),
				newPayment.getDeposit_price(), newPayment.getStatus());
		
	}

	@Override
	public void update(PaymentModel updatePayment) {
		sql = " UPDATE payment SET return_time = ?, status =?, rent_price =?, id_station_return = ? WHERE id = ?";
		update(sql, updatePayment.getRent_time(), updatePayment.getStatus(), updatePayment.getRent_price(),
				updatePayment.getId_station_return(), updatePayment.getId());

	}

}
