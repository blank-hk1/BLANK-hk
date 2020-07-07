package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hk.freshnetwork.itf.IpurchaseManager;
import hk.freshnetwork.model.Beanadminfo;
import hk.freshnetwork.model.Beancommodity_information;
import hk.freshnetwork.model.Beanpurchase;
import hk.freshnetwork.ui.FrmFreshCom;
import hk.freshnetwork.ui.FrmFreshPurchase;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;
import hk.freshnetwork.util.DBUtil;
import hk.freshnetwork.util.DbException;

public class ExamplePurchaseManager implements IpurchaseManager{
	@Override
	public List<Beanpurchase> loadPur()throws BaseException{
		List<Beanpurchase> purchase = new ArrayList<Beanpurchase>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from purchase_list";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beanpurchase pur = new Beanpurchase();
                pur.setChase_number(rs.getInt(2));
                pur.setTrade_number(rs.getInt(3));
                pur.setEmp_number(rs.getInt(4));
                pur.setPurchase_amount(rs.getInt(5));
                pur.setChase_stat(rs.getString(6));
                purchase.add(pur);
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
		return purchase;
	}
	public List<Beanpurchase> searchCom(int chase_number){
		List<Beanpurchase> purchase = new ArrayList<Beanpurchase>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from purchase_list where chase_number = ?";		
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, chase_number);
			java.sql.ResultSet rs=pst.executeQuery();			
			while(rs.next()) {
				Beanpurchase pur = new Beanpurchase();
				pur.setChase_number(rs.getInt(2));
				pur.setTrade_number(rs.getInt(3));
				pur.setEmp_number(rs.getInt(4));
				pur.setPurchase_amount(rs.getInt(5));
				pur.setChase_stat(rs.getString(6));
				purchase.add(pur);
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
		return purchase;
	}
	public void modifyPurchase(int chase_number,int Trade_number,int Emp_number,int purchase_amount,String chase_stat) throws BusinessException{
		Connection conn=null;
		try {
		    int t = 0;
			conn=DBUtil.getConnection();
			String sql = "select chase_num from purchase_list where chase_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,FrmFreshPurchase.Purchases.getChase_number());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				t=rs.getInt(1);
			}
			rs.close();
			pst.close();
			sql="select * from commodity_information where Trade_number = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Trade_number);
			rs=pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("要采购的商品不存在");
			}
			rs.close();
			pst.close();
			sql="select * from adm_info where Emp_number = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Emp_number);
			rs=pst.executeQuery();
			if (!rs.next()) {
				throw new BusinessException("不存在该管理员");
			}
			rs.close();
			pst.close();
			sql="update purchase_list set chase_number = ?,Trade_number=?,Emp_number=?,purchase_amount=?,chase_stat=? where chase_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, chase_number);
			pst.setFloat(2, Trade_number);
			pst.setFloat(3, Emp_number);
			pst.setInt(4,purchase_amount);
			pst.setString(5, chase_stat);
			pst.setInt(6, t);
			pst.execute();
			pst.close();
			if (chase_stat.equals("入库")) {
				sql="update commodity_information set number = number + ? where Trade_number = ?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, purchase_amount);
				pst.setInt(2, Trade_number);
				pst.execute();
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
		
	}
	public void deletePur(int chase_number){
		Connection conn=null;
		try {
			String str = null;
			conn=DBUtil.getConnection();
			String sql="delete from purchase_list where chase_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, chase_number);
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
	public Beanpurchase regpur(int chase_number,int Trade_number,int Emp_number,int purchase_amount,String chase_stat) throws BaseException{
		if(chase_number==0 || Emp_number==0||purchase_amount==0){
			throw new BusinessException("输入内容中有一项为空");
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select chase_num,purchase_amount from purchase_list where chase_number= ? and Trade_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, chase_number);
			pst.setInt(2, Trade_number);
			java.sql.ResultSet rs=pst.executeQuery();
			Beanpurchase ur = new Beanpurchase();
			int flag=0;
			if(rs.next()){
				flag=1;
			}
			else {
				flag=2;
			}
			rs.close();
			pst.close();
			if(flag==1) {
				//System.out.println(chase_number);
				 int t =rs.getInt(2);
			     sql="update purchase_list set purchase_amount = purchase_amount+ ? where chase_number= ? and Trade_number = ?";
			     pst=conn.prepareStatement(sql);
			     pst.setInt(1, purchase_amount);
			     pst.setInt(2, chase_number);
				 pst.setInt(3, Trade_number);
				 ur.setChase_number(chase_number);
				 ur.setEmp_number(Emp_number);
				 ur.setTrade_number(Trade_number);
				 ur.setPurchase_amount(purchase_amount+t);
				 ur.setChase_stat(chase_stat);
				 pst.execute();
				 pst.close();
			}
			else if(flag==2){
				System.out.println(chase_number);
				sql="insert into purchase_list(chase_number,Trade_number,Emp_number,purchase_amount,chase_stat) values (?,?,?,?,?)";
				 pst=conn.prepareStatement(sql);
				 pst.setInt(1, chase_number);
			     pst.setInt(2, Trade_number);
				 pst.setInt(3, Emp_number);
			     pst.setInt(4, purchase_amount);
				 pst.setString(5, chase_stat);
				 System.out.println(ur);
				 ur.setChase_number(chase_number);			 
				 ur.setEmp_number(Emp_number);
				 ur.setTrade_number(Trade_number);
				 ur.setPurchase_amount(purchase_amount);
				 ur.setChase_stat(chase_stat);
				 pst.execute();
				 pst.close();
			}					
			return ur;
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
	public List<Beanpurchase> searchPur(String stat){
		List<Beanpurchase> purchase = new ArrayList<Beanpurchase>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from purchase_list where chase_stat = ?";		
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, stat);
			java.sql.ResultSet rs=pst.executeQuery();			
			while(rs.next()) {
				Beanpurchase pur = new Beanpurchase();
				pur.setChase_number(rs.getInt(2));
				pur.setTrade_number(rs.getInt(3));
				pur.setEmp_number(rs.getInt(4));
				pur.setPurchase_amount(rs.getInt(5));
				pur.setChase_stat(rs.getString(6));
				purchase.add(pur);
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
		return purchase;
	}
}
