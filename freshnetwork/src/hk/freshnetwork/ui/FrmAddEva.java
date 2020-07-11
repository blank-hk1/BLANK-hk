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
import hk.freshnetwork.control.ExamplecoupouManager;
import hk.freshnetwork.model.Beanfull_sheet;
import hk.freshnetwork.model.Beangoods_eva;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;

public class FrmAddEva extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("添加用户评价");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelcontent = new JLabel("评价内容:");
	private JLabel labelnumber = new JLabel("评价星级：");
	private JTextField edtcontent= new JTextField(20);
	private JTextField edtStar = new JTextField(20);
	
	public FrmAddEva(FrmComRecords frmComRecords, String s, boolean b) {
		super(frmComRecords, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelcontent);
		workPane.add(edtcontent);
		workPane.add(labelnumber);
		workPane.add(edtStar);
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
			String content=this.edtcontent.getText();
			int star = Integer.parseInt(this.edtStar.getText());
			ExamplecoupouManager sum = new ExamplecoupouManager();
			try {
				Beangoods_eva cou = FreshNetUtil.orderManager.Addeva(Beanuser_table.currentLoginUser.getUser_num(), FrmComRecords.records.getCom_Trade_number(), content, star);
				if(cou!=null) {
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
