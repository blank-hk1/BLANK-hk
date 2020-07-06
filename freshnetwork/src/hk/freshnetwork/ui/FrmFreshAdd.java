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

import hk.freshnetwork.control.ExampleadminManager;
import hk.freshnetwork.control.ExamplefreshManager;
import hk.freshnetwork.model.Beanadminfo;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.util.BaseException;

public class FrmFreshAdd extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("添加生鲜类别名称");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelfreshname = new JLabel("生鲜类别名称:");
	private JLabel labelfreshdec = new JLabel("生鲜类别描述：");
	private JTextField edtUserId = new JTextField(20);
	private JTextField edtPwd = new JTextField(20);
	
	public FrmFreshAdd(FrmFreshcat frmFreshcat, String s, boolean b) {
		super(frmFreshcat, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelfreshname);
		workPane.add(edtUserId);
		workPane.add(labelfreshdec);
		workPane.add(edtPwd);
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
			String freshname=this.edtUserId.getText();
			String freshdec=new String(this.edtPwd.getText());
			ExamplefreshManager sum = new ExamplefreshManager();
			try {
				Beanfresh_information admin=sum.regFresh(freshname,freshdec);
				if(admin!=null) {
					this.setVisible(false);
					JOptionPane.showMessageDialog(null, "添加成功","成功",JOptionPane.INFORMATION_MESSAGE);
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
