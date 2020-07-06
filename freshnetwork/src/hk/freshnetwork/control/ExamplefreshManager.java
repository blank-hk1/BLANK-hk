package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hk.freshnetwork.itf.IUserManager;
import hk.freshnetwork.itf.IfreshManager;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.ui.FrmFreshcat;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;
import hk.freshnetwork.util.DBUtil;
import hk.freshnetwork.util.DbException;


public class ExamplefreshManager implements IfreshManager{
	@Override
	public List<Beanfresh_information> loadFresh()throws BaseException{
		List<Beanfresh_information> fresh = new ArrayList<Beanfresh_information>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from fresh_information";
			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beanfresh_information fr = new Beanfresh_information();
				fr.setCategory_number(rs.getInt(1));
				fr.setCategory_name(rs.getString(2));
				fr.setCategory_description(rs.getString(3));
				fresh.add(fr);
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
		return fresh;
	}
	public List<Beanfresh_information> searchFresh(String Category_name)throws BaseException{
		List<Beanfresh_information> fresh = new ArrayList<Beanfresh_information>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from fresh_information where Category_name = ?";		
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, Category_name);
			java.sql.ResultSet rs=pst.executeQuery();			
			while(rs.next()) {
				Beanfresh_information fr = new Beanfresh_information();
				fr.setCategory_number(rs.getInt(1));
				fr.setCategory_name(rs.getString(2));
				fr.setCategory_description(rs.getString(3));
				fresh.add(fr);
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
		return fresh;
	}
	public Beanfresh_information regFresh(String freshname,String miaoshu) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from fresh_information where Category_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, freshname);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆账号已经存在");
			rs.close();
			pst.close();
			
			sql="insert into fresh_information(Category_name,Category_description) values(?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, freshname);
			pst.setString(2, miaoshu);
			Beanfresh_information fresh = new Beanfresh_information();
			fresh.setCategory_name(freshname);
			fresh.setCategory_description(miaoshu);
			pst.execute();
			pst.close();
			
			return fresh;
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
	public void modifyFresh(String freshname, String state) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
		    int t = 0;
			conn=DBUtil.getConnection();
			String sql = "select Category_number from fresh_information where Category_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,FrmFreshcat.freshs.getCategory_name());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				t=rs.getInt(1);
			}
			rs.close();
			pst.close();
			sql="update fresh_information set Category_name= ?,Category_description= ? where Category_number=?";
			pst=conn.prepareStatement(sql);
				pst.setString(1, freshname);
				pst.setString(2, state);
				pst.setInt(3, t);
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
	public String getFreshname(int Category_number) throws BaseException{
		Connection conn=null;
		try {
			String str = null;
			conn=DBUtil.getConnection();
			String sql="select Category_name from fresh_information where Category_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, Category_number);
				java.sql.ResultSet rs=pst.executeQuery();
				if (rs.next()) {
					str=rs.getString(1);
				}
				rs.close();
				pst.close();
				return str;
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
