package hk.freshnetwork.model;

public class Beancommodity_information {
   private int Trade_number;
   private int Pro_number;
   private int chase_number;
   private int Category_number;
   private String Trade_name;
   private float Price;
   private float Member_price;
   private int number;
   private String Specifications;
   private String details;
public int getTrade_number() {
	return Trade_number;
}
public void setTrade_number(int trade_number) {
	Trade_number = trade_number;
}
public int getPro_number() {
	return Pro_number;
}
public void setPro_number(int pro_number) {
	Pro_number = pro_number;
}
public int getChase_number() {
	return chase_number;
}
public void setChase_number(int chase_number) {
	this.chase_number = chase_number;
}
public int getCategory_number() {
	return Category_number;
}
public void setCategory_number(int category_number) {
	Category_number = category_number;
}
public String getTrade_name() {
	return Trade_name;
}
public void setTrade_name(String trade_name) {
	Trade_name = trade_name;
}
public float getPrice() {
	return Price;
}
public void setPrice(float price) {
	Price = price;
}
public float getMember_price() {
	return Member_price;
}
public void setMember_price(float member_price) {
	Member_price = member_price;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}
public String getSpecifications() {
	return Specifications;
}
public void setSpecifications(String specifications) {
	Specifications = specifications;
}
public String getDetails() {
	return details;
}
public void setDetails(String details) {
	this.details = details;
}
}