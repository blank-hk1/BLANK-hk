package hk.freshnetwork.itf;

import java.util.List;

import hk.freshnetwork.model.BeanShopping;
import hk.freshnetwork.model.Beanorder_details;
import hk.freshnetwork.model.Beanorder_form;
import hk.freshnetwork.model.Beantime_pro;
import hk.freshnetwork.util.BaseException;

public interface IOrderManager {
	public List<BeanShopping> loadOrd()throws BaseException;
	public void RegOrdDetails(int Trade_number,int number,float money) throws BaseException;
	public float sumPrice()throws BaseException;
	public Beantime_pro searchPro(int Trade_number) throws BaseException;
	public void deleteShop(int Trade_number);
}
