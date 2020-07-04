package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.control.ExampleUserManager;
import hk.freshnetwork.control.ExampleadminManager;
import hk.freshnetwork.model.Beanadminfo;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;




public class FrmLogin extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnCancel = new JButton("退出");
	private JButton btnRegister = new JButton("用户注册");
	
	private JButton btnLogin = new JButton("登陆");
	//private JButton btnadm = new JButton("管理员登录");
	
	String[] strArray= {"用户","管理员"};
	JComboBox combobox=new JComboBox(strArray);
	
	private JLabel labelUser = new JLabel("用户：");
	private JLabel labelPwd = new JLabel("密码：");
	private JLabel labeliden = new JLabel("身份：");
	private JTextField edtId = new JTextField(25);
	private JPasswordField edtPwd = new JPasswordField(25);
	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);
		//toolBar.add(btnadm);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labeliden);
		workPane.add(combobox);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(380, 200);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		this.btnRegister.addActionListener(this);
		//btnadm.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnLogin) {
			String identity=combobox.getSelectedItem().toString();
			if ("用户".equals(identity)) {
				ExampleUserManager sum = new ExampleUserManager();
				String username=this.edtId.getText();
				String pwd=new String(this.edtPwd.getPassword());
				try {
					Beanuser_table  user= sum.login(username, pwd);
					if(pwd.equals(user.getUser_pwd())) {
						Beanuser_table.currentLoginUser= FreshNetUtil.userManager.login(username, pwd);
						this.setVisible(false);
						FrmMain.Identity=1;
					}
					else {
						JOptionPane.showMessageDialog(null,"密码错误","错误提示ʾ",JOptionPane.ERROR_MESSAGE);
					}				
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			else {
				ExampleadminManager sum = new ExampleadminManager();
				String Emp_name=this.edtId.getText();
				String pwd=new String(this.edtPwd.getPassword());
				try {
					Beanadminfo  admin= sum.login(Emp_name, pwd);
					if(pwd.equals(admin.getEmp_pwd())) {
						Beanadminfo.currentLoginadmin= FreshNetUtil.adminManager.login(Emp_name, pwd);
						this.setVisible(false);
						FrmMain.Identity=0;
					}
					else {
						JOptionPane.showMessageDialog(null,"密码错误","错误提示ʾ",JOptionPane.ERROR_MESSAGE);
					}				
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		} else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		} else if(e.getSource()==this.btnRegister){
			FrmRegister dlg=new FrmRegister(this,"注册",true);
			dlg.setVisible(true);
			System.exit(0);
		}
	}


}
