package hk.freshnetwork.action;

import hk.freshnetwork.control.ExampleUserManager;
import hk.freshnetwork.control.ExampleadminManager;
import hk.freshnetwork.control.ExamplefreshManager;
import hk.freshnetwork.control.ExampleComMananger;
import hk.freshnetwork.itf.IUserManager;
import hk.freshnetwork.itf.IadminManager;
import hk.freshnetwork.itf.IcomManager;
import hk.freshnetwork.itf.IfreshManager;

public class FreshNetUtil {
	public static IUserManager userManager=  new ExampleUserManager();//��Ҫ����������Ƶ�ʵ����
	public static IadminManager adminManager=  new ExampleadminManager();//��Ҫ����������Ƶ�ʵ����
	public static IfreshManager freshManager=  new ExamplefreshManager();
	public static IcomManager comManager=  new ExampleComMananger();
}
