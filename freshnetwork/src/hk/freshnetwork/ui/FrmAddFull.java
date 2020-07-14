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

import hk.freshnetwork.control.ExamplecoupouManager;
import hk.freshnetwork.model.Beanfull_sheet;
import hk.freshnetwork.model.Beantime_pro;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;

public class FrmAddFull extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("添加满折信息");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelcontent = new JLabel("满折内容:");
	private JLabel labelnumber = new JLabel("适用商品数量：");
	private JLabel labeldis = new JLabel("折扣：");
	private JLabel labelStart = new JLabel("开始日期：");
	private JLabel labelload = new JLabel("结束时间：");
	private JTextField edtcontent= new JTextField(20);
	private JTextField edtAppmoney = new JTextField(20);
	private JTextField edtDedmoney = new JTextField(20);
	private JTextField edtStart = new JTextField(20);
	private JTextField edtload = new JTextField(20);
	
	public FrmAddFull(FrmFreshDis frmFreshDis, String s, boolean b) {
		super(frmFreshDis, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelcontent);
		workPane.add(edtcontent);
		workPane.add(labelnumber);
		workPane.add(edtAppmoney);
		workPane.add(labeldis);
		workPane.add(edtDedmoney);
		workPane.add(labelStart);
		workPane.add(edtStart);
		workPane.add(labelload);
		workPane.add(edtload);
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
			if(this.edtAppmoney.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "输入不能为空!","错误",JOptionPane.ERROR_MESSAGE);
			}
			int number=Integer.parseInt(this.edtAppmoney.getText());
			Float Prom_number=Float.parseFloat(this.edtDedmoney.getText());
			String Start = this.edtStart.getText();
			String end = this.edtload.getText();
			ExamplecoupouManager sum = new ExamplecoupouManager();
			try {
				Beanfull_sheet cou=sum.RegFul(content,number,Prom_number,Start,end);
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
