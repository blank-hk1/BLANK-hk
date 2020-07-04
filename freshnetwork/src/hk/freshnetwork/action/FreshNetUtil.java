package hk.freshnetwork.action;

import hk.freshnetwork.control.ExampleUserManager;
import hk.freshnetwork.control.ExampleadminManager;
import hk.freshnetwork.itf.IUserManager;
import hk.freshnetwork.itf.IadminManager;

public class FreshNetUtil {
	public static IUserManager userManager=  new ExampleUserManager();//��Ҫ����������Ƶ�ʵ����
	public static IadminManager adminManager=  new ExampleadminManager();//��Ҫ����������Ƶ�ʵ����
}
