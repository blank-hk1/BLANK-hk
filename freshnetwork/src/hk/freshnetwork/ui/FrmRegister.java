package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import hk.freshnetwork.control.ExampleUserManager;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;




public class FrmRegister extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("用户注册");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelUser = new JLabel("用户名:");
	private JLabel labelgender = new JLabel("性别:");
	private JLabel labelPwd = new JLabel("密码：");
	private JLabel labelPwd2 = new JLabel("确认密码：");
	private JLabel labelphone = new JLabel("手机号码：");
	private JLabel labelmail = new JLabel("邮箱：");
	private JLabel labelcity = new JLabel("城市：");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);	
	private JTextField edtphone = new JTextField(20);
	private JTextField edtmail = new JTextField(20);
	private JTextField edtcity = new JTextField(20);
	
	ButtonGroup gender = new ButtonGroup();
	private JRadioButton male = new JRadioButton("男            ");
	private JRadioButton female = new JRadioButton("女                     ");
	String[] strArray= {"杭州","南京","北京","上海","成都","济南","长沙","苏州"};
	JComboBox combobox=new JComboBox(strArray);
	public FrmRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelgender);
		gender.add(male);
		gender.add(female);
		workPane.add(male);
		workPane.add(female);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		workPane.add(labelphone);
		workPane.add(edtphone);
		workPane.add(labelmail);
		workPane.add(edtmail);
		workPane.add(labelcity );
		workPane.add(combobox);
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
			String username=this.edtUserId.getText();
			String gender1 = male.isSelected()?"男" : "女";
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			String phone=this.edtphone.getText();
			String mail=this.edtmail.getText();
			String city=combobox.getSelectedItem().toString();
			ExampleUserManager sum = new ExampleUserManager();
			try {
				if(!male.isSelected()&&!female.isSelected()) {
					JOptionPane.showMessageDialog(null, "未选择性别","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				Beanuser_table user=sum.reg(username,gender1,pwd1,pwd2, phone,mail, city);
				if(user!=null) {
					this.setVisible(false);
					JOptionPane.showMessageDialog(null, "注册成功","成功",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "注册失败","错误",JOptionPane.ERROR_MESSAGE);
				}
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
	}
}
