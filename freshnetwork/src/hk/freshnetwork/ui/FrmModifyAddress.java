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
import hk.freshnetwork.control.ExampleAddressMannager;
import hk.freshnetwork.control.ExamplecoupouManager;
import hk.freshnetwork.util.BaseException;

public class FrmModifyAddress extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("修改配送地址");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelAddnumber = new JLabel("当前配送地址编号"+FrmFreshaddress.Addresslists.getAdd_number()+"      ");
	private JLabel labelqunow = new JLabel("当前所在区域:"+FrmFreshaddress.Addresslists.getQu()+"            ");
	private JLabel labeladdressnow = new JLabel("详细地址："+FrmFreshaddress.Addresslists.getAddress()+"        ");
	private JLabel labelcontactsnow = new JLabel("当前联系人："+FrmFreshaddress.Addresslists.getContacts()+"      ");
	private JLabel labelphonenow = new JLabel("当前联系电话："+FrmFreshaddress.Addresslists.getCon_phone()+"                      ");
	private JLabel labelqu = new JLabel("配送区域:");
	private JLabel labeladdress = new JLabel("配送详细地址：");
	private JLabel labelcontacts = new JLabel("联系人：");
	private JLabel labelphone = new JLabel("联系电话：");
	private JTextField edtqu= new JTextField(20);
	private JTextField edtaddress = new JTextField(20);
	private JTextField edtcontacts = new JTextField(20);
	private JTextField edtphone = new JTextField(20);
	
	public FrmModifyAddress(FrmFreshaddress frmFreshaddress, String s, boolean b) {
		super(frmFreshaddress, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);	
		workPane.add(labelAddnumber);
		workPane.add(labeladdressnow);
		workPane.add(labelcontactsnow);
		workPane.add(labelcontactsnow);
		workPane.add(labelphonenow);
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
	public void actionPerformed(ActionEvent e){		
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String qu = this.edtqu.getText();
			String address = this.edtaddress.getText();
			String contacts = this.edtcontacts.getText();
			String phone = this.edtphone.getText();
			if(qu.equals("")) {
				qu=FrmFreshaddress.Addresslists.getQu();
			}
			if(address.equals("")) {
				address=FrmFreshaddress.Addresslists.getAddress();
			}
			if(contacts.equals("")) {
				contacts=FrmFreshaddress.Addresslists.getContacts();
			}
			if(phone.equals("")) {
				phone = FrmFreshaddress.Addresslists.getCon_phone();
			}
			ExampleAddressMannager sum = new ExampleAddressMannager();		
				try {
					FreshNetUtil.addressManager.modifyFresh(FrmFreshaddress.Addresslists.getAdd_number(), qu, address, contacts, phone);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}; 
				JOptionPane.showMessageDialog(null, "修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
			
		}
	}
}
