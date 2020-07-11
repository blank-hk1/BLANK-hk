package hk.freshnetwork.model;

import java.sql.Blob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Beangoods_eva {
	public static final String[] tableTitles={"用户编号","内容","评论日期","星级"};
    private int Use_User_num;
    private int Com_Trade_number;
    private String Eva_content;
    private Timestamp Eva_date;
    private int Star;
    private Blob Photo;
	public int getUse_User_num() {
		return Use_User_num;
	}
	public void setUse_User_num(int use_User_num) {
		Use_User_num = use_User_num;
	}
	public int getCom_Trade_number() {
		return Com_Trade_number;
	}
	public void setCom_Trade_number(int com_Trade_number) {
		Com_Trade_number = com_Trade_number;
	}
	public String getEva_content() {
		return Eva_content;
	}
	public void setEva_content(String eva_content) {
		Eva_content = eva_content;
	}
	public Timestamp getEva_date() {
		return Eva_date;
	}
	public void setEva_date(Timestamp eva_date) {
		Eva_date = eva_date;
	}
	public int getStar() {
		return Star;
	}
	public void setStar(int star) {
		Star = star;
	}
	public Blob getPhoto() {
		return Photo;
	}
	public void setPhoto(Blob photo) {
		Photo = photo;
	}
	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	public String getCell(int col){
		if(col==0) return Integer.toString(this.getUse_User_num());
		else if(col==1) return this.getEva_content();
		else if(col==2) return df.format(this.getEva_date());
		else if(col==3) return Integer.toString(this.getStar());
		else return "";
	}
}
