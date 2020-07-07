package hk.freshnetwork.itf;

import java.util.List;

import hk.freshnetwork.model.Beanpurchase;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;

public interface IpurchaseManager {

	List<Beanpurchase> loadPur() throws BaseException;

	List<Beanpurchase> searchCom(int p);

	void modifyPurchase(int chase_number, int t, int e, int p, String stat) throws BusinessException;

	void deletePur(int chase_number);

	Beanpurchase regpur(int chase_number, int Trade_number, int Emp_number, int purchase_amount, String chase_stat)
			throws BaseException;

	List<Beanpurchase> searchPur(String string);

}
