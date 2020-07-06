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
	@Override
	public Beanadminfo reg(String adminname, String pwd,String pwd2) throws BaseException{
		if(adminname==null || "".equals(adminname) || adminname.length()>20){
			throw new BusinessException("注册id必须是1-20个字");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select Emp_name,Emp_pwd from adm_info where Emp_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, adminname);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆账号已经存在");
			rs.close();
			pst.close();
			Beanadminfo ur = new Beanadminfo();
			if(!pwd.equals(pwd2)) {
				throw new BusinessException("两次输入密码不一致");
			}else {
			    sql="insert into adm_info(Emp_name,Emp_pwd) values(?,?)";
				pst=conn.prepareStatement(sql);
				pst.setString(1, adminname);
				pst.setString(2, pwd);
				pst.execute();
				pst.close();
				ur.setEmp_name(adminname);
				ur.setEmp_pwd(pwd);
				return ur;
			}
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
	@Override
	public Beanadminfo login(String Emp_name, String pwd) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from adm_info where Emp_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, Emp_name);
			java.sql.ResultSet rs=pst.executeQuery();
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
		if(oldPwd==null) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd) || newPwd.length()>16) throw new BusinessException("必须为1-16个字符");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select Emp_pwd from adm_info where Emp_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, admin.getEmp_name());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不 存在");
			if(!rs.getString(1).equals(oldPwd)) throw new BusinessException("原密码输入错误");
			rs.close();
			pst.close();
			if(!newPwd.equals(newPwd2)) {
				throw new BusinessException("第二次输入与第一次输入不同");
			}
			else{
				sql="update adm_info set Emp_pwd= ? where Emp_name= ?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, newPwd);
				pst.setString(2, admin.getEmp_name());
				pst.execute();
				pst.close();
			}		
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
}
