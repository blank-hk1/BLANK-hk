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
import hk.freshnetwork.model.Beanfull_sheet;
import hk.freshnetwork.model.Beanrelation;
import hk.freshnetwork.util.BaseException;

public class FrmDisCom extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JButton btnAdd = new JButton("添加满折商品信息");
	private JButton btnModify = new JButton("修改满折商品信息");
	private JButton btnStop = new JButton("删除满折商品信息");
	private JTextField edtKeyword = new JTextField(10);
	private JButton btnSearch = new JButton("按满折信息编号查询");
	private Object tblTitle[]=Beanrelation.tableTitles;
	private Object tblData[][];
	public List<Beanrelation> Discom=null;
	public static Beanrelation Discoms = null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			Discom=FreshNetUtil.couponManager.loadDisCom();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[Discom.size()][Beanrelation.tableTitles.length];
		for(int i=0;i<Discom.size();i++){
			for(int j=0;j<Beanrelation.tableTitles.length;j++)
				tblData[i][j]=Discom.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	private void searchreloadTable(){
		try {
			if (this.edtKeyword.getText().equals("")) {
				Discom=FreshNetUtil.couponManager.loadDisCom();
			}
			else {
				Discom = FreshNetUtil.couponManager.loadDisComSearch(Integer.parseInt(this.edtKeyword.getText()));
			}			
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[Discom.size()][Beanrelation.tableTitles.length];
		for(int i=0;i<Discom.size();i++){
			for(int j=0;j<Beanrelation.tableTitles.length;j++)
				tblData[i][j]=Discom.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	
	public FrmDisCom(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(btnStop);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		
		
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
		this.btnSearch.addActionListener(this);
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
			FrmAddFulCom dlg=new FrmAddFulCom(this,"添加满折商品信息",true);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if(e.getSource()==this.btnModify){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择满折商品信息","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Discoms=this.Discom.get(i);
		    
			FrmModifyFulCom dlg=new FrmModifyFulCom(this,"修改满折信息",true);
			dlg.setVisible(true);
			this.reloadTable();
		}
		else if(e.getSource()==this.btnStop){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择满折信息","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Discoms=this.Discom.get(i);
			FreshNetUtil.couponManager.deleteFulCom(Discoms.getFul_Full_number());
			JOptionPane.showMessageDialog(null,  "删除满折信息成功","提示",JOptionPane.INFORMATION_MESSAGE);
			this.reloadTable();
		}
		else if(e.getSource()==this.btnSearch){
			this.searchreloadTable();
		}
		
	}
}
