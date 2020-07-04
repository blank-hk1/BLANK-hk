package hk.freshnetwork.itf;

import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;

public interface IUserManager {
	public Beanuser_table reg(String username, String gender,String pwd,String pwd2,String phone,String mail,String city) throws BaseException;
	public Beanuser_table login(String username, String pwd) throws BaseException;
	public void changePwd(Beanuser_table user, String oldPwd, String newPwd,
			String newPwd2) throws BaseException;
}
