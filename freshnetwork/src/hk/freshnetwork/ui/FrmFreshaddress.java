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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.model.Beanaddlist;
import hk.freshnetwork.util.BaseException;
import hk.freshnetwork.util.BusinessException;

public class FrmFreshaddress extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JButton btnAdd = new JButton("添加配送地址");
	private JButton btnModify = new JButton("修改配送地址");
	private JButton btnStop = new JButton("删除配送地址");
	private Object tblTitle[]=Beanaddlist.tableTitles;
	private Object tblData[][];
	public List<Beanaddlist> Addresslist=null;
	public static Beanaddlist Addresslists = null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable() throws BaseException{
		try {
			Addresslist=FreshNetUtil.addressManager.loadAddress();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[Addresslist.size()][Beanaddlist.tableTitles.length];
		for(int i=0;i<Addresslist.size();i++){
			for(int j=0;j<Beanaddlist.tableTitles.length;j++)
				tblData[i][j]=Addresslist.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	
	public FrmFreshaddress(Frame f, String s, boolean b) throws BaseException {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(btnStop);
		
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);

		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnStop.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnAdd){
			FrmAddAddress dlg=new FrmAddAddress(this,"添加配送地址",true);
			dlg.setVisible(true);
			try {
				this.reloadTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==this.btnModify){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择配送地址","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Addresslists=this.Addresslist.get(i);
		    
			FrmModifyAddress dlg=new FrmModifyAddress(this,"修改配送地址",true);
			dlg.setVisible(true);
			try {
				this.reloadTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==this.btnStop){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择配送地址","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Addresslists=this.Addresslist.get(i);
			try {
				FreshNetUtil.addressManager.deleteAdd(Addresslists.getAdd_number());
				JOptionPane.showMessageDialog(null,  "删除配送地址成功","提示",JOptionPane.INFORMATION_MESSAGE);
			} catch (BusinessException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null,  "该地址已存在相连接的订单，无法删除!","提示",JOptionPane.INFORMATION_MESSAGE);
			};
			try {
				this.reloadTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}
