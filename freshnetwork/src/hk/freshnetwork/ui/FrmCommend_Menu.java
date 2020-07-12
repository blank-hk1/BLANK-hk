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
import hk.freshnetwork.model.Beancommodity_information;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;

public class FrmCommend_Menu extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JButton btnadd = new JButton("加入购物车");
	private Object tblTitle[]=Beancommodity_information.tableTitles;
	private Object tblData[][];
	public List<Beancommodity_information> Recommend=null;
	public static Beancommodity_information Recommends = null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable() throws BaseException{
		Recommend=FreshNetUtil.comManager.loadRec(FrmMain.details.getTrade_number());
		tblData =  new Object[Recommend.size()][Beancommodity_information.tableTitles.length];
		for(int i=0;i<Recommend.size();i++){
			for(int j=0;j<Beancommodity_information.tableTitles.length;j++)
				tblData[i][j]=Recommend.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}	
	public FrmCommend_Menu(Frame f, String s, boolean b) throws BaseException {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnadd);
		
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
				JOptionPane.showMessageDialog(null,  "请选择要购买的物品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Recommends=this.Recommend.get(i);
			FrmRecommendOrder dlg=new FrmRecommendOrder(this,"添加至购物车",true);
			dlg.setVisible(true);
		}
	}
}
