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

import hk.freshnetwork.control.ExampleAddressMannager;
import hk.freshnetwork.model.Beanaddlist;
import hk.freshnetwork.util.BaseException;

public class FrmPayment extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("支付");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelqu = new JLabel("结算金额:");
	private JLabel labeladdress = new JLabel("配送详细地址：");
	private JLabel labelcontacts = new JLabel("联系人：");
	private JLabel labelphone = new JLabel("联系电话：");
	private JTextField edtqu= new JTextField(20);
	private JTextField edtaddress = new JTextField(20);
	private JTextField edtcontacts = new JTextField(20);
	private JTextField edtphone = new JTextField(20);
	
	public FrmPayment(FrmFreshaddress frmFreshaddress, String s, boolean b) {
		super(frmFreshaddress, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelqu);
		workPane.add(edtqu);
		workPane.add(labeladdress);
		workPane.add(edtaddress);
		workPane.add(labelcontacts);
		workPane.add(edtcontacts);
		workPane.add(labelphone);
		workPane.add(edtphone);
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
			String qu = this.edtqu.getText();
			String address = this.edtaddress.getText();
			String contacts = this.edtcontacts.getText();
			String phone = this.edtphone.getText();
			ExampleAddressMannager sum = new ExampleAddressMannager();
			try {
				Beanaddlist cou=sum.regAdd(qu, address, contacts, phone);
				if(cou!=null) {
					this.setVisible(false);
					JOptionPane.showMessageDialog(null, "添加成功","成功",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "添加失败","错误",JOptionPane.ERROR_MESSAGE);
				}
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
	}
}
