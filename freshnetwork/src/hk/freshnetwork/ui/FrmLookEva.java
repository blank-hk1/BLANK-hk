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
import javax.swing.table.DefaultTableModel;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.model.Beangoods_eva;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;

public class FrmLookEva extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Object tblTitle[]=Beangoods_eva.tableTitles;
	private Object tblData[][];
	public List<Beangoods_eva> eva=null;
	public static Beangoods_eva evas = null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable() throws BaseException{
		try {
			eva=FreshNetUtil.orderManager.Showeva(FrmComRecords.records.getCom_Trade_number());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[eva.size()][Beangoods_eva.tableTitles.length];
		for(int i=0;i<eva.size();i++){
			for(int j=0;j<Beangoods_eva.tableTitles.length;j++)
				tblData[i][j]=eva.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}	
	public FrmLookEva(FrmComRecords frmComRecords, String s, boolean b) throws BaseException {
		super(frmComRecords, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);

		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			try {
				this.reloadTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
}
