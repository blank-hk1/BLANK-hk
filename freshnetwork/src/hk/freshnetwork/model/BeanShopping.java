package hk.freshnetwork.model;

import hk.freshnetwork.util.BaseException;

public class BeanShopping {
	 public static final String[] tableTitles= {"商品编号","满折编号","数量","折扣","价格"};
     private int Com_Trade_number;
     private int Ful_Full_number;
     private int pur_number;
     private float Discount;
     private float set_money;
     private int user_number;
	public int getUser_number() {
		return user_number;
	}
	public void setUser_number(int user_number) {
		this.user_number = user_number;
	}
	public int getCom_Trade_number() {
		return Com_Trade_number;
	}
	public void setCom_Trade_number(int com_Trade_number) {
		Com_Trade_number = com_Trade_number;
	}
	public int getFul_Full_number() {
		return Ful_Full_number;
	}
	public void setFul_Full_number(int ful_Full_number) {
		Ful_Full_number = ful_Full_number;
	}
	public int getPur_number() {
		return pur_number;
	}
	public void setPur_number(int pur_number) {
		this.pur_number = pur_number;
	}
	public float getDiscount() {
		return Discount;
	}
	public void setDiscount(float discount) {
		Discount = discount;
	}
	public float getSet_money() {
		return set_money;
	}
	public void setSet_money(float set_money) {
		this.set_money = set_money;
	}
	 public String getCell(int col) throws BaseException{
			if(col==0) return Integer.toString(this.getCom_Trade_number());
			else if(col==1) {
				if(this.getFul_Full_number()!=0) {
					return Integer.toString(this.getFul_Full_number());
				}
				else {
					return "没有满折方案";
				}
			}
			else if(col==2) return Integer.toString(this.getPur_number());
			else if(col==3) return Float.toString(this.getDiscount());
			else if(col==4) return Float.toString(this.getSet_money());
			else return "";
		 }
}
