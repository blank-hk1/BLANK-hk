package hk.freshnetwork.itf;

import java.util.List;

import hk.freshnetwork.model.Beancoupon;
import hk.freshnetwork.model.Beanfull_sheet;
import hk.freshnetwork.model.Beantime_pro;
import hk.freshnetwork.util.BaseException;

public interface IcoupouManager {
	public List<Beancoupon> loadCou()throws BaseException;
	public List<Beancoupon> SearchCou(float App_money)throws BaseException;
	public Beancoupon RegCou(String content,float App_money,float Ded_money,String Start,int month) throws BaseException;
	public void deleteCou(int Cou_number);
	public void modifyCou(int Cou_number,String content,float App_money,float Ded_money,String Start,int month) throws BaseException;
	public void deletePro(int Pro_number);
	public List<Beantime_pro> loadTimeSearch(int Pronumber)throws BaseException;
	public List<Beantime_pro> loadTime()throws BaseException;
	public Beantime_pro RegPro(int Trade_number,float Pro_price,int Prom_number,String ProStart_date,String ProEnd_date) throws BaseException;
	public void modifyPro(int Pro_number,int Trade_number,float Pro_price,int Prom_number,String ProStart_date,String ProEnd_date) throws BaseException;
	public List<Beanfull_sheet> loadFull()throws BaseException;
	public List<Beanfull_sheet> loadFullSearch(int Full_number)throws BaseException;
	public Beanfull_sheet RegFul(String Full_content,int App_number,Float Discount,String FulStart_date,String FulEnd_date) throws BaseException;
	public void modifyFul(int Full_number,String Full_content,int App_number,Float Discount,String FulStart_date,String FulEnd_date) throws BaseException;
	public void deleteFul(int Full_number);
	
}
