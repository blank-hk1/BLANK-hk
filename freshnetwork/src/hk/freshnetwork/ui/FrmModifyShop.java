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
import hk.freshnetwork.control.ExamplefreshManager;
import hk.freshnetwork.model.Beantime_pro;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;

public class FrmModifyShop extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("修改商品数量");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelstepNow = new JLabel("当前商品数量："+FrmMain.shops.getPur_number());
	private JLabel labelName = new JLabel("修改的商品数量：");	
	private JTextField edtstep = new JTextField(20);
	
	public FrmModifyShop(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);	
		workPane.add(labelstepNow);
		workPane.add(labelName);
		workPane.add(edtstep);
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
			int number=0;
			if(this.edtstep.getText().equals("")) {
				number=FrmMain.shops.getPur_number();
			}
			else {
				number=Integer.parseInt(this.edtstep.getText());
			}
			FreshNetUtil.orderManager.deleteShop(FrmMain.shops.getCom_Trade_number());
			try {
				Beantime_pro t =FreshNetUtil.orderManager.searchPro(FrmMain.shops.getCom_Trade_number());
				if(t==null) {
					if(Beanuser_table.currentLoginUser.isISmember()==true) {
						FreshNetUtil.orderManager.RegOrdDetails(FrmMain.shops.getCom_Trade_number(), number,FrmMain.details.getMember_price());
					}
					else {
						FreshNetUtil.orderManager.RegOrdDetails(FrmMain.shops.getCom_Trade_number(), number,FrmMain.details.getPrice());
					}
				}
				else {
					Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
					if(t.getProStart_date().before(time)&&t.getProEnd_date().after(time)) {
						if(number>t.getProm_number()) {
							FreshNetUtil.orderManager.RegOrdDetails(FrmMain.shops.getCom_Trade_number(), t.getProm_number(),t.getPro_price());
							if(Beanuser_table.currentLoginUser.isISmember()) {
								FreshNetUtil.orderManager.RegOrdDetails(FrmMain.shops.getCom_Trade_number(), number-t.getProm_number(),FrmMain.details.getMember_price());
							}
							else {
								FreshNetUtil.orderManager.RegOrdDetails(FrmMain.shops.getCom_Trade_number(), number-t.getProm_number(),FrmMain.details.getPrice());
							}
							
						}
						else {
							FreshNetUtil.orderManager.RegOrdDetails(FrmMain.shops.getCom_Trade_number(),number,t.getPro_price());
						}
					}
					else {
						if(Beanuser_table.currentLoginUser.isISmember()) {
							FreshNetUtil.orderManager.RegOrdDetails(FrmMain.shops.getCom_Trade_number(), number,FrmMain.details.getMember_price());
						}
						else {
							FreshNetUtil.orderManager.RegOrdDetails(FrmMain.shops.getCom_Trade_number(), number,FrmMain.details.getPrice());
						}
					}
				}
				JOptionPane.showMessageDialog(null, "修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			this.setVisible(false);
			
		}
	}
}
