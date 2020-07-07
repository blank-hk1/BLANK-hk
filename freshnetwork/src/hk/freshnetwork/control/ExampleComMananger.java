package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hk.freshnetwork.itf.IcomManager;
import hk.freshnetwork.model.Beancommodity_information;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.ui.FrmFreshCom;
import hk.freshnetwork.ui.FrmFreshcat;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;
import hk.freshnetwork.util.DBUtil;
import hk.freshnetwork.util.DbException;

public class ExampleComMananger implements IcomManager{
	@Override
	public List<Beancommodity_information> loadcom()throws BaseException{
		List<Beancommodity_information> fresh = new ArrayList<Beancommodity_information>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from commodity_information";
			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beancommodity_information fr = new Beancommodity_information();
                fr.setTrade_number(rs.getInt(1));
                fr.setCategory_number(rs.getInt(3));
                fr.setTrade_name(rs.getString(4));
                fr.setPrice(rs.getFloat(5));
                fr.setMember_price(rs.getFloat(6));
                fr.setNumber(rs.getInt(7));
                fr.setSpecifications(rs.getString(8));
                fr.setDetails(rs.getString(9));
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
	public List<Beancommodity_information> searchCom(String Trade_name)throws BaseException{
		List<Beancommodity_information> fresh = new ArrayList<Beancommodity_information>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from commodity_information where Trade_name = ?";		
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, Trade_name);
			java.sql.ResultSet rs=pst.executeQuery();			
			while(rs.next()) {
				Beancommodity_information com = new Beancommodity_information();
				com.setTrade_name(Trade_name);
				com.setPrice(rs.getFloat(5));
				com.setMember_price(rs.getFloat(6));
				fresh.add(com);
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
	public Beancommodity_information regCom(String Trade_name,String category_number,String price,String member_price,String number,String Specifications,String details) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from commodity_information where Trade_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, Trade_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆账号已经存在");
			rs.close();
			pst.close();
			int c=Integer.parseInt(category_number);
			float p = Float.parseFloat(price);
			float m = Float.parseFloat(member_price);
			int n=Integer.parseInt(number);
			sql="insert into commodity_information(Trade_name,Category_number,Price,Member_price,number,Specifications,details) values(?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, Trade_name);
			pst.setInt(2, c);
			pst.setFloat(3, p);
			pst.setFloat(4, m);
			pst.setInt(5, n);
			pst.setString(6, Specifications);
			pst.setString(7, details);
			Beancommodity_information com = new Beancommodity_information();
			com.setTrade_name(Trade_name);
			com.setCategory_number(c);
			com.setPrice(p);
			com.setMember_price(m);
			com.setNumber(n);
			com.setSpecifications(Specifications);
			com.setDetails(details);
			pst.execute();
			pst.close();
			
			return com;
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
	public void modifyCom(String Trade_name,String category_number,String price,String member_price,String number,String Specifications,String details) throws BaseException{
		Connection conn=null;
		try {
		    int t = 0;
			conn=DBUtil.getConnection();
			String sql = "select Trade_number from commodity_information where Trade_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,FrmFreshCom.COMS.getTrade_name());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				t=rs.getInt(1);
			}
			rs.close();
			pst.close();
			int c=Integer.parseInt(category_number);
			float p = Float.parseFloat(price);
			float m = Float.parseFloat(member_price);
			int n=Integer.parseInt(number);
			sql="update commodity_information set Trade_name= ?,Category_number= ?,Price=?,Member_price=?,number=?,Specifications=?,details=? where Trade_number=?";
			pst=conn.prepareStatement(sql);
				pst.setString(1, Trade_name);
				pst.setInt(2, c);
				pst.setFloat(3, p);
				pst.setFloat(4, m);
				pst.setInt(5, n);
				pst.setString(6, Specifications);
				pst.setString(7, details);
				pst.setInt(8, t);
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
	public void deleteCom(String Trade_name){
		Connection conn=null;
		try {
			String str = null;
			conn=DBUtil.getConnection();
			String sql="delete from commodity_information where Trade_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, Trade_name);
			pst.execute();
		    pst.close();
		}catch (SQLException e) {
			e.printStackTrace();

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
	public List<Beancommodity_information> loadcompur()throws BaseException{
		List<Beancommodity_information> fresh = new ArrayList<Beancommodity_information>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from commodity_information where number <= 10 order by number desc";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beancommodity_information fr = new Beancommodity_information();
                fr.setTrade_number(rs.getInt(1));
                fr.setCategory_number(rs.getInt(3));
                fr.setTrade_name(rs.getString(4));
                fr.setPrice(rs.getFloat(5));
                fr.setMember_price(rs.getFloat(6));
                fr.setNumber(rs.getInt(7));
                fr.setSpecifications(rs.getString(8));
                fr.setDetails(rs.getString(9));
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
}
