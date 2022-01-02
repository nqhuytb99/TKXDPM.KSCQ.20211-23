package dao;

import java.util.List;

import model.PaymentModel;

public interface IPaymentDAO extends IGenericDAO<PaymentModel>{
	List<PaymentModel> findAll();

    int insert(PaymentModel newPayment);
    
    void update(PaymentModel updatePayment);
}
