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
import hk.freshnetwork.control.ExampleComMananger;
import hk.freshnetwork.control.ExamplefreshManager;
import hk.freshnetwork.util.BaseException;

public class FrmModifyCom extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("修改商品");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelNameNow = new JLabel("当前商品名称"+FrmFreshCom.COMS.getTrade_name());
	private JLabel labelstateNow = new JLabel("当前类别编号："+FrmFreshCom.COMS.getCategory_number());
	private JLabel labelstepNow = new JLabel("当前商品单价："+FrmFreshCom.COMS.getPrice());
	private JLabel labelmemberpricenow = new JLabel("当前会员价："+FrmFreshCom.COMS.getMember_price());
	private JLabel labelnumbernow = new JLabel("当前数量:"+FrmFreshCom.COMS.getNumber());
	private JLabel labelspecificationsnow = new JLabel("当前规格："+FrmFreshCom.COMS.getSpecifications());
	private JLabel labeldetailsnow = new JLabel("当前详情："+FrmFreshCom.COMS.getDetails());
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
	
	public FrmModifyCom(FrmFreshCom frmFreshCom, String s, boolean b) {
		super(frmFreshCom, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);	
		workPane.add(labelNameNow);
		workPane.add(labelstateNow);
		workPane.add(labelstepNow);
		workPane.add(labelmemberpricenow);
		workPane.add(labelnumbernow);
		workPane.add(labelspecificationsnow);
		workPane.add(labeldetailsnow);
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
		this.setSize(260, 600);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);	
	}
	@Override
	public void actionPerformed(ActionEvent e){		
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String Trade_name=this.edtcomname.getText();
			String Category_number=this.edtcomnumber.getText();
			String Price=this.edtcomprice.getText();
			String Member_price = this.edtmemberprice.getText();
			String number = this.edtnumber.getText();
			String specifications = this.edtspecifications.getText();
			String details = this.edtdetails.getText();
			ExampleComMananger sum = new ExampleComMananger();
			if (Trade_name.equals("")) {
				Trade_name=FrmFreshCom.COMS.getTrade_name();
			}
			if(Category_number.equals("")) {
				String c= Integer.toString(FrmFreshCom.COMS.getCategory_number());
				Category_number=c;
			}
			if(Price.equals("")) {
				String p= Float.toString(FrmFreshCom.COMS.getPrice());
				Price=p;
			}
			if (Member_price.equals("")) {
				String m= Float.toString(FrmFreshCom.COMS.getMember_price());
				Member_price=m;
			}
			if(number.equals("")) {
				String n= Integer.toString(FrmFreshCom.COMS.getNumber());
				number=n;
			}
			if(specifications.equals("")) {
				specifications=FrmFreshCom.COMS.getSpecifications();
			}
			if(details.equals("")) {
				details=FrmFreshCom.COMS.getDetails();
			}
				try {
					FreshNetUtil.comManager.modifyCom(Trade_name, Category_number, Price, Member_price, number, specifications, details);
					JOptionPane.showMessageDialog(null, "修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
					this.setVisible(false);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
		}
	}
}
