package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hk.freshnetwork.itf.IOrderManager;
import hk.freshnetwork.model.BeanShopping;
import hk.freshnetwork.model.Beancoupon;
import hk.freshnetwork.model.Beanorder_details;
import hk.freshnetwork.model.Beanorder_form;
import hk.freshnetwork.model.Beantime_pro;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;
import hk.freshnetwork.util.DBUtil;
import hk.freshnetwork.util.DbException;

public class ExampleOrderManager implements IOrderManager{
	public List<BeanShopping> loadOrd()throws BaseException{
		List<BeanShopping> Cou = new ArrayList<BeanShopping>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from shopping";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				BeanShopping cou = new BeanShopping();
				cou.setFul_Full_number(rs.getInt(2));
				cou.setCom_Trade_number(rs.getInt(1));
				cou.setPur_number(rs.getInt(3));
				cou.setDiscount(rs.getFloat(4));
				cou.setSet_money(rs.getFloat(5));
				Cou.add(cou);
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
		return Cou;		
	}
	public void RegOrdDetails(int Trade_number,int number,float money) throws BaseException{
		Connection conn=null;
		try {
			int t = 0,synumber=0;float dis= 0;
			conn=DBUtil.getConnection();
			String sql = "select * from relation where Com_Trade_number = ?";	
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);					
            pst.setInt(1,Trade_number);
            java.sql.ResultSet rs=pst.executeQuery();
            if(rs.next()) {
            	t= rs.getInt(1);
            }
            else {
            	t=0;
            }
            rs.close();
            pst.close();
            if(t!=0) {
            	sql = "select * from full_sheet where Full_number = ?";
                pst=conn.prepareStatement(sql);
                pst.setInt(1, t);
                rs=pst.executeQuery();
                if(rs.next()) {
                	dis=rs.getFloat(4);
                	synumber=rs.getInt(3);
                }
                rs.close();
                pst.close();
            }
            else {
            	dis=(float) 1.0;
            }
            int flag=0,num = 0;
            float mon = 0;        
            sql="select * from shopping where Com_Trade_number = ?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1, Trade_number);
			rs=pst.executeQuery();
            if(rs.next()) {
            	flag=1;
            	mon = rs.getInt(5);
            	num=rs.getInt(3);
            }
            rs.close();
            pst.close();
            if(flag==0) {
            	if(t!=0) {
            		if(number>synumber) {
            			sql="insert into shopping(Com_Trade_number,Ful_Full_number,pur_number,Discount,set_money) values (?,?,?,?,?)";
                        pst=conn.prepareStatement(sql);
                        pst.setInt(1, Trade_number);
                        pst.setInt(2, t);
                        pst.setInt(3, number);
                        pst.setFloat(4, dis);
                        pst.setFloat(5, number*money*dis);
                        pst.execute();
                        pst.close();
            		}
            		else {
            			sql="insert into shopping(Com_Trade_number,Ful_Full_number,pur_number,Discount,set_money) values (?,?,?,?,?)";
                        pst=conn.prepareStatement(sql);
                        pst.setInt(1, Trade_number);
                        pst.setInt(2, t);
                        pst.setInt(3, number);
                        pst.setFloat(4, dis);
                        pst.setFloat(5, number*money);
                        pst.execute();
                        pst.close();
            		}
            	}
            	else {           		
            		sql="insert into shopping(Com_Trade_number,Ful_Full_number,pur_number,Discount,set_money) values (?,?,?,?,?)";
                    pst=conn.prepareStatement(sql);
                    pst.setInt(1, Trade_number);
                    pst.setInt(2, t);
                    pst.setInt(3, number);
                    pst.setFloat(4, dis);
                    pst.setFloat(5, number*money);
                    pst.execute();
                    pst.close();
            	}
            }
            else {
            	if(t!=0) {
            		if(number>synumber) {
            			sql = "update shopping set pur_number = ?,set_money = ? where Com_Trade_number = ?";
                    	pst=conn.prepareStatement(sql);
                    	pst.setInt(1, number+num);
                    	pst.setFloat(2, mon+number*money*dis);
                    	pst.setInt(3, Trade_number);
                    	pst.execute();
                    	pst.close();
            		}
            		else {
            			sql = "update shopping set pur_number = ?,set_money = ? where Com_Trade_number = ?";
                    	pst=conn.prepareStatement(sql);
                    	pst.setInt(1, number+num);
                    	pst.setFloat(2, mon+number*money);
                    	pst.setInt(3, Trade_number);
                    	pst.execute();
                    	pst.close();
            		}
            	}
            	else {
            		sql = "update shopping set pur_number = ?,set_money = ? where Com_Trade_number = ?";
                	pst=conn.prepareStatement(sql);
                	pst.setInt(1, number+num);
                	pst.setFloat(2, mon+number*money);
                	pst.setInt(3, Trade_number);
                	pst.execute();
                	pst.close();
            	}
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
	public float sumPrice()throws BaseException{
		float price = 0;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from shopping";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();				
			while(rs.next()) {
				price = price + rs.getInt(5);
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
		return price;
	}
	public Beantime_pro searchPro(int Trade_number) throws BaseException{
		Beantime_pro xs = new Beantime_pro();	
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from time_pro where Trade_number = ?";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Trade_number);
			java.sql.ResultSet rs=pst.executeQuery();
			System.out.println(Trade_number);
			if(rs.next()) {
				xs.setPro_number(rs.getInt(1));
				xs.setPro_price(rs.getFloat(3));
				xs.setProm_number(rs.getInt(4));
				xs.setProStart_date(rs.getTimestamp(5));
				xs.setProEnd_date(rs.getTimestamp(6));
			}
			else {
				xs=null;
			}
			rs.close();
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
		return xs;
	}
	public void deleteShop(int Trade_number) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			System.out.println(Trade_number);
			String sql = "delete from shopping where Com_Trade_number = ?";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Trade_number);
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
