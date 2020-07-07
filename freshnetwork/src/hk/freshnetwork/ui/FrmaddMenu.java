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

import hk.freshnetwork.control.ExamplefreshManager;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.model.Beanmenu_info;
import hk.freshnetwork.util.BaseException;

public class FrmaddMenu extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("添加菜谱名称");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelmenuname = new JLabel("菜谱名称:");
	private JLabel labelmenuMaterials = new JLabel("菜谱原料：");
	private JLabel labelmenustep = new JLabel("步骤：");
	private JTextField edtmenuname = new JTextField(20);
	private JTextField edtMaterials = new JTextField(20);
	private JTextField edtStep = new JTextField(20);
	
	public FrmaddMenu(FrmFreshMenu frmFreshMenu, String s, boolean b) {
		super(frmFreshMenu, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelmenuname);
		workPane.add(edtmenuname);
		workPane.add(labelmenuMaterials);
		workPane.add(edtMaterials);
		workPane.add(labelmenustep);
		workPane.add(edtStep);
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
			String menuname=this.edtmenuname.getText();
			String menuMaterials=new String(this.edtMaterials.getText());
			String step=new String(this.edtStep.getText());
			ExamplefreshManager sum = new ExamplefreshManager();
			try {
				Beanmenu_info admin=sum.regMenu(menuname,menuMaterials,step);
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
