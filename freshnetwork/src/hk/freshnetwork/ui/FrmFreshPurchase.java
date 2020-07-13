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
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.model.Beanpurchase;
import hk.freshnetwork.util.BaseException;

public class FrmFreshPurchase extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JButton btnModify = new JButton("修改采购订单");
	private JButton btnStop = new JButton("删除采购订单");
	String[] strArray= {" ","下单","在途","入库"};
	private JComboBox combobox=new JComboBox(strArray);
	private JTextField edtKeyword = new JTextField(10);
	private JButton btnSearch = new JButton("自定义查询");
	private JButton btnSearchStat = new JButton("按状态查询");
	private Object tblTitle[]=Beanpurchase.tableTitles;
	private Object tblData[][];
	public List<Beanpurchase> Purchase=null;
	public static Beanpurchase  Purchases = null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			Purchase = FreshNetUtil.purchaseManager.loadPur();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[Purchase.size()][Beanpurchase.tableTitles.length];
		for(int i=0;i<Purchase.size();i++){
			for(int j=0;j<Beanpurchase.tableTitles.length;j++)
				tblData[i][j]=Purchase.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	private void searchreloadTable(){
		try {
			if (this.edtKeyword.getText().equals("")) {
				Purchase = FreshNetUtil.purchaseManager.loadPur();
			}
			else {
				int p = Integer.parseInt(this.edtKeyword.getText());
				Purchase = FreshNetUtil.purchaseManager.searchCom(p);
			}			
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[Purchase.size()][Beanpurchase.tableTitles.length];
		for(int i=0;i<Purchase.size();i++){
			for(int j=0;j<Beanpurchase.tableTitles.length;j++)
				tblData[i][j]=Purchase.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	private void searchreloadTableStat(){
		try {
			if (combobox.getSelectedItem().toString().equals(" ")) {
				Purchase = FreshNetUtil.purchaseManager.loadPur();
			}
			else {
				Purchase = FreshNetUtil.purchaseManager.searchPur(combobox.getSelectedItem().toString());
			}	
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[Purchase.size()][Beanpurchase.tableTitles.length];
		for(int i=0;i<Purchase.size();i++){
			for(int j=0;j<Beanpurchase.tableTitles.length;j++)
				tblData[i][j]=Purchase.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	
	public FrmFreshPurchase(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnModify);
		toolBar.add(btnStop);
		toolBar.add(combobox);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		toolBar.add(btnSearchStat);
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnModify.addActionListener(this);
		this.btnStop.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.btnSearchStat.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnModify){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择类别","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
		    Purchases=this.Purchase.get(i);
		    
			FrmPurchaseModify dlg=new FrmPurchaseModify(this,"采购单修改",true);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if(e.getSource()==this.btnStop){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择类别","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Purchases=this.Purchase.get(i);
			FreshNetUtil.purchaseManager.deletePur(FrmFreshPurchase.Purchases.getChase_num());
			JOptionPane.showMessageDialog(null,  "删除成功","提示",JOptionPane.INFORMATION_MESSAGE);
			this.reloadTable();
		}
		else if(e.getSource()==this.btnSearch){
			this.searchreloadTable();
		}
		else if(e.getSource()==this.btnSearchStat) {
			this.searchreloadTableStat();
		}
	}
}
