package hk.freshnetwork.itf;

import java.util.List;

import hk.freshnetwork.model.Beanaddlist;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;

public interface IAddressManager {
	public List<Beanaddlist> loadAddress()throws BaseException;
	public Beanaddlist regAdd(String qu,String address,String contacts,String con_phone) throws BaseException;
	public void modifyFresh(int add_number,String qu,String address,String contacts,String con_phone) throws BaseException;
	public void deleteAdd(int add_number) throws BusinessException;
}
