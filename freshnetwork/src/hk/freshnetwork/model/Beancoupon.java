package hk.freshnetwork.model;

import java.sql.Timestamp;

public class Beancoupon {
   private int Cou_number;
   private int ord_number;
   private String content;
   private float App_money;
   private float Ded_money;
   private Timestamp Start_date;
   private Timestamp End_date;
public int getCou_number() {
	return Cou_number;
}
public void setCou_number(int cou_number) {
	Cou_number = cou_number;
}
public int getOrd_number() {
	return ord_number;
}
public void setOrd_number(int ord_number) {
	this.ord_number = ord_number;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public float getApp_money() {
	return App_money;
}
public void setApp_money(float app_money) {
	App_money = app_money;
}
public float getDed_money() {
	return Ded_money;
}
public void setDed_money(float ded_money) {
	Ded_money = ded_money;
}
public Timestamp getStart_date() {
	return Start_date;
}
public void setStart_date(Timestamp start_date) {
	Start_date = start_date;
}
public Timestamp getEnd_date() {
	return End_date;
}
public void setEnd_date(Timestamp end_date) {
	End_date = end_date;
}
}
