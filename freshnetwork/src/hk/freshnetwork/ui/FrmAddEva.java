package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
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
	private int number = 5;
	private JButton btnOk = new JButton("添加用户评价");
	private JButton btnCancel = new JButton("取消");	
	private JButton btnadd = new JButton("+");	
	private JButton btndesc = new JButton("-");
	private JLabel labelcontent = new JLabel("评价内容:");
	private JLabel labelnumber = new JLabel("评价星级：           "+number+"           ");
	private JTextField edtcontent= new JTextField(20);
	
	public FrmAddEva(FrmComRecords frmComRecords, String s, boolean b) {
		super(frmComRecords, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(this.btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelcontent);
		workPane.add(edtcontent);
		workPane.add(labelnumber);
		workPane.add(btnadd);
		workPane.add(btndesc);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(260, 300);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.btnadd.addActionListener(this);
		this.btndesc.addActionListener(this);
		
		btnadd.setFont(new Font(Font.DIALOG,Font.BOLD,15));
		btndesc.setFont(new Font(Font.DIALOG,Font.BOLD,15));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnadd) {
			number=(number+1)%5;
			if(number==0) {
				number=5;
			}
			this.labelnumber.setText("评价星级：           "+number+"           ");
		}
		else if(e.getSource()==this.btndesc) {
			number=(number-1)%5;
			if(number==0) {
				number=5;
			}
			this.labelnumber.setText("评价星级：           "+number+"           ");
		}
		else if(e.getSource()==this.btnOk){
			String content=this.edtcontent.getText();
			ExamplecoupouManager sum = new ExamplecoupouManager();
			try {
				Beangoods_eva cou = FreshNetUtil.orderManager.Addeva(Beanuser_table.currentLoginUser.getUser_num(), FrmComRecords.records.getCom_Trade_number(), content, number);
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
