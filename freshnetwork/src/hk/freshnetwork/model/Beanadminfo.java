package hk.freshnetwork.model;

public class Beanadminfo {
    public static Beanadminfo currentLoginadmin=null;
	private int Emp_number;
    private String Emp_name;
    private String Emp_pwd;
	public int getEmp_number() {
		return Emp_number;
	}
	public void setEmp_number(int emp_number) {
		Emp_number = emp_number;
	}
	public String getEmp_name() {
		return Emp_name;
	}
	public void setEmp_name(String emp_name) {
		Emp_name = emp_name;
	}
	public String getEmp_pwd() {
		return Emp_pwd;
	}
	public void setEmp_pwd(String emp_pwd) {
		Emp_pwd = emp_pwd;
	}
}
