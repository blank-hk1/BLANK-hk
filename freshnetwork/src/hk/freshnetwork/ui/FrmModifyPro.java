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
import hk.freshnetwork.util.BaseException;

public class FrmModifyPro extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("修改限时促销");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelNameNow = new JLabel("当前限时促销编号"+FrmFreshTimepro.time_pros.getPro_number());
	private JLabel labelTrade = new JLabel("商品编号:");
	private JLabel labelAppmoney = new JLabel("促销价格：");
	private JLabel labelnumber = new JLabel("促销数量：");
	private JLabel labelStart = new JLabel("开始日期：");
	private JLabel labelload = new JLabel("结束日期：");
	private JTextField edtTrade= new JTextField(20);
	private JTextField edtAppmoney = new JTextField(20);
	private JTextField edtDedmoney = new JTextField(20);
	private JTextField edtStart = new JTextField(20);
	private JTextField edtload = new JTextField(20);
	
	public FrmModifyPro(FrmFreshTimepro frmFreshTimepro, String s, boolean b) {
		super(frmFreshTimepro, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);	
		workPane.add(labelNameNow);
		workPane.add(labelTrade);
		workPane.add(edtTrade);
		workPane.add(labelAppmoney);
		workPane.add(edtAppmoney);
		workPane.add(labelnumber);
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
			Float App_money;
			int Trade,number;
			if(this.edtTrade.getText().equals("")) {
				Trade=FrmFreshTimepro.time_pros.getTrade_number();
			}
			else {
				Trade=Integer.parseInt(this.edtTrade.getText());
			}
			if(this.edtAppmoney.getText().equals("")) {
				App_money=FrmFreshTimepro.time_pros.getPro_price();
			}
			else {
				App_money=Float.parseFloat(this.edtAppmoney.getText());
			}
			if(this.edtDedmoney.getText().equals("")) {
				number=FrmFreshTimepro.time_pros.getProm_number();
			}
			else {
				number=Integer.parseInt(this.edtDedmoney.getText());
			}
			String Start = this.edtStart.getText();	
			String end = this.edtload.getText();	
			ExamplecoupouManager sum = new ExamplecoupouManager();
			if(Start.equals("")) {
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				Start=df.format(FrmFreshTimepro.time_pros.getProStart_date());
			}
			if(end.equals("")) {
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				end=df.format(FrmFreshTimepro.time_pros.getProEnd_date());
			}
				try {
					FreshNetUtil.couponManager.modifyPro(FrmFreshTimepro.time_pros.getPro_number(), Trade, App_money,number, Start, end);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}; 
				JOptionPane.showMessageDialog(null, "修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
			
		}
	}
}
