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
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;

public class FrmShopping extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("下单");
	private JButton btnCancel = new JButton("取消");	
	private JLabel  labelcoupon= new JLabel(" 优惠券为:                ");
	private JButton psaddress = new JButton("选择配送地址");
	private JLabel address = new JLabel("配送地址为:       ");
	private JLabel contacts = new JLabel("联系人为:                           ");
	private JLabel phone = new JLabel("联系电话为:                        ");
	private JButton xzcoupon = new JButton("选择优惠券");
	float t= FreshNetUtil.orderManager.sumPrice();
	float money = 0;
	private JLabel labeldis = new JLabel("总价格:                  "+t+"                 ");
	private JLabel buyprice = new JLabel("结算金额："+t);
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
		workPane.add(contacts);
		workPane.add(phone);
		workPane.add(labelcoupon);
		workPane.add(xzcoupon);
		workPane.add(buyprice);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(260, 400);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.psaddress.addActionListener(this);
		this.xzcoupon.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){			
			try {
				FreshNetUtil.orderManager.updateCom(Beanuser_table.currentLoginUser.getUser_num());
				FreshNetUtil.orderManager.CreatOrder(Beanuser_table.currentLoginUser.getUser_num(), FrmChooseAddress.Beanaddlist.getAdd_number(), FrmChooseCoupon.ChooseCoupons.getCou_number(), t, money);
				JOptionPane.showMessageDialog(null,  "下单成功","提示",JOptionPane.INFORMATION_MESSAGE);
				FreshNetUtil.orderManager.deleteAllShop(Beanuser_table.currentLoginUser.getUser_num());
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,  "购买数量超过商品库存!","提示",JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}			
			this.setVisible(false);
		}
		else if(e.getSource()==this.psaddress) {
			try {
				FrmChooseAddress dlg = new FrmChooseAddress(this,"选择配送地址",true);
				dlg.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(FrmChooseAddress.Beanaddlist!=null) {
				this.psaddress.setLabel(FrmChooseAddress.Beanaddlist.getAddress());
			}
			this.contacts.setText("配送联系人为:                     "+FrmChooseAddress.Beanaddlist.getContacts());
			this.phone.setText("配送电话为:                  "+FrmChooseAddress.Beanaddlist.getCon_phone());
		}
		else if(e.getSource()==this.xzcoupon) {
			try {
				FrmChooseCoupon dlg = new FrmChooseCoupon(this,"添加优惠券",true);
				dlg.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(FrmChooseCoupon.ChooseCoupons!=null) {
				this.xzcoupon.setLabel(FrmChooseCoupon.ChooseCoupons.getContent());
			}
			if(t>=FrmChooseCoupon.ChooseCoupons.getApp_money()) {
				money=t-FrmChooseCoupon.ChooseCoupons.getDed_money();
			}
			this.buyprice.setText("结算金额:"+money);
		}
	}
}
