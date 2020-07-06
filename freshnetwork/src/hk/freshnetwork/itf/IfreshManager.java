package hk.freshnetwork.itf;

import java.util.List;

import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.util.BaseException;

public interface IfreshManager {
	public List<Beanfresh_information> loadFresh()throws BaseException;
	public void modifyFresh(String freshname, String state) throws BaseException;
	public List<Beanfresh_information> searchFresh(String Category_name)throws BaseException;
	public String getFreshname(int Category_number) throws BaseException;
}
