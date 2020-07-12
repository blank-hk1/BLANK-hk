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

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.control.ExampleadminManager;
import hk.freshnetwork.control.ExamplefreshManager;
import hk.freshnetwork.model.Beanadminfo;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;

public class FrmFreshModify extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("修改生鲜类别");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelNameNow = new JLabel("当前生鲜名称"+FrmFreshcat.freshs.getCategory_name()+"                       ");
	private JLabel labelstateNow = new JLabel("当前生鲜描述："+FrmFreshcat.freshs.getCategory_description()+"                        ");
	private JLabel labelName = new JLabel("生鲜名称：");	
	private JLabel labelstate = new JLabel("生鲜描述：");
	private JTextField edtUserId = new JTextField(20);
	private JTextField edtPwd = new JTextField(20);
	
	public FrmFreshModify(FrmFreshcat frmFreshcat, String s, boolean b) {		
		super(frmFreshcat, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);	
		workPane.add(labelNameNow);
		workPane.add(labelstateNow);
		workPane.add(labelName);
		workPane.add(edtUserId);
		workPane.add(labelstate);
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
			String state=new String(this.edtPwd.getText());
			ExamplefreshManager sum = new ExamplefreshManager();
			try {
				if (freshname.equals("")&&!state.equals("")) {
					FreshNetUtil.freshManager.modifyFresh(FrmFreshcat.freshs.getCategory_name(), state);
				}
				else if(!freshname.equals("")&&state.equals("")) {
					FreshNetUtil.freshManager.modifyFresh(freshname, FrmFreshcat.freshs.getCategory_description());
				}
				else if(freshname.equals("")&&state.equals("")) {
					FreshNetUtil.freshManager.modifyFresh(FrmFreshcat.freshs.getCategory_name(), FrmFreshcat.freshs.getCategory_description());
				}
				else {
					FreshNetUtil.freshManager.modifyFresh(freshname, state);	
				}								    
					this.setVisible(false);
					JOptionPane.showMessageDialog(null, "添加成功","成功",JOptionPane.INFORMATION_MESSAGE);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
	}
}
