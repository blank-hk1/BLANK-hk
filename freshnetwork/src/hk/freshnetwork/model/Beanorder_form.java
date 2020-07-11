package hk.freshnetwork.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.ui.FrmChooseAddress;
import hk.freshnetwork.ui.FrmChooseCoupon;
import hk.freshnetwork.util.BaseException;

public class Beanorder_form {
	public static final String[] tableTitles={"订单编号","详细地址","优惠券内容","原始金额","结算金额","要求到达时间","订单状态"};
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
	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	public String getCell(int col) throws BaseException{
		if(col==0) return Integer.toString(this.ord_number);
		else if(col==1) return FreshNetUtil.orderManager.ReadAddName(this.add_number).getAddress();
		else if(col==2) return FreshNetUtil.orderManager.ReadCouName(this.Cou_number).getContent();
		else if(col==3) return Float.toString(this.ori_money);
		else if(col==4) return Float.toString(this.set_money);
		else if(col==5) return df.format(this.ari_time);
		else if(col==6) return this.ord_state;
		else return "";
	}
}
