package hk.freshnetwork.model;

import java.sql.Blob;

public class Beanmenu_info {
	public static final String[] tableTitles={"菜谱编号","菜谱名称","菜谱原料","步骤"};
    private int Menu_number;
    private String Menu_name;
    private String Menu_Materials;
    private String step;
    private Blob picture;
	public int getMenu_number() {
		return Menu_number;
	}
	public void setMenu_number(int menu_number) {
		Menu_number = menu_number;
	}
	public String getMenu_name() {
		return Menu_name;
	}
	public void setMenu_name(String menu_name) {
		Menu_name = menu_name;
	}
	public String getMenu_Materials() {
		return Menu_Materials;
	}
	public void setMenu_Materials(String menu_Materials) {
		Menu_Materials = menu_Materials;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public Blob getPicture() {
		return picture;
	}
	public void setPicture(Blob picture) {
		this.picture = picture;
	}
	public String getCell(int col){
		if(col==0) return Integer.toString(this.getMenu_number());
		else if(col==1) return this.getMenu_name();
		else if(col==2) return this.getMenu_Materials();
		else if(col==3) return this.getStep();
		else return "";
	}
}
