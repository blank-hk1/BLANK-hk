package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.itf.IAddressManager;
import hk.freshnetwork.model.Beanaddlist;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.ui.FrmFreshcat;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;
import hk.freshnetwork.util.DBUtil;
import hk.freshnetwork.util.DbException;

public class ExampleAddressMannager implements IAddressManager{
	public List<Beanaddlist> loadAddress()throws BaseException{
		List<Beanaddlist> address = new ArrayList<Beanaddlist>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from add_list where User_num = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Beanuser_table.currentLoginUser.getUser_num());
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beanaddlist add = new Beanaddlist();
				add.setAdd_number(rs.getInt(1));
				add.setUser_num(rs.getInt(2));
				add.setSheng(rs.getString(3));
				add.setShi(rs.getString(4));
				add.setQu(rs.getString(5));
				add.setAddress(rs.getString(6));
				add.setContacts(rs.getString(7));
				add.setCon_phone(rs.getString(8));
				address.add(add);
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
		return address;
	}
	public Beanaddlist regAdd(String qu,String address,String contacts,String con_phone) throws BaseException{
		Connection conn=null;
		if(qu.equals("")||address.equals("")||contacts.equals("")||con_phone.equals("")) {
			throw new BusinessException("输入内容不能为空!");
		}
		if(con_phone.length()!=11) {
			throw new BusinessException("联系电话应为11位!");
		}	
		try {
			conn=DBUtil.getConnection();		
			String sql="insert into add_list(User_num,sheng,shi,qu,address,contacts,con_phone) values(?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Beanuser_table.currentLoginUser.getUser_num());
			pst.setString(2, "浙江省");
			pst.setString(3, "杭州市");
			pst.setString(4, qu);
			pst.setString(5,address);
			pst.setString(6, contacts);
			pst.setString(7, con_phone);
			Beanaddlist add = new Beanaddlist();
			add.setUser_num(Beanuser_table.currentLoginUser.getUser_num());
			add.setSheng("浙江省");
			add.setShi("杭州市");
			add.setQu(qu);
			add.setAddress(address);
			add.setContacts(contacts);
			add.setCon_phone(con_phone);
			pst.execute();
			pst.close();
			
			return add;
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
	public void modifyFresh(int add_number,String qu,String address,String contacts,String con_phone) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from add_list where add_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,add_number);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("要修改的配送地址不存在");
			}
			rs.close();
			pst.close();
			sql="update add_list set qu= ?,address= ? ,contacts= ? , con_phone= ? where add_number=?";
			pst=conn.prepareStatement(sql);
				pst.setString(1, qu);
				pst.setString(2,address);
				pst.setString(3, contacts);
				pst.setString(4, con_phone);
				pst.setInt(5, add_number);
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
	public void deleteAdd(int add_number){
		Connection conn=null;
		try {
			String str = null;
			conn=DBUtil.getConnection();
			String sql="delete from add_list where add_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, add_number);
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
