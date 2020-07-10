package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.control.ExampleOrderManager;
import hk.freshnetwork.util.BaseException;

public class FrmShopping extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("添加至购物车");
	private JButton btnCancel = new JButton("取消");	
	private JLabel address = new JLabel("配送地址为:                  ");
	private JLabel  labelcoupon= new JLabel("                  优惠券为:                    ");
	private JButton psaddress = new JButton("选择配送地址"+"");	
	private JButton xzcoupon = new JButton("选择优惠券");
	float t= FreshNetUtil.orderManager.sumPrice();
	private JLabel labeldis = new JLabel("总价格:                             "+t);
	private JLabel labelprice = new JLabel();
	
	public FrmShopping(FrmMain frmMain, String s, boolean b) throws BaseException {	
		super(frmMain, s, b);
		
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		toolBar.add(xzcoupon);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labeldis);
		workPane.add(address);
		workPane.add(psaddress);
		workPane.add(labelcoupon);
		workPane.add(xzcoupon);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(260, 400);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			ExampleOrderManager sum = new ExampleOrderManager();
			
			
		}
		else if(e.getSource()==this.psaddress) {
			
		}
		else if(e.getSource()==this.xzcoupon) {
			
			
		}
	}
}
