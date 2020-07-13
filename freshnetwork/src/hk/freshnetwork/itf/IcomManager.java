package hk.freshnetwork.itf;

import java.util.List;

import hk.freshnetwork.model.Beancommodity_information;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;

public interface IcomManager {
	public List<Beancommodity_information> loadcom()throws BaseException;
	public List<Beancommodity_information> searchCom(String Trade_name)throws BaseException;
	public Beancommodity_information regCom(String Trade_name,String category_number,String price,String member_price,String number,String Specifications,String details) throws BaseException;
	public void modifyCom(String Trade_name,String category_number,String price,String member_price,String number,String Specifications,String details) throws BaseException;
	public void deleteCom(String Trade_name) throws BusinessException;
	public List<Beancommodity_information> loadcompur() throws BaseException;
	public List<Beancommodity_information> searchComFresh(int Category_number) throws BaseException;
	public float searchCuxiao(int Trade_number,float price);
	public List<Beancommodity_information> loadRec(int Trade_number);
	public float SearchPrice(int Trade_number);
	public List<Beancommodity_information> searchComNumber(int Trade_number) throws BaseException;
}
