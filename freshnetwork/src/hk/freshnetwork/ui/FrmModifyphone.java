package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.control.ExampleUserManager;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;

public class FrmModifyphone extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	
	private JLabel labelPhoneOld = new JLabel("原手机号码："+Beanuser_table.currentLoginUser.getUser_phone()+"                                ");
	private JLabel labelPhone = new JLabel("新手机号码：");
	private JPasswordField edtPhone = new JPasswordField(15);
	public  FrmModifyphone(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPhoneOld);
		workPane.add(labelPhone);
		workPane.add(edtPhone);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 250);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ExampleUserManager sum = new ExampleUserManager();
		String phone=new String(this.edtPhone.getPassword());
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
		}			
		else if(e.getSource()==this.btnOk){
			try {
				FreshNetUtil.userManager.changephone(Beanuser_table.currentLoginUser,new String(edtPhone.getPassword()));
				Beanuser_table.currentLoginUser= FreshNetUtil.userManager.login(Beanuser_table.currentLoginUser.getUser_name(), Beanuser_table.currentLoginUser.getUser_pwd());
				JOptionPane.showMessageDialog(null, "修改成功", "成功",JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
				
	}
}
