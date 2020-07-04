package hk.freshnetwork.model;

import java.sql.Timestamp;

public class Beanfull_sheet {
     private int Full_number;
     private String Full_content;
     private int App_number;
     private float Discount;
     private Timestamp FulStart_date;
     private Timestamp FulEnd_date;
	public int getFull_number() {
		return Full_number;
	}
	public void setFull_number(int full_number) {
		Full_number = full_number;
	}
	public String getFull_content() {
		return Full_content;
	}
	public void setFull_content(String full_content) {
		Full_content = full_content;
	}
	public int getApp_number() {
		return App_number;
	}
	public void setApp_number(int app_number) {
		App_number = app_number;
	}
	public float getDiscount() {
		return Discount;
	}
	public void setDiscount(float discount) {
		Discount = discount;
	}
	public Timestamp getFulStart_date() {
		return FulStart_date;
	}
	public void setFulStart_date(Timestamp fulStart_date) {
		FulStart_date = fulStart_date;
	}
	public Timestamp getFulEnd_date() {
		return FulEnd_date;
	}
	public void setFulEnd_date(Timestamp fulEnd_date) {
		FulEnd_date = fulEnd_date;
	}
}
