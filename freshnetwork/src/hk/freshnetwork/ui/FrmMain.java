package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import hk.freshnetwork.action.FreshNetUtil;
import hk.freshnetwork.model.Beanadminfo;
import hk.freshnetwork.model.Beancommodity_information;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;




public class FrmMain extends JFrame implements ActionListener {
	public static int Identity;
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu fresh_back=new JMenu("生鲜后台管理");
    private JMenu Discount_back=new JMenu("优惠后台管理");
    private JMenu User_back=new JMenu("采购商品");
    private JMenu admin_person=new JMenu("更多");
    
    private JMenu person_plan=new JMenu("商品推荐");
    private JMenu person_step=new JMenu("评价商品");
    private JMenu person_static=new JMenu("用户管理");
    private JMenu person_more=new JMenu("个人中心");
    
    private JMenuItem  Fresh_category=new JMenuItem("生鲜类别管理");
    private JMenuItem  menu_manager=new JMenuItem("菜谱管理");
    private JMenuItem  Fresh_Trade=new JMenuItem("商品管理");
    private JMenuItem  Time_pro=new JMenuItem("限时促销管理");
    private JMenuItem  Full_dis=new JMenuItem("满折优惠管理");
    private JMenuItem  coupon=new JMenuItem("优惠券管理");
    
    private JMenuItem  person_modifyPwd=new JMenuItem("修改密码");
    private JMenuItem  person_modifyphone=new JMenuItem("修改手机号码");
    private JMenuItem  person_modifymail=new JMenuItem("修改邮箱");
    private JMenuItem  person_modifycity=new JMenuItem("修改所在城市");
    private JMenuItem  person_openvip=new JMenuItem("会员中心");
    
    private JMenuItem  admin_modifyPwd=new JMenuItem("密码修改");
    private JMenuItem  admin_add=new JMenuItem("添加管理员");
    
    private JMenuItem  menuItem_static1=new JMenuItem("统计1");
    
    
	private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	
	private Object tblPlanTitle[]=Beancommodity_information.tableTitles;
	private Object tblPlanData[][];
	DefaultTableModel tabPlanModel=new DefaultTableModel();
	private JTable dataTablePlan=new JTable(tabPlanModel);
	
	
	/*private Object tblStepTitle[]=BeanStep.tblStepTitle;
	private Object tblStepData[][];
	DefaultTableModel tabStepModel=new DefaultTableModel();
	private JTable dataTableStep=new JTable(tabStepModel);*/
	
