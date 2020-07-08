package hk.freshnetwork.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Beancoupon {
	public static final String[] tableTitles={"优惠券编号","内容","适用金额","减免金额","开始日期","结束日期"};
   private int Cou_number;
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
SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
public String getCell(int col){
	if(col==0) return Integer.toString(this.Cou_number);
	else if(col==1) return this.getContent();
	else if(col==2) return Float.toString(this.getApp_money());
	else if(col==3) return Float.toString(this.getDed_money());
	else if(col==4) return df.format(this.getStart_date());
	else if(col==5) return df.format(this.getEnd_date());
	else return "";
}
}
