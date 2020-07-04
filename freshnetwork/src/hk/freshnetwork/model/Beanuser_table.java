package hk.freshnetwork.model;

import java.sql.Timestamp;

public class Beanuser_table {
    public static Object currentLoginUser=null;
	private int User_num;
    private String User_name;
    private boolean User_gender;
    private String User_pwd;
    private String User_phone;
    private String User_mail;
    private String city;
    private Timestamp Regtime;
    private boolean ISmember;
    private Timestamp closedate;
	public int getUser_num() {
		return User_num;
	}
	public void setUser_num(int user_num) {
		User_num = user_num;
	}
	public String getUser_name() {
		return User_name;
	}
	public void setUser_name(String user_name) {
		User_name = user_name;
	}
	public boolean isUser_gender() {
		return User_gender;
	}
	public void setUser_gender(boolean user_gender) {
		User_gender = user_gender;
	}
	public String getUser_pwd() {
		return User_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		User_pwd = user_pwd;
	}
	public String getUser_phone() {
		return User_phone;
	}
	public void setUser_phone(String user_phone) {
		User_phone = user_phone;
	}
	public String getUser_mail() {
		return User_mail;
	}
	public void setUser_mail(String user_mail) {
		User_mail = user_mail;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Timestamp getRegtime() {
		return Regtime;
	}
	public void setRegtime(Timestamp regtime) {
		Regtime = regtime;
	}
	public boolean isISmember() {
		return ISmember;
	}
	public void setISmember(boolean iSmember) {
		ISmember = iSmember;
	}
	public Timestamp getClosedate() {
		return closedate;
	}
	public void setClosedate(Timestamp closedate) {
		this.closedate = closedate;
	}
}
