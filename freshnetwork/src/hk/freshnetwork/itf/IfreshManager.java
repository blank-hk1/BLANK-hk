package hk.freshnetwork.itf;

import java.util.List;

import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.model.Beanmenu_info;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;

public interface IfreshManager {
	public List<Beanfresh_information> loadFresh()throws BaseException;
	public void modifyFresh(String freshname, String state) throws BaseException;
	public List<Beanfresh_information> searchFresh(String Category_name)throws BaseException;
	public String getFreshname(int Category_number) throws BaseException;
	public void deleteFresh(String category_name) throws BusinessException;
	public List<Beanmenu_info> loadmenu()throws BaseException;
	public List<Beanmenu_info> searchMenu(String menu_name)throws BaseException;
	public void deleteMenu(String menu_name) throws BusinessException;
	public void modifyMenu(String menuname, String state, String step);
}
