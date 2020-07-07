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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.model.Beancommodity_information;
import hk.freshnetwork.model.Beanpurchase;
import hk.freshnetwork.util.BaseException;

public class FrmAddPurchase extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JLabel labelNameNow = new JLabel("需要采购的商品      ");
	private JTextField edtKeyword = new JTextField(10);
	private JButton btnSearch = new JButton("查询");
	private JButton btnPur = new JButton("采购");
	private Object tblTitle[]=Beancommodity_information.tableTitles;
	private Object tblData[][];
	public List<Beancommodity_information> purcom=null;
	public static Beancommodity_information  purcoms = null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable() throws BaseException{
		try {
			purcom = FreshNetUtil.comManager.loadcompur();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[purcom.size()][Beancommodity_information.tableTitles.length];
		for(int i=0;i<purcom.size();i++){
			for(int j=0;j<Beancommodity_information.tableTitles.length;j++)
				tblData[i][j]=purcom.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	private void searchreloadTable() throws BaseException{
		try {
			if (this.edtKeyword.getText().equals("")) {
				purcom = FreshNetUtil.comManager.loadcompur();
			}
			else {
				purcom = FreshNetUtil.comManager.searchCom(this.edtKeyword.getText());
			}			
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[purcom.size()][Beancommodity_information.tableTitles.length];
		for(int i=0;i<purcom.size();i++){
			for(int j=0;j<Beancommodity_information.tableTitles.length;j++)
				tblData[i][j]=purcom.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	
	public FrmAddPurchase(Frame f, String s, boolean b) throws BaseException {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		toolBar.add(btnPur);
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);

		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnSearch.addActionListener(this);
		this.btnPur.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnPur) {
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择要采购的商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
				purcoms=this.purcom.get(i);
				FrmAddNum dlg = new FrmAddNum(this,"采购",true);
				dlg.setVisible(true);
			try {
				this.reloadTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==this.btnSearch){
			try {
				this.searchreloadTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
