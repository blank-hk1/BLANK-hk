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
import hk.freshnetwork.model.Beancoupon;
import hk.freshnetwork.util.BaseException;

public class FrmAddChoooseCoupon extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("添加优惠券");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelcontent = new JLabel("优惠券内容:");
	private JLabel labelAppmoney = new JLabel("优惠券使用金额：");
	private JLabel labelDedmoney = new JLabel("减免金额：");
	private JLabel labelStart = new JLabel("开始日期：");
	private JLabel labelload = new JLabel("持续时间(以月为单位)：");
	private JTextField edtcontent= new JTextField(20);
	private JTextField edtAppmoney = new JTextField(20);
	private JTextField edtDedmoney = new JTextField(20);
	private JTextField edtStart = new JTextField(20);
	private JTextField edtload = new JTextField(20);
	
	public FrmAddChoooseCoupon(FrmChooseCoupon frmChooseCoupon, String s, boolean b) {
		super(frmChooseCoupon, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelcontent);
		workPane.add(edtcontent);
		workPane.add(labelAppmoney);
		workPane.add(edtAppmoney);
		workPane.add(labelDedmoney);
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
			Float App_money=Float.parseFloat(this.edtAppmoney.getText());
			Float Ded_money=Float.parseFloat(this.edtDedmoney.getText());
			String Start = this.edtStart.getText();
			int month = Integer.parseInt(this.edtload.getText());
			ExamplecoupouManager sum = new ExamplecoupouManager();
			try {
				Beancoupon cou=sum.RegCou(content,App_money,Ded_money,Start,month);
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
