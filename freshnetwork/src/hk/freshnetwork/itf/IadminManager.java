package hk.freshnetwork.itf;

import hk.freshnetwork.model.Beanadminfo;
import hk.freshnetwork.util.BaseException;

public interface IadminManager {

	Beanadminfo login(String Emp_name, String pwd) throws BaseException;
	public Beanadminfo reg(String adminname, String pwd,String pwd2) throws BaseException;
	void changePwd(Beanadminfo currentLoginadmin, String string, String string2, String string3) throws BaseException;

}
