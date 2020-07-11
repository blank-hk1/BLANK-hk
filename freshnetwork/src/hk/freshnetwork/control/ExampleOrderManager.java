package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import hk.freshnetwork.itf.IOrderManager;
import hk.freshnetwork.model.BeanShopping;
import hk.freshnetwork.model.Beanaddlist;
import hk.freshnetwork.model.Beancoupon;
import hk.freshnetwork.model.Beangoods_eva;
import hk.freshnetwork.model.Beanorder_details;
import hk.freshnetwork.model.Beanorder_form;
import hk.freshnetwork.model.Beantime_pro;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;
import hk.freshnetwork.util.DBUtil;
import hk.freshnetwork.util.DbException;

public class ExampleOrderManager implements IOrderManager{
	public List<BeanShopping> loadOrd(int usernum)throws BaseException{
		List<BeanShopping> Cou = new ArrayList<BeanShopping>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from shopping where user_number = ?";		
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, usernum);
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
            			sql="insert into shopping(Com_Trade_number,Ful_Full_number,pur_number,Discount,set_money,user_number) values (?,?,?,?,?,?)";
                        pst=conn.prepareStatement(sql);
                        pst.setInt(1, Trade_number);
                        pst.setInt(2, t);
                        pst.setInt(3, number);
                        pst.setFloat(4, dis);
                        pst.setFloat(5, number*money*dis);
                        pst.setInt(6, Beanuser_table.currentLoginUser.getUser_num());
                        pst.execute();
                        pst.close();
            		}
            		else {
            			sql="insert into shopping(Com_Trade_number,Ful_Full_number,pur_number,Discount,set_money,user_number) values (?,?,?,?,?,?)";
                        pst=conn.prepareStatement(sql);
                        pst.setInt(1, Trade_number);
                        pst.setInt(2, t);
                        pst.setInt(3, number);
                        pst.setFloat(4, dis);
                        pst.setFloat(5, number*money);
                        pst.setInt(6, Beanuser_table.currentLoginUser.getUser_num());
                        pst.execute();
                        pst.close();
            		}
            	}
            	else {           		
            		sql="insert into shopping(Com_Trade_number,Ful_Full_number,pur_number,Discount,set_money,user_number) values (?,?,?,?,?,?)";
                    pst=conn.prepareStatement(sql);
                    pst.setInt(1, Trade_number);
                    pst.setInt(2, t);
                    pst.setInt(3, number);
                    pst.setFloat(4, dis);
                    pst.setFloat(5, number*money);
                    pst.setInt(6, Beanuser_table.currentLoginUser.getUser_num());
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
	public void deleteAllShop(int username) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "delete from shopping where user_number = ?";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,username);
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
	public Beanorder_form CreatOrder(int User_num,int add_number,int Cou_number,float ori_money,float set_money) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			int ordid=1;
			Calendar c= Calendar.getInstance();
			Date time = new Date(System.currentTimeMillis());
			c.setTime(time);
			c.add(Calendar.HOUR, 1);
			java.util.Date date = c.getTime();
			String sql = "select max(ord_number) from order_form where User_num = ?";	
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,User_num);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				ordid=rs.getInt(1)+1;
			}
			rs.close();
			pst.close();
			sql="insert into order_form(ord_number,User_num,add_number,Cou_number,ori_money,set_money,ari_time,ord_state) values (?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, ordid);
			pst.setInt(2, User_num);
			pst.setInt(3, add_number);
			pst.setInt(4, Cou_number);
			pst.setFloat(5, ori_money);
			pst.setFloat(6, set_money);
			pst.setTimestamp(7, new java.sql.Timestamp(date.getTime()));
			pst.setString(8, "下单");
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
		return null;
	}
	public void CreatDetails(int usernum) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			int ordid=1;
			String sql = "select max(ord_number) from order_form where User_num = ?";	
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,usernum);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				ordid=rs.getInt(1);
			}
			rs.close();
			pst.close();
			sql = "select * from shopping where user_number = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,usernum);
			rs=pst.executeQuery();
			while(rs.next()) {
				String sql1 = "insert into order_details(ord_ord_number,Com_Trade_number,Ful_Full_number,pur_number,Discount,set_money) values (?,?,?,?,?,?)";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, ordid);
				pst1.setInt(2, rs.getInt(1));
				pst1.setInt(3, rs.getInt(2));
				pst1.setInt(4, rs.getInt(3));
				pst1.setFloat(5, rs.getFloat(4));
				pst1.setFloat(6, rs.getFloat(5));
				pst1.execute();
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
	}
	public void updateCom(int username) throws BusinessException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			int Trade_number=1,pur_number=0;
			String sql = "select Com_Trade_number,pur_number from shopping where user_number = ?";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,username);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Trade_number = rs.getInt(1);
				pur_number = rs.getInt(2);
				String sql1 = "select number from commodity_information where Trade_number = ?";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, Trade_number);
				java.sql.ResultSet rs1=pst1.executeQuery();
				if(rs1.next()) {
					if(pur_number>rs1.getInt(1)) {
						throw new BusinessException("购买数量超过商品库存!");
					}
				}
				rs1.close();
				pst1.close();				
			}
			rs.close();
			pst.close();
			sql = "select Com_Trade_number,pur_number from shopping where user_number = ?";			
			pst=conn.prepareStatement(sql);
			pst.setInt(1,username);
			rs=pst.executeQuery();
			while(rs.next()) {
				Trade_number = rs.getInt(1);
				pur_number = rs.getInt(2);
				System.out.println(pur_number);
				String sql1 = "update commodity_information set number = number-? where Trade_number = ?";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, pur_number);
				pst1.setInt(2, Trade_number);
				pst1.execute();
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
	}
	public List<Beanorder_details> loadRecords(int Usernum)throws BaseException{
		List<Beanorder_details> Cou = new ArrayList<Beanorder_details>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from order_form where User_num = ?";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Usernum);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beanorder_details cou = new Beanorder_details();
				String sql1 = "select * from order_details where ord_ord_number = ?";
				java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
				pst1.setInt(1, rs.getInt(2));
				java.sql.ResultSet rs1=pst1.executeQuery();
				while(rs1.next()) {
					cou.setOrd_ord_number(rs1.getInt(1));
					cou.setCom_Trade_number(rs1.getInt(2));
					cou.setFul_Full_number(rs1.getInt(3));
					cou.setPur_number(rs1.getInt(4));
					cou.setDiscount(rs1.getFloat(5));
					cou.setSet_money(rs1.getFloat(6));
					Cou.add(cou);
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
		return Cou;		
	}
	public Beanaddlist ReadAddName(int add_number) throws BaseException{
		Beanaddlist an = new Beanaddlist();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from add_list where add_number = ?";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, add_number);
			java.sql.ResultSet rs=pst.executeQuery();					
			if(rs.next()) {
                 an.setAddress(rs.getString(6));
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
		return an;
	}
	public Beancoupon ReadCouName(int Cou_number) throws BaseException{
		Beancoupon an = new Beancoupon();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from coupon where Cou_number = ?";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Cou_number);
			java.sql.ResultSet rs=pst.executeQuery();					
			if(rs.next()) {
                 an.setContent(rs.getString(2));
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
		return an;
	}
	public Beangoods_eva Addeva(int Usernum,int Tradenum,String content,int star) throws BaseException{
		Beangoods_eva eva =new Beangoods_eva();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
			String sql = "insert into goods_eva(Use_User_num,Com_Trade_number,Eva_content,Eva_date,Star) values (?,?,?,?,?)";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Usernum);
			pst.setInt(2, Tradenum);
			pst.setString(3, content);
			pst.setInt(5, star);
			pst.setTimestamp(4, time);
			pst.execute();
			pst.close();
			eva.setUse_User_num(Usernum);
			eva.setCom_Trade_number(Tradenum);
			eva.setEva_content(content);
			eva.setStar(star);
			eva.setEva_date(time);
			
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
		return eva;
	}
	public List<Beangoods_eva> Showeva(int Tradenum) throws BaseException{
		List<Beangoods_eva> eva =new ArrayList<Beangoods_eva>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
			String sql = "select * from goods_eva where Com_Trade_number = ?";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Tradenum);       
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				Beangoods_eva good = new Beangoods_eva();
				good.setUse_User_num(rs.getInt(1));
				good.setEva_content(rs.getString(3));
				good.setEva_date(rs.getTimestamp(4));
				good.setStar(rs.getInt(5));
				eva.add(good);
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
		return eva;
	}
}
