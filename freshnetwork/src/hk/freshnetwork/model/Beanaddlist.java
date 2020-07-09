package hk.freshnetwork.model;

import hk.freshnetwork.util.BaseException;

public class Beanaddlist {
	 public static final String[] tableTitles={"地址编号","用户编号","省","市","区","地址","联系人","电话"};
     private int add_number;
     private int User_num;
     private String sheng;
     private String shi;
     private String qu;
     private String address;
     private String contacts;
     private String con_phone;
	public int getAdd_number() {
		return add_number;
	}
	public void setAdd_number(int add_number) {
		this.add_number = add_number;
	}
	public int getUser_num() {
		return User_num;
	}
	public void setUser_num(int user_num) {
		User_num = user_num;
	}
	public String getSheng() {
		return sheng;
	}
	public void setSheng(String sheng) {
		this.sheng = sheng;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getQu() {
		return qu;
	}
	public void setQu(String qu) {
		this.qu = qu;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getCon_phone() {
		return con_phone;
	}
	public void setCon_phone(String con_phone) {
		this.con_phone = con_phone;
	}
	public String getCell(int col) throws BaseException{
		if(col==0) return Integer.toString(this.add_number);
		else if(col==1) return Integer.toString(this.User_num);
		else if(col==2) return this.getSheng();
		else if(col==3) return this.getShi();
		else if(col==4) return this.getQu();
		else if(col==5) return this.getAddress();
		else if(col==6) return this.getContacts();
		else if(col==7) return this.getCon_phone();
		else return "";
	 }
}
