package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.control.ExamplefreshManager;
import hk.freshnetwork.util.BaseException;

public class FrmModifyMenu extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("修改菜谱");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelNameNow = new JLabel("当前菜谱名称"+FrmFreshMenu.menus.getMenu_name());
	private JLabel labelstateNow = new JLabel("当前菜谱原料："+FrmFreshMenu.menus.getMenu_Materials());
	private JLabel labelstepNow = new JLabel("当前步骤："+FrmFreshMenu.menus.getStep());
	private JLabel labelName = new JLabel("菜谱名称：");	
	private JLabel labelstate = new JLabel("菜谱描述：");
	private JLabel labelstep = new JLabel("菜谱步骤：");
	private JTextField edtUserId = new JTextField(20);
	private JTextField edtPwd = new JTextField(20);
	private JTextField edtstep = new JTextField(20);
	
	public FrmModifyMenu(FrmFreshMenu frmFreshMenu, String s, boolean b) {
		super(frmFreshMenu, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);	
		workPane.add(labelNameNow);
		workPane.add(labelstateNow);
		workPane.add(labelstepNow);
		workPane.add(labelName);
		workPane.add(edtUserId);
		workPane.add(labelstate);
		workPane.add(edtPwd);
		workPane.add(labelstep);
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
			String menuname=this.edtUserId.getText();
			String state=new String(this.edtPwd.getText());
			String step=new String(this.edtstep.getText());
			ExamplefreshManager sum = new ExamplefreshManager();
			if (menuname.equals("")) {
				menuname=FrmFreshMenu.menus.getMenu_name();
			}
			if(state.equals("")) {
				state=FrmFreshMenu.menus.getMenu_Materials();
			}
			if(step.equals("")) {
				step=FrmFreshMenu.menus.getStep();
			}
				FreshNetUtil.freshManager.modifyMenu(menuname,state,step); 
				JOptionPane.showMessageDialog(null, "修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
			
		}
	}
}
