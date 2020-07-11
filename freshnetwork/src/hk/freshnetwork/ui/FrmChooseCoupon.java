package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import hk.freshnetwork.model.Beanaddlist;
import hk.freshnetwork.model.Beancoupon;
import hk.freshnetwork.util.BaseException;

public class FrmChooseCoupon extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JButton btnAdd = new JButton("添加优惠券");
	private JButton add = new JButton("选择优惠券");
	private Object tblTitle[]=Beancoupon.tableTitles;
	private Object tblData[][];
	public List<Beancoupon> ChooseCoupon=null;
	public static Beancoupon ChooseCoupons = null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	private void reloadTable() throws BaseException{
		try {
			ChooseCoupon=FreshNetUtil.couponManager.loadCou();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData =  new Object[ChooseCoupon.size()][Beancoupon.tableTitles.length];
		for(int i=0;i<ChooseCoupon.size();i++){
			for(int j=0;j<Beancoupon.tableTitles.length;j++)
				tblData[i][j]=ChooseCoupon.get(i).getCell(j);
		}
		tablmod.setDataVector(tblData,tblTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}	
	public FrmChooseCoupon(FrmShopping frmShopping, String s, boolean b) throws BaseException {
		super(frmShopping, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(add);
		
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
		this.add.addActionListener(this);
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
			FrmAddChoooseCoupon dlg=new FrmAddChoooseCoupon(this,"添加优惠券",true);
			dlg.setVisible(false);
			try {
				this.reloadTable();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==this.add) {
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择要配送的地址","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			ChooseCoupons=this.ChooseCoupon.get(i);
			JOptionPane.showMessageDialog(null,  "选择成功","提示",JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
		}
	}
}
