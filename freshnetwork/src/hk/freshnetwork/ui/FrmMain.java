package hk.freshnetwork.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
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
import hk.freshnetwork.model.BeanShopping;
import hk.freshnetwork.model.Beanadminfo;
import hk.freshnetwork.model.Beancommodity_information;
import hk.freshnetwork.model.Beanfresh_information;
import hk.freshnetwork.model.Beanuser_table;
import hk.freshnetwork.util.BaseException;




public class FrmMain extends JFrame implements ActionListener {
	public static int Identity;
	public static int fflag=0;
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar();
	private JPanel workPane = new JPanel();
    private JMenu fresh_back=new JMenu("生鲜后台管理");
    private JMenu Discount_back=new JMenu("优惠后台管理");
    private JMenu User_back=new JMenu("采购商品");
    private JMenu admin_person=new JMenu("更多");
    
    private JMenu person_plan=new JMenu("商品推荐");
    private JMenu person_step=new JMenu("消费记录");
    private JMenu person_static=new JMenu("优惠活动");
    private JMenu person_more=new JMenu("个人中心");
    private JLabel blank=new JLabel("                                                                                                                                                                                                                                                                                                                              ");
    private JButton person_add=new JButton("添加至购物车");
    private JButton person_buy = new JButton("下单");
    private JButton person_delete = new JButton("删除该商品");
    private JButton person_modify = new JButton("修改商品");
    
    private JMenuItem lookRecords = new JMenuItem("查看消费记录");
    private JMenuItem lookRecommend = new JMenuItem("查看推荐商品");
    private JMenuItem lookmenu = new JMenuItem("查看菜谱");
    
    private JMenuItem  Fresh_category=new JMenuItem("生鲜类别管理");
    private JMenuItem  menu_manager=new JMenuItem("菜谱管理");
    private JMenuItem  Fresh_Trade=new JMenuItem("商品管理");
    private JMenuItem  Time_pro=new JMenuItem("限时促销管理");
    private JMenuItem  Full_dis=new JMenuItem("满折优惠管理");
    private JMenuItem  coupon=new JMenuItem("优惠券管理");
    private JMenuItem  Dis_Com=new JMenuItem("满折商品关联管理");
    
    private JMenuItem  chase_info=new JMenuItem("采购订单详情");
    private JMenuItem  chase=new JMenuItem("采购商品");
    
    private JMenuItem  person_modifyPwd=new JMenuItem("修改密码");
    private JMenuItem  person_modifyphone=new JMenuItem("修改手机号码");
    private JMenuItem  person_modifymail=new JMenuItem("修改邮箱");
    private JMenuItem  person_modifycity=new JMenuItem("修改所在城市");
    private JMenuItem  person_openvip=new JMenuItem("会员中心");
    private JMenuItem  person_addlist = new JMenuItem("个人配送地址");
    
    private JMenuItem  admin_modifyPwd=new JMenuItem("密码修改");
    private JMenuItem  admin_add=new JMenuItem("添加管理员");
    
    private JMenuItem  menuItem_time=new JMenuItem("限时促销活动");
    private JMenuItem  menuItem_full=new JMenuItem("满折活动");
    private JMenuItem  menuItem_coupon=new JMenuItem("优惠券活动");
    private JMenuItem  menuItem_relation=new JMenuItem("满折商品关联");
    
	private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	
	private Object tblPlanTitle[]=Beanfresh_information.tableTitles;
	private Object tblPlanData[][];
	private Object tblcomTitle[]=Beancommodity_information.tableTitles;
	private Object tblOrdTitle[]=BeanShopping.tableTitles;
	public static Beancommodity_information details=null;
	public static BeanShopping shops=null;
	public static Beanfresh_information curPlan=null;
	DefaultTableModel tabPlanModel=new DefaultTableModel();
	DefaultTableModel tabComModel=new DefaultTableModel();
	DefaultTableModel tabOrdModel=new DefaultTableModel();
	private JTable dataTablePlan=new JTable(tabPlanModel);
	private JTable datacomPlan=new JTable(tabComModel);
	private JTable dataOrdPlan=new JTable(tabOrdModel);
	
	/*private Object tblStepTitle[]=BeanStep.tblStepTitle;
	private Object tblStepData[][];
	DefaultTableModel tabStepModel=new DefaultTableModel();
	private JTable dataTableStep=new JTable(tabStepModel);*/
	
