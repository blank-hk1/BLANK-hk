package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.control.ExampleUserManager;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;

public class FrmOpenVip extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	
	private JLabel labelUser = new JLabel("当前用户：                     "+Beanuser_table.currentLoginUser.getUser_name()+"                                                             ");
	private JLabel labelpd = new JLabel("您当前不是会员，开通会员可享更多优惠！");
	private JLabel labelpd1 = new JLabel("您已经是尊贵的会员了，到期时间："+Beanuser_table.currentLoginUser.getClosedate());
	
	ButtonGroup VIP = new ButtonGroup();
	private JRadioButton onemonth = new JRadioButton("单月会员");
	private JRadioButton threemonth = new JRadioButton("季度会员 ");
	private JRadioButton halfyear = new JRadioButton("半年制会员");
	private JRadioButton oneyear = new JRadioButton("年费会员 ");
	
	public  FrmOpenVip(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		if(Beanuser_table.currentLoginUser.isISmember()) {
			workPane.add(labelpd1);
		}
		else {
			workPane.add(labelpd);
		}
		VIP.add(onemonth);
		VIP.add(threemonth);
		VIP.add(halfyear);
		VIP.add(oneyear);
		workPane.add(onemonth);
		workPane.add(threemonth);
		workPane.add(halfyear);
		workPane.add(oneyear);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(400, 250);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {	
		ExampleUserManager sum = new ExampleUserManager();
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
		}			
		else if(e.getSource()==this.btnOk){
			try {
				if (onemonth.isSelected()) {
					String str = "单月会员";
					if (Beanuser_table.currentLoginUser.isISmember()) {						
						JOptionPane.showMessageDialog(null, "续费单月会员成功", "成功",JOptionPane.INFORMATION_MESSAGE);
						FreshNetUtil.userManager.openvip(Beanuser_table.currentLoginUser,str);
						Beanuser_table.currentLoginUser= FreshNetUtil.userManager.login(Beanuser_table.currentLoginUser.getUser_name(), Beanuser_table.currentLoginUser.getUser_pwd());
						this.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "开通单月会员成功", "成功",JOptionPane.INFORMATION_MESSAGE);
						FreshNetUtil.userManager.openvip(Beanuser_table.currentLoginUser,str);
						Beanuser_table.currentLoginUser= FreshNetUtil.userManager.login(Beanuser_table.currentLoginUser.getUser_name(), Beanuser_table.currentLoginUser.getUser_pwd());
						this.setVisible(false);
					}
				}
				else if(threemonth.isSelected()) {
					String str = "季度会员";
					if (Beanuser_table.currentLoginUser.isISmember()) {
						JOptionPane.showMessageDialog(null, "续费季度会员成功", "成功",JOptionPane.INFORMATION_MESSAGE);
						FreshNetUtil.userManager.openvip(Beanuser_table.currentLoginUser,str);
						Beanuser_table.currentLoginUser= FreshNetUtil.userManager.login(Beanuser_table.currentLoginUser.getUser_name(), Beanuser_table.currentLoginUser.getUser_pwd());
						this.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "开通季度会员成功", "成功",JOptionPane.INFORMATION_MESSAGE);
						FreshNetUtil.userManager.openvip(Beanuser_table.currentLoginUser,str);
						Beanuser_table.currentLoginUser= FreshNetUtil.userManager.login(Beanuser_table.currentLoginUser.getUser_name(), Beanuser_table.currentLoginUser.getUser_pwd());
						this.setVisible(false);
					}
				}
				else if(halfyear.isSelected()) {
					String str = "半年制会员";
					if (Beanuser_table.currentLoginUser.isISmember()) {
						JOptionPane.showMessageDialog(null, "续费半年制会员成功", "成功",JOptionPane.INFORMATION_MESSAGE);
						FreshNetUtil.userManager.openvip(Beanuser_table.currentLoginUser,str);
						Beanuser_table.currentLoginUser= FreshNetUtil.userManager.login(Beanuser_table.currentLoginUser.getUser_name(), Beanuser_table.currentLoginUser.getUser_pwd());
						this.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "开通半年制会员成功", "成功",JOptionPane.INFORMATION_MESSAGE);
						FreshNetUtil.userManager.openvip(Beanuser_table.currentLoginUser,str);
						Beanuser_table.currentLoginUser= FreshNetUtil.userManager.login(Beanuser_table.currentLoginUser.getUser_name(), Beanuser_table.currentLoginUser.getUser_pwd());
						this.setVisible(false);
					}
				}
				else if(oneyear.isSelected()) {
					String str = "年度会员";
					if (Beanuser_table.currentLoginUser.isISmember()) {
						JOptionPane.showMessageDialog(null, "续费年度会员成功", "成功",JOptionPane.INFORMATION_MESSAGE);
						FreshNetUtil.userManager.openvip(Beanuser_table.currentLoginUser,str);
						Beanuser_table.currentLoginUser= FreshNetUtil.userManager.login(Beanuser_table.currentLoginUser.getUser_name(), Beanuser_table.currentLoginUser.getUser_pwd());
						this.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "开通年度会员成功", "成功",JOptionPane.INFORMATION_MESSAGE);
						FreshNetUtil.userManager.openvip(Beanuser_table.currentLoginUser,str);
						Beanuser_table.currentLoginUser= FreshNetUtil.userManager.login(Beanuser_table.currentLoginUser.getUser_name(), Beanuser_table.currentLoginUser.getUser_pwd());
						this.setVisible(false);
					}
				}
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
				
	}
}
