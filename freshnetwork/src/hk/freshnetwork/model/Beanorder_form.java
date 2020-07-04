package hk.freshnetwork.model;

import java.sql.Timestamp;

public class Beanorder_form {
    private int ord_number;
    private int User_num;
    private int add_number;
    private int Cou_number;
    private float ori_money;
    private float set_money;
    private Timestamp ari_time;
    private String ord_state;
    public int getOrd_number() {
		return ord_number;
	}
	public void setOrd_number(int ord_number) {
		this.ord_number = ord_number;
	}
	public int getUser_num() {
		return User_num;
	}
	public void setUser_num(int user_num) {
		User_num = user_num;
	}
	public int getAdd_number() {
		return add_number;
	}
	public void setAdd_number(int add_number) {
		this.add_number = add_number;
	}
	public int getCou_number() {
		return Cou_number;
	}
	public void setCou_number(int cou_number) {
		Cou_number = cou_number;
	}
	public float getOri_money() {
		return ori_money;
	}
	public void setOri_money(float ori_money) {
		this.ori_money = ori_money;
	}
	public float getSet_money() {
		return set_money;
	}
	public void setSet_money(float set_money) {
		this.set_money = set_money;
	}
	public Timestamp getAri_time() {
		return ari_time;
	}
	public void setAri_time(Timestamp ari_time) {
		this.ari_time = ari_time;
	}
	public String getOrd_state() {
		return ord_state;
	}
	public void setOrd_state(String ord_state) {
		this.ord_state = ord_state;
	}
	
}
