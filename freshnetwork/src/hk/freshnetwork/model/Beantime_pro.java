package hk.freshnetwork.model;

import java.sql.Timestamp;

public class Beantime_pro {
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
}
