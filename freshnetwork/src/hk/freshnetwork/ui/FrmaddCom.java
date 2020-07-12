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

import hk.freshnetwork.control.ExampleComMananger;
import hk.freshnetwork.control.ExamplefreshManager;
import hk.freshnetwork.model.Beancommodity_information;
import hk.freshnetwork.model.Beanmenu_info;
import hk.freshnetwork.util.BaseException;

public class FrmaddCom extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("添加商品名称");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelcomname = new JLabel("商品名称:");
	private JLabel labelcomnumber = new JLabel("类别编号：");
	private JLabel labelcomprice = new JLabel("商品单价:");
	private JLabel labelmemberprice = new JLabel("会员价：");
	private JLabel labelnumber = new JLabel("数量:");
	private JLabel labelspecifications = new JLabel("规格：");
	private JLabel labeldetails = new JLabel("详情：");
	private JTextField edtcomname = new JTextField(20);
	private JTextField edtcomnumber = new JTextField(20);
	private JTextField edtcomprice = new JTextField(20);
	private JTextField edtmemberprice = new JTextField(20);
	private JTextField edtnumber = new JTextField(20);
	private JTextField edtspecifications = new JTextField(20);
	private JTextField edtdetails = new JTextField(20);
	
	public FrmaddCom(FrmFreshCom frmFreshCom, String s, boolean b) {
		super(frmFreshCom, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelcomname);
		workPane.add(edtcomname);
		workPane.add(labelcomnumber);
		workPane.add(edtcomnumber);
		workPane.add(labelcomprice);
		workPane.add(edtcomprice);
		workPane.add(labelmemberprice);
		workPane.add(edtmemberprice);
		workPane.add(labelnumber);
		workPane.add(edtnumber);
		workPane.add(labelspecifications);
		workPane.add(edtspecifications);
		workPane.add(labeldetails);
		workPane.add(edtdetails);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(260, 420);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String Trade_name=this.edtcomname.getText();
			String Category_number=this.edtcomnumber.getText();
			String Price=this.edtcomprice.getText();
			String Member_price = this.edtmemberprice.getText();
			String number = this.edtnumber.getText();
			String Specifications = this.edtspecifications.getText();
			String details = this.edtdetails.getText();
			ExampleComMananger sum = new ExampleComMananger();
			try {
				Beancommodity_information admin=sum.regCom(Trade_name, Category_number, Price, Member_price,number,Specifications,details);
				if(admin!=null) {
					this.setVisible(false);
					JOptionPane.showMessageDialog(null, "添加成功","成功",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "该商品类别不存在!","错误",JOptionPane.ERROR_MESSAGE);
				}
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
	}
}
