package hk.freshnetwork.model;

public class Beanpurchase {
  public static final String[] tableTitles={"采购单编号","员工编号","采购数量","状态"};
  private int chase_number;
  private int Trade_number;
  private int chase_num;
  public int getChase_num() {
	return chase_num;
}
public void setChase_num(int chase_num) {
	this.chase_num = chase_num;
}
public int getTrade_number() {
	return Trade_number;
}
public void setTrade_number(int trade_number) {
	Trade_number = trade_number;
}
private int Emp_number;
  private int purchase_amount;
  private String chase_stat;
public int getChase_number() {
	return chase_number;
}
public void setChase_number(int chase_number) {
	this.chase_number = chase_number;
}
public int getEmp_number() {
	return Emp_number;
}
public void setEmp_number(int emp_number) {
	Emp_number = emp_number;
}
public int getPurchase_amount() {
	return purchase_amount;
}
public void setPurchase_amount(int purchase_amount) {
	this.purchase_amount = purchase_amount;
}
public String getChase_stat() {
	return chase_stat;
}
public void setChase_stat(String chase_stat) {
	this.chase_stat = chase_stat;
}
public String getCell(int col){
	if(col==0) return Integer.toString(this.getChase_number());
	else if(col==1) return Integer.toString(this.getEmp_number());
	else if(col==2) return Integer.toString(this.getPurchase_amount());
	else if(col==3) return this.getChase_stat();
	else return "";
}
}
