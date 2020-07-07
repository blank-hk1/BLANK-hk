package hk.freshnetwork.model;

public class Beanfresh_information {
	public static Beanfresh_information currentLoginfresh=null;
	public static final String[] tableTitles={"类别编号","类别名称","类别描述"};
    private int Category_number;
    private String Category_name;
    private String Category_description;
	public int getCategory_number() {
		return Category_number;
	}
	public void setCategory_number(int category_number) {
		Category_number = category_number;
	}
	public String getCategory_name() {
		return Category_name;
	}
	public void setCategory_name(String category_name) {
		Category_name = category_name;
	}
	public String getCategory_description() {
		return Category_description;
	}
	public void setCategory_description(String category_description) {
		Category_description = category_description;
	}
	public String getCell(int col){
		if(col==0) return Integer.toString(this.getCategory_number());
		else if(col==1) return this.getCategory_name();
		else if(col==2) return this.getCategory_description();
		else return "";
	}
}
