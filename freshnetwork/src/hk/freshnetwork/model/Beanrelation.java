package hk.freshnetwork.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Beanrelation {
   public static final String[] tableTitles={"满折编号","商品编号","开始时间","结束时间"};
   private int Ful_Full_number;
   private int Com_Trade_number;
   private Timestamp FulStart_date;
   private Timestamp FulEnd_date;
public int getFul_Full_number() {
	return Ful_Full_number;
}
public void setFul_Full_number(int ful_Full_number) {
	Ful_Full_number = ful_Full_number;
}
public int getCom_Trade_number() {
	return Com_Trade_number;
}
public void setCom_Trade_number(int com_Trade_number) {
	Com_Trade_number = com_Trade_number;
}
public Timestamp getFulStart_date() {
	return FulStart_date;
}
public void setFulStart_date(Timestamp fulStart_date) {
	FulStart_date = fulStart_date;
}
public Timestamp getFulEnd_date() {
	return FulEnd_date;
}
public void setFulEnd_date(Timestamp fulEnd_date) {
	FulEnd_date = fulEnd_date;
}
SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
public String getCell(int col){
	if(col==0) return Integer.toString(this.getFul_Full_number());
	else if(col==1) return Integer.toString(this.getCom_Trade_number());
	else if(col==2) return df.format(this.getFulStart_date());
	else if(col==3) return df.format(this.getFulEnd_date());
	else return "";
}
}