	private Beancommodity_information curCommodity=null;
	List<Beancommodity_information> allPlan=null;
	List<Beancommodity_information> planSteps=null;
	private void reloadPlanTable() throws BaseException{//这是测试数据，需要用实际数替换
		try {
			allPlan=FreshNetUtil.comManager.loadcom();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPlanData =  new Object[allPlan.size()][Beancommodity_information.tableTitles.length];
		for(int i=0;i<allPlan.size();i++){
			for(int j=0;j<Beancommodity_information.tableTitles.length;j++)
				tblPlanData[i][j]=allPlan.get(i).getCell(j);
		}
		tabPlanModel.setDataVector(tblPlanData,tblPlanTitle);
		this.dataTablePlan.validate();
		this.dataTablePlan.repaint();
	}
	/*private void reloadPlanStepTabel(int planIdx){
		if(planIdx<0) return;
		curPlan=allPlan.get(planIdx);
		try {
			planSteps=PersonPlanUtil.stepManager.loadSteps(curPlan);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblStepData =new Object[planSteps.size()][BeanStep.tblStepTitle.length];
		for(int i=0;i<planSteps.size();i++){
			for(int j=0;j<BeanStep.tblStepTitle.length;j++)
				tblStepData[i][j]=planSteps.get(i).getCell(j);
		}
		
		tabStepModel.setDataVector(tblStepData,tblStepTitle);
		this.dataTableStep.validate();
		this.dataTableStep.repaint();
	}*/
	public FrmMain() throws BaseException{
		dlgLogin=new FrmLogin(this,"生鲜海超登陆",true);
		dlgLogin.setVisible(true);
		if (Identity==1) {
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("生鲜海超用户系统");
			
		    //菜单
		    this.person_static.add(this.menuItem_static1); this.menuItem_static1.addActionListener(this);
		    this.person_more.add(this.person_modifyPwd); this.person_modifyPwd.addActionListener(this);
		    this.person_more.add(this.person_modifyphone); this.person_modifyphone.addActionListener(this);
		    this.person_more.add(this.person_modifymail); this.person_modifymail.addActionListener(this);
		    this.person_more.add(this.person_modifycity); this.person_modifycity.addActionListener(this);
		    this.person_more.add(this.person_openvip); this.person_openvip.addActionListener(this);
		    
		    menubar.add(person_plan);
		    menubar.add(person_step);
		    menubar.add(person_static);
		    menubar.add(person_more);
		    this.setJMenuBar(menubar);
		    
		    this.getContentPane().add(new JScrollPane(this.dataTablePlan), BorderLayout.WEST);
		    /*this.dataTablePlan.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmMain.this.dataTablePlan.getSelectedRow();
					if(i<0) {
						return;
					}
					FrmMain.this.reloadPlanStepTabel(i);
				}
		    	
		    });*/
		    //this.getContentPane().add(new JScrollPane(this.dataTableStep), BorderLayout.CENTER);
		    
		    this.reloadPlanTable();
		    //状态栏
		    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		    JLabel label=new JLabel("您好          "+Beanuser_table.currentLoginUser.getUser_name());//修改成   您好！+登陆用户名
		    statusBar.add(label);
		    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		    this.addWindowListener(new WindowAdapter(){   
		    	public void windowClosing(WindowEvent e){ 
		    		System.exit(0);
	             }
	        });
		    this.setVisible(true);
		}
		else {
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("生鲜海超管理系统");
			
		    //菜单
		    this.fresh_back.add(this.Fresh_category); this.Fresh_category.addActionListener(this);
		    this.fresh_back.add(this.menu_manager); this.menu_manager.addActionListener(this);
		    this.fresh_back.add(this.Fresh_Trade); this.Fresh_Trade.addActionListener(this);
		    this.Discount_back.add(this.Time_pro);this.Time_pro.addActionListener(this);
		    this.Discount_back.add(this.Full_dis);this.Full_dis.addActionListener(this);
		    this.Discount_back.add(this.coupon);this.coupon.addActionListener(this);
		    this.admin_person.add(this.admin_modifyPwd); this.admin_modifyPwd.addActionListener(this);
		    this.admin_person.add(this.admin_add); this.admin_add.addActionListener(this);
		    
		    menubar.add(fresh_back);
		    menubar.add(Discount_back);
		    menubar.add(User_back);
		    menubar.add(admin_person);
		    this.setJMenuBar(menubar);
		    
		    /*this.getContentPane().add(new JScrollPane(this.dataTablePlan), BorderLayout.WEST);
		    this.dataTablePlan.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmMain.this.dataTablePlan.getSelectedRow();
					if(i<0) {
						return;
					}
					FrmMain.this.reloadPlanStepTabel(i);
				}
		    	
		    });
		    this.getContentPane().add(new JScrollPane(this.dataTableStep), BorderLayout.CENTER);
		    
		    this.reloadPlanTable();*/
		    //状态栏
		    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		    JLabel label=new JLabel("您好"+Beanadminfo.currentLoginadmin.getEmp_name());//修改成   您好！+登陆用户名
		    statusBar.add(label);
		    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
		    this.addWindowListener(new WindowAdapter(){   
		    	public void windowClosing(WindowEvent e){ 
		    		System.exit(0);
	             }
	        });
		    this.setVisible(true);
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		/*if(e.getSource()==this.menuItem_AddPlan){
			FrmAddPlan dlg=new FrmAddPlan(this,"添加计划",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_DeletePlan){
			if(this.curPlan==null) {
				JOptionPane.showMessageDialog(null, "请选择计划", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PersonPlanUtil.planManager.deletePlan(this.curPlan);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.menuItem_AddStep){
			FrmAddStep dlg=new FrmAddStep(this,"添加步骤",true);
			dlg.plan=curPlan;
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_DeleteStep){
			int i=FrmMain.this.dataTableStep.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择步骤", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PersonPlanUtil.stepManager.deleteStep(this.planSteps.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.menuItem_startStep){
			int i=FrmMain.this.dataTableStep.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择步骤", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				FreshNetUtil.stepManager.startStep(this.planSteps.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.menuItem_finishStep){
			int i=FrmMain.this.dataTableStep.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择步骤", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				FreshNetUtil.stepManager.finishStep(this.planSteps.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.menuItem_moveUpStep){
			int i=FrmMain.this.dataTableStep.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择步骤", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				FreshNetUtil.stepManager.moveUp(this.planSteps.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.menuItem_moveDownStep){
			int i=FrmMain.this.dataTableStep.getSelectedRow();S
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择步骤", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				FreshNetUtil.stepManager.moveDown(this.planSteps.get(i));
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.menuItem_static1){
			
		}*/
		if(e.getSource()==this.admin_modifyPwd){
			FrmModifyPwd dlg=new FrmModifyPwd(this,"密码修改",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.admin_add) {
			FrmaddAdm adm = new FrmaddAdm(this,"添加管理员",true);
			adm.setVisible(true);
		}
		else if(e.getSource()==this.person_modifyPwd) {
			FrmUserPwd dlg = new FrmUserPwd(this,"修改密码",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.person_modifyphone) {
			FrmModifyphone dlg = new FrmModifyphone(this,"修改手机号码",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.person_modifymail) {
			FrmModifymail dlg = new FrmModifymail(this,"修改邮箱",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.person_modifycity) {
			FrmModifyCity dlg = new FrmModifyCity(this,"修改所在城市",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.person_openvip) {
			FrmOpenVip dlg = new FrmOpenVip(this,"会员中心",true);
			dlg.setVisible(true);
	}
		else if(e.getSource()==this.Fresh_category) {
			FrmFreshcat dlg = new FrmFreshcat(this,"生鲜类别管理",true);
			dlg.setVisible(true);
		}
	
  }
}
