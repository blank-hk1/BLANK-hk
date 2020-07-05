package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.control.ExampleUserManager;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;

public class FrmModifyCity extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	
	private JLabel labelCityOld = new JLabel("原所在城市：                            "+Beanuser_table.currentLoginUser.getCity()+"       ");
	private JLabel labelCity = new JLabel("新所在城市：                         ");
	String[] strArray= {"杭州","南京","北京","上海","成都","济南","长沙","苏州"};
	JComboBox combobox=new JComboBox(strArray);
	public  FrmModifyCity(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelCityOld);
		workPane.add(labelCity);
		workPane.add(combobox);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 250);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		ExampleUserManager sum = new ExampleUserManager();
		String city=combobox.getSelectedItem().toString();
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
		}			
		else if(e.getSource()==this.btnOk){
			try {
				FreshNetUtil.userManager.changecity(Beanuser_table.currentLoginUser,city);
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
