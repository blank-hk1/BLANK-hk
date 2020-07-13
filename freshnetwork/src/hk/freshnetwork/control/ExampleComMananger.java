package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hk.freshnetwork.itf.IcomManager;
import hk.freshnetwork.model.Beancommodity_information;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.model.Beanrecommend_menu;
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
                fr.setSaleNumber(rs.getInt(10));                
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
			String sql = "select * from commodity_information where Trade_name like ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+Trade_name+"%");
			java.sql.ResultSet rs=pst.executeQuery();			
			while(rs.next()) {
				Beancommodity_information com = new Beancommodity_information();
				com.setTrade_number(rs.getInt(1));
				com.setCategory_number(rs.getInt(3));
				com.setTrade_name(rs.getString(4));
				com.setPrice(rs.getFloat(5));
				com.setMember_price(rs.getFloat(6));
				com.setNumber(rs.getInt(7));
				com.setDetails(rs.getString(9));
				com.setSaleNumber(rs.getInt(10));
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
	public List<Beancommodity_information> searchComNumber(int Trade_number) throws BaseException{
		List<Beancommodity_information> fresh = new ArrayList<Beancommodity_information>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from commodity_information where Trade_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Trade_number);
			java.sql.ResultSet rs=pst.executeQuery();			
			while(rs.next()) {
				Beancommodity_information com= new Beancommodity_information();
				com.setTrade_number(rs.getInt(1));
				com.setCategory_number(rs.getInt(3));
				com.setTrade_name(rs.getString(4));
				com.setPrice(rs.getFloat(5));
				com.setMember_price(rs.getFloat(6));
				com.setNumber(rs.getInt(7));
				com.setDetails(rs.getString(9));
				com.setSaleNumber(rs.getInt(10));
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
		if(fresh.size()==0) {
			return null;
		}
		return fresh;
	}
	public List<Beancommodity_information> searchComFresh(int Category_number)throws BaseException{
		List<Beancommodity_information> fresh = new ArrayList<Beancommodity_information>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from commodity_information where Category_number = ?";	
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Category_number);
			java.sql.ResultSet rs=pst.executeQuery();			
			while(rs.next()) {
				Beancommodity_information com = new Beancommodity_information();
				com.setTrade_number(rs.getInt(1));
				com.setPro_number(rs.getInt(2));
				com.setCategory_number(rs.getInt(3));
				com.setTrade_name(rs.getString(4));
				com.setPrice(rs.getFloat(5));
				com.setMember_price(rs.getFloat(6));
				com.setNumber(rs.getInt(7));
				com.setDetails(rs.getString(8));
				com.setSaleNumber(rs.getInt(10));
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
		if(Trade_name.equals("")||category_number.equals("")) {
			throw new BusinessException("输入不能为空!");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from commodity_information where Trade_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, Trade_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("该商品已经存在");
			rs.close();
			pst.close();
			int c=Integer.parseInt(category_number);
			float p = Float.parseFloat(price);
			float m = Float.parseFloat(member_price);
			int n=Integer.parseInt(number);
			sql = "select * from fresh_information where Category_number = ?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, category_number);
			rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("该类别编号不存在!");
			}
			rs.close();
			pst.close();
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
			sql = "select * from fresh_information where Category_number = ?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, category_number);
			rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("该类别编号不存在!");
			}
			rs.close();
			pst.close();
			sql="update commodity_information set Trade_name= ?,Category_number = ?,Price=?,Member_price=?,number=?,Specifications=?,details=? where Trade_number=?";
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
	public void deleteCom(String Trade_name) throws BusinessException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from commodity_information where Trade_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, Trade_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				String sql1 = "select * from shopping where Com_Trade_number = ?";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(1));
				java.sql.ResultSet rs1=pst1.executeQuery();
				if(rs1.next()) {
					throw new BusinessException("该商品无法删除!");
				}
				rs1.close();
				pst1.close();
				sql1 = "select * from goods_eva where Com_Trade_number = ?";
				pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(1));
				rs1=pst1.executeQuery();
				if(rs1.next()) {
					throw new BusinessException("该商品无法删除!");
				}
				rs1.close();
				pst1.close();
				sql1 = "select * from relation where Com_Trade_number = ?";
				pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(1));
				rs1=pst1.executeQuery();
				if(rs1.next()) {
					throw new BusinessException("该商品无法删除!");
				}
				rs1.close();
				pst1.close();
				sql1 = "select * from order_details where Com_Trade_number = ?";
				pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(1));
				rs1=pst1.executeQuery();
				if(rs1.next()) {
					throw new BusinessException("该商品无法删除!");
				}
				rs1.close();
				pst1.close();
				sql1 = "select * from time_pro where Trade_number = ?";
				pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(1));
				rs1=pst1.executeQuery();
				if(rs1.next()) {
					throw new BusinessException("该商品无法删除!");
				}
				rs1.close();
				pst1.close();
			}
			rs.close();
			pst.close();
			sql="delete from commodity_information where Trade_name = ?";
			pst=conn.prepareStatement(sql);
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
                fr.setSaleNumber(rs.getInt(10));
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
	public float searchCuxiao(int Trade_number,float price){
		float cx = 0;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from time_pro where Trade_number = ?";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Trade_number);
			java.sql.ResultSet rs=pst.executeQuery();			
			if(rs.next()) {
				cx=rs.getFloat(3);
			}
			else {
				cx=price;
			}
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
		return cx;
	}
	public List<Beancommodity_information> loadRec(int Trade_number){
		List<Beancommodity_information> menu =new ArrayList<Beancommodity_information>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from recommend_menu where Com_Trade_number = ?";		
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Trade_number);
			java.sql.ResultSet rs=pst.executeQuery();			
			while(rs.next()) {
				String sql1 = "select * from recommend_menu where Men_Menu_number = ?";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(1));
				java.sql.ResultSet rs1=pst1.executeQuery();
				while (rs1.next()) {
					System.out.println(rs1.getInt(1));
					Beancommodity_information comm = new Beancommodity_information();
					String sql2 = "select * from commodity_information where Trade_number = ?";
					java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
					pst2.setInt(1, rs1.getInt(2));
					java.sql.ResultSet rs2=pst2.executeQuery();
					while(rs2.next()) {
						comm.setTrade_number(rs2.getInt(1));
						comm.setCategory_number(rs2.getInt(3));
						comm.setTrade_name(rs2.getString(4));
						comm.setPrice(rs2.getFloat(5));
						comm.setMember_price(rs2.getFloat(6));
						comm.setNumber(rs2.getInt(7));
						comm.setDetails(rs2.getString(8));
						comm.setSaleNumber(rs2.getInt(10));
						menu.add(comm);
					}					
					rs2.close();
					pst2.close();
				}
				rs1.close();
				pst1.close();
			}
			rs.close();
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
		return menu;
	}
	public float SearchPrice(int Trade_number) {
		float price=0;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from commodity_information where Trade_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Trade_number);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				price=rs.getFloat(6);
			}
			rs.close();
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
		return price;
	}
}
