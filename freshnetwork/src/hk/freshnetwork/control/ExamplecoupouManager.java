package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hk.freshnetwork.itf.IcoupouManager;
import hk.freshnetwork.model.Beancoupon;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.model.Beanfull_sheet;
import hk.freshnetwork.model.Beanrelation;
import hk.freshnetwork.model.Beantime_pro;
import hk.freshnetwork.ui.FrmFreshcat;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;
import hk.freshnetwork.util.DBUtil;
import hk.freshnetwork.util.DbException;

public class ExamplecoupouManager implements IcoupouManager{
	public List<Beancoupon> loadCou()throws BaseException{
		List<Beancoupon> Cou = new ArrayList<Beancoupon>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from coupon";
			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beancoupon cou = new Beancoupon();
				cou.setCou_number(rs.getInt(1));
				cou.setContent(rs.getString(2));
				cou.setApp_money(rs.getFloat(3));
				cou.setDed_money(rs.getFloat(4));
				cou.setStart_date(rs.getTimestamp(5));
				cou.setEnd_date(rs.getTimestamp(6));
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
	public List<Beancoupon> SearchCou(float App_money)throws BaseException{
		List<Beancoupon> Cou = new ArrayList<Beancoupon>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from coupon where App_money = ?";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setFloat(1, App_money);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beancoupon cou = new Beancoupon();
				cou.setCou_number(rs.getInt(1));
				cou.setContent(rs.getString(2));
				cou.setApp_money(rs.getFloat(3));
				cou.setDed_money(rs.getFloat(4));
				cou.setStart_date(rs.getTimestamp(5));
				cou.setEnd_date(rs.getTimestamp(6));
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
	public Beancoupon RegCou(String content,float App_money,float Ded_money,String Start,int month) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date Start_begin = null;
			try {
				Start_begin = new java.sql.Date(df.parse(Start).getTime());
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			Calendar c= Calendar.getInstance();
			Date time = Start_begin;
			c.setTime(time);
			c.add(Calendar.MONTH, month);
			java.util.Date date = c.getTime();
			String sql="insert into coupon(content,App_money,Ded_money,Start_date,End_date) values(?,?,?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, content);
			pst.setFloat(2, App_money);
			pst.setFloat(3, Ded_money);
			pst.setTimestamp(4, new java.sql.Timestamp(Start_begin.getTime()));
			pst.setTimestamp(5, new java.sql.Timestamp(date.getTime()));
			Beancoupon cou = new Beancoupon();
			cou.setContent(content);
			cou.setApp_money(App_money);
			cou.setDed_money(Ded_money);
			cou.setStart_date(new java.sql.Timestamp(System.currentTimeMillis()));
			cou.setEnd_date(new java.sql.Timestamp(date.getTime()));
			pst.execute();
			pst.close();
			
			return cou;
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
	public void modifyCou(int Cou_number,String content,float App_money,float Ded_money,String Start,int month) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date Start_begin = null;
			try {
				Start_begin = new java.sql.Date(df.parse(Start).getTime());
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			Calendar c= Calendar.getInstance();
			Date time = Start_begin;
			c.setTime(time);
			c.add(Calendar.MONTH, month);
			java.util.Date date = c.getTime();
			String sql = "select * from coupon where Cou_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Cou_number);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("要修改的优惠券不存在");
			}
			rs.close();
			pst.close();
			if(month!=0) {
				sql="update coupon set content= ?,App_money= ?,Ded_money= ? , Start_date= ? ,End_date = ? where Cou_number=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, content);
				pst.setFloat(2, App_money);
				pst.setFloat(3,Ded_money);
				pst.setTimestamp(4, new java.sql.Timestamp(Start_begin.getTime()));
				pst.setTimestamp(5, new java.sql.Timestamp(date.getTime()));
				pst.setInt(6, Cou_number);
				pst.execute();
				pst.close();
			}
			else {
				sql="update coupon set content= ?,App_money= ?,Ded_money= ? , Start_date= ? where Cou_number=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, content);
				pst.setFloat(2, App_money);
				pst.setFloat(3,Ded_money);
				pst.setTimestamp(4, new java.sql.Timestamp(Start_begin.getTime()));
				pst.setInt(6, Cou_number);
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
	public void deleteCou(int Cou_number) throws BusinessException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from order_form where Cou_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Cou_number);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				throw new BusinessException("订单已使用优惠券无法删除!");
			}
			sql="delete from coupon where Cou_number = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, Cou_number);
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
	public List<Beantime_pro> loadTime()throws BaseException{
		List<Beantime_pro> Time = new ArrayList<Beantime_pro>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from time_pro";
			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beantime_pro pro = new Beantime_pro();
				pro.setPro_number(rs.getInt(1));
				pro.setTrade_number(rs.getInt(2));
				pro.setPro_price(rs.getFloat(3));
				pro.setProm_number(rs.getInt(4));
				pro.setProStart_date(rs.getTimestamp(5));
				pro.setProEnd_date(rs.getTimestamp(6));
				Time.add(pro);
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
		return Time;		
	}
	public List<Beantime_pro> loadTimeSearch(int Pronumber)throws BaseException{
		List<Beantime_pro> Time = new ArrayList<Beantime_pro>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from time_pro where Pro_number = ?";			
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Pronumber);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beantime_pro pro = new Beantime_pro();
				pro.setPro_number(rs.getInt(1));
				pro.setTrade_number(rs.getInt(2));
				pro.setPro_price(rs.getFloat(3));
				pro.setProm_number(rs.getInt(4));
				pro.setProStart_date(rs.getTimestamp(5));
				pro.setProEnd_date(rs.getTimestamp(6));
				Time.add(pro);
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
		return Time;		
	}
	public Beantime_pro RegPro(int Trade_number,float Pro_price,int Prom_number,String ProStart_date,String ProEnd_date) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date Start_begin = null;
			java.sql.Date End_pro = null;
			int t=0;
			try {
				Start_begin = new java.sql.Date(df.parse(ProStart_date).getTime());
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			try {
				End_pro = new java.sql.Date(df.parse(ProEnd_date).getTime());
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			String sql = "select * from commodity_information where Trade_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Trade_number);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("该商品编号不存在!");
			}
			rs.close();
			pst.close();
			sql="insert into time_pro(Trade_number,Pro_price,Prom_number,ProStart_date,ProEnd_date) values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, Trade_number);
			pst.setFloat(2, Pro_price);
			pst.setInt(3, Prom_number);
			pst.setTimestamp(4, new java.sql.Timestamp(Start_begin.getTime()));
			pst.setTimestamp(5, new java.sql.Timestamp(End_pro.getTime()));
			Beantime_pro cou = new Beantime_pro();
			cou.setTrade_number(Trade_number);
			cou.setPro_price(Pro_price);
			cou.setProm_number(Prom_number);
			cou.setProStart_date(new java.sql.Timestamp(Start_begin.getTime()));
			cou.setProEnd_date(new java.sql.Timestamp(End_pro.getTime()));
			pst.execute();
			pst.close();
			sql = "select Pro_number from time_pro where Trade_number = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Trade_number);
			rs=pst.executeQuery();
			if(rs.next()) {
				t= rs.getInt(1);
			}
			sql = "update commodity_information set Pro_number = ? where Trade_number = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, t);
			pst.setInt(2, Trade_number);
			pst.execute();
			return cou;
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
	public void modifyPro(int Pro_number,int Trade_number,float Pro_price,int Prom_number,String ProStart_date,String ProEnd_date) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date Start_begin = null;
			java.sql.Date End_pro = null;
			try {
				Start_begin = new java.sql.Date(df.parse(ProStart_date).getTime());
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			try {
				End_pro = new java.sql.Date(df.parse(ProEnd_date).getTime());
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			String sql = "select * from commodity_information where Trade_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Trade_number);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("该商品编号不存在!");
			}
			rs.close();
			pst.close();
			sql = "select * from time_pro where Pro_number = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Pro_number);
			rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("要修改的限时促销不存在");
			}
			rs.close();
			pst.close();
				sql="update time_pro set Trade_number= ?,Pro_price = ?,Prom_number = ?,ProStart_date = ?,ProEnd_date =?  where Pro_number=?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, Trade_number);
				pst.setFloat(2, Pro_price);
				pst.setInt(3,Prom_number);
				pst.setTimestamp(4, new java.sql.Timestamp(Start_begin.getTime()));
				pst.setTimestamp(5, new java.sql.Timestamp(End_pro.getTime()));
				pst.setInt(6, Pro_number);
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
	public void deletePro(int Pro_number) throws BusinessException{
		Connection conn=null;
		try {
			String str = null;
			conn=DBUtil.getConnection();
			String sql="select * from commodity_information where Pro_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Pro_number);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				throw new BusinessException("订单已使用优惠券无法删除!");
			}
			sql="delete from time_pro where Pro_number = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, Pro_number);
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
	public List<Beanfull_sheet> loadFull()throws BaseException{
		List<Beanfull_sheet> Ful = new ArrayList<Beanfull_sheet>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from full_sheet";		
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beanfull_sheet ful = new Beanfull_sheet();
				ful.setFull_number(rs.getInt(1));
				ful.setFull_content(rs.getString(2));
				ful.setApp_number(rs.getInt(3));
				ful.setDiscount(rs.getFloat(4));
				ful.setFulStart_date(rs.getTimestamp(5));
				ful.setFulEnd_date(rs.getTimestamp(6));
				Ful.add(ful);
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
		return Ful;		
	}
	public List<Beanfull_sheet> loadFullSearch(int Full_number)throws BaseException{
		List<Beanfull_sheet> Ful = new ArrayList<Beanfull_sheet>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from full_sheet where Full_number = ?";		
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Full_number);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beanfull_sheet ful = new Beanfull_sheet();
				ful.setFull_number(rs.getInt(1));
				ful.setFull_content(rs.getString(2));
				ful.setApp_number(rs.getInt(3));
				ful.setDiscount(rs.getFloat(4));
				ful.setFulStart_date(rs.getTimestamp(5));
				ful.setFulEnd_date(rs.getTimestamp(6));
				Ful.add(ful);
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
		return Ful;		
	}
	public Beanfull_sheet RegFul(String Full_content,int App_number,Float Discount,String FulStart_date,String FulEnd_date) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date Start_begin = null;
			java.sql.Date End_pro = null;
			try {
				Start_begin = new java.sql.Date(df.parse(FulStart_date).getTime());
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			try {
				End_pro = new java.sql.Date(df.parse(FulEnd_date).getTime());
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			String sql="insert into full_sheet(Full_content,App_number,Discount,FulStart_date,FulEnd_date) values(?,?,?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, Full_content);
			pst.setInt(2, App_number);
			pst.setFloat(3, Discount);
			pst.setTimestamp(4, new java.sql.Timestamp(Start_begin.getTime()));
			pst.setTimestamp(5, new java.sql.Timestamp(End_pro.getTime()));
			Beanfull_sheet cou = new Beanfull_sheet();
			cou.setApp_number(App_number);
			cou.setDiscount(Discount);
			cou.setFull_content(Full_content);
			cou.setFulStart_date(new java.sql.Timestamp(Start_begin.getTime()));
			cou.setFulEnd_date(new java.sql.Timestamp(End_pro.getTime()));
			pst.execute();
			pst.close();
			
			return cou;
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
	public void modifyFul(int Full_number,String Full_content,int App_number,Float Discount,String FulStart_date,String FulEnd_date) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date Start_begin = null;
			java.sql.Date End_pro = null;
			try {
				Start_begin = new java.sql.Date(df.parse(FulStart_date).getTime());
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			try {
				End_pro = new java.sql.Date(df.parse(FulEnd_date).getTime());
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			String sql = "select * from full_sheet where Full_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Full_number);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("要修改的满折信息不存在");
			}
			rs.close();
			pst.close();
				sql="update full_sheet set Full_content= ?,App_number = ?,Discount = ?,FulStart_date = ?,FulEnd_date =?  where Full_number =?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, Full_content);
				pst.setInt(2, App_number);
				pst.setFloat(3,Discount);
				pst.setTimestamp(4, new java.sql.Timestamp(Start_begin.getTime()));
				pst.setTimestamp(5, new java.sql.Timestamp(End_pro.getTime()));
				pst.setInt(6, Full_number);
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
	public void deleteFul(int Full_number) throws BusinessException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from relation where Ful_Full_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Full_number);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				throw new BusinessException("该满折已与商品连接无法删除!");
			}
			sql="delete from full_sheet where Full_number = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, Full_number);
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
	public List<Beanrelation> loadDisCom()throws BaseException{
		List<Beanrelation> Ful = new ArrayList<Beanrelation>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from relation";		
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beanrelation dis = new Beanrelation();
				dis.setFul_Full_number(rs.getInt(1));
				dis.setCom_Trade_number(rs.getInt(2));
				dis.setFulStart_date(rs.getTimestamp(3));
				dis.setFulEnd_date(rs.getTimestamp(4));
				Ful.add(dis);
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
		return Ful;		
	}
	public List<Beanrelation> loadDisComSearch(int Full_number)throws BaseException{
		List<Beanrelation> Ful = new ArrayList<Beanrelation>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from relation where Ful_Full_number = ?";		
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Full_number);
			java.sql.ResultSet rs=pst.executeQuery();					
			while(rs.next()) {
				Beanrelation dis = new Beanrelation();
				dis.setFul_Full_number(rs.getInt(1));
				dis.setCom_Trade_number(rs.getInt(2));
				dis.setFulStart_date(rs.getTimestamp(3));
				dis.setFulEnd_date(rs.getTimestamp(4));
				Ful.add(dis);
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
		return Ful;		
	}
	public Beanrelation RegFulCom(int Ful_number,int Trade_number,String FulStart_date,String FulEnd_date) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			if(Ful_number==0||Trade_number==0) {
				throw new BusinessException("输入不能为空!");
			}
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date Start_begin = null;
			java.sql.Date End_pro = null;
			try {
				Start_begin = new java.sql.Date(df.parse(FulStart_date).getTime());
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			try {
				End_pro = new java.sql.Date(df.parse(FulEnd_date).getTime());
			}catch(ParseException e1){
				e1.printStackTrace();
			}
			String sql = "select * from full_sheet where Full_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Ful_number);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("要添加的满折编号不存在!");
			}
			rs.close();
			pst.close();
			sql = "select * from commodity_information where Trade_number = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,Trade_number);
			rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("要添加的商品编号不存在!");
			}
			rs.close();
			pst.close();
			sql="insert into relation(Ful_Full_number,Com_Trade_number,FulStart_date,FulEnd_date) values(?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, Ful_number);
			pst.setInt(2, Trade_number);
			pst.setTimestamp(3, new java.sql.Timestamp(Start_begin.getTime()));
			pst.setTimestamp(4, new java.sql.Timestamp(End_pro.getTime()));
			Beanrelation cou = new Beanrelation();
			cou.setCom_Trade_number(Trade_number);
			cou.setFulStart_date(new java.sql.Timestamp(Start_begin.getTime()));
			cou.setFulEnd_date(new java.sql.Timestamp(End_pro.getTime()));
			pst.execute();
			pst.close();
			
			return cou;
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
	public void modifyFulCom(int Full_number,int Trade_number) throws BaseException{
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from commodity_information where Trade_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,Trade_number);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("不存在该商品");
			}
			rs.close();
			pst.close();
				sql="update relation set Com_Trade_number = ? where Ful_Full_number =?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, Trade_number);
				pst.setInt(2, Full_number);
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
	public void deleteFulCom(int Full_number,int Trade_number){
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from relation where Ful_Full_number = ? and Com_Trade_number = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Full_number);
			pst.setInt(2, Trade_number);
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
