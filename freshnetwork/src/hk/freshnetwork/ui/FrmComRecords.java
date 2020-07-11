package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.model.Beanorder_details;
import hk.freshnetwork.model.Beanorder_form;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;

public class FrmComRecords extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JButton btnadd = new JButton("添加评论");
	private JButton btnlook = new JButton("查看评论");
	private Object tblTitle[]=Beanorder_details.tableTitles;
	private Object tblData[][];
	public List<Beanorder_details> record=null;
	public static Beanorder_details records = null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable() throws BaseException{
		try {
			record=FreshNetUtil.orderManager.loadRecords(Beanuser_table.currentLoginUser.getUser_num());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[record.size()][Beanorder_details.tableTitles.length];
		for(int i=0;i<record.size();i++){
			for(int j=0;j<Beanorder_details.tableTitles.length;j++)
				tblData[i][j]=record.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}	
	public FrmComRecords(Frame f, String s, boolean b) throws BaseException {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnadd);
		toolBar.add(btnlook);
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);

		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnadd.addActionListener(this);
		this.btnlook.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnadd){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择消费记录","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			records=this.record.get(i);
			FrmAddEva dlg = new FrmAddEva(this,"添加评论",true);	
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.btnlook) {
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择要查看的商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			records=this.record.get(i);
			FrmLookEva dlg = null;
			try {
				dlg = new FrmLookEva(this,"查看评论",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			dlg.setVisible(true);
		}
	}
}
