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
import hk.freshnetwork.model.Beanrelation;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;

public class FrmAddFulCom extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	
	private JButton btnOk = new JButton("添加满折商品信息");
	private JButton btnCancel = new JButton("取消");	
	private JLabel labelnumber = new JLabel("满折编号：");
	private JLabel labeldis = new JLabel("商品编号：");
	private JTextField edtAppmoney = new JTextField(20);
	private JTextField edtDedmoney = new JTextField(20);
	
	public FrmAddFulCom(FrmDisCom frmDisCom, String s, boolean b) {
		super(frmDisCom, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelnumber);
		workPane.add(edtAppmoney);
		workPane.add(labeldis);
		workPane.add(edtDedmoney);
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
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			if(this.edtAppmoney.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "输入不能为空!","错误",JOptionPane.ERROR_MESSAGE);
			}
			if(this.edtDedmoney.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "输入不能为空!","错误",JOptionPane.ERROR_MESSAGE);
			}
			int number=Integer.parseInt(this.edtAppmoney.getText());
			int Trade_number=Integer.parseInt(this.edtDedmoney.getText());
			String Start = null;
			try {
				if(FreshNetUtil.couponManager.loadFullSearch(number)==null) {		
					throw new BusinessException("要添加的满折编号不存在!");
				}
				if(FreshNetUtil.comManager.searchComNumber(Trade_number)==null) {
					
					throw new BusinessException("要添加的商品编号不存在!");
				}
				Start = df.format(FreshNetUtil.couponManager.loadFullSearch(number).get(0).getFulStart_date());	
				
			} catch (BaseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "满折或商品编号不存在!","成功",JOptionPane.INFORMATION_MESSAGE);
			}
			String end = null;
			try {
				if(FreshNetUtil.couponManager.loadFullSearch(number)==null) {
					throw new BusinessException("要添加的商品编号不存在!");
				}
				end = df.format(FreshNetUtil.couponManager.loadFullSearch(number).get(0).getFulEnd_date());
			} catch (BaseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, "满折编号不存在!","成功",JOptionPane.INFORMATION_MESSAGE);
			}
			ExamplecoupouManager sum = new ExamplecoupouManager();
			try {
				Beanrelation cou=sum.RegFulCom(number,Trade_number, Start, end);
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
