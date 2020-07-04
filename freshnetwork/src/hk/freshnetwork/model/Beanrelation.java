package hk.freshnetwork.model;

import java.sql.Timestamp;

public class Beanrelation {
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
}
