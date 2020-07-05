package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
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
			String sql="select * from user_table where User_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, username);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登录账号不存在");
			Beanuser_table user = new Beanuser_table();
		    user.setUser_num(rs.getInt(1));
		    user.setUser_name(rs.getString(2));
		    user.setUser_pwd(rs.getString(4));
		    user.setUser_phone(rs.getString(5));
		    user.setUser_mail(rs.getString(6));
		    user.setCity(rs.getString(7));
		    user.setRegtime(rs.getTimestamp(8));
		    user.setISmember(rs.getBoolean(9));
		    user.setClosedate(rs.getTimestamp(10));
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
		if(oldPwd==null) throw new BusinessException("原始密码不能为空");
		if(newPwd==null || "".equals(newPwd) || newPwd.length()>16) throw new BusinessException("必须为1-16个字符");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select User_pwd from user_table where User_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUser_name());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不 存在");
			if(!rs.getString(1).equals(oldPwd)) throw new BusinessException("原密码输入错误");
			rs.close();
			pst.close();
			if(!newPwd.equals(newPwd2)) {
				throw new BusinessException("第二次输入与第一次输入不同");
			}
			else{
				sql="update user_table set User_pwd= ? where User_name= ?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, newPwd);
				pst.setString(2, user.getUser_name());
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
	@Override
	public void changephone(Beanuser_table user,String newphone) throws BaseException{
		if(newphone==null || "".equals(newphone) || newphone.length()!=11) throw new BusinessException("手机号码必须为11位");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select User_phone from user_table where User_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUser_name());
			java.sql.ResultSet rs=pst.executeQuery();
			rs.close();
			pst.close();
				sql="update user_table set User_phone= ? where User_name= ?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, newphone);
				pst.setString(2, user.getUser_name());
				pst.execute();
				pst.close();	
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
	public void changemail(Beanuser_table user,String newmail) throws BaseException{
		if(newmail==null || "".equals(newmail) || !newmail.matches("\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}")) throw new BusinessException("邮箱格式不正确");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select User_mail from user_table where User_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUser_name());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("原邮箱不存在");
			rs.close();
			pst.close();
				sql="update user_table set User_mail= ? where User_name= ?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, newmail);
				pst.setString(2, user.getUser_name());
				pst.execute();
				pst.close();	
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
	public void changecity(Beanuser_table user,String newcity) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select city from user_table where User_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUser_name());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("原邮箱不存在");
			rs.close();
			pst.close();
				sql="update user_table set city= ? where User_name= ?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, newcity);
				pst.setString(2, user.getUser_name());
				pst.execute();
				pst.close();	
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
	public void openvip(Beanuser_table user,String vip) throws BaseException{
		Connection conn=null;
		Calendar c= Calendar.getInstance();
		try {
			conn=DBUtil.getConnection();
			if (user.isISmember()) {
				c.setTime(user.getClosedate());
			}
			else {
				Date time = new Date(System.currentTimeMillis());
				c.setTime(time);
			}
			if (vip.equals("单月会员")) {
				c.add(Calendar.MONTH, 1);
				java.util.Date date = c.getTime();
				String sql="update user_table set ISmember= ? ,closedate= ? where User_name= ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1, 1);
                pst.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
				pst.setString(3, user.getUser_name());
				pst.execute();
				pst.close();
			}
			else if(vip.equals("季度会员")) {
				c.add(Calendar.MONTH, 3);
				java.util.Date date = c.getTime();
				String sql="update user_table set ISmember= ? ,closedate= ? where User_name= ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1, 1);
                pst.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
				pst.setString(3, user.getUser_name());
				pst.execute();
				pst.close();
			}
			else if(vip.equals("半年制会员")) {
				c.add(Calendar.MONTH, 6);
				java.util.Date date = c.getTime();
				String sql="update user_table set ISmember= ? ,closedate= ? where User_name= ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1, 1);
                pst.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
				pst.setString(3, user.getUser_name());
				pst.execute();
				pst.close();
			}
			else if(vip.equals("年度会员")){
				c.add(Calendar.MONTH, 12);
				java.util.Date date = c.getTime();
				String sql="update user_table set ISmember= ? ,closedate= ? where User_name= ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1, 1);
                pst.setTimestamp(2, new java.sql.Timestamp(date.getTime()));
				pst.setString(3, user.getUser_name());
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
