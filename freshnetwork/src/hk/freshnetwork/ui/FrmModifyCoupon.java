package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.control.ExamplecoupouManager;
import hk.freshnetwork.control.ExamplefreshManager;
import hk.freshnetwork.util.BaseException;

public class FrmModifyCoupon extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("修改优惠券");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelNameNow = new JLabel("当前优惠券编号"+FrmFreshCoupon.coupons.getCou_number()+"                       ");
	private JLabel labelcontent = new JLabel("优惠券内容:");
	private JLabel labelAppmoney = new JLabel("优惠券使用金额：");
	private JLabel labelDedmoney = new JLabel("减免金额：");
	private JLabel labelStart = new JLabel("开始日期：");
	private JLabel labelload = new JLabel("持续时间(以月为单位)：");
	private JTextField edtcontent= new JTextField(20);
	private JTextField edtAppmoney = new JTextField(20);
	private JTextField edtDedmoney = new JTextField(20);
	private JTextField edtStart = new JTextField(20);
	private JTextField edtload = new JTextField(20);
	
	public FrmModifyCoupon(FrmFreshCoupon frmFreshCoupon, String s, boolean b) {
		super(frmFreshCoupon, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);	
		workPane.add(labelNameNow);
		workPane.add(labelcontent);
		workPane.add(edtcontent);
		workPane.add(labelAppmoney);
		workPane.add(edtAppmoney);
		workPane.add(labelDedmoney);
		workPane.add(edtDedmoney);
		workPane.add(labelStart);
		workPane.add(edtStart);
		workPane.add(labelload);
		workPane.add(edtload);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(260, 400);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);	
	}
	@Override
	public void actionPerformed(ActionEvent e){		
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String content=this.edtcontent.getText();
			Float App_money,Ded_money;
			int month;
			if(this.edtAppmoney.getText().equals("")) {
				App_money=FrmFreshCoupon.coupons.getApp_money();
			}
			else {
				App_money=Float.parseFloat(this.edtAppmoney.getText());
			}
			if(this.edtDedmoney.getText().equals("")) {
				Ded_money=FrmFreshCoupon.coupons.getDed_money();
			}
			else {
				Ded_money=Float.parseFloat(this.edtAppmoney.getText());
			}
			if(this.edtload.getText().equals("")) {
				month=0;
			}
			else {
				month = Integer.parseInt(this.edtload.getText());
			}
			String Start = this.edtStart.getText();			
			ExamplecoupouManager sum = new ExamplecoupouManager();
			if (content.equals("")) {
				content=FrmFreshCoupon.coupons.getContent();
			}
			if(Start.equals("")) {
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				Start=df.format(FrmFreshCoupon.coupons.getStart_date());
			}
				try {
					FreshNetUtil.couponManager.modifyCou(FrmFreshCoupon.coupons.getCou_number(), content, App_money, Ded_money, Start, month);
					JOptionPane.showMessageDialog(null, "修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "要修改的商品编号不存在!","成功",JOptionPane.INFORMATION_MESSAGE);
				}; 
				
				this.setVisible(false);
			
		}
	}
}
