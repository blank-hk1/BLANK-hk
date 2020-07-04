package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.SQLException;

import hk.freshnetwork.itf.IadminManager;
import hk.freshnetwork.model.Beanadminfo;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;
import hk.freshnetwork.util.DBUtil;
import hk.freshnetwork.util.DbException;

public class ExampleadminManager implements IadminManager{
	public Beanadminfo reg(String userid, String pwd,String pwd2) throws BaseException{
		return null;		
	}
	@Override
	public Beanadminfo login(String Emp_name, String pwd) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from adm_info where Emp_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, Emp_name);
			java.sql.ResultSet rs=pst.executeQuery();
			System.out.println(Emp_name);
			if(!rs.next()) throw new BusinessException("登录账号不存在");
			Beanadminfo admin = new Beanadminfo();
			admin.setEmp_number(rs.getInt(1));
			admin.setEmp_name(rs.getString(2));
			admin.setEmp_pwd(rs.getString(3));
			rs.close();
			pst.close();
			return admin;
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
	}
	public void changePwd(Beanadminfo admin, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		
	}
}
