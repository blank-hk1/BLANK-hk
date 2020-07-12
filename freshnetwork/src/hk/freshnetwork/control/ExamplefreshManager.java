package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hk.freshnetwork.itf.IUserManager;
import hk.freshnetwork.itf.IfreshManager;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.model.Beanmenu_info;
import hk.freshnetwork.ui.FrmFreshMenu;
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
			String sql = "select * from fresh_information where Category_name like ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+Category_name+"%");
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
	public void deleteFresh(String Category_name) throws BusinessException{
		Connection conn=null;
		try {
			String str = null;
			conn=DBUtil.getConnection();
			String sql="select * from fresh_information where Category_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, Category_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				String sql1 = "select * from commodity_information where Category_number = ?";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(1));
				java.sql.ResultSet rs1=pst1.executeQuery();
				if(rs1.next()) {
					throw new BusinessException("该商品无法删除!");
				}
				rs1.close();
				pst1.close();
			}
			sql="delete from fresh_information where Category_name = ?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, Category_name);
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
	public List<Beanmenu_info> loadmenu()throws BaseException{
		List<Beanmenu_info> Menu = new ArrayList<Beanmenu_info>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from menu_information";
			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beanmenu_info me = new Beanmenu_info();
				me.setMenu_number(rs.getInt(1));
				me.setMenu_name(rs.getString(2));
				me.setMenu_Materials(rs.getString(3));
				me.setStep(rs.getString(4));
				Menu.add(me);
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
		return Menu;
	}
	public List<Beanmenu_info> searchMenu(String menu_name)throws BaseException{
		List<Beanmenu_info> Menu = new ArrayList<Beanmenu_info>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from menu_information where Menu_name like ?";		
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+menu_name+"%");
			java.sql.ResultSet rs=pst.executeQuery();			
			while(rs.next()) {
				Beanmenu_info me = new Beanmenu_info();
				me.setMenu_number(rs.getInt(1));
				me.setMenu_name(rs.getString(2));
				me.setMenu_Materials(rs.getString(3));
				me.setStep(rs.getString(4));
				Menu.add(me);
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
		return Menu;
	}
	public void deleteMenu(String Menu_name) throws BusinessException{
		Connection conn=null;
		try {
			String str = null;
			conn=DBUtil.getConnection();
			String sql="select * from menu_information where Menu_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, Menu_name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				String sql1 = "select * from recommend_menu where Men_Menu_number = ?";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(1));
				java.sql.ResultSet rs1=pst1.executeQuery();
				if(rs1.next()) {
					throw new BusinessException("该商品无法删除!");
				}
				rs1.close();
				pst1.close();
			}
			sql="delete from menu_information where Menu_name = ?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, Menu_name);
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
	public Beanmenu_info regMenu(String menuname,String menuMaterials,String step) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from menu_information where Menu_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, menuname);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆账号已经存在");
			rs.close();
			pst.close();
			
			sql="insert into menu_information(Menu_name,Menu_Materials,step) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, menuname);
			pst.setString(2, menuMaterials);
			pst.setString(3, step);
			Beanmenu_info menu = new Beanmenu_info();
			menu.setMenu_name(menuname);
			menu.setMenu_Materials(menuMaterials);
			menu.setStep(step);
			pst.execute();
			pst.close();
			
			return menu;
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
	public void modifyMenu(String menuname, String state,String step){
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
		    int t = 0;
			conn=DBUtil.getConnection();
			String sql = "select Menu_number from menu_information where Menu_name = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,FrmFreshMenu.menus.getMenu_name());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				t=rs.getInt(1);
			}
			rs.close();
			pst.close();
			System.out.println("1"+state);
			sql="update menu_information set  Menu_name= ?,Menu_Materials= ?,step=? where Menu_number=?";
			pst=conn.prepareStatement(sql);
				pst.setString(1, menuname);
				pst.setString(2, state);
				pst.setString(3, step);
				pst.setInt(4, t);
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
}
