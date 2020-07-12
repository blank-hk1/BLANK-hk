package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.control.ExampleComMananger;
import hk.freshnetwork.control.ExamplefreshManager;
import hk.freshnetwork.util.BusinessException;

public class FrmPurchaseModify extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("修改采购订单");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelNameNow = new JLabel("当前采购订单编号"+FrmFreshPurchase.Purchases.getChase_number()+"     ");
	private JLabel labelTradeNow = new JLabel("当前采购商品号："+FrmFreshPurchase.Purchases.getTrade_number()+"        ");
	private JLabel labelEmpNow = new JLabel("当前员工编号："+FrmFreshPurchase.Purchases.getEmp_number()+"       ");
	private JLabel labelamountNow = new JLabel("当前采购数量："+FrmFreshPurchase.Purchases.getPurchase_amount()+"      ");
	private JLabel labelstatNow = new JLabel("当前采购单状态："+FrmFreshPurchase.Purchases.getChase_stat()+"                   ");
	private JLabel labelName = new JLabel("采购商品号：");	
	private JLabel labelstate = new JLabel("员工编号：");
	private JLabel labelamount = new JLabel("采购数量：");
	private JLabel labelstat = new JLabel("采购单状态：");
	private JTextField edtUserId = new JTextField(20);
	private JTextField edtPwd = new JTextField(20);
	private JTextField edtstep = new JTextField(20);
	String[] strArray= {"下单","在途","入库"};
	JComboBox combobox=new JComboBox(strArray);
	
	public FrmPurchaseModify(FrmFreshPurchase frmFreshPurchase, String s, boolean b) {
		super(frmFreshPurchase, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);	
		workPane.add(labelNameNow);
		workPane.add(labelTradeNow);
		workPane.add(labelEmpNow);
		workPane.add(labelamountNow);
		workPane.add(labelstatNow);
		workPane.add(labelName);
		workPane.add(edtUserId);
		workPane.add(labelstate);
		workPane.add(edtPwd);
		workPane.add(labelamount);
		workPane.add(edtstep);
		workPane.add(labelstat);		
		workPane.add(combobox);		
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
			String Trade_number=this.edtUserId.getText();
			String Emp_number=new String(this.edtPwd.getText());
			String purchase_amount=new String(this.edtstep.getText());
			String stat=combobox.getSelectedItem().toString();
			ExampleComMananger sum = new ExampleComMananger();
			if (Trade_number.equals("")) {
				Trade_number=Integer.toString(FrmFreshPurchase.Purchases.getTrade_number());
			}
			if(Emp_number.equals("")) {
				Emp_number=Integer.toString(FrmFreshPurchase.Purchases.getEmp_number());
			}
			if(purchase_amount.equals("")) {
				purchase_amount=Integer.toString(FrmFreshPurchase.Purchases.getPurchase_amount());
			}
			if(stat.equals("")) {
				stat=FrmFreshPurchase.Purchases.getChase_stat();
			}
			int t = Integer.parseInt(Trade_number);
			int E = Integer.parseInt(Emp_number);
			int p = Integer.parseInt(purchase_amount);
				try {
					FreshNetUtil.purchaseManager.modifyPurchase(FrmFreshPurchase.Purchases.getChase_number(),t,E,p,stat);
					JOptionPane.showMessageDialog(null, "修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
					this.setVisible(false);
				} catch (BusinessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "请检查输入的商品编号或员工编号是否出错!","错误",JOptionPane.INFORMATION_MESSAGE);
				} 		
		}
	}
}
