package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hk.freshnetwork.control.ExamplePurchaseManager;
import hk.freshnetwork.control.ExampleadminManager;
import hk.freshnetwork.model.Beanadminfo;
import hk.freshnetwork.model.Beanpurchase;
import hk.freshnetwork.util.BaseException;

public class FrmAddNum extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("进行采购");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelUser = new JLabel("要采购的数量:");
	private JLabel labelPwd = new JLabel("进行采购的员工:");
	private JLabel labelPwd2 = new JLabel("采购编号:");
	private JTextField edtUserId = new JTextField(20);
	private JTextField edtPwd = new JTextField(20);
	private JTextField edtPwd2 = new JTextField(20);	
	
	public FrmAddNum(FrmAddPurchase frmAddPurchase, String s, boolean b) {
		super(frmAddPurchase, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
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
			int purnumber=Integer.parseInt(this.edtUserId.getText());
			int Emp=Integer.parseInt(new String(this.edtPwd.getText()));
			int chase=Integer.parseInt(new String(this.edtPwd2.getText()));
			int t = FrmAddPurchase.purcoms.getTrade_number();
			String state = "下单";
			//System.out.println(chase+" "+t+" "+Emp+" "+purnumber+" "+state);
			ExamplePurchaseManager sum = new ExamplePurchaseManager();
			try {
				Beanpurchase admin=sum.regpur(chase,t,Emp,purnumber,state);
				if(admin!=null) {
					this.setVisible(false);
					JOptionPane.showMessageDialog(null, "采购成功","成功",JOptionPane.INFORMATION_MESSAGE);
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
