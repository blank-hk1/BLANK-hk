package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.control.ExamplecoupouManager;
import hk.freshnetwork.util.BaseException;

public class FrmModifyFul extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("修改满折信息");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelNameNow = new JLabel("当前满折编号"+FrmFreshDis.fulls.getFull_number()+"                                ");
	private JLabel labelcontent = new JLabel("满折内容:");
	private JLabel labelAppmoney = new JLabel("适用商品数量：");
	private JLabel labelDedmoney = new JLabel("折扣：");
	private JLabel labelStart = new JLabel("开始日期：");
	private JLabel labelload = new JLabel("持续时间(以月为单位)：");
	private JTextField edtcontent= new JTextField(20);
	private JTextField edtAppmoney = new JTextField(20);
	private JTextField edtDedmoney = new JTextField(20);
	private JTextField edtStart = new JTextField(20);
	private JTextField edtload = new JTextField(20);
	
	public FrmModifyFul(FrmFreshDis frmFreshDis, String s, boolean b) {
		super(frmFreshDis, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);	
		workPane.add(labelNameNow);
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
	public void actionPerformed(ActionEvent e){	
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String content=this.edtcontent.getText();
			Float Discount;
			int App_number;
			if(this.edtAppmoney.getText().equals("")) {
				App_number=FrmFreshDis.fulls.getApp_number();
			}
			else {
				App_number=Integer.parseInt(this.edtAppmoney.getText());
			}
			if(this.edtDedmoney.getText().equals("")) {
				Discount=FrmFreshDis.fulls.getDiscount();
			}
			else {
				Discount=Float.parseFloat(this.edtDedmoney.getText());
			}
			String Start = this.edtStart.getText();
			String end = this.edtload.getText();
			ExamplecoupouManager sum = new ExamplecoupouManager();
			if (content.equals("")) {
				content=FrmFreshDis.fulls.getFull_content();
			}
			if(Start.equals("")) {
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				Start=df.format(FrmFreshDis.fulls.getFulStart_date());
			}
			if(end.equals("")) {
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				end=df.format(FrmFreshDis.fulls.getFulEnd_date());
			}
				try {
					FreshNetUtil.couponManager.modifyFul(FrmFreshDis.fulls.getFull_number(),content,App_number,Discount,Start,end);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}; 
				JOptionPane.showMessageDialog(null, "修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
				this.setVisible(false);
			
		}
	}
}
