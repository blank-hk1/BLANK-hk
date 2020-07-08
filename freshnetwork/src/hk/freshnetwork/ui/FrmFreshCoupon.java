package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.model.Beancoupon;
import hk.freshnetwork.util.BaseException;

public class FrmFreshCoupon extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JButton btnAdd = new JButton("添加优惠券");
	private JButton btnModify = new JButton("修改优惠券信息");
	private JButton btnStop = new JButton("删除优惠券");
	private JTextField edtKeyword = new JTextField(10);
	private JButton btnSearch = new JButton("按适用金额查询");
	private Object tblTitle[]=Beancoupon.tableTitles;
	private Object tblData[][];
	public List<Beancoupon> coupon=null;
	public static Beancoupon coupons = null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			coupon=FreshNetUtil.couponManager.loadCou();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[coupon.size()][Beancoupon.tableTitles.length];
		for(int i=0;i<coupon.size();i++){
			for(int j=0;j<Beancoupon.tableTitles.length;j++)
				tblData[i][j]=coupon.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	private void searchreloadTable(){
		try {
			if (this.edtKeyword.getText().equals("")) {
				coupon = FreshNetUtil.couponManager.loadCou();
			}
			else {
				coupon = FreshNetUtil.couponManager.SearchCou(Float.parseFloat(this.edtKeyword.getText()));
			}			
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[coupon.size()][Beancoupon.tableTitles.length];
		for(int i=0;i<coupon.size();i++){
			for(int j=0;j<Beancoupon.tableTitles.length;j++)
				tblData[i][j]=coupon.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	
	public FrmFreshCoupon(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(btnStop);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);

		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnStop.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnAdd){
			FrmAddCoupon dlg=new FrmAddCoupon(this,"添加优惠券",true);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if(e.getSource()==this.btnModify){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择优惠券","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
		    coupons=this.coupon.get(i);
		    
			FrmModifyCoupon dlg=new FrmModifyCoupon(this,"修改优惠券",true);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if(e.getSource()==this.btnStop){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择优惠券","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			coupons=this.coupon.get(i);
			FreshNetUtil.couponManager.deleteCou(coupons.getCou_number());
			JOptionPane.showMessageDialog(null,  "删除优惠券成功","提示",JOptionPane.INFORMATION_MESSAGE);
			this.reloadTable();
		}
		else if(e.getSource()==this.btnSearch){
			this.searchreloadTable();
		}
		
	}
}
