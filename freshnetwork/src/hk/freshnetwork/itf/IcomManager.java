package hk.freshnetwork.itf;

import java.util.List;

import hk.freshnetwork.model.Beancommodity_information;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.util.BaseException;

public interface IcomManager {
	public List<Beancommodity_information> loadcom()throws BaseException;
	public List<Beancommodity_information> searchCom(String Trade_name)throws BaseException;
	public Beancommodity_information regCom(String Trade_name,String category_number,String price,String member_price,String number,String Specifications,String details) throws BaseException;
	public void modifyCom(String Trade_name,String category_number,String price,String member_price,String number,String Specifications,String details) throws BaseException;
	public void deleteCom(String Trade_name);
	public List<Beancommodity_information> loadcompur() throws BaseException;
}
