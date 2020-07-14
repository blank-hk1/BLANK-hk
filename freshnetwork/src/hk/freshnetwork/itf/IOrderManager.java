package hk.freshnetwork.itf;

import java.util.List;

import hk.freshnetwork.model.BeanShopping;
import hk.freshnetwork.model.Beanaddlist;
import hk.freshnetwork.model.Beancoupon;
import hk.freshnetwork.model.Beangoods_eva;
import hk.freshnetwork.model.Beanorder_details;
import hk.freshnetwork.model.Beanorder_form;
import hk.freshnetwork.model.Beantime_pro;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;
import hk.freshnetwork.util.DbException;

public interface IOrderManager {
	public List<BeanShopping> loadOrd(int usernum)throws BaseException;
	public void RegOrdDetails(int Trade_number,int number,float money) throws BaseException;
	public float sumPrice()throws BaseException;
	public Beantime_pro searchPro(int Trade_number) throws BaseException;
	public void deleteShop(int Trade_number);
	public Beanorder_form CreatOrder(int User_num,int add_number,int Cou_number,float ori_money,float set_money);
	public void deleteAllShop(int user_num);
	public void updateCom(int username) throws BusinessException;
	public List<Beanorder_details> loadRecords(int Usernum)throws BaseException;
	public Beancoupon ReadCouName(int Cou_number) throws BaseException;
	public Beanaddlist ReadAddName(int add_number) throws BaseException;
	public Beangoods_eva Addeva(int Usernum,int Tradenum,String content,int star) throws BaseException;
	public void CreatDetails(int usernum);
	public List<Beangoods_eva> Showeva(int Tradenum) throws BaseException;
	public void ReloadShop() throws BaseException;
	public void RegOrdDet(int userid,int Trade_number,int number,float money) throws BaseException;
}
