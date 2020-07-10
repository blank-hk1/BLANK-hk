package hk.freshnetwork.action;

import hk.freshnetwork.control.ExampleUserManager;
import hk.freshnetwork.control.ExampleadminManager;
import hk.freshnetwork.control.ExamplecoupouManager;
import hk.freshnetwork.control.ExamplefreshManager;
import hk.freshnetwork.control.ExampleAddressMannager;
import hk.freshnetwork.control.ExampleComMananger;
import hk.freshnetwork.control.ExampleOrderManager;
import hk.freshnetwork.control.ExamplePurchaseManager;
import hk.freshnetwork.itf.IAddressManager;
import hk.freshnetwork.itf.IOrderManager;
import hk.freshnetwork.itf.IUserManager;
import hk.freshnetwork.itf.IadminManager;
import hk.freshnetwork.itf.IcomManager;
import hk.freshnetwork.itf.IfreshManager;
import hk.freshnetwork.itf.IpurchaseManager;
import hk.freshnetwork.itf.IcoupouManager;

public class FreshNetUtil {
	public static IUserManager userManager=  new ExampleUserManager();//��Ҫ����������Ƶ�ʵ����
	public static IadminManager adminManager=  new ExampleadminManager();//��Ҫ����������Ƶ�ʵ����
	public static IfreshManager freshManager=  new ExamplefreshManager();
	public static IcomManager comManager=  new ExampleComMananger();
	public static IpurchaseManager purchaseManager=  new ExamplePurchaseManager();
	public static IcoupouManager couponManager=  new ExamplecoupouManager();
	public static IAddressManager addressManager = new ExampleAddressMannager();
	public static IOrderManager orderManager = new ExampleOrderManager();
}
