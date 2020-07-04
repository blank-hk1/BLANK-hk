package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hk.freshnetwork.itf.IUserManager;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;
import hk.freshnetwork.util.DBUtil;
import hk.freshnetwork.util.DbException;

public class ExampleUserManager implements IUserManager{
	@Override
	public Beanuser_table reg(String username, String gender1,String pwd,String pwd2,String phone,String mail,String city) throws BaseException{
		if(username==null || "".equals(username) || username.length()>20){
			throw new BusinessException("用户名称必须是1-20个字");
		}
		if(phone.length()!=11) {
			throw new BusinessException("手机号码必须为11位");
		}
		System.out.println(mail);
		if (!mail.matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}")) {
			throw new BusinessException("邮箱地址格式错误");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from user_table where user_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆账号已经存在");
			rs.close();
			pst.close();
			Beanuser_table ur = new Beanuser_table();
			if(!pwd.equals(pwd2)) {
				throw new BusinessException("两次输入密码不一致");
			}else {
			    sql="insert into user_table(User_name,User_gender,User_pwd,User_phone,User_mail,city,Regtime,ISmember,closedate) values(?,?,?,?,?,?,?,null,null)";
				pst=conn.prepareStatement(sql);
				pst.setString(1, username);
				pst.setString(2, gender1);
				pst.setString(3, pwd);
				pst.setString(4, phone);
				pst.setString(5, mail);
				pst.setString(6, city);
				pst.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
				pst.execute();
				pst.close();
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
	public Beanuser_table login(String username, String pwd) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select User_num,User_name,User_pwd from user_table where User_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登录账号不存在");
			Beanuser_table user = new Beanuser_table();
		    user.setUser_num(rs.getInt(1));
		    user.setUser_name(rs.getString(2));
		    user.setUser_pwd(rs.getString(3));
			rs.close();
			pst.close();
			return user;
			
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
	public void changePwd(Beanuser_table user, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		
	}
}
