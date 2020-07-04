package hk.freshnetwork.itf;

import hk.freshnetwork.model.Beanadminfo;
import hk.freshnetwork.util.BaseException;

public interface IadminManager {

	Beanadminfo login(String Emp_name, String pwd) throws BaseException;

}
