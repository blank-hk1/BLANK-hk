package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
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
import hk.freshnetwork.control.ExampleadminManager;
import hk.freshnetwork.model.Beanadminfo;
import hk.freshnetwork.util.BaseException;



public class FrmModifyPwd extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	
	private JLabel labelPwdOld = new JLabel("原密码：");
	private JLabel labelPwd = new JLabel("新密码：");
	private JLabel labelPwd2 = new JLabel("新密码：");
	private JTextField edtPwdOld = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	public FrmModifyPwd(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelPwdOld);
		workPane.add(edtPwdOld);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 250);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ExampleadminManager sum = new ExampleadminManager();
		String pwd=new String(this.edtPwd.getPassword());
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
		}			
		else if(e.getSource()==this.btnOk){
			try {
				FreshNetUtil.adminManager.changePwd(Beanadminfo.currentLoginadmin,new String(edtPwdOld.getText()),new String(edtPwd.getPassword()),new String(edtPwd2.getPassword()));
				Beanadminfo.currentLoginadmin= FreshNetUtil.adminManager.login(Beanadminfo.currentLoginadmin.getEmp_name(), pwd);
				JOptionPane.showMessageDialog(null, "修改成功", "成功",JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
				
	}
}
