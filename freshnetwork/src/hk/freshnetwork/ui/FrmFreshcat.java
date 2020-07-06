package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.Button;
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
import hk.freshnetwork.util.BaseException;


public class FrmFreshcat extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JButton btnAdd = new JButton("添加生鲜类别");
	private JButton btnModify = new JButton("修改生鲜类别");
	private JButton btnStop = new JButton("删除生鲜类别");
	private JComboBox cmbState=new JComboBox(new String[]{"在库","已删除"});
	private JTextField edtKeyword = new JTextField(10);
	private JButton btnSearch = new JButton("查询");
	private Object tblTitle[]=Beanfresh_information.tableTitles;
	private Object tblData[][];
	public List<Beanfresh_information> fresh=null;
	public static Beanfresh_information freshs = null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			fresh = FreshNetUtil.freshManager.loadFresh();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[fresh.size()][Beanfresh_information.tableTitles.length];
		for(int i=0;i<fresh.size();i++){
			for(int j=0;j<Beanfresh_information.tableTitles.length;j++)
				tblData[i][j]=fresh.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	private void searchreloadTable(){
		try {
			if (this.edtKeyword.getText()==null) {
				fresh = FreshNetUtil.freshManager.loadFresh();
			}
			else {
				fresh = FreshNetUtil.freshManager.searchFresh(this.edtKeyword.getText());
			}			
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[fresh.size()][Beanfresh_information.tableTitles.length];
		for(int i=0;i<fresh.size();i++){
			for(int j=0;j<Beanfresh_information.tableTitles.length;j++)
				tblData[i][j]=fresh.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	
	public FrmFreshcat(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(btnStop);
		toolBar.add(cmbState);
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
			FrmFreshAdd dlg=new FrmFreshAdd(this,"添加类别",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.btnModify){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择类别","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
		    freshs=this.fresh.get(i);
		    
			FrmFreshModify dlg=new FrmFreshModify(this,"修改类别",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.btnStop){
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择类别","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Beanfresh_information freshs=this.fresh.get(i);
			if(!"在库".equals(freshs.getCategory_description())){
				JOptionPane.showMessageDialog(null,  "当前类别没有‘在库’","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(JOptionPane.showConfirmDialog(this,"确定删除"+freshs.getCategory_name()+"吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				freshs.setCategory_description("在库");
				/*try {
					FreshNetUtil.freshManager.modifyFresh(freshs);
					this.reloadTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}*/
			}
		}
		else if(e.getSource()==this.btnSearch){
			this.searchreloadTable();
		}
		
	}
}
