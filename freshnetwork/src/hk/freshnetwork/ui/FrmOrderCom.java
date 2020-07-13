package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.control.ExampleOrderManager;
import hk.freshnetwork.control.ExamplecoupouManager;
import hk.freshnetwork.model.Beantime_pro;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;

public class FrmOrderCom extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("添加至购物车");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labeldis = new JLabel("购买的商品数量：");
	private JTextField edtDedmoney = new JTextField(20);
	
	public FrmOrderCom(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labeldis);
		workPane.add(edtDedmoney);
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
			int Trade_number=Integer.parseInt(this.edtDedmoney.getText());
			if(Trade_number<=0) {
				 JOptionPane.showMessageDialog(null, "购买数量必须为正数","错误",JOptionPane.INFORMATION_MESSAGE);
				 return;
			}
			try {
				Beantime_pro t =FreshNetUtil.orderManager.searchPro(FrmMain.details.getTrade_number());
				if(t==null) {
					if(Beanuser_table.currentLoginUser.isISmember()==true) {
						FreshNetUtil.orderManager.RegOrdDetails(FrmMain.details.getTrade_number(), Trade_number,FrmMain.details.getMember_price());
					}
					else {
						FreshNetUtil.orderManager.RegOrdDetails(FrmMain.details.getTrade_number(), Trade_number,FrmMain.details.getPrice());
					}
				}
				else {
					Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
					if(t.getProStart_date().before(time)&&t.getProEnd_date().after(time)) {
						if(Trade_number>t.getProm_number()) {
							FreshNetUtil.orderManager.RegOrdDetails(FrmMain.details.getTrade_number(), t.getProm_number(),t.getPro_price());
							if(Beanuser_table.currentLoginUser.isISmember()) {
								FreshNetUtil.orderManager.RegOrdDetails(FrmMain.details.getTrade_number(), Trade_number-t.getProm_number(),FrmMain.details.getMember_price());
							}
							else {
								FreshNetUtil.orderManager.RegOrdDetails(FrmMain.details.getTrade_number(), Trade_number-t.getProm_number(),FrmMain.details.getPrice());
							}
							
						}
						else {
							FreshNetUtil.orderManager.RegOrdDetails(FrmMain.details.getTrade_number(),Trade_number,t.getPro_price());
						}
					}
					else {
						if(Beanuser_table.currentLoginUser.isISmember()) {
							FreshNetUtil.orderManager.RegOrdDetails(FrmMain.details.getTrade_number(), Trade_number,FrmMain.details.getMember_price());
						}
						else {
							FreshNetUtil.orderManager.RegOrdDetails(FrmMain.details.getTrade_number(), Trade_number,FrmMain.details.getPrice());
						}
					}
				}
				this.setVisible(false);
			    JOptionPane.showMessageDialog(null, "添加成功","成功",JOptionPane.INFORMATION_MESSAGE);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, "添加错误","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}			
		}
	}
}
