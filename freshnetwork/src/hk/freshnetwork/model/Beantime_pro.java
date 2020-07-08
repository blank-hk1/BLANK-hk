package hk.freshnetwork.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Beantime_pro {
	public static final String[] tableTitles={"限时促销编号","商品编号","促销价格","促销数量","开始时间","结束时间"};
    private int Pro_number;
    private int Trade_number;
    private float Pro_price;
    private int Prom_number;
    private Timestamp ProStart_date;
	private Timestamp ProEnd_date;
    public int getPro_number() {
		return Pro_number;
	}
	public void setPro_number(int pro_number) {
		Pro_number = pro_number;
	}
	public int getTrade_number() {
		return Trade_number;
	}
	public void setTrade_number(int trade_number) {
		Trade_number = trade_number;
	}
	public float getPro_price() {
		return Pro_price;
	}
	public void setPro_price(float pro_price) {
		Pro_price = pro_price;
	}
	public int getProm_number() {
		return Prom_number;
	}
	public void setProm_number(int prom_number) {
		Prom_number = prom_number;
	}
	public Timestamp getProStart_date() {
		return ProStart_date;
	}
	public void setProStart_date(Timestamp proStart_date) {
		ProStart_date = proStart_date;
	}
	public Timestamp getProEnd_date() {
		return ProEnd_date;
	}
	public void setProEnd_date(Timestamp proEnd_date) {
		ProEnd_date = proEnd_date;
	}
	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	public String getCell(int col){
		if(col==0) return Integer.toString(this.getPro_number());
		else if(col==1) return Integer.toString(this.getTrade_number());
		else if(col==2) return Float.toString(this.getPro_price());
		else if(col==3) return Integer.toString(this.getProm_number());
		else if(col==4) return df.format(this.getProStart_date());
		else if(col==5) return df.format(this.getProEnd_date());
		else return "";
	}
}