	List<Beanfresh_information> allPlan=null;
	List<Beancommodity_information> planSteps=null;
	public List<BeanShopping> orddetails=null;
	private void reloadPlanTable() throws BaseException{//这是测试数据，需要用实际数替换
		try {
			allPlan=FreshNetUtil.freshManager.loadFresh();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPlanData =  new Object[allPlan.size()][Beanfresh_information.tableTitles.length];
		for(int i=0;i<allPlan.size();i++){
			for(int j=0;j<Beanfresh_information.tableTitles.length;j++)
				tblPlanData[i][j]=allPlan.get(i).getCell(j);
		}
		tabPlanModel.setDataVector(tblPlanData,tblPlanTitle);
		this.dataTablePlan.validate();
		this.dataTablePlan.repaint();
	}
	private void reloadPlanStepTabel(int planIdx) throws BaseException{
		if(planIdx<0) return;
		curPlan = allPlan.get(planIdx);
		try {
			planSteps=FreshNetUtil.comManager.searchComFresh(curPlan.getCategory_number());
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Object[][] tblStepData = new Object[planSteps.size()][Beancommodity_information.tableTitles.length];
		for(int i=0;i<planSteps.size();i++){
			for(int j=0;j<Beancommodity_information.tableTitles.length;j++)
				tblStepData[i][j]=planSteps.get(i).getCell(j);
		}		
		tabComModel.setDataVector(tblStepData,tblcomTitle);
		this.datacomPlan.validate();
		this.datacomPlan.repaint();
	}
	private void reloadShopCart(int commoditys) throws BaseException{
		if(commoditys<0) return;
		//Beancommodity_information details = planSteps.get(commodity);
		try {
			orddetails = FreshNetUtil.orderManager.loadOrd(Beanuser_table.currentLoginUser.getUser_num());
		}catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Object[][] tblComData = new Object[orddetails.size()][BeanShopping.tableTitles.length];
		for(int i=0;i<orddetails.size();i++){
			for(int j=0;j<BeanShopping.tableTitles.length;j++)
				tblComData[i][j]=orddetails.get(i).getCell(j);
		}
		
		tabOrdModel.setDataVector(tblComData,tblOrdTitle);
		this.dataOrdPlan.validate();
		this.dataOrdPlan.repaint();
	}
	public FrmMain() throws BaseException{
		dlgLogin=new FrmLogin(this,"生鲜海超登陆",true);
		dlgLogin.setVisible(true);
		if (Identity==1) {
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setTitle("生鲜海超用户系统");
			
		    //菜单
		    this.person_static.add(this.menuItem_time); this.menuItem_time.addActionListener(this);
		    this.person_static.add(this.menuItem_coupon); this.menuItem_coupon.addActionListener(this);
		    this.person_static.add(this.menuItem_full); this.menuItem_full.addActionListener(this);
		    this.person_static.add(this.menuItem_relation); this.menuItem_relation.addActionListener(this);
		    this.person_more.add(this.person_modifyPwd); this.person_modifyPwd.addActionListener(this);
		    this.person_more.add(this.person_modifyphone); this.person_modifyphone.addActionListener(this);
		    this.person_more.add(this.person_modifymail); this.person_modifymail.addActionListener(this);
		    this.person_more.add(this.person_modifycity); this.person_modifycity.addActionListener(this);
		    this.person_more.add(this.person_openvip); this.person_openvip.addActionListener(this);
		    this.person_more.add(this.person_addlist); this.person_addlist.addActionListener(this);
		    this.person_step.add(lookRecords);this.lookRecords.addActionListener(this);
		    this.person_plan.add(this.lookRecommend);this.lookRecommend.addActionListener(this);
		    this.person_plan.add(this.lookmenu);this.lookmenu.addActionListener(this);
		    
		    person_buy.setFont(new Font(Font.DIALOG,Font.BOLD,15));
		    person_plan.setFont(new Font(Font.DIALOG,Font.BOLD,15));
		    person_step.setFont(new Font(Font.DIALOG,Font.BOLD,15));
		    person_static.setFont(new Font(Font.DIALOG,Font.BOLD,15));
		    person_delete.setFont(new Font(Font.DIALOG,Font.BOLD,15));
		    person_add.setFont(new Font(Font.DIALOG,Font.BOLD,15));
		    person_more.setFont(new Font(Font.DIALOG,Font.BOLD,15));
		    person_modify.setFont(new Font(Font.DIALOG,Font.BOLD,15));
		    
		    person_buy.setBorderPainted(false);
		    person_plan.setBorderPainted(false);
		    person_step.setBorderPainted(false);
		    person_static.setBorderPainted(false);
		    person_delete.setBorderPainted(false);
		    person_add.setBorderPainted(false);
		    person_more.setBorderPainted(false);
		    person_modify.setBorderPainted(false);
		    
		    menubar.add(person_plan);
		    menubar.add(person_step);
		    menubar.add(person_static);
		    menubar.add(person_more);
		    menubar.add(person_add);
		    menubar.add(blank);
		    
		    workPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		    workPane.add(person_buy);
		    workPane.add(person_modify);
		    workPane.add(person_delete);
		    this.getContentPane().add(workPane, BorderLayout.SOUTH);
		    
		    this.person_add.addActionListener(this);
		    this.person_buy.addActionListener(this);
		    this.person_delete.addActionListener(this);
		    this.person_modify.addActionListener(this);
		    this.setJMenuBar(menubar);
		    
		    JScrollPane store = new JScrollPane(this.dataTablePlan);
		    store.setPreferredSize(new Dimension(700,10));
		    this.getContentPane().add(store, BorderLayout.WEST);
		    
		    
		    this.dataTablePlan.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmMain.this.dataTablePlan.getSelectedRow();
					if(i<0) {
						return;
					}
					try {
						FrmMain.this.reloadPlanStepTabel(i);
					} catch (BaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		    	
		    });
		    this.datacomPlan.addMouseListener(new MouseAdapter (){

				@Override
				public void mouseClicked(MouseEvent e) {
					int i=FrmMain.this.datacomPlan.getSelectedRow();
					if(i<0) {
						return;
					}
					try {
						FrmMain.this.reloadShopCart(i);
					} catch (BaseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		    	
		    });
		    this.getContentPane().add(new JScrollPane(this.datacomPlan), BorderLayout.CENTER);
		    this.getContentPane().add(new JScrollPane(this.dataOrdPlan), BorderLayout.EAST);
		    this.reloadPlanTable();
		    //状态栏
		    //statusBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		    //JLabel label=new JLabel("您好          "+Beanuser_table.currentLoginUser.getUser_name());//修改成   您好！+登陆用户名
		    //statusBar.add(label);
		    //this.getContentPane().add(statusBar,BorderLayout.SOUTH);
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
		    this.Discount_back.add(this.Dis_Com);this.Dis_Com.addActionListener(this);
		    this.User_back.add(this.chase_info);this.chase_info.addActionListener(this);
		    this.User_back.add(this.chase);this.chase.addActionListener(this);
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
		else if(e.getSource()==this.menu_manager) {
			FrmFreshMenu dlg = new FrmFreshMenu(this,"菜谱管理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.Fresh_Trade) {
			FrmFreshCom dlg = null;
			try {
				dlg = new FrmFreshCom(this,"商品管理",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.chase_info) {
			FrmFreshPurchase dlg = new FrmFreshPurchase(this,"采购订单详情",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.chase) {
			FrmAddPurchase dlg = null;
			try {
				dlg = new FrmAddPurchase(this,"采购商品",true);
				dlg.setVisible(true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else if(e.getSource()==this.coupon) {
			FrmFreshCoupon dlg = new FrmFreshCoupon(this,"优惠券管理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.Time_pro) {
			FrmFreshTimepro dlg = new FrmFreshTimepro(this,"限时促销管理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.Full_dis) {
			FrmFreshDis dlg = new FrmFreshDis(this,"满折优惠管理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.person_addlist) {
			FrmFreshaddress dlg = null;
			try {
				dlg = new FrmFreshaddress(this,"个人配送地址",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.Dis_Com) {
			FrmDisCom dlg = new FrmDisCom(this,"满折商品关联管理",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.person_add) {
			int i=this.datacomPlan.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择要购买的商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			details=this.planSteps.get(i);		
			FrmOrderCom dlg=new FrmOrderCom(this,"添加至购物车",true);
			dlg.setVisible(true);
			try {
				this.reloadShopCart(i);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==this.person_buy) {
			FrmShopping dlg = null;
			try {
				dlg = new FrmShopping(this,"下单",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.person_delete) {
			int i=this.dataOrdPlan.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择要删除的商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			shops=this.orddetails.get(i);
			FreshNetUtil.orderManager.deleteShop(shops.getCom_Trade_number());
			JOptionPane.showMessageDialog(null,  "删除成功","提示",JOptionPane.INFORMATION_MESSAGE);
			try {
				this.reloadShopCart(i);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==this.person_modify) {
			int i=this.dataOrdPlan.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择要修改的商品","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			shops=this.orddetails.get(i);
			FrmModifyShop dlg = new FrmModifyShop(this,"修改商品",true);
			dlg.setVisible(true);
			try {
				this.reloadShopCart(i);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==this.lookRecords) {
			FrmComRecords dlg = null;
			try {
				dlg = new FrmComRecords(this,"查看消费记录",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.lookRecommend) {
			int i=this.datacomPlan.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择相关商品用以推荐","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			details=this.planSteps.get(i);
			FrmCommend_Menu dlg = null;
			try {
				dlg = new FrmCommend_Menu(this,"查看推荐商品",true);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.lookmenu) {
			FrmShowMenu dlg = new FrmShowMenu(this,"查看菜谱",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_time) {
			FrmShowTime dlg = new FrmShowTime(this,"限时促销活动",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_coupon) {
			FrmShowCoupon dlg = new FrmShowCoupon(this,"优惠券活动",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_full) {
			FrmShowFull dlg = new FrmShowFull(this,"满折活动",true);
			dlg.setVisible(true);
		}
		else if(e.getSource()==this.menuItem_relation) {
			FrmShowRelation dlg = new FrmShowRelation(this,"满折商品关联",true);
			dlg.setVisible(true);
		}
  }
}
