package hk.freshnetwork.model;

import java.sql.Blob;
import java.sql.Timestamp;

public class Beangoods_eva {
    private int Use_User_num;
    private int Com_Trade_number;
    private String Eva_content;
    private Timestamp Eva_date;
    private String Star;
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
	public String getStar() {
		return Star;
	}
	public void setStar(String star) {
		Star = star;
	}
	public Blob getPhoto() {
		return Photo;
	}
	public void setPhoto(Blob photo) {
		Photo = photo;
	}
}
