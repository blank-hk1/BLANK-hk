package hk.freshnetwork.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hk.freshnetwork.itf.IcomManager;
import hk.freshnetwork.itf.IfreshManager;
import hk.freshnetwork.model.Beancommodity_information;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.util.BaseException;
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
                fr.setCategory_number(rs.getInt(4));
                fr.setTrade_name(rs.getString(5));
                fr.setPrice(rs.getFloat(6));
                fr.setMember_price(rs.getFloat(7));
                fr.setNumber(rs.getInt(8));
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
